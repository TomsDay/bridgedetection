package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceLogListAdapter;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceLogListBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceLogListActivity extends Activity {
    private ListView mListView;
    private MaintenanceLogListAdapter maintenanceLogListAdapter;
    private Context mContext;
    ArrayList<MaintenanceLogListBean> listBeen = new ArrayList<MaintenanceLogListBean>();
    public final int SUCCESS_CODE = 0;
    public final int ERROR_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log_list);
        mContext = this;
        mListView = (ListView) findViewById(R.id.maintenance_logList_listView);
        maintenanceLogListAdapter = new MaintenanceLogListAdapter(mContext);
        mListView.setAdapter(maintenanceLogListAdapter);
        loadDate();
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_logList_back:
                finish();
                break;
        }
    }


    private void loadDate() {
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());
                JSONArray array = result.getJSONArray("datas");
                Gson gson = new Gson();
                for (int i = 0; i<array.size();i++) {
                    String datas = array.getString(i);
                    MaintenanceLogListBean bean = gson.fromJson(datas, MaintenanceLogListBean.class);
                    listBeen.add(bean);
                }
                Logger.e("aaa", listBeen.toString());
                Message message = new Message();
                message.what = SUCCESS_CODE;
                mHandler.sendMessage(message);



            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
                Message message = new Message();
                message.what = ERROR_CODE;
                mHandler.sendMessage(message);
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
                pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteCooperationByUID).executePost(list);
            }
        });
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_CODE:
                    maintenanceLogListAdapter.setDate(listBeen);
                    maintenanceLogListAdapter.notifyDataSetChanged();
                    break;
                case ERROR_CODE:
                    break;
            }
        }
    };
}
