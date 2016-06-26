package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.ProjectAcceptanceListAdapter;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;

import java.util.ArrayList;
import java.util.List;

public class ProjectAcceptanceListActivity extends Activity {
    private ListView project_acceptance_listView;
    private Context mContext;
    private ProjectAcceptanceListAdapter mAdapter;
    private ProjectAcceptanceDao projectAcceptanceDao;
    private List<ProjectAcceptanceBean> projectAcceptanceBeen = new ArrayList<ProjectAcceptanceBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_acceptance_list);
        mContext = this;
        projectAcceptanceDao = new ProjectAcceptanceDao();
        initView();
    }

    private void initView() {
        projectAcceptanceBeen = projectAcceptanceDao.queryAll();
        project_acceptance_listView = (ListView) findViewById(R.id.project_acceptance_listView);
        mAdapter = new ProjectAcceptanceListAdapter(mContext);
        mAdapter.setData(projectAcceptanceBeen);
        project_acceptance_listView.setAdapter(mAdapter);
        project_acceptance_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent in = new Intent(mContext, ProjectAcceptanceActivity.class);
                in.putExtra("id", projectAcceptanceBeen.get(position).getId());
                startActivity(in);
            }
        });

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.project_acceptance_back:
                finish();
                break;
        }
    }
}
