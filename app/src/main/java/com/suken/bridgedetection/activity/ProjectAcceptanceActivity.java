package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.ProjectAcceptanceAdapter;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.widget.ListViewForScrollView;

import java.util.ArrayList;
/**
 * 速公路维修保养工程验收单
 */
public class ProjectAcceptanceActivity extends Activity {
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceTableItemBean> list = new ArrayList<MaintenanceTableItemBean>();
    private ProjectAcceptanceAdapter mAdapter;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_acceptance);
        mContext = this;
        initView();
    }

    private void initView() {
        mListView = (ListViewForScrollView) findViewById(R.id.projectacceptance_listview);
        mAdapter = new ProjectAcceptanceAdapter(ProjectAcceptanceActivity.this);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(list);
        MaintenanceTableItemBean bean = new MaintenanceTableItemBean();
        bean.setShow(true);
        list.add(bean);
    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.projectacceptance_back:
                finish();
                break;
            case R.id.projectacceptance_save:

                break;
        }

    }
    public void operate(View view){
        switch (view.getId()) {
            case R.id.operateAdd:
                list = mAdapter.getData();
                MaintenanceTableItemBean bean = new MaintenanceTableItemBean();
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
