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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceTableAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.CatalogueByUIDBean;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseDao;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.UploadBean;
import com.suken.bridgedetection.bean.UploadListBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.storage.GXLuXianInfo;
import com.suken.bridgedetection.storage.GXLuXianInfoDao;
import com.suken.bridgedetection.storage.SdxcFormAndDetailDao;
import com.suken.bridgedetection.storage.SdxcFormData;
import com.suken.bridgedetection.storage.SdxcFormDetail;
import com.suken.bridgedetection.util.FileUtils;
import com.suken.bridgedetection.util.NetUtil;
import com.suken.bridgedetection.widget.DoubleDatePickerDialog;
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

import com.suken.bridgedetection.util.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * 高速公路养护巡查日志
 */
public class MaintenanceTableActivity extends BaseActivity implements OnLocationFinishedListener{
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceTableItemBean> maintenanceTableItemBeen = new ArrayList<MaintenanceTableItemBean>();
    private ArrayList<MaintenanceTableBean> maintenanceTableBeen = new ArrayList<MaintenanceTableBean>();
    private List<GXLuXianInfo> gxLuXianInfos = new ArrayList<GXLuXianInfo>();
    private MaintenanceTableAdapter mAdapter;
    private EditText maintenancetable_time_ev,
            maintenancetable_date_ev,
            maintenancetable_gydw_ev,
            maintenancetable_cxld_ev,
            maintenancetable_xcr_ev;


    private String checkTime;

    private Context mContext;
    private int mPosition;
    int years,months,days;

    private Spinner maintenancetable_weather_spinner,
            maintenancetable_searchType_spinner;
    private ArrayAdapter<String> mArrayWeatherAdapter, mArraySearchTypeAdapter;
    private String [] mStringArrayWeather,mStringArraySearchType;
    private String strWeather = "晴", strSearchType = "日常巡查";

    private int intSearchType;

    private MaintenanceTableDao maintenanceTableDao;
    private MaintenanceDiseaseDao maintenanceDiseaseDao;
    private GXLuXianInfoDao gxLuXianInfoDao;

    private TextView saveBtn,gps_text;

    IVDescDao ivDescDao;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_table);
        maintenanceTableDao = new MaintenanceTableDao();
        maintenanceDiseaseDao = new MaintenanceDiseaseDao();
        gxLuXianInfoDao = new GXLuXianInfoDao();
        ivDescDao = new IVDescDao();
        id = getIntent().getIntExtra("id", 0);
        mContext = this;
        initView();
    }

    int selsctWeather;
    public void getData() {
        gxLuXianInfos = gxLuXianInfoDao.queryAll();
        if (id != 0) {
            maintenanceTableBeen = (ArrayList<MaintenanceTableBean>) maintenanceTableDao.queryByID(id);
            Logger.e("aaa","maintenanceTableBeanList++"+maintenanceTableBeen.toString());
            if(maintenanceTableBeen.size()>0){
                MaintenanceTableBean bean = maintenanceTableBeen.get(0);
                maintenancetable_gydw_ev.setText(bean.getGydwName());
                maintenancetable_cxld_ev.setText(bean.getXcld());
                maintenancetable_xcr_ev.setText(bean.getXcry());
                maintenancetable_time_ev.setText(bean.getJcks()+"-"+bean.getJcjs());
                maintenancetable_date_ev.setText(bean.getJcjs());

                strWeather = bean.getWeather();
                Logger.e("aaa", "strWeather===" + strWeather);
                for (int i = 0; i < mStringArrayWeather.length; i++) {
                    if(mStringArrayWeather[i].equals(strWeather)){
                        selsctWeather = i;
                        break;
                    }
                }
                Logger.e("aaa", "selsctWeather===" + selsctWeather);
                intSearchType = bean.getXclx();
                Logger.e("aaa", "intSearchType===" + intSearchType);


                maintenancetable_weather_spinner.setSelection(selsctWeather);
                maintenancetable_searchType_spinner.setSelection(intSearchType);



                ForeignCollection<MaintenanceTableItemBean> orders = bean.getMaintenanceTableItemBeen();
                CloseableIterator<MaintenanceTableItemBean> iterator = orders.closeableIterator();
                try {
                    while(iterator.hasNext()){
                        MaintenanceTableItemBean b = iterator.next();

                        List<IVDesc> imageDesc = ivDescDao.getImageMaintenanceTableItemBeanByUserId(b.getId());
                        b.setmImages(imageDesc);

                        List<IVDesc> videoDesc = ivDescDao.getVideoMaintenanceTableItemBeanByUserId(b.getId());
                        b.setmVideo(videoDesc);

                        maintenanceTableItemBeen.add(b);
                        Logger.e("aaa",b.toString());
                    }
                } finally {
                    if(maintenanceTableItemBeen != null && maintenanceTableItemBeen.size() != 0){
                        maintenanceTableItemBeen.get(0).setShow(true);
                    }
                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else{
                MaintenanceTableItemBean bean = new MaintenanceTableItemBean();
                bean.setShow(true);
                maintenanceTableItemBeen.add(bean);
                maintenancetable_xcr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName());
            }

        }else{
            MaintenanceTableItemBean bean = new MaintenanceTableItemBean();
            bean.setShow(true);
            maintenanceTableItemBeen.add(bean);
            maintenancetable_xcr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName());
        }

        mAdapter = new MaintenanceTableAdapter(MaintenanceTableActivity.this);

        mAdapter.setDiseaseData(maintenanceDiseaseDao.queryAll());
        mAdapter.setData(maintenanceTableItemBeen);
        mListView.setAdapter(mAdapter);

    }
    private void initView() {
        mListView = (ListViewForScrollView) findViewById(R.id.maintenancetable_listview);
        maintenancetable_gydw_ev = (EditText) findViewById(R.id.maintenancetable_gydw_ev);
        maintenancetable_cxld_ev = (EditText) findViewById(R.id.maintenancetable_cxld_ev);
        maintenancetable_xcr_ev = (EditText) findViewById(R.id.maintenancetable_xcr_ev);
        maintenancetable_time_ev = (EditText) findViewById(R.id.maintenancetable_time_ev);
        maintenancetable_date_ev = (EditText) findViewById(R.id.maintenancetable_date_ev);
        maintenancetable_weather_spinner = (Spinner) findViewById(R.id.maintenancetable_weather_spinner);
        maintenancetable_searchType_spinner = (Spinner) findViewById(R.id.maintenancetable_searchType_spinner);

        gps_text = (TextView) findViewById(R.id.gps_text);
        gps_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gps_text.getText().toString().equals("定位失败，点击从新定位")){
                    LocationManager.getInstance().syncLocation(MaintenanceTableActivity.this);
                }
            }
        });

        LocationManager.getInstance().syncLocation(this);



        saveBtn = (TextView) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDialog();
            }
        });

        maintenancetable_gydw_ev.setText(BridgeDetectionApplication.mCurrentUser.getDefgqName());

        setCXLD();

        setTime();

        initSpinner();

        getData();

//        loadDate();


    }

    private void setCXLD() {
        maintenancetable_cxld_ev.setKeyListener(null);
        maintenancetable_gydw_ev.setKeyListener(null);
        maintenancetable_cxld_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initListDialog();
            }
        });
//        maintenancetable_gydw_ev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initListDialog();
//            }
//        });

    }

    int xcldPosition;
    private void initListDialog() {
        final String[] names = new String[gxLuXianInfos.size()];
        for (int i = 0; i < gxLuXianInfos.size(); i++) {
            GXLuXianInfo bean = gxLuXianInfos.get(i);
            names[i] = bean.getQdzh() + "(" + bean.getQdmc() + ") - " + bean.getZdzh() + "(" + bean.getZdmc()  + ")";
        }

        new AlertDialog.Builder(mContext)
                .setItems(names, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Logger.e("aaa", "which++" + which);
                        xcldPosition = which;
                        GXLuXianInfo bean = gxLuXianInfos.get(which);

                        maintenancetable_cxld_ev.setText(bean.getQdzh()+"-"+bean.getQdzh());

                    }
                })
                .show();
    }


    private void initSpinner() {

        mStringArrayWeather =getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(MaintenanceTableActivity.this, mStringArrayWeather);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancetable_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancetable_weather_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strWeather = mStringArrayWeather[i];
                Logger.e("aaa", "strWeather=ItemSelectedListenerImpl=="+strWeather);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mStringArraySearchType = getResources().getStringArray(R.array.spinnerSearchType);
        mArraySearchTypeAdapter = new TestArrayAdapter(MaintenanceTableActivity.this,mStringArraySearchType);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancetable_searchType_spinner.setAdapter(mArraySearchTypeAdapter);
        //监听Item选中事件
        maintenancetable_searchType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strSearchType = mStringArraySearchType[i];
                intSearchType = i;
                Logger.e("aaa", "intSearchType=  ItemSelectedListenerImpl=="+intSearchType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }





    public void setTime() {

        Calendar c = Calendar.getInstance();
        years = c.get(Calendar.YEAR);
        months = c.get(Calendar.MONTH)+1;
        days = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        checkTime = (hour < 10 ? "0" + hour : hour)
                + ":" +
                (minute < 10 ? "0" + minute : minute) +
                ("-") +
                (hour + 1 < 10 ? "0" + (hour + 1) : (hour + 1)) +
                (":") +
                (minute < 10 ? "0" + minute : minute);

        initCheckDate(years,months,days);




        maintenancetable_time_ev.setText(checkTime);
        maintenancetable_time_ev.setKeyListener(null);
        maintenancetable_time_ev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Logger.e("aaa","点击选择！！！！");
                // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
                new DoubleDatePickerDialog(MaintenanceTableActivity.this, new DoubleDatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(String times) {
                        checkTime = times;
                        maintenancetable_time_ev.setText(checkTime);
                    }
                }, checkTime).show();
            }
        });

        maintenancetable_date_ev.setKeyListener(null);
        maintenancetable_date_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int returnYear, int returnMonth, int returnDay) {
                        years = returnYear;
                        months = returnMonth + 1;
                        days = returnDay;
                        initCheckDate(years,months,days);
                    }
                },years,months-1,days);
                datePickerDialog.setTitle("日期：");
                datePickerDialog.show();
            }
        });


    }
    public void initCheckDate(int year,int month,int day){
        String checkDate = year + ("-") +
                (month < 10 ? "0" + month : month) +
                ("-") +
                (day < 10 ? "0" + day : day);
        maintenancetable_date_ev.setText(checkDate);
    }

    public void operate(View view) {
        switch (view.getId()) {
            case R.id.operateAdd:
                maintenanceTableItemBeen = mAdapter.getData();
                MaintenanceTableItemBean bean = new MaintenanceTableItemBean();
                maintenanceTableItemBeen.add(bean);
                mAdapter.setData(maintenanceTableItemBeen);
                mAdapter.notifyDataSetChanged();

                break;
            case R.id.operateDelete:
                maintenanceTableItemBeen = mAdapter.getData();
                maintenanceTableItemBeen.remove(maintenanceTableItemBeen.size() - 1);
                mAdapter.setData(maintenanceTableItemBeen);
                mAdapter.notifyDataSetChanged();
                break;
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.maintenancetable_back:
                back();
                break;
            case R.id.maintenancetable_save:
                saveDialog();
                break;
            case R.id.maintenancetable_synchronizationData:
                showLoading("正在同步病害库");
                synchronizationDiseaseData();
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

                        String time = maintenancetable_time_ev.getText().toString();
                        String date = maintenancetable_date_ev.getText().toString();
                        String xcr = maintenancetable_xcr_ev.getText().toString();
                        MaintenanceTableBean maintenanceTableBean = new MaintenanceTableBean();
                        GXLuXianInfo bean = gxLuXianInfos.get(xcldPosition);
                        maintenanceTableBean.setGydwId(BridgeDetectionApplication.mCurrentUser.getDefgqId());
                        maintenanceTableBean.setGydwName(BridgeDetectionApplication.mCurrentUser.getDefgqName());


                        maintenanceTableBean.setJcsj(date);
                        String[] timeStr = time.split("-");
                        maintenanceTableBean.setJcks(timeStr[0]);
                        maintenanceTableBean.setJcjs(timeStr[1]);
                        maintenanceTableBean.setLxbh(bean.getLxbh());
                        maintenanceTableBean.setLxmc(bean.getLxmc());
                        maintenanceTableBean.setLxid(bean.getId());
                        maintenanceTableBean.setTjsj(date);
                        maintenanceTableBean.setWeather(strWeather);
                        maintenanceTableBean.setXcld(bean.getQdzh()+"-"+bean.getZdzh());
                        maintenanceTableBean.setXcry(xcr);
                        maintenanceTableBean.setJcry(BridgeDetectionApplication.mCurrentUser.getUserName());
                        maintenanceTableBean.setXclx(intSearchType);

//
                        if (id != 0) {
                            maintenanceTableBean.setId(id);
                        }

                        if (id != 0) {
                            maintenanceTableDao.update(maintenanceTableBean);
                        }else{
                            maintenanceTableDao.add(maintenanceTableBean);
                        }
//
                        maintenanceTableItemBeen = mAdapter.getData();
//

                        for (int j = 0; j < maintenanceTableItemBeen.size(); j++) {
                            MaintenanceTableItemBean  itemBean = maintenanceTableItemBeen.get(j);
                            itemBean.setYhzt("1");
                            if (itemBean.getTpjd() != null) {
                                itemBean.setTpjd(latitude+"");
                                itemBean.setTpwd(longitude+"");
                            }

                            String fx = itemBean.getFx();
                            itemBean.setFx(fx != null ? fx : "上行内侧");

                            itemBean.setMaintenanceTableBean(maintenanceTableBean);
                            if (itemBean.getId() != 0) {
                                maintenanceTableDao.updateItem(itemBean);
                            }else {
                                maintenanceTableDao.addItem(itemBean);
                            }

                            List<IVDesc> imagesDescList = itemBean.getmImages();
                            List<IVDesc> videoDescList = itemBean.getmVideo();
                            for(int q = 0; q < imagesDescList.size(); q++){
                                IVDesc imageDesc = imagesDescList.get(q);
                                imageDesc.setImageMaintenanceTableItemBean(itemBean);
                                if (imageDesc.getId() != 0) {
                                    ivDescDao.update(imageDesc);
                                }else {
                                    ivDescDao.add(imageDesc);
                                }
                            }
                            for(int w = 0; w < videoDescList.size(); w++){
                                IVDesc videoDesc = videoDescList.get(w);
                                videoDesc.setVideoMaintenanceTableItemBean(itemBean);
                                if (videoDesc.getId() != 0) {
                                    ivDescDao.update(videoDesc);
                                }else {
                                    ivDescDao.add(videoDesc);
                                }
                            }

                        }

                        Logger.e("aaa", "queryAll===" + ivDescDao.queryAll().toString());
                        finish();
//


                    }
                })
                .setNegativeButton("取消，再改改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();


    }

    public String generateMediaName(boolean isImg) {
        if (isImg) {
            return "pic-" + System.currentTimeMillis() + "-image.png";
        } else {
            return "vdo-" + System.currentTimeMillis() + "-video.3gp";
        }
    }

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
        if(resultCode == RESULT_OK){


        try {
            f = new File(new URI(mOutPutFileUri.toString()));
            if (!f.exists()) {
//                f.mkdirs();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
        Logger.e("aaa", "requestCode===" + requestCode);
        if (requestCode == Constants.REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {

            Logger.e("aaa", "resultCode=REQUEST_CODE_CAMERA=" + resultCode);
            IVDesc desc = new IVDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            Logger.e("aaa", " desc.name===" + desc.name);
            Logger.e("aaa", " desc.path===" + desc.path);
            maintenanceTableItemBeen.get(mPosition).getmImages().add(desc);
            mAdapter.setData(maintenanceTableItemBeen);
            mAdapter.notifyDataSetChanged();

//            mEditController.updateImg(desc);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            // 保存在原先的图片中所以不处理

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO && resultCode == RESULT_OK) {

            String str = null;
            IVDesc desc = new IVDesc();

            Logger.e("aaa", "resultCode=REQUEST_CODE_VIDEO=" + resultCode);
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
            } catch (Exception e) {
                ;

            }


            maintenanceTableItemBeen.get(mPosition).getmVideo().add(desc);
            mAdapter.setData(maintenanceTableItemBeen);
            mAdapter.notifyDataSetChanged();
//            mEditController.updateVideo(desc);
        }
    }

    public void synchronizationDiseaseData(){
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                List<MaintenanceDiseaseBean> maintenanceDiseaseBeens = JSON.parseArray(result.getString("datas"), MaintenanceDiseaseBean.class);

                Logger.e("aaa", maintenanceDiseaseBeens.toString());

                maintenanceDiseaseDao.addList(maintenanceDiseaseBeens);

                dismissLoading();
                toast("同步病害库成功！");
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "===(" + resultCode + ")");
                Logger.e("aaa", "type===" + type);

                dismissLoading();
                toast("同步病害库失败！");
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                maintenanceDiseaseDao.deleteAll();
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteDeseaseByUID).executePost(list);


            }
        });

    }


//    public void uploadIV(){
//        final OnReceivedHttpResponseListener listener = new OnReceivedHttpResponseListener() {
//
//            @Override
//            public void onRequestSuccess(RequestType type, JSONObject obj) {
////                Logger.e("aaa","obj====="+obj.toString());
//                Logger.e("aaa","type====="+type.toString());
//            }
//
//            @Override
//            public void onRequestFail(RequestType type, String resultCode, String result) {
//                Logger.e("aaa","type====="+type.toString());
//                Logger.e("aaa","resultCode====="+resultCode);
//                Logger.e("aaa","result====="+result);
//
//            }
//        };
//
//        BackgroundExecutor.execute(new Runnable() {
//
//            @Override
//            public void run() {
//
//                List<NameValuePair> list = new ArrayList<NameValuePair>();
//                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
//                list.add(pair);
//                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
//                list.add(pair);
//
//
//
//                List<IVDesc> image = maintenanceTableItemBeen.get(0).getmImages();
//                String[] pics = new String[image.size()];
//                for (int i = 0; i < image.size(); i++) {
//                    pics[i] = image.get(i).getPath();
//                }
//                List<IVDesc> video = maintenanceTableItemBeen.get(0).getmVideo();
//                String[] vdos = new String[video.size()];
//                for (int i = 0; i < video.size(); i++) {
//                    vdos[i] = video.get(i).getPath();
//                }
//
//
//                String[] attaches = concat(pics, vdos);
//                Logger.e("aaa","===================="+attaches[0]);
//                for (int i = 0; i < attaches.length; i++) {
//
//                    Logger.e("aaa", "i====================" + attaches[i]);
//                }
//                if (attaches.length > 0) {
//                    new HttpTask(listener, RequestType.uploadFile).uploadFile(list, attaches);
//                }
//
//            }
//        });
//    }
    public static String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
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

}
