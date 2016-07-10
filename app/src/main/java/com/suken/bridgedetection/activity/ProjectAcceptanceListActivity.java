package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.ProjectAcceptanceListAdapter;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ProjectAcceptanceListActivity extends Activity {
    private ListView project_acceptance_listView;
    private Context mContext;
    private ProjectAcceptanceListAdapter mAdapter;
    private ProjectAcceptanceDao projectAcceptanceDao;
    private List<ProjectAcceptanceBean> projectAcceptanceBeen = new ArrayList<ProjectAcceptanceBean>();

    private List<ProjacceptBean> projacceptBeen = new ArrayList<ProjacceptBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_acceptance_list);
        mContext = this;
        projectAcceptanceDao = new ProjectAcceptanceDao();
        initView();
    }

    private void initView() {
        projectAcceptanceBeen = projectAcceptanceDao.queryAll();
        project_acceptance_listView = (ListView) findViewById(R.id.project_acceptance_listView);
        mAdapter = new ProjectAcceptanceListAdapter(mContext);
//        mAdapter.setData(projectAcceptanceBeen);
        project_acceptance_listView.setAdapter(mAdapter);
        project_acceptance_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final String[] names = { "生成维修保养工程验收单", "取消" };
                new AlertDialog.Builder(mContext)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                switch (which) {
                                    case 0:
                                        Intent in = new Intent(mContext, ProjectAcceptanceActivity.class);
                                        in.putExtra("bean", projacceptBeen.get(position));
                                        startActivity(in);
                                        break;
                                    default:
                                        break;
                                }


                            }
                        })
                        .show();
            }
        });
        getYanShouData();

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.project_acceptance_back:
                finish();
                break;
        }
    }

    public final int SUCCESS_CODE = 0;
    public final int ERROR_CODE = 1;
    private void getYanShouData(){

        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                projacceptBeen = JSON.parseArray(result.getString("datas"), ProjacceptBean.class);

                Logger.e("aaa", projacceptBeen.toString());
                Message message = new Message();
                message.what = SUCCESS_CODE;
                mHandler.sendMessage(message);



            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "===(" + resultCode + ")");
                Logger.e("aaa", "type===" + type);
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
                new HttpTask(onReceivedHttpResponseListener, RequestType.getProjacceptByUID).executePost(list);


            }
        });


    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_CODE:
                    mAdapter.setData(projacceptBeen);
                    mAdapter.notifyDataSetChanged();
                    break;
                case ERROR_CODE:
                    break;
            }
        }
    };

}
