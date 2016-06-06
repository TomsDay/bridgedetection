package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceTableAdapter;
import com.suken.bridgedetection.widget.ListViewForScrollView;

import java.util.ArrayList;

public class MaintenanceTableActivity extends Activity {
    ListViewForScrollView mListView;
    private ArrayList<Boolean> list = new ArrayList<Boolean>();
    private MaintenanceTableAdapter mAdapter;
    private Context mContext;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_table);
        mContext = this;
        initView();
    }

    private void initView() {
        mListView = (ListViewForScrollView) findViewById(R.id.maintenancetable_listview);
        mAdapter = new MaintenanceTableAdapter(mContext);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(list);
        list.add(true);


    }
    public void operate(View view){
        switch (view.getId()) {
            case R.id.operateAdd:
                list = mAdapter.getData();
                list.add(false);
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
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.maintenancetable_back:
                finish();
                break;
            case R.id.maintenancetable_save:

                break;
        }

    }
}
