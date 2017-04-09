package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.OfOrderListAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.CooperationBean;
import com.suken.bridgedetection.bean.CooperationDao;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.QualityDemandBean;
import com.suken.bridgedetection.bean.QualityDemandDao;
import com.suken.bridgedetection.bean.WxdbhByUID;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.DeviceInfoUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.suken.bridgedetection.R.id.maintenancelog_data_ev;

public class MaintenanceRequisitionActivity extends BaseActivity {

    private EditText maintenancerequisition_gydw_ev,
            maintenancerequisition_wxbm_ev,
            maintenancerequisition_xcld_ev,
            maintenancerequisition_jcsjstart_ev,
            maintenancerequisition_jcsjend_ev,
            maintenancerequisition_zlyq_ev,
            maintenancerequisition_bcsm_ev,
            maintenancerequisition_qfr_ev,
            maintenancerequisition_qfrq_ev;

    private RadioGroup maintenancerequisition_wxlx_radio;

    private TextView maintenancerequisition_fx_tv,
            maintenancerequisition_zh_tv,
            maintenancerequisition_bhmc_tv,
            maintenancerequisition_bhxq_tv,
            maintenancerequisition_dw_tv,
            maintenancerequisition_ygsl_tv;

    private Spinner maintenancerequisition_weather_spinner;

    WxdbhByUID getwxdbhByUID;

    Context mContext;

    private String[] mStringArrayWeather;
    private ArrayAdapter<String> mArrayWeatherAdapter;
    private String strWeather = "晴";
    private int wxlxPosition = 0;

    private CooperationDao cooperationDao;
    private QualityDemandDao qualityDemandDao;

    List<CooperationBean> cooperationBeens;
    List<QualityDemandBean> qualityDemandBeens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_requisition);
        toast("生成维修通知单 成功！");
        getwxdbhByUID = (WxdbhByUID) getIntent().getSerializableExtra("bean");
        mContext = MaintenanceRequisitionActivity.this;
        cooperationDao = new CooperationDao();
        qualityDemandDao = new QualityDemandDao();
        cooperationBeens = cooperationDao.queryAll();
        qualityDemandBeens = qualityDemandDao.queryAll();
        initView();
    }
    public void initView(){
        maintenancerequisition_gydw_ev = (EditText) findViewById(R.id.maintenancerequisition_gydw_ev);
        maintenancerequisition_xcld_ev = (EditText) findViewById(R.id.maintenancerequisition_xcld_ev);
        maintenancerequisition_wxbm_ev = (EditText) findViewById(R.id.maintenancerequisition_wxbm_ev);
        maintenancerequisition_jcsjstart_ev = (EditText) findViewById(R.id.maintenancerequisition_jcsjstart_ev);
        maintenancerequisition_jcsjend_ev = (EditText) findViewById(R.id.maintenancerequisition_jcsjend_ev);
        maintenancerequisition_zlyq_ev = (EditText) findViewById(R.id.maintenancerequisition_zlyq_ev);
        maintenancerequisition_bcsm_ev = (EditText) findViewById(R.id.maintenancerequisition_bcsm_ev);
        maintenancerequisition_qfr_ev = (EditText) findViewById(R.id.maintenancerequisition_qfr_ev);
        maintenancerequisition_qfrq_ev = (EditText) findViewById(R.id.maintenancerequisition_qfrq_ev);

        maintenancerequisition_wxlx_radio = (RadioGroup) findViewById(R.id.maintenancerequisition_wxlx_radio);

        maintenancerequisition_fx_tv = (TextView) findViewById(R.id.maintenancerequisition_fx_tv);
        maintenancerequisition_zh_tv = (TextView) findViewById(R.id.maintenancerequisition_zh_tv);
        maintenancerequisition_bhmc_tv = (TextView) findViewById(R.id.maintenancerequisition_bhmc_tv);
        maintenancerequisition_bhxq_tv = (TextView) findViewById(R.id.maintenancerequisition_bhxq_tv);
        maintenancerequisition_dw_tv = (TextView) findViewById(R.id.maintenancerequisition_dw_tv);
        maintenancerequisition_ygsl_tv = (TextView) findViewById(R.id.maintenancerequisition_ygsl_tv);

        initData();
        initListDialog();
        maintenancerequisition_wxlx_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.maintenancerequisition_wxlx_zy){
                    wxlxPosition = 0;
                } else if (checkedId == R.id.maintenancerequisition_wxlx_by) {
                    wxlxPosition = 1;
                }
            }
        });


    }

    public void initListDialog(){
        maintenancerequisition_wxbm_ev.setOnKeyListener(null);
        maintenancerequisition_wxbm_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showListDialog();
            }
        });
        maintenancerequisition_zlyq_ev.setOnKeyListener(null);
        maintenancerequisition_zlyq_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showListDialog();
            }
        });
    }

//    AlertDialog listDialog;
//    public void showListDialog(boolean type){
//        View dialogView = getLayoutInflater().inflate(R.layout.oforderlist_dialog, null);
//        ListView dialogListView = (ListView) dialogView.findViewById(R.id.oforderlist_listview);
//        OfOrderListAdapter ofOrderListAdapter = new OfOrderListAdapter(mContext);
//        ofOrderListAdapter.setData(maintenanceLogBeenList);
//        dialogListView.setAdapter(ofOrderListAdapter);
//
//        listDialog = new AlertDialog.Builder(mContext)
//                .setView(dialogView)
//                .show();
//
//        WindowManager.LayoutParams params = listDialog.getWindow().getAttributes();
//        params.width = DeviceInfoUtil.getScreenHeight(mContext) - 200;
////                params.height = 200 ;
//
//        listDialog.getWindow().setAttributes(params);
//        listDialog.setCanceledOnTouchOutside(true);
//
//        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//响应listview中的item的点击事件
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, final int position,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//
//                for(MaintenanceLogBean b1:thisMaintenanceLogBeenList){
//                    if(maintenanceLogBeenList.get(position).getBno().equals(b1.getBno())){
//                        toast("不能添加已添加的数据");
//                        return;
//                    }
//                }
//                new AlertDialog.Builder(mContext)
//                        .setTitle("提示")
//                        .setMessage("是否选择表单编号为" + maintenanceLogBeenList.get(position).getBno() + "的数据？")
//                        .setPositiveButton("确定", new DatePickerDialog.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                thisMaintenanceLogBeenList = new ArrayList<>();
//                                thisMaintenanceLogBeenList.add(maintenanceLogBeenList.get(position));
//                                if(!TextUtil.isListEmpty(thisMaintenanceLogBeenList)){
//                                    List<MaintenanceLogItemBean> checkData = thisMaintenanceLogBeenList.get(0).getUpkeepdiseaseList();
//                                    Logger.e("aaa","checkData=="+checkData.toString());
////                                    List<MaintenanceLogItemBean> deleteData = new ArrayList<MaintenanceLogItemBean>();
////                                    for (MaintenanceLogItemBean thisBean : checkData) {
////                                        String wxsl = thisBean.getWxsl();
////                                        if (TextUtil.isEmptyString(wxsl) || "0".equals(wxsl)) {
////                                            deleteData.add(thisBean);
////                                        }
////                                    }
////                                    checkData.removeAll(deleteData);
//                                    mAdapter.setData(checkData);
//                                    mAdapter.notifyDataSetChanged();
//                                }
//
//                                if(maintenanceoforder_content_ev.getVisibility() == View.VISIBLE){
//                                    maintenanceoforder_content_ev.setVisibility(View.GONE);
//                                    maintenanceoforder_content_Listlayout.setVisibility(View.VISIBLE);
//                                }
//                                listDialog.dismiss();
//
//                            }
//                        })
//                        .setNegativeButton("取消", new DatePickerDialog.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        }).show();
//            }
//        });
//
//    }

    public void initData(){
        getwxdbhByUID = (WxdbhByUID) getIntent().getSerializableExtra("bean");

        maintenancerequisition_gydw_ev.setText(BridgeDetectionApplication.mCurrentUser.getDefgqName());
        maintenancerequisition_xcld_ev.setText(getwxdbhByUID.getXcld());


        initTime();
        initSpinner();


    }

    private void initSpinner() {
        maintenancerequisition_weather_spinner = (Spinner) findViewById(R.id.maintenancerequisition_weather_spinner);
        mStringArrayWeather = getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(mContext, mStringArrayWeather);
//        设置下拉列表风格(这句不些也行)
//        mAdapter.setDropDownViewResource(android.R.layout.simple);
        maintenancerequisition_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancerequisition_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

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
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DATE);
        maintenancerequisition_jcsjstart_ev.setText(getTime(year, month, day));
        maintenancerequisition_jcsjstart_ev.setKeyListener(null);
        maintenancerequisition_jcsjstart_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(maintenancerequisition_jcsjstart_ev.getText().toString(),true);
            }
        });
        maintenancerequisition_jcsjend_ev.setText(getTime(year, month, day));
        maintenancerequisition_jcsjend_ev.setKeyListener(null);
        maintenancerequisition_jcsjend_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(maintenancerequisition_jcsjend_ev.getText().toString(),false);
            }
        });
    }
    public void showDatePicker(String time,final boolean isStart){

        Calendar c = DateUtil.strToCalendarLong2(time);

        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if(isStart){
                    maintenancerequisition_jcsjstart_ev.setText(getTime(year, month+1, day));
                }else{
                    maintenancerequisition_jcsjend_ev.setText(getTime(year, month+1, day));
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
        dlg.setTitle("日期：");
        dlg.show();

    }
    public String getTime(int year,int month,int day){
        return  year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }


    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenancerequisition_back:
                finish();
                break;
            case R.id.maintenancerequisition_save:
                toast("保存1");
                break;
            case R.id.saveBtn:
                toast("保存2");
                break;
            case R.id.maintenancerequisition_synchronizationwxbm:
//                loadDate();
                synchronizationQualityDemand();

                toast("同步维修部门");
                break;
            case R.id.maintenancelog_synchronizationzlyq:
//                loadDate();
                synchronizationCooperation();
                toast("同步质量要求");
                break;
        }
    }

    /**
     * 质量要求
     */
    public void synchronizationCooperation(){
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());
                cooperationDao.deleteAll();
                List<CooperationBean> cooperationBeens = JSON.parseArray(result.getString("datas"), CooperationBean.class);

                Logger.e("aaa", "size=="+cooperationBeens.size());

                cooperationDao.addList(cooperationBeens);

                dismissLoading();
                toast("同步质量要求库成功！");
                if(TextUtil.isListEmpty(cooperationBeens)){
                    ToastUtil.showMessage("暂无质量要求库数据");
                }
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "===(" + resultCode + ")");
                Logger.e("aaa", "type===" + type);

                dismissLoading();
                toast("同步质量要求库失败！");
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
//                maintenanceDiseaseDao.deleteAll();
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteCooperationByUID).executePost(list);


            }
        });

    }

    /**
     * 获取外协单位
     */
    public void synchronizationQualityDemand(){
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                List<QualityDemandBean> qualityDemandBeens = JSON.parseArray(result.getString("datas"), QualityDemandBean.class);

                Logger.e("aaa", "size=="+qualityDemandBeens.size());

                qualityDemandDao.addList(qualityDemandBeens);

                dismissLoading();
                toast("同步外协单位库成功！");
                if(TextUtil.isListEmpty(qualityDemandBeens)){
                    ToastUtil.showMessage("暂无外协单位库数据");
                }
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "===(" + resultCode + ")");
                Logger.e("aaa", "type===" + type);

                dismissLoading();
                toast("同步外协单位库失败！");
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
//                maintenanceDiseaseDao.deleteAll();
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteQualityDemandByUID).executePost(list);


            }
        });

    }

}
