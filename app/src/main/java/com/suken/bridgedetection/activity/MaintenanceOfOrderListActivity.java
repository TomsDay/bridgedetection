package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceOfOrderListAdapter;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderDao;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceOfOrderListActivity extends Activity {
    private ListView maintenanceoforderlist_table_listView;
    private Context mContext;
    private MaintenanceOfOrderListAdapter mAdapter;
    private List<MaintenanceOfOrderBean> maintenanceOfOrderBeen = new ArrayList<MaintenanceOfOrderBean>();
    private MaintenanceOfOrderDao maintenanceOfOrderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_of_order_list);
        mContext = this;
        maintenanceOfOrderDao = new MaintenanceOfOrderDao();
        initView();
    }

    private void initView() {
        maintenanceOfOrderBeen = maintenanceOfOrderDao.queryAll();
        maintenanceoforderlist_table_listView = (ListView) findViewById(R.id.maintenanceoforderlist_table_listView);
        mAdapter = new MaintenanceOfOrderListAdapter(mContext);
        mAdapter.setDate(maintenanceOfOrderBeen);
        maintenanceoforderlist_table_listView.setAdapter(mAdapter);

        maintenanceoforderlist_table_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent in = new Intent(mContext, MaintenanceOfOrderActivity.class);
                in.putExtra("id", maintenanceOfOrderBeen.get(position).getId());
                startActivity(in);

            }
        });
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenanceoforderlist_table_back:
                finish();
                break;
        }
    }
}
