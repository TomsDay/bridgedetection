package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.MaintenanceItemBean;

public class MaintenanceOfOrderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_of_order);
    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.maintenanceoforder_back:
                finish();
                break;
            case R.id.maintenanceoforder_save:

                break;
        }

    }
}
