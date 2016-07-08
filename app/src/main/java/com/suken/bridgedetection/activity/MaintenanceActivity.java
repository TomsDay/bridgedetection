package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceAdapter;
import com.suken.bridgedetection.bean.MaintenanceLogDao;
import com.suken.bridgedetection.bean.MaintenanceOfOrderDao;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.widget.CkeckXMDialog;

import java.util.ArrayList;

public class MaintenanceActivity extends BaseActivity {
    private TextView maintenance_back;
    private ListView maintenance_mListView;
    private MaintenanceAdapter mAdapter;
    private Context mContext;
    private MaintenanceTableDao maintenanceTableDao;
    private MaintenanceLogDao maintenanceLogDao;
    private MaintenanceOfOrderDao maintenanceOfOrderDao;
    private ProjectAcceptanceDao projectAcceptanceDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        mContext = this;
        maintenanceTableDao = new MaintenanceTableDao();
        maintenanceLogDao = new MaintenanceLogDao();
        maintenanceOfOrderDao = new MaintenanceOfOrderDao();
        projectAcceptanceDao = new ProjectAcceptanceDao();
        initView();
        Logger.e("aaa", "token === " + BridgeDetectionApplication.mCurrentUser.getToken());
        Logger.e("aaa", "userId === " + BridgeDetectionApplication.mCurrentUser.getUserId());
        Logger.e("aaa", "username === " + BridgeDetectionApplication.mCurrentUser.getUserName());
    }
    private void initView(){
        maintenance_back = (TextView) findViewById(R.id.maintenance_back);
        maintenance_mListView = (ListView) findViewById(R.id.maintenance_mListView);
        mAdapter = new MaintenanceAdapter(mContext);
        maintenance_mListView.setAdapter(mAdapter);
        maintenance_mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent in = new Intent(mContext, WebViewActivity.class);
                startActivity(in);
            }
        });
        getDate();
        CkeckXMDialog ckeckXMDialog = new CkeckXMDialog(MaintenanceActivity.this);
        ckeckXMDialog.show();
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDate();
    }

    public void getDate(){
        ArrayList<String> list = new ArrayList<>();
        list.add(maintenanceTableDao.queryAll().size()+"");
        list.add(maintenanceLogDao.queryAll().size()+"");
        list.add(maintenanceOfOrderDao.queryAll().size()+"");
        list.add(projectAcceptanceDao.queryAll().size()+"");
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }
}
