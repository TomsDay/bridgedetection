package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceLogListAdapter;

public class MaintenanceLogListActivity extends Activity {
    private ListView mListView;
    private MaintenanceLogListAdapter maintenanceLogListAdapter;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log_list);
        mContext = this;
        mListView = (ListView) findViewById(R.id.maintenance_logList_listView);
        maintenanceLogListAdapter = new MaintenanceLogListAdapter(mContext);
        mListView.setAdapter(maintenanceLogListAdapter);
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_logList_back:
                finish();
                break;
        }
    }
}
