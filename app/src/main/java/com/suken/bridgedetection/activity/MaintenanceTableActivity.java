package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceTableAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.MaintenanceItemBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.widget.DoubleDatePickerDialog;
import com.suken.bridgedetection.widget.ListViewForScrollView;
import com.suken.imageditor.ImageditorActivity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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
    private ArrayList<MaintenanceItemBean> list = new ArrayList<MaintenanceItemBean>();
    private MaintenanceTableAdapter mAdapter;
    private EditText maintenancetable_time_ev,
            maintenancetable_date_ev;


    private String checkTime;

    private Context mContext;
    private int mPosition;
    int years,months,days;

    private Spinner maintenancetable_weather_spinner,
            maintenancetable_searchType_spinner;
    private ArrayAdapter<String> mArrayWeatherAdapter, mArraySearchTypeAdapter;
    private String [] mStringArrayWeather,mStringArraySearchType;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_table);
        mContext = this;
        initView();
    }

    private void initView() {
        mListView = (ListViewForScrollView) findViewById(R.id.maintenancetable_listview);
        mAdapter = new MaintenanceTableAdapter(MaintenanceTableActivity.this);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(list);

        setTime();

        initSpinner();

        MaintenanceItemBean bean = new MaintenanceItemBean();
        bean.setShow(true);
        list.add(bean);
        loadDate();


    }

    private void initSpinner() {
        maintenancetable_weather_spinner = (Spinner) findViewById(R.id.maintenancetable_weather_spinner);
        mStringArrayWeather =getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(MaintenanceTableActivity.this, mStringArrayWeather);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancetable_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancetable_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

        maintenancetable_searchType_spinner = (Spinner) findViewById(R.id.maintenancetable_searchType_spinner);
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
                    System.out.println("maintenancetable_searchType_spinner==选中了:"+ mStringArrayWeather[position]);
                    break;
                case R.id.maintenancetable_weather_spinner:
                    System.out.println("maintenancetable_weather_spinner==选中了:"+ mStringArrayWeather[position]);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}

    }

    public void setTime() {
        maintenancetable_time_ev = (EditText) findViewById(R.id.maintenancetable_time_ev);
        maintenancetable_date_ev = (EditText) findViewById(R.id.maintenancetable_date_ev);
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
                list = mAdapter.getData();
                MaintenanceItemBean bean = new MaintenanceItemBean();
                list.add(bean);
                mAdapter.setData(list);
                mAdapter.notifyDataSetChanged();

                break;
            case R.id.operateDelete:
                list = mAdapter.getData();
                list.remove(list.size() - 1);
                mAdapter.setData(list);
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

                break;
        }

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
    public void jumpToMedia(int position, int requestCode, MaintenanceItemBean.ImageDesc desc) {
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
            MaintenanceItemBean.ImageDesc desc = new MaintenanceItemBean.ImageDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            Logger.e("aaa", " desc.name===" + desc.name);
            Logger.e("aaa", " desc.path===" + desc.path);
            list.get(mPosition).getmImages().add(desc);
            mAdapter.setData(list);
            mAdapter.notifyDataSetChanged();

//            mEditController.updateImg(desc);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            // 保存在原先的图片中所以不处理

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO) {
            MaintenanceItemBean.VideoDesc desc = new MaintenanceItemBean.VideoDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            list.get(mPosition).getmVideo().add(desc);
            mAdapter.setData(list);
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
