package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceAdapter;

public class MaintenanceActivity extends BaseActivity {
    private TextView maintenance_back;
    private ListView maintenance_mListView;
    private MaintenanceAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        mContext = this;
        initView();
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

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_back:
                finish();
                break;
        }
    }
}
