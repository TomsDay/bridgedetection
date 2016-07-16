package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceLogAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.CatalogueByUIDDao;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogDao;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.FileUtils;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.widget.ListViewForScrollView;
import com.suken.imageditor.ImageditorActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 高速公路维修保养日志
 */
public class MaintenanceLogActivity extends BaseActivity implements OnLocationFinishedListener {
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceLogBean> maintenanceLogBeen = new ArrayList<MaintenanceLogBean>();
    private ArrayList<MaintenanceLogItemBean> maintenanceLogItemBeen = new ArrayList<MaintenanceLogItemBean>();

    private MaintenanceLogAdapter mAdapter;
    private Context mContext;
    private MaintenanceLogBean allMaintenanceLogBean;

    private EditText maintenancelog_gydw_ev,
            maintenancelog_bh_ev,
            maintenancelog_data_ev,
            maintenancelog_wxbm_ev,
            maintenancelog_jcr_ev,
            maintenancelog_jlr_ev;

    private TextView saveBtn,gps_text;

    private Spinner maintenancelog_weather_spinner;

    private ArrayAdapter<String> mArrayWeatherAdapter;

    private String[] mStringArrayWeather;
    private int selsctWeather;
    private String strWeather = "晴";

    private int mYear,mMonth, mDay;

    private long id;

    MaintenanceLogDao maintenanceLogDao;
    IVDescDao ivDescDao;
    CatalogueByUIDDao catalogueByUIDDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log);
        mContext = this;
        maintenanceLogDao = new MaintenanceLogDao();
        ivDescDao = new IVDescDao();
        catalogueByUIDDao = new CatalogueByUIDDao();
        LocationManager.getInstance().syncLocation(this);
        id = getIntent().getLongExtra("id", 0);
        initView();
    }

    private void initView() {
        Logger.e("aaa", "queryAll===" + ivDescDao.queryAll().toString());


        mListView = (ListViewForScrollView) findViewById(R.id.maintenancelog_listview);
        maintenancelog_gydw_ev = (EditText) findViewById(R.id.maintenancelog_gydw_ev);
        maintenancelog_bh_ev = (EditText) findViewById(R.id.maintenancelog_bh_ev);
        maintenancelog_wxbm_ev = (EditText) findViewById(R.id.maintenancelog_wxbm_ev);
        maintenancelog_jcr_ev = (EditText) findViewById(R.id.maintenancelog_jcr_ev);
        maintenancelog_jlr_ev = (EditText) findViewById(R.id.maintenancelog_jlr_ev);
        saveBtn = (TextView) findViewById(R.id.saveBtn);
        gps_text = (TextView) findViewById(R.id.gps_text);
        gps_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gps_text.getText().toString().equals("定位失败，点击从新定位")){
                    LocationManager.getInstance().syncLocation(MaintenanceLogActivity.this);
                }
            }
        });

        maintenancelog_bh_ev.setKeyListener(null);
        maintenancelog_gydw_ev.setKeyListener(null);
        maintenancelog_wxbm_ev.setKeyListener(null);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDialog();
            }
        });



        initSpinner();
        initTime();


        if(id != 0){

            maintenanceLogBeen = (ArrayList<MaintenanceLogBean>) maintenanceLogDao.queryByID(id);
            if(maintenanceLogBeen.size()>0) {
                MaintenanceLogBean bean = maintenanceLogBeen.get(0);

                maintenancelog_gydw_ev.setText(bean.getGldwName()+"");
                maintenancelog_bh_ev.setText(bean.getBno()+"");
                maintenancelog_data_ev.setText(bean.getWxrq()+"");
                maintenancelog_wxbm_ev.setText(bean.getWxbmmc()+"");
                strWeather = bean.getWeather();
                for (int i = 0; i < mStringArrayWeather.length; i++) {
                    if(mStringArrayWeather[i].equals(strWeather)){
                        selsctWeather = i;
                        break;
                    }
                }
                maintenancelog_weather_spinner.setSelection(selsctWeather);
                maintenancelog_jcr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName()+"");
                maintenancelog_jlr_ev.setText(bean.getFzry());

                ForeignCollection<MaintenanceLogItemBean> orders = bean.getMaintenanceLogItemBeen();
                CloseableIterator<MaintenanceLogItemBean> iterator = orders.closeableIterator();
                try {
                    while (iterator.hasNext()) {
                        MaintenanceLogItemBean b = iterator.next();

                        List<IVDesc> imageDesc = ivDescDao.getImageMaintenanceLogItemBeanByUserId(b.getIds());
                        b.setmImages(imageDesc);

                        List<IVDesc> videoDesc = ivDescDao.getVideoMaintenanceLogItemBeanByUserId(b.getIds());
                        b.setmVideo(videoDesc);

                        maintenanceLogItemBeen.add(b);
                        Logger.e("aaa", b.toString());
                    }
                } finally {
                    if(maintenanceLogItemBeen != null && maintenanceLogItemBeen.size() != 0){
                        maintenanceLogItemBeen.get(0).setShow(true);
                    }
                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                initDate();
            }

        }else{
            initDate();
        }



        mAdapter = new MaintenanceLogAdapter(MaintenanceLogActivity.this);
        mAdapter.setData(maintenanceLogItemBeen);
        mAdapter.setCatalogueByUID(catalogueByUIDDao.queryAll());
        mListView.setAdapter(mAdapter);



    }
    public void initDate(){
        allMaintenanceLogBean = (MaintenanceLogBean) getIntent().getSerializableExtra("bean");
        Logger.e("aaa","allMaintenanceLogBean==="+allMaintenanceLogBean.toString());

        maintenancelog_gydw_ev.setText(BridgeDetectionApplication.mCurrentUser.getDefgqName());
        maintenancelog_bh_ev.setText(allMaintenanceLogBean.getBno()+"");
        maintenancelog_wxbm_ev.setText(allMaintenanceLogBean.getWxbmmc()+"");

        strWeather = allMaintenanceLogBean.getWeather().trim();
        for (int i = 0; i < mStringArrayWeather.length; i++) {
            if(mStringArrayWeather[i].equals(strWeather)){
                selsctWeather = i;
                break;
            }
        }
        maintenancelog_weather_spinner.setSelection(selsctWeather);

        maintenancelog_jcr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName()+"");
        maintenanceLogItemBeen = (ArrayList<MaintenanceLogItemBean>) allMaintenanceLogBean.getUpkeepdiseaseList();
        Logger.e("aaa", "maintenanceLogItemBeen===" + maintenanceLogItemBeen.size());
        if(maintenanceLogItemBeen != null && maintenanceLogItemBeen.size() != 0){
            maintenanceLogItemBeen.get(0).setShow(true);
        }
    }

    private void initTime() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DATE);
        maintenancelog_data_ev = (EditText) findViewById(R.id.maintenancelog_data_ev);
        maintenancelog_data_ev.setText(getTime(mYear, mMonth, mDay));
        maintenancelog_data_ev.setKeyListener(null);
        maintenancelog_data_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    private void initSpinner() {
        maintenancelog_weather_spinner = (Spinner) findViewById(R.id.maintenancelog_weather_spinner);
        mStringArrayWeather = getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(mContext, mStringArrayWeather);
//        设置下拉列表风格(这句不些也行)
//        mAdapter.setDropDownViewResource(android.R.layout.simple);
        maintenancelog_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancelog_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

    }

    boolean mIsGpsSuccess;
    double latitude, longitude;
    @Override
    public void onLocationFinished(LocationResult result) {

        if(this == null || ((BaseActivity)this).isDestroyed() || this.isFinishing()){
            return;
        }

        if (result.isSuccess) {
            mIsGpsSuccess = true;
            Logger.e("aaa","经度:" + result.latitude);
            Logger.e("aaa","纬度:" + result.longitude);
            latitude =  result.latitude;
            longitude =  result.longitude;

            Toast.makeText(this, "定位成功 经度:" + result.latitude+",纬度:" + result.longitude, Toast.LENGTH_SHORT).show();
//            mjingdu.setText("经度:" + result.latitude);
//            mWeidu.setText("纬度:" + result.longitude);
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
            gps_text.setText("定位成功");
            gps_text.setTextColor(Color.WHITE);
        } else if(!mIsGpsSuccess){
            Toast.makeText(this, "定位失败！请您到空旷的地点从新定位，绝就不要在室内！", Toast.LENGTH_LONG).show();
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
            gps_text.setText("定位失败，点击从新定位");
            gps_text.setTextColor(Color.RED);
        }
    }

    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
            strWeather = mStringArrayWeather[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.maintenancelog_back:
                back();
                break;
            case R.id.maintenancelog_save:
                saveDialog();
                break;
        }

    }

    public void back() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提醒");
        builder.setMessage("返回将丢失当前未保存信息");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(!mIsGpsSuccess){
                            Toast.makeText(mContext, "正在定位...\n" +
                                    "请您到空旷的地点从新定位，绝就不要在室内", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Logger.e("aaa","itemlist===="+ maintenanceLogItemBeen.toString());

                        String date = maintenancelog_data_ev.getText().toString();
                        String jcr = maintenancelog_jcr_ev.getText().toString();
                        String fzr = maintenancelog_jlr_ev.getText().toString();

                        MaintenanceLogBean maintenanceLogBean = new MaintenanceLogBean();
                        if(id != 0){
                            maintenanceLogBean = maintenanceLogBeen.get(0);
                        }else{
                            maintenanceLogBean = allMaintenanceLogBean;
                            maintenanceLogBean.setGldwId(BridgeDetectionApplication.mCurrentUser.getDefgqId());
                            maintenanceLogBean.setGldwName(BridgeDetectionApplication.mCurrentUser.getDefgqName());
                        }


                        maintenanceLogBean.setWeather(strWeather);
                        maintenanceLogBean.setWxrq(date);
                        maintenanceLogBean.setByrzzt("3");


                        maintenanceLogBean.setJcry(jcr);
                        maintenanceLogBean.setFzry(fzr);
                        if (id != 0) {
                            maintenanceLogBean.setId(id);
                        }
                        Logger.e("aaa", "=======11111====="+maintenanceLogBean.toString());



                        Logger.e("aaa","maintenanceTableBean.toString()===="+maintenanceLogBean.toString());
                        if (id != 0) {
                            maintenanceLogDao.update(maintenanceLogBean);
                        }else{
                            maintenanceLogDao.add(maintenanceLogBean);
                        }
                        maintenanceLogItemBeen = mAdapter.getData();
                        for (int j = 0; j < maintenanceLogItemBeen.size(); j++) {
                            MaintenanceLogItemBean itemBean = maintenanceLogItemBeen.get(j);
                            if (itemBean.getTpjd() != null) {
                                itemBean.setTpjd(latitude+"");
                                itemBean.setTpwd(longitude+"");
                            }
                            String fx = itemBean.getFx();
                            itemBean.setFx(fx != null ? fx : "上行内侧");

                            itemBean.setMaintenanceLogBean(maintenanceLogBean);
                            if (itemBean.getIds() != 0) {
                                maintenanceLogDao.updateItem(itemBean);
                            }else {
                                maintenanceLogDao.addItem(itemBean);
                            }
                            List<IVDesc> imagesDescList = itemBean.getmImages();
                            List<IVDesc> videoDescList = itemBean.getmVideo();
                            for(int q = 0; q < imagesDescList.size(); q++){
                                IVDesc imageDesc = imagesDescList.get(q);
                                imageDesc.setImageMaintenanceLogItemBean(itemBean);
                                if (imageDesc.getId() != 0) {
                                    ivDescDao.update(imageDesc);
                                }else {
                                    ivDescDao.add(imageDesc);
                                }
                            }
                            for(int w = 0; w < videoDescList.size(); w++){
                                IVDesc videoDesc = videoDescList.get(w);
                                videoDesc.setVideoMaintenanceLogItemBean(itemBean);
                                if (videoDesc.getId() != 0) {
                                    ivDescDao.update(videoDesc);
                                }else {
                                    ivDescDao.add(videoDesc);
                                }
                            }
                        }
                        Logger.e("aaa", "=======11111====="+ivDescDao.queryAll().toString());
//                        Logger.e("aaa", "========2222======="+ maintenanceLogDao.queryItemAll().toString());
                        finish();



                    }
                })
                .setNegativeButton("取消，再改改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();


    }
    public void upLoadData(final MaintenanceLogBean bean){
        final Gson gson = new Gson();
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa","111111111111"+ result.toString());

            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
//                                Message message = new Message();
//                                message.what = ERROR_CODE;
//                                mHandler.sendMessage(message);
            }
        };


        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                Logger.e("aaa", "gson======" + gson.toJson(bean));
                pair = new BasicNameValuePair("json", gson.toJson(bean));
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.uploadInspectlog).executePost(list);
            }
        });
    }


    public void operate(View view){
        switch (view.getId()) {
            case R.id.operateAdd:
                maintenanceLogItemBeen = mAdapter.getData();
                MaintenanceLogItemBean bean = new MaintenanceLogItemBean();
                maintenanceLogItemBeen.add(bean);
                mAdapter.setData(maintenanceLogItemBeen);
                mAdapter.notifyDataSetChanged();

                break;
            case R.id.operateDelete:
                maintenanceLogItemBeen = mAdapter.getData();
                maintenanceLogItemBeen.remove(maintenanceLogItemBeen.size() - 1);
                mAdapter.setData(maintenanceLogItemBeen);
                mAdapter.notifyDataSetChanged();
                break;
        }

    }
    public void showDatePicker(){
        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month + 1;
                mDay = day;
                maintenancelog_data_ev.setText(getTime(mYear, mMonth, mDay));
            }
        }, mYear, mMonth - 1, mDay);
        dlg.setTitle("日期：");
        dlg.show();

    }
    public String getTime(int year,int month,int day){
        return  year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }


    private int mPosition;
    private Uri mOutPutFileUri = null;
    File mPlayerFile;
    //    private FormItemController mEditController;
    public void jumpToMedia(int position, int requestCode, IVDesc desc) {
//        mEditController = con;
        mPosition = position;
        String path = Environment.getExternalStorageDirectory().toString() + File.separator + getPackageName();
        File path1 = new File(path);
        if (!path1.exists()) {
            path1.mkdirs();
        }
        String name = "";
        if (requestCode == Constants.REQUEST_CODE_CAMERA ) {
            name = path1 + File.separator + generateMediaName(true);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            name = desc.path;
        } else {
            name = path1 + File.separator + generateMediaName(false);
        }
        mPlayerFile = new File(name);
        mOutPutFileUri = Uri.fromFile(mPlayerFile);
        Intent intent = new Intent();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, requestCode);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            intent.setClass(this, ImageditorActivity.class);
            startActivityForResult(intent, requestCode);
        } else {
            // intent.setClass(this, RecorderActivity.class);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);// 参数设置可以省略
            intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, requestCode);
        }
    }

    String videofileName;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.e("aaa", "requestCode===" + requestCode);
        File f = null;
        try {
            f = new File(new URI(mOutPutFileUri.toString()));
            if (!f.exists()) {
//                f.mkdirs();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Logger.e("aaa", "requestCode===" + requestCode);
        if (requestCode == Constants.REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            IVDesc desc = new IVDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            Logger.e("aaa", " desc.name===" + desc.name);
            Logger.e("aaa", " desc.path===" + desc.path);
            maintenanceLogItemBeen.get(mPosition).getmImages().add(desc);
            mAdapter.setData(maintenanceLogItemBeen);
            mAdapter.notifyDataSetChanged();

//            mEditController.updateImg(desc);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            // 保存在原先的图片中所以不处理

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO && resultCode == RESULT_OK) {
            String str = null;
            IVDesc desc = new IVDesc();
            try {
                Log.e("aaa", "333333");
                desc.name = mPlayerFile.getName();
                desc.path = mPlayerFile.getPath();
                Logger.e("aaa", " REQUEST_CODE_VIDEO  +====== desc.name===" + desc.name);
                Logger.e("aaa", " REQUEST_CODE_VIDEO  +====== desc.path===" + desc.path);
                Uri uri = Uri.parse(data.getData().toString());

                ContentResolver cr = this.getContentResolver();

                Cursor cursor = cr.query(uri, null, null, null, null);
                cursor.moveToFirst();
                str = cursor.getString(1);
                videofileName = cursor.getString(2);
                cursor.close();

                File srcfile = new File(str);

                FileUtils.moveFileTo(srcfile, mPlayerFile);

                super.onActivityResult(requestCode, resultCode, data);
            }
            catch(Exception e) {;

            }




            maintenanceLogItemBeen.get(mPosition).getmVideo().add(desc);
            mAdapter.setData(maintenanceLogItemBeen);
            mAdapter.notifyDataSetChanged();
//            mEditController.updateVideo(desc);
        }
    }
    public String generateMediaName(boolean isImg) {
        if (isImg) {
            return "pic-" + System.currentTimeMillis() + "-image.png";
        } else {
            return "vdo-" + System.currentTimeMillis() + "-video.3gp";
        }
    }
}
