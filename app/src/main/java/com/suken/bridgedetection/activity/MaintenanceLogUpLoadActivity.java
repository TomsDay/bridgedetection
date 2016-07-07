package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogDao;

import java.util.ArrayList;

public class MaintenanceLogUpLoadActivity extends BaseActivity {
    private ListView mListView;
    ArrayList<MaintenanceLogBean> listBeen = new ArrayList<MaintenanceLogBean>();
    MaintenanceLogDao maintenanceLogDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log_up_load);
        maintenanceLogDao = new MaintenanceLogDao();
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.maintenance_logListUpLoad_listView);
    }
}
