package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceLogAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogDao;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceLogListBean;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.util.FileUtils;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.widget.ListViewForScrollView;
import com.suken.imageditor.ImageditorActivity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 高速公路维修保养日志
 */
public class MaintenanceLogActivity extends Activity {
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceLogBean> maintenanceLogBeen = new ArrayList<MaintenanceLogBean>();
    private ArrayList<MaintenanceLogItemBean> maintenanceLogItemBeen = new ArrayList<MaintenanceLogItemBean>();

    private MaintenanceLogAdapter mAdapter;
    private Context mContext;
    private MaintenanceLogListBean maintenanceLogListBean;

    private EditText maintenancelog_gydw_ev,
            maintenancelog_bh_ev,
            maintenancelog_data_ev,
            maintenancelog_wxbm_ev,
            maintenancelog_jcr_ev,
            maintenancelog_jlr_ev;

    private Spinner maintenancelog_weather_spinner;

    private ArrayAdapter<String> mArrayWeatherAdapter;

    private String[] mStringArrayWeather;

    private String strWeather = "晴";

    private int mYear,mMonth, mDay;

    private int id;

    MaintenanceLogDao maintenanceLogDao;
    IVDescDao ivDescDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log);
        mContext = this;
        maintenanceLogDao = new MaintenanceLogDao();
        ivDescDao = new IVDescDao();

        maintenanceLogListBean = (MaintenanceLogListBean) getIntent().getSerializableExtra("bean");
        id = getIntent().getIntExtra("id", 0);
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
        initSpinner();
        initTime();


        if(id != 0){

            maintenanceLogBeen = (ArrayList<MaintenanceLogBean>) maintenanceLogDao.queryByID(id);
            if(maintenanceLogBeen.size()>0) {
                MaintenanceLogBean bean = maintenanceLogBeen.get(0);

                maintenancelog_gydw_ev.setText(bean.getCustodyUnit()+"");
                maintenancelog_bh_ev.setText(bean.getSerialNumber()+"");
                maintenancelog_data_ev.setText(bean.getDate()+"");
                maintenancelog_wxbm_ev.setText(bean.getMaintenanceDepartment()+"");
                maintenancelog_jcr_ev.setText(bean.getRummager()+"");
                maintenancelog_jlr_ev.setText(bean.getPrincipal()+"");


                ForeignCollection<MaintenanceLogItemBean> orders = bean.getMaintenanceTableItemBeen();
                CloseableIterator<MaintenanceLogItemBean> iterator = orders.closeableIterator();
                try {
                    while (iterator.hasNext()) {
                        MaintenanceLogItemBean b = iterator.next();

                        List<IVDesc> imageDesc = ivDescDao.getImageMaintenanceLogItemBeanByUserId(b.getId());
                        b.setmImages(imageDesc);

                        List<IVDesc> videoDesc = ivDescDao.getVideoMaintenanceLogItemBeanByUserId(b.getId());
                        b.setmVideo(videoDesc);

                        maintenanceLogItemBeen.add(b);
                        Logger.e("aaa", b.toString());
                    }
                } finally {

                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                MaintenanceLogItemBean bean = new MaintenanceLogItemBean();
                bean.setShow(true);
                maintenanceLogItemBeen.add(bean);
                maintenancelog_gydw_ev.setEnabled(false);
                maintenancelog_gydw_ev.setText(maintenanceLogListBean.getGydwName());
                maintenancelog_bh_ev.setText(UUID.randomUUID().toString());
            }

        }else{
            MaintenanceLogItemBean bean = new MaintenanceLogItemBean();
            bean.setShow(true);
            maintenanceLogItemBeen.add(bean);
            maintenancelog_gydw_ev.setEnabled(false);
            maintenancelog_gydw_ev.setText(maintenanceLogListBean.getGydwName());
            maintenancelog_bh_ev.setText(UUID.randomUUID().toString());
        }



        mAdapter = new MaintenanceLogAdapter(MaintenanceLogActivity.this);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(maintenanceLogItemBeen);


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
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancelog_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancelog_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

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
                finish();
                break;
            case R.id.maintenancelog_save:
                saveDialog();
                break;
        }

    }
    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Logger.e("aaa","itemlist===="+ maintenanceLogItemBeen.toString());

                        String gydw = maintenancelog_gydw_ev.getText().toString();
                        String bh = maintenancelog_bh_ev.getText().toString();
                        String wxbm = maintenancelog_wxbm_ev.getText().toString();
                        String date = maintenancelog_data_ev.getText().toString();
                        String jcr = maintenancelog_jcr_ev.getText().toString();
                        String fzr = maintenancelog_jlr_ev.getText().toString();

                        MaintenanceLogBean maintenanceLogBean = new MaintenanceLogBean();
                        maintenanceLogBean.setCustodyUnit(gydw);
                        maintenanceLogBean.setSerialNumber(bh);
                        maintenanceLogBean.setWeather(strWeather);
                        maintenanceLogBean.setDate(date);
                        maintenanceLogBean.setMaintenanceDepartment(wxbm);
                        maintenanceLogBean.setRummager(jcr);
                        maintenanceLogBean.setPrincipal(fzr);
                        if (id != 0) {
                            maintenanceLogBean.setId(id);
                        }


                        Logger.e("aaa","maintenanceTableBean.toString()===="+maintenanceLogBean.toString());
                        if (id != 0) {
                            maintenanceLogDao.update(maintenanceLogBean);
                        }else{
                            maintenanceLogDao.add(maintenanceLogBean);
                        }
                        maintenanceLogItemBeen = mAdapter.getData();
                        for (int j = 0; j < maintenanceLogItemBeen.size(); j++) {
                            MaintenanceLogItemBean itemBean = maintenanceLogItemBeen.get(j);
                            itemBean.setMaintenanceLogBean(maintenanceLogBean);
                            if (itemBean.getId() != 0) {
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
                        Logger.e("aaa", "=======11111====="+maintenanceLogDao.queryAll().toString());
                        Logger.e("aaa", "========2222======="+ maintenanceLogDao.queryItemAll().toString());




                    }
                })
                .setNegativeButton("取消，再改改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();


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
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
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
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
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

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO) {
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
