package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.ProjectAcceptanceListAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;
import com.suken.bridgedetection.util.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 速公路维修保养工程验收单
 */
public class ProjectAcceptanceActivity extends Activity {
    private ArrayList<MaintenanceTableItemBean> list = new ArrayList<MaintenanceTableItemBean>();
    private ProjectAcceptanceListAdapter mAdapter;
    private Context mContext;
    private EditText projectacceptance_gydw_ev,
            projectacceptance_bh_ev,
            projectacceptance_wxbydw_ev,
            projectacceptance_sgrqs_ev,
            projectacceptance_sgrqe_ev,
            projectacceptance_content_ev,
            projectacceptance_return_ev,
            projectacceptance_xsfzr_ev;
    private Spinner projectacceptance_weather_spinner;

    private int mSYear,mSMonth, mSDay,mEYear,mEMonth, mEDay;

    private String[] mStringArrayWeather;
    private ArrayAdapter<String> mArrayWeatherAdapter;
    private String strWeather = "晴";
    private int id;

    private ProjectAcceptanceDao projectAcceptanceDao;
    private List<ProjectAcceptanceBean> projectAcceptanceBeen = new ArrayList<ProjectAcceptanceBean>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_acceptance);
        mContext = this;
        projectAcceptanceDao = new ProjectAcceptanceDao();
        initView();
    }

    private void initView() {
        projectacceptance_gydw_ev = (EditText) findViewById(R.id.projectacceptance_gydw_ev);
        projectacceptance_bh_ev = (EditText) findViewById(R.id.projectacceptance_bh_ev);
        projectacceptance_wxbydw_ev = (EditText) findViewById(R.id.projectacceptance_wxbydw_ev);
        projectacceptance_sgrqs_ev = (EditText) findViewById(R.id.projectacceptance_sgrqs_ev);
        projectacceptance_sgrqe_ev = (EditText) findViewById(R.id.projectacceptance_sgrqe_ev);
        projectacceptance_content_ev = (EditText) findViewById(R.id.projectacceptance_content_ev);
        projectacceptance_return_ev = (EditText) findViewById(R.id.projectacceptance_return_ev);
        projectacceptance_xsfzr_ev = (EditText) findViewById(R.id.projectacceptance_xsfzr_ev);

        projectacceptance_weather_spinner = (Spinner) findViewById(R.id.projectacceptance_weather_spinner);

        initTime();
        initSpinner();

        if (id != 0) {
            projectAcceptanceBeen = (ArrayList<ProjectAcceptanceBean>)  projectAcceptanceDao.queryByID(id);
            Logger.e("aaa","maintenanceTableBeanList++"+projectAcceptanceBeen.toString());
            if(projectAcceptanceBeen.size()>0){
                ProjectAcceptanceBean bean = projectAcceptanceBeen.get(0);

                projectacceptance_gydw_ev.setText(bean.getCustodyUnit());
                projectacceptance_bh_ev.setText(bean.getSerialNumber());
                projectacceptance_wxbydw_ev.setText(bean.getMaintenanceUnit());
                projectacceptance_sgrqs_ev.setText(bean.getConstructionStartTime());
                projectacceptance_sgrqe_ev.setText(bean.getConstructionEndTime());
                projectacceptance_content_ev.setText(bean.getContent());
                projectacceptance_return_ev.setText(bean.getAcceptanceReturn());
                projectacceptance_xsfzr_ev.setText(bean.getAcceptancePeople());

            }else{
                projectacceptance_bh_ev.setText(UUID.randomUUID().toString());
            }

        }else{
            projectacceptance_bh_ev.setText(UUID.randomUUID().toString());
        }




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
    private void initSpinner() {
        projectacceptance_weather_spinner = (Spinner) findViewById(R.id.maintenanceoforder_weather_spinner);
        mStringArrayWeather = getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(mContext, mStringArrayWeather);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectacceptance_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        projectacceptance_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

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
    private void initTime() {
        Calendar c = Calendar.getInstance();
        mSYear = c.get(Calendar.YEAR);
        mSMonth = c.get(Calendar.MONTH)+1;
        mSDay = c.get(Calendar.DATE);
        mEYear = c.get(Calendar.YEAR);
        mEMonth = c.get(Calendar.MONTH)+1;
        mEDay = c.get(Calendar.DATE);
        projectacceptance_sgrqs_ev = (EditText) findViewById(R.id.maintenancelog_data_ev);
        projectacceptance_sgrqs_ev.setText(getTime(mSYear, mSMonth, mSDay));
        projectacceptance_sgrqs_ev.setKeyListener(null);
        projectacceptance_sgrqs_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(true);
            }
        });
        projectacceptance_sgrqe_ev = (EditText) findViewById(R.id.maintenancelog_data_ev);
        projectacceptance_sgrqe_ev.setText(getTime(mEYear, mEMonth, mEDay));
        projectacceptance_sgrqe_ev.setKeyListener(null);
        projectacceptance_sgrqe_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(false);
            }
        });
    }
    public String getTime(int year,int month,int day){
        return  year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }
    public void showDatePicker(final boolean isSorE){
        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if (isSorE) {
                    mSYear = year;
                    mSMonth = month + 1;
                    mSDay = day;
                    projectacceptance_sgrqs_ev.setText(getTime(mSYear, mSMonth, mSDay));
                } else {
                    mEYear = year;
                    mEMonth = month + 1;
                    mEDay = day;
                    projectacceptance_sgrqe_ev.setText(getTime(mEYear, mEMonth, mEDay));
                }
            }
        }, isSorE ? mSYear : mEYear, isSorE ? (mSMonth - 1) : (mEMonth - 1), isSorE ? mSDay : mEDay);
        dlg.setTitle(isSorE ?"开始日期：":"结束日期：");
        dlg.show();

    }

    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        String gydw = projectacceptance_gydw_ev.getText().toString();
                        String bh = projectacceptance_bh_ev.getText().toString();
                        String wxbydw = projectacceptance_wxbydw_ev.getText().toString();
                        String sgrqs = projectacceptance_sgrqs_ev.getText().toString();
                        String sgrqe = projectacceptance_sgrqe_ev.getText().toString();
                        String content = projectacceptance_content_ev.getText().toString();
                        String returnContent = projectacceptance_return_ev.getText().toString();
                        String xsfzr = projectacceptance_xsfzr_ev.getText().toString();

                        ProjectAcceptanceBean bean = new ProjectAcceptanceBean();
                        bean.setCustodyUnit(gydw);
                        bean.setSerialNumber(bh);
                        bean.setWeather(strWeather);
                        bean.setMaintenanceUnit(wxbydw);
                        bean.setConstructionStartTime(sgrqs);
                        bean.setConstructionEndTime(sgrqe);
                        bean.setContent(content);
                        bean.setAcceptanceReturn(returnContent);
                        bean.setAcceptancePeople(xsfzr);
                        if (id != 0) {
                            bean.setId(id);
                        }


                        Logger.e("aaa","maintenanceTableBean.toString()===="+bean.toString());
                        if (id != 0) {
                            projectAcceptanceDao.update(bean);
                        }else{
                            projectAcceptanceDao.add(bean);
                        }

                        Logger.e("aaa", "=======11111====="+projectAcceptanceDao.queryAll().toString());




                    }
                })
                .setNegativeButton("取消，再改改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();


    }


}
