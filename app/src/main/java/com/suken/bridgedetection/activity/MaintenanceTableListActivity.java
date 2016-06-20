package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceTableListAdapter;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 高速公路维修保养日志(列表)
 */

public class MaintenanceTableListActivity extends Activity {
    private ListView maintenance_table_listView;
    private MaintenanceTableListAdapter mAdapter;
    List<MaintenanceTableBean> maintenanceTableBeanList = new ArrayList<MaintenanceTableBean>();
    private MaintenanceTableDao maintenanceTableDao;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_table_list);
        mContext = MaintenanceTableListActivity.this;
        maintenanceTableDao = new MaintenanceTableDao();
        initView();
    }

    private void initView() {
        maintenance_table_listView = (ListView) findViewById(R.id.maintenance_table_listView);
        maintenanceTableBeanList= maintenanceTableDao.queryAll();
        mAdapter = new MaintenanceTableListAdapter(MaintenanceTableListActivity.this);
        mAdapter.setData(maintenanceTableBeanList);
        maintenance_table_listView.setAdapter(mAdapter);
        maintenance_table_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent in = new Intent(mContext, MaintenanceTableActivity.class);
                in.putExtra("id", maintenanceTableBeanList.get(position).getId());
                startActivity(in);
            }
        });

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_table_back:
                finish();
                break;
        }
    }
}
