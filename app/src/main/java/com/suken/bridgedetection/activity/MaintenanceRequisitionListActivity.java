package com.suken.bridgedetection.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.bean.WxdbhByUID;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceRequisitionListActivity extends BaseActivity {
    List<WxdbhByUID> listBean = new ArrayList<WxdbhByUID>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_requisition_list);

        loadDate();
    }

    private void loadDate() {

        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                listBean = (ArrayList<WxdbhByUID>) JSON.parseArray(result.getString("datas"), WxdbhByUID.class);

                Logger.e("aaa", listBean.toString());
                Message message = new Message();
                message.what = Constants.SUCCESS_CODE;
                mHandler.sendMessage(message);



            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
                Message message = new Message();
                message.what = Constants.ERROR_CODE;
                mHandler.sendMessage(message);
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                showLoading("正在同步通知单...");
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
//                if (tzdbh.length() != 0) {
//                    pair = new BasicNameValuePair("bno", tzdbh);
//                    list.add(pair);
//                }
//                if (wxbm.length() != 0) {
//                    pair = new BasicNameValuePair("wxbmmc", wxbm);
//                    list.add(pair);
//                }
//                if (qfrq.length() != 0) {
//                    pair = new BasicNameValuePair("qfrq", qfrq);
//                    list.add(pair);
//                }
//                if (qfr.length() != 0) {
//                    pair = new BasicNameValuePair("qfry", qfr);
//                    list.add(pair);
//                }
                new HttpTask(onReceivedHttpResponseListener, RequestType.getWxdbhByUID).executePost(list);
            }
        });
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.SUCCESS_CODE:
                    dismissLoading();
//                    maintenanceLogListAdapter.setDate(listBeen);
//                    maintenanceLogListAdapter.notifyDataSetChanged();
                    break;
                case Constants.ERROR_CODE:
                    break;
            }
            dismissLoading();
        }
    };
}
