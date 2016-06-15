package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

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
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
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

import com.suken.bridgedetection.util.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * 高速公路养护巡查日志
 */
public class MaintenanceTableActivity extends Activity {
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceTableItemBean> maintenanceTableItemBeen = new ArrayList<MaintenanceTableItemBean>();
    private ArrayList<MaintenanceTableBean> maintenanceTableBeen = new ArrayList<MaintenanceTableBean>();
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
    private MaintenanceTableDao maintenanceTableDao;
    private int id;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_table);
        maintenanceTableDao = new MaintenanceTableDao();
        id = getIntent().getIntExtra("id", 0);
        mContext = this;

        initView();
    }

    public void getData() {
        if (id != 0) {
            maintenanceTableBeen = (ArrayList<MaintenanceTableBean>) maintenanceTableDao.queryByID(id);
            Logger.e("aaa","maintenanceTableBeanList++"+maintenanceTableBeen.toString());
            if(maintenanceTableBeen.size()>0){
                MaintenanceTableBean bean = maintenanceTableBeen.get(0);
                maintenancetable_gydw_ev.setText(bean.getCustodyUnit());
                maintenancetable_cxld_ev.setText(bean.getPatrolSection());
                maintenancetable_xcr_ev.setText(bean.getInspectOne());
                maintenancetable_time_ev.setText(bean.getTimeQuantum());
                maintenancetable_date_ev.setText(bean.getDatetime());
                maintenancetable_weather_spinner.setSelection(2);
                maintenancetable_searchType_spinner.setSelection(2);



                ForeignCollection<MaintenanceTableItemBean> orders = bean.getMaintenanceTableItemBeen();
                CloseableIterator<MaintenanceTableItemBean> iterator = orders.closeableIterator();
                try {
                    while(iterator.hasNext()){
                        MaintenanceTableItemBean b = iterator.next();
                        maintenanceTableItemBeen.add(b);
                        Logger.e("aaa",b.toString());
                    }
                } finally {

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
            }

        }else{
            MaintenanceTableItemBean bean = new MaintenanceTableItemBean();
            bean.setShow(true);
            maintenanceTableItemBeen.add(bean);
        }
        mAdapter = new MaintenanceTableAdapter(MaintenanceTableActivity.this);
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

        getData();

        setTime();

        initSpinner();


//        loadDate();


    }

    private void initSpinner() {

        mStringArrayWeather =getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(MaintenanceTableActivity.this, mStringArrayWeather);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancetable_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancetable_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());


        mStringArraySearchType = getResources().getStringArray(R.array.spinnerSearchType);
        mArraySearchTypeAdapter = new TestArrayAdapter(MaintenanceTableActivity.this,mStringArraySearchType);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancetable_searchType_spinner.setAdapter(mArraySearchTypeAdapter);
        //监听Item选中事件
        maintenancetable_searchType_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
    }



    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
            switch (view.getId()) {
                case R.id.maintenancetable_searchType_spinner:
                    strSearchType = mStringArraySearchType[position];
                    System.out.println("maintenancetable_searchType_spinner==选中了:"+ strSearchType);
                    break;
                case R.id.maintenancetable_weather_spinner:
                    strWeather = mStringArrayWeather[position];
                    System.out.println("maintenancetable_weather_spinner==选中了:"+ strWeather);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}

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
                finish();
                break;
            case R.id.maintenancetable_save:
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
                        maintenanceTableItemBeen = mAdapter.getData();
                        Logger.e("aaa","itemlist===="+ maintenanceTableItemBeen.toString());
                        String gydw = maintenancetable_gydw_ev.getText().toString();
                        String xcld = maintenancetable_cxld_ev.getText().toString();
                        String time = maintenancetable_time_ev.getText().toString();
                        String date = maintenancetable_date_ev.getText().toString();
                        String xcr = maintenancetable_xcr_ev.getText().toString();
                        MaintenanceTableBean maintenanceTableBean = new MaintenanceTableBean();
                        maintenanceTableBean.setCustodyUnit(gydw);
                        maintenanceTableBean.setPatrolSection(xcld);
                        maintenanceTableBean.setTimeQuantum(time);
                        maintenanceTableBean.setDatetime(date);
                        maintenanceTableBean.setWeather(strWeather);
                        maintenanceTableBean.setSearchType(strSearchType);
                        maintenanceTableBean.setInspectOne(xcr);

                        Logger.e("aaa","maintenanceTableBean.toString()===="+maintenanceTableBean.toString());
                        maintenanceTableDao.add(maintenanceTableBean);
                        maintenanceTableItemBeen = mAdapter.getData();
                        for (int j = 0; j < maintenanceTableItemBeen.size(); j++) {
                            MaintenanceTableItemBean itemBean = maintenanceTableItemBeen.get(j);
                            itemBean.setMaintenanceTableBean(maintenanceTableBean);
                            maintenanceTableDao.addItem(itemBean);
                        }





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
            return "vdo-" + System.currentTimeMillis() + "-video.mp4";
        }
    }

    private Uri mOutPutFileUri = null;

    //    private FormItemController mEditController;
    public void jumpToMedia(int position, int requestCode, MaintenanceTableItemBean.ImageDesc desc) {
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
        File file = new File(name);
        mOutPutFileUri = Uri.fromFile(file);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File f = null;
        try {
            f = new File(new URI(mOutPutFileUri.toString()));
            if (!f.exists()) {
                return;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            MaintenanceTableItemBean.ImageDesc desc = new MaintenanceTableItemBean.ImageDesc();
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

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO) {
            MaintenanceTableItemBean.VideoDesc desc = new MaintenanceTableItemBean.VideoDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            maintenanceTableItemBeen.get(mPosition).getmVideo().add(desc);
            mAdapter.setData(maintenanceTableItemBeen);
            mAdapter.notifyDataSetChanged();
//            mEditController.updateVideo(desc);
        }
    }

    private void loadDate() {
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());
                JSONArray array = result.getJSONArray("datas");
                String datas = array.getString(0);
                Logger.e("aaa", "datas==" + datas);
                Gson gson = new Gson();
                MaintenanceDiseaseBean bean = gson.fromJson(datas, MaintenanceDiseaseBean.class);
                Logger.e("aaa", bean.toString());
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
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
                pair = new BasicNameValuePair("did", BridgeDetectionApplication.mDeviceId);
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteDeseaseByUID).executePost(list);
            }
        });
    }
}
