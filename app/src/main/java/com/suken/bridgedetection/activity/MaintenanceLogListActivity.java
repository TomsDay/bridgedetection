package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceLogListAdapter;

public class MaintenanceLogListActivity extends Activity {
    private ListView maintenanceLogList_listView;
    private MaintenanceLogListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log_list);
        initView();
    }

    private void initView() {
        maintenanceLogList_listView = (ListView) findViewById(R.id.maintenanceLogList_listView);
        mAdapter = new MaintenanceLogListAdapter(MaintenanceLogListActivity.this);
        maintenanceLogList_listView.setAdapter(mAdapter);

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenanceLogList_back:
                finish();
                break;
        }
    }
}
