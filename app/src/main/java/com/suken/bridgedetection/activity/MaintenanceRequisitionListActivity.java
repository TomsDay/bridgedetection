package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceRequisitionListAdapter;
import com.suken.bridgedetection.bean.CooperationBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.QualityDemandBean;
import com.suken.bridgedetection.bean.WxdbhByUID;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MaintenanceRequisitionListActivity extends BaseActivity {
    List<WxdbhByUID> listBean = new ArrayList<WxdbhByUID>();
    MaintenanceRequisitionListAdapter adapter;
    ListView maintenance_requisitionList_listView;
    LinearLayout maintenance_requisitionList_selectCondition_layout;
    TextView maintenance_requisitionList_selectCondition_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_requisition_list);

        initView();
        loadDate();
    }
    public void initView(){
        maintenance_requisitionList_selectCondition_layout = (LinearLayout) findViewById(R.id.maintenance_requisitionList_selectCondition_layout);
        maintenance_requisitionList_selectCondition_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowCheckDialog();
            }
        });
        maintenance_requisitionList_selectCondition_tv = (TextView) findViewById(R.id.maintenance_requisitionList_selectCondition_tv);
        maintenance_requisitionList_listView = (ListView) findViewById(R.id.maintenance_requisitionList_listView);
        adapter = new MaintenanceRequisitionListAdapter(this);
        maintenance_requisitionList_listView.setAdapter(adapter);
    }

    private void loadDate() {

        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                listBean = (ArrayList<WxdbhByUID>) JSON.parseArray(result.getString("datas"), WxdbhByUID.class);

                Logger.e("aaa", listBean.toString());
                Message message = new Message();
                message.what = Constants.SUCCESS_CODE;
                mHandler.sendMessage(message);



            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
                Message message = new Message();
                message.what = Constants.ERROR_CODE;
                mHandler.sendMessage(message);
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                showLoading("正在同步通知单...");
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                if (bhmc_str.length() != 0) {
                    pair = new BasicNameValuePair("bhmc", bhmc_str);
                    list.add(pair);
                }
                if (yhInspNo_str.length() != 0) {
                    pair = new BasicNameValuePair("yhInspNo", yhInspNo_str);
                    list.add(pair);
                }
                if (jcsjstart_str.length() != 0) {
                    pair = new BasicNameValuePair("jcsjstart", jcsjstart_str);
                    list.add(pair);
                }
                if (jcsjend_str.length() != 0) {
                    pair = new BasicNameValuePair("jcsjend", jcsjend_str);
                    list.add(pair);
                }
                if (xcry_str.length() != 0) {
                    pair = new BasicNameValuePair("xcry", xcry_str);
                    list.add(pair);
                }
                Logger.e("aaa",list.toString());
                new HttpTask(onReceivedHttpResponseListener, RequestType.getWxdbhByUID).executePost(list);
            }
        });
    }
    public String getTime(int year,int month,int day){
        return  year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.SUCCESS_CODE:
                    dismissLoading();
                    adapter.setListBean(listBean);
                    adapter.notifyDataSetChanged();
//                    maintenanceLogListAdapter.setDate(listBeen);
//                    maintenanceLogListAdapter.notifyDataSetChanged();
                    break;
                case Constants.ERROR_CODE:
                    break;
            }
            dismissLoading();
        }
    };

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_requisitionList_back:
                finish();
                break;
            case R.id.maintenance_requisitionList_getUpkeepnoticeByUID:
//                loadDate();
                toast("同步");
                break;
        }
    }


    /**
     * 创建复选框对话框
     */
    boolean[] flags=new boolean[]{false,false,false,false};//初始复选情况
    String[] items=null;
    View loglistDialog;
    String bhmc_str="",yhInspNo_str="",jcsjstart_str="", jcsjend_str="",xcry_str="";
    private EditText maintenancelog_tzdbh_ev,
            maintenancelog_wxdw_ev,
            maintenancelog_qfrq_ev,
            maintenancelog_qfrqend_ev,
            maintenancelog_qfr_ev;
    protected void onShowCheckDialog() {
        Logger.e("aaa","1111111111111111");

//        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.NOmengceng_dialog);
        //设置对话框的图标
//                builder.setIcon(R.drawable.header);
        //设置对话框的标题
//        builder.setTitle("查询条件");
        loglistDialog = getLayoutInflater().inflate(R.layout.loglist_dialog, null);

        ((LinearLayout) loglistDialog.findViewById(R.id.maintenancelog_qfrqend_layout)).setVisibility(View.VISIBLE);

        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_tzdbh_tv)).setText("病害名称");
        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_wxdw_tv)).setText("巡查日志单号");
        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_qfrq_tv)).setText("巡查日期开始");
        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_qfrqend_tv)).setText("巡查日期结束");
        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_qfr_tv)).setText("巡查人");



        maintenancelog_tzdbh_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_tzdbh_ev);
        maintenancelog_wxdw_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_wxdw_ev);
        maintenancelog_qfrq_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_qfrq_ev);
        maintenancelog_qfrqend_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_qfrqend_ev);
        maintenancelog_qfr_ev = (EditText) loglistDialog.findViewById(R.id.maintenancelog_qfr_ev);

        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_qfrq_clean)).setVisibility(View.VISIBLE);
        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_qfrq_clean)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maintenancelog_qfrq_ev.setText("");
                jcsjstart_str ="";

            }
        });
        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_qfrqend_clean)).setVisibility(View.VISIBLE);
        ((TextView) loglistDialog.findViewById(R.id.maintenancelog_qfrqend_clean)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maintenancelog_qfrqend_ev.setText("");
                jcsjend_str="";
            }
        });

        maintenancelog_tzdbh_ev.setHint("请填写正确的病害名称");
        maintenancelog_wxdw_ev.setHint("请填写正确的巡查日志单号");
        maintenancelog_qfrq_ev.setHint("点击选择");
        maintenancelog_qfrqend_ev.setHint("点击选择");
        maintenancelog_qfr_ev.setHint("请填写正确的巡查人（可以填写多个）");


        maintenancelog_tzdbh_ev.setText(bhmc_str);
        maintenancelog_wxdw_ev.setText(yhInspNo_str);
        maintenancelog_qfrq_ev.setText(jcsjstart_str);
        maintenancelog_qfrqend_ev.setText(jcsjend_str);
        maintenancelog_qfr_ev.setText(xcry_str);

        maintenancelog_qfrq_ev.setKeyListener(null);
        maintenancelog_qfrq_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = maintenancelog_qfrq_ev.getText().toString();
                int y = 0, m = 0, d = 0;
                Calendar c = DateUtil.strToCalendarLong2(str);
                y =  c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DATE);
                DatePickerDialog dlg = new DatePickerDialog(MaintenanceRequisitionListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        int mYear = year;
                        int mMonth = month + 1;
                        int mDay = day;
                        maintenancelog_qfrq_ev.setText(getTime(mYear, mMonth, mDay));
                    }
                }, y, m , d);
                dlg.setTitle("日期：");
                dlg.show();
            }
        });

        maintenancelog_qfrqend_ev.setKeyListener(null);
        maintenancelog_qfrqend_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = maintenancelog_qfrqend_ev.getText().toString();
                int y = 0, m = 0, d = 0;
                Calendar c = DateUtil.strToCalendarLong2(str);
                y =  c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DATE);
                DatePickerDialog dlg = new DatePickerDialog(MaintenanceRequisitionListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        int mYear = year;
                        int mMonth = month + 1;
                        int mDay = day;
                        maintenancelog_qfrqend_ev.setText(getTime(mYear, mMonth, mDay));
                    }
                }, y, m , d);
                dlg.setTitle("日期：");
                dlg.show();
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(this, R.style.NOmengceng_dialog)
                .setTitle("查询条件")
                .setView(loglistDialog)
                .setPositiveButton(" 查 询 ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        bhmc_str = maintenancelog_tzdbh_ev.getText().toString();
                        yhInspNo_str = maintenancelog_wxdw_ev.getText().toString();
                        jcsjstart_str = maintenancelog_qfrq_ev.getText().toString();
                        jcsjend_str = maintenancelog_qfrqend_ev.getText().toString();
                        xcry_str = maintenancelog_qfr_ev.getText().toString();
                        StringBuffer str = new StringBuffer();
                        if (bhmc_str.length() != 0) {
                            str.append("病害名称：");
                            str.append(bhmc_str);
                        }
                        if (yhInspNo_str.length() != 0) {
                            str.append("巡查日志单号：");
                            str.append(yhInspNo_str);
                        }
                        if (jcsjstart_str.length() != 0) {
                            str.append("巡查日期开始：");
                            str.append(jcsjstart_str);
                        }
                        if (jcsjend_str.length() != 0) {
                            str.append("巡查日期结束：");
                            str.append(jcsjend_str);
                        }
                        if (xcry_str.length() != 0) {
                            str.append("巡查人：");
                            str.append(xcry_str);
                        }
                        maintenance_requisitionList_selectCondition_tv.setText(str);
//                        if (str.length() != 0) {
                            loadDate();
//                        }

                    }
                })
                .show();
//        //创建一个复选框对话框
//        AlertDialog dialog = builder.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 500;
//                params.height = 200 ;

        dialog.getWindow().setAttributes(params);
        dialog.setCanceledOnTouchOutside(true);

    }
// geteCooperationByUID  geteQualityDemandByUID
    public void synchronizationCooperation(){
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                List<CooperationBean> maintenanceDiseaseBeens = JSON.parseArray(result.getString("datas"), CooperationBean.class);

                Logger.e("aaa", "size=="+maintenanceDiseaseBeens.size());

//                maintenanceDiseaseDao.addList(maintenanceDiseaseBeens);

                dismissLoading();
                toast("同步质量要求库成功！");
                if(TextUtil.isListEmpty(maintenanceDiseaseBeens)){
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
    public void synchronizationQualityDemand(){
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                List<QualityDemandBean> qualityDemandBeens = JSON.parseArray(result.getString("datas"), QualityDemandBean.class);

                Logger.e("aaa", "size=="+qualityDemandBeens.size());

//                maintenanceDiseaseDao.addList(maintenanceDiseaseBeens);

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
