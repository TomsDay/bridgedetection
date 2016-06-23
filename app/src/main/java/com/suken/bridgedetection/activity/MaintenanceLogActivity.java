package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceLogAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogDao;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceLogListBean;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * 高速公路维修保养日志
 */
public class MaintenanceLogActivity extends Activity {
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceLogBean> maintenanceLogBeen = new ArrayList<MaintenanceLogBean>();
    private ArrayList<MaintenanceLogItemBean> maintenanceLogItemBeen = new ArrayList<MaintenanceLogItemBean>();

    private MaintenanceLogAdapter mAdapter;
    private Context mContext;
    private MaintenanceLogListBean maintenanceLogListBean;

    private EditText maintenancelog_gydw_ev,
            maintenancelog_bh_ev,
            maintenancelog_data_ev,
            maintenancelog_wxbm_ev,
            maintenancelog_jcr_ev,
            maintenancelog_jlr_ev;

    private Spinner maintenancelog_weather_spinner;

    private ArrayAdapter<String> mArrayWeatherAdapter;

    private String[] mStringArrayWeather;

    private String strWeather = "晴";

    private int mYear,mMonth, mDay;

    private int id;

    MaintenanceLogDao maintenanceLogDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_log);
        mContext = this;
        maintenanceLogDao = new MaintenanceLogDao();
        maintenanceLogListBean = (MaintenanceLogListBean) getIntent().getSerializableExtra("bean");
        Log.e("aaa", maintenanceLogListBean.toString());
        initView();
    }

    private void initView() {
        mListView = (ListViewForScrollView) findViewById(R.id.maintenancelog_listview);
        maintenancelog_gydw_ev = (EditText) findViewById(R.id.maintenancelog_gydw_ev);
        maintenancelog_bh_ev = (EditText) findViewById(R.id.maintenancelog_bh_ev);
        maintenancelog_wxbm_ev = (EditText) findViewById(R.id.maintenancelog_wxbm_ev);
        maintenancelog_jcr_ev = (EditText) findViewById(R.id.maintenancelog_jcr_ev);
        maintenancelog_jlr_ev = (EditText) findViewById(R.id.maintenancelog_jlr_ev);



        maintenancelog_gydw_ev.setEnabled(false);
        maintenancelog_gydw_ev.setText(maintenanceLogListBean.getGydwName());
        maintenancelog_bh_ev.setText(UUID.randomUUID().toString());

        mAdapter = new MaintenanceLogAdapter(MaintenanceLogActivity.this);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(maintenanceLogItemBeen);
        MaintenanceLogItemBean bean = new MaintenanceLogItemBean();
        bean.setShow(true);
        maintenanceLogItemBeen.add(bean);
        initSpinner();
        initTime();
    }

    private void initTime() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DATE);
        maintenancelog_data_ev = (EditText) findViewById(R.id.maintenancelog_data_ev);
        maintenancelog_data_ev.setText(getTime(mYear, mMonth, mDay));
        maintenancelog_data_ev.setKeyListener(null);
        maintenancelog_data_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    private void initSpinner() {
        maintenancelog_weather_spinner = (Spinner) findViewById(R.id.maintenancelog_weather_spinner);
        mStringArrayWeather = getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(mContext, mStringArrayWeather);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancelog_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancelog_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

    }
    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
                    strWeather = mStringArrayWeather[position];
            }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.maintenancelog_back:
                finish();
                break;
            case R.id.maintenancelog_save:
                saveDialog();
                break;
        }

    }
    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Logger.e("aaa","itemlist===="+ maintenanceLogItemBeen.toString());

                        String gydw = maintenancelog_gydw_ev.getText().toString();
                        String bh = maintenancelog_bh_ev.getText().toString();
                        String wxbm = maintenancelog_wxbm_ev.getText().toString();
                        String date = maintenancelog_data_ev.getText().toString();
                        String jcr = maintenancelog_jcr_ev.getText().toString();
                        String fzr = maintenancelog_jlr_ev.getText().toString();

                        MaintenanceLogBean maintenanceLogBean = new MaintenanceLogBean();
                        maintenanceLogBean.setCustodyUnit(gydw);
                        maintenanceLogBean.setSerialNumber(bh);
                        maintenanceLogBean.setWeather(strWeather);
                        maintenanceLogBean.setDate(date);
                        maintenanceLogBean.setMaintenanceDepartment(wxbm);
                        maintenanceLogBean.setRummager(jcr);
                        maintenanceLogBean.setPrincipal(fzr);
                        if (id != 0) {
                            maintenanceLogBean.setId(id);
                        }


                        Logger.e("aaa","maintenanceTableBean.toString()===="+maintenanceLogBean.toString());
                        if (id != 0) {
                            maintenanceLogDao.update(maintenanceLogBean);
                        }else{
                            maintenanceLogDao.add(maintenanceLogBean);
                        }
                        maintenanceLogItemBeen = mAdapter.getData();
                        for (int j = 0; j < maintenanceLogItemBeen.size(); j++) {
                            MaintenanceLogItemBean itemBean = maintenanceLogItemBeen.get(j);
                            itemBean.setMaintenanceLogBean(maintenanceLogBean);
                            if (itemBean.getId() != 0) {
                                maintenanceLogDao.updateItem(itemBean);
                            }else {
                                maintenanceLogDao.addItem(itemBean);
                            }
                        }





                    }
                })
                .setNegativeButton("取消，再改改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();


    }


    public void operate(View view){
        switch (view.getId()) {
            case R.id.operateAdd:
                maintenanceLogItemBeen = mAdapter.getData();
                MaintenanceLogItemBean bean = new MaintenanceLogItemBean();
                maintenanceLogItemBeen.add(bean);
                mAdapter.setData(maintenanceLogItemBeen);
                mAdapter.notifyDataSetChanged();

                break;
            case R.id.operateDelete:
                maintenanceLogItemBeen = mAdapter.getData();
                maintenanceLogItemBeen.remove(maintenanceLogItemBeen.size() - 1);
                mAdapter.setData(maintenanceLogItemBeen);
                mAdapter.notifyDataSetChanged();
                break;
        }

    }
    public void showDatePicker(){
        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month + 1;
                mDay = day;
                maintenancelog_data_ev.setText(getTime(mYear, mMonth, mDay));
            }
        }, mYear, mMonth - 1, mDay);
        dlg.setTitle("日期：");
        dlg.show();

    }
    public String getTime(int year,int month,int day){
        return  year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }
}
