package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceLogListAdapter;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogDao;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceLogListBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceLogListActivity extends Activity {
    private ListView mListView;
    private MaintenanceLogListAdapter maintenanceLogListAdapter;
    private Context mContext;
    ArrayList<MaintenanceLogBean> listBeen = new ArrayList<MaintenanceLogBean>();
    private ArrayList<MaintenanceLogBean> maintenanceLogBeen = new ArrayList<MaintenanceLogBean>();
    public final int SUCCESS_CODE = 0;
    public final int ERROR_CODE = 1;
    private LinearLayout maintenance_logList_selectCondition_layout;
    private TextView maintenance_logList_selectCondition_tv;
    MaintenanceLogDao maintenanceLogDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log_list);
        maintenanceLogDao = new MaintenanceLogDao();
        mContext = this;
        initView();
        loadDate();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.maintenance_logList_listView);
        maintenanceLogListAdapter = new MaintenanceLogListAdapter(mContext);
        mListView.setAdapter(maintenanceLogListAdapter);
        maintenance_logList_selectCondition_layout = (LinearLayout) findViewById(R.id.maintenance_logList_selectCondition_layout);
        maintenance_logList_selectCondition_tv = (TextView) findViewById(R.id.maintenance_logList_selectCondition_tv);
        items=getResources().getStringArray(R.array.maintenance_logList_selectCondition_str);
        maintenance_logList_selectCondition_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShowCheckDialog();
            }
        });

    }

    /**
     * 创建复选框对话框
     */
    boolean[] flags=new boolean[]{false,false,false,false};//初始复选情况
    String[] items=null;
    View loglistDialog;
    String tzdbh="",wxbm="",qfrq="", qfr="";
    private EditText maintenancelog_tzdbh_ev,
            maintenancelog_wxdw_ev,
            maintenancelog_qfrq_ev,
            maintenancelog_qfr_ev;
    protected void onShowCheckDialog() {
        Logger.e("aaa","1111111111111111");
        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.NOmengceng_dialog);
        //设置对话框的图标
//                builder.setIcon(R.drawable.header);
        //设置对话框的标题
        builder.setTitle("查询条件");
        loglistDialog = getLayoutInflater().inflate(R.layout.loglist_dialog, null);

        maintenancelog_tzdbh_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_tzdbh_ev);
        maintenancelog_wxdw_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_wxdw_ev);
        maintenancelog_qfrq_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_qfrq_ev);
        maintenancelog_qfr_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_qfr_ev);

        maintenancelog_tzdbh_ev.setText(tzdbh);
        maintenancelog_wxdw_ev.setText(wxbm);
        maintenancelog_qfrq_ev.setText(qfrq);
        maintenancelog_qfr_ev.setText(qfr);

        maintenancelog_qfrq_ev.setKeyListener(null);
        maintenancelog_qfrq_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        MaintenanceLogListActivity.this, "", new DateTimePickDialogUtil.ReturnTime() {
                    @Override
                    public void getTime(String time) {
                        qfrq = time;
                        maintenancelog_tzdbh_ev.setText(qfrq);
                    }
                });
            }
        });

        builder.setView(loglistDialog);
//        builder.setMultiChoiceItems(items, flags, new DialogInterface.OnMultiChoiceClickListener(){
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        flags[which]=isChecked;
//                        String result = "";
//                        for (int i = 0; i < flags.length; i++) {
//                            if(flags[i]){
//                                result=result+items[i]+"、";
//                            }
//                        }
//                        maintenance_logList_selectCondition_tv.setText(result.substring(0, result.length()-1));
//                    }
//                });
        //添加一个确定按钮
        builder.setPositiveButton(" 查 询 ", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                tzdbh = maintenancelog_tzdbh_ev.getText().toString();
                wxbm = maintenancelog_wxdw_ev.getText().toString();
                qfrq = maintenancelog_qfrq_ev.getText().toString();
                qfr = maintenancelog_qfr_ev.getText().toString();
                StringBuffer str = new StringBuffer();
                if (tzdbh.length() != 0) {
                    str.append("通知单编号：");
                    str.append(tzdbh);
                }
                if (wxbm.length() != 0) {
                    str.append("维修单位：");
                    str.append(wxbm);
                }
                if (qfrq.length() != 0) {
                    str.append("签发日期：");
                    str.append(qfrq);
                }
                if (qfr.length() != 0) {
                    str.append("签发人：");
                    str.append(qfr);
                }
                maintenance_logList_selectCondition_tv.setText(str);
                if(str.length() != 0){
                    loadDate();
                }

            }
        });
        //创建一个复选框对话框
        AlertDialog dialog = builder.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 500;
//                params.height = 200 ;

        dialog.getWindow().setAttributes(params);

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

                listBeen = (ArrayList<MaintenanceLogBean>) JSON.parseArray(result.getString("datas"), MaintenanceLogBean.class);

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
                if (tzdbh.length() != 0) {
                    pair = new BasicNameValuePair("bno", tzdbh);
                    list.add(pair);
                }
                if (wxbm.length() != 0) {
                    pair = new BasicNameValuePair("wxbmmc", wxbm);
                    list.add(pair);
                }
                if (qfrq.length() != 0) {
                    pair = new BasicNameValuePair("qfrq", qfrq);
                    list.add(pair);
                }
                if (qfr.length() != 0) {
                    pair = new BasicNameValuePair("qfry", qfr);
                    list.add(pair);
                }
                new HttpTask(onReceivedHttpResponseListener, RequestType.getUpkeepnoticeByUID).executePost(list);
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
