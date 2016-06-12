package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceLogAdapter;
import com.suken.bridgedetection.adapter.MaintenanceTableAdapter;
import com.suken.bridgedetection.adapter.ProjectAcceptanceAdapter;
import com.suken.bridgedetection.bean.MaintenanceItemBean;
import com.suken.bridgedetection.widget.ListViewForScrollView;

import java.util.ArrayList;
/**
 * 高速公路维修保养日志
 */
public class MaintenanceLogActivity extends Activity {
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceItemBean> list = new ArrayList<MaintenanceItemBean>();
    private MaintenanceLogAdapter mAdapter;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log);
        mContext = this;
        initView();
    }

    private void initView() {
        mListView = (ListViewForScrollView) findViewById(R.id.maintenancelog_listview);
        mAdapter = new MaintenanceLogAdapter(MaintenanceLogActivity.this);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(list);
        MaintenanceItemBean bean = new MaintenanceItemBean();
        bean.setShow(true);
        list.add(bean);
    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.maintenancelog_back:
                finish();
                break;
            case R.id.maintenancelog_save:

                break;
        }

    }
    public void operate(View view){
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
}