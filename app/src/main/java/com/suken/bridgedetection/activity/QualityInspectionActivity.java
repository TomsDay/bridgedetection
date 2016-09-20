package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.ProjectAcceptanceListAdapter;
import com.suken.bridgedetection.adapter.QualityInspectionExpandableListAdapter;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.DeviceInfoUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


/**
 * 质量抽检页面
 */
public class QualityInspectionActivity extends BaseActivity implements OnLocationFinishedListener {
    private Context mContext;

    private TextView gps_text;

    private EditText qualityInspection_gydw_ev,
            qualityInspection_cjr_ev,
            qualityInspection_data_ev,
            qualityInspection_gqzr_ev,
            qualityInspection_yhkz_ev;

    private Button qualityInspection_addBon_btn;

    private ExpandableListView qualityInspection_expandableListView;

    private QualityInspectionExpandableListAdapter mAdapter;


    private List<ProjacceptBean> projacceptBeens = new ArrayList<ProjacceptBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_quality_inspection);
        initView();
    }
    public void initView(){

        qualityInspection_expandableListView = (ExpandableListView) findViewById(R.id.qualityInspection_expandableListView);

        View view = getLayoutInflater().inflate(R.layout.hv_quality_inspection, null);

        gps_text = (TextView) view.findViewById(R.id.gps_text);

        qualityInspection_gydw_ev = (EditText) view.findViewById(R.id.qualityInspection_gydw_ev);
        qualityInspection_cjr_ev = (EditText) view.findViewById(R.id.qualityInspection_cjr_ev);
        qualityInspection_data_ev = (EditText) view.findViewById(R.id.qualityInspection_data_ev);
        qualityInspection_gqzr_ev = (EditText) view.findViewById(R.id.qualityInspection_gqzr_ev);
        qualityInspection_yhkz_ev = (EditText) view.findViewById(R.id.qualityInspection_yhkz_ev);

        qualityInspection_addBon_btn = (Button) view.findViewById(R.id.qualityInspection_addBon_btn);
        qualityInspection_addBon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getYanShouData();
            }
        });

        mAdapter = new QualityInspectionExpandableListAdapter(QualityInspectionActivity.this);
        qualityInspection_expandableListView.addHeaderView(view);
        qualityInspection_expandableListView.setAdapter(mAdapter);



    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.qualityInspection_back:
                back();
                break;
            case R.id.qualityInspection_save:
                saveDialog();
                break;
        }

    }
    public void back() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提醒");
        builder.setMessage("返回将丢失当前未保存信息");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(!mIsGpsSuccess){
                            Toast.makeText(mContext, "正在定位...\n" +
                                    "请您到空旷的地点从新定位，绝就不要在室内", Toast.LENGTH_SHORT).show();
                            return;
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
    boolean mIsGpsSuccess;
    double latitude, longitude;
    @Override
    public void onLocationFinished(LocationResult result) {

        if(this == null || ((BaseActivity)this).isDestroyed() || this.isFinishing()){
            return;
        }

        if (result.isSuccess) {
            mIsGpsSuccess = true;
            Logger.e("aaa","经度:" + result.longitude);
            Logger.e("aaa","纬度:" + result.latitude);
            latitude =  result.latitude;
            longitude =  result.longitude;

            Toast.makeText(this, "定位成功 经度:" + result.longitude+",纬度:" + result.latitude, Toast.LENGTH_SHORT).show();
//            mjingdu.setText("经度:" + result.latitude);
//            mWeidu.setText("纬度:" + result.longitude);
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
            gps_text.setText("定位成功");
            gps_text.setTextColor(Color.WHITE);
        } else if(!mIsGpsSuccess){
            Toast.makeText(this, "定位失败！请您到空旷的地点从新定位，绝就不要在室内！", Toast.LENGTH_LONG).show();
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
            gps_text.setText("定位失败，点击从新定位");
            gps_text.setTextColor(Color.RED);
        }
    }
    public final int SUCCESS_CODE = 0;
    public final int ERROR_CODE = 1;
    private void getYanShouData(){

        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                projacceptListDialogBeens = JSON.parseArray(result.getString("datas"), ProjacceptBean.class);

                Logger.e("aaa", projacceptListDialogBeens.toString());
                Message message = new Message();
                message.what = SUCCESS_CODE;
                mHandler.sendMessage(message);



            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "===(" + resultCode + ")");
                Logger.e("aaa", "type===" + type);
                Message message = new Message();
                message.what = ERROR_CODE;
                mHandler.sendMessage(message);
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                showLoading("正在同步验收单...");
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.getProjacceptByUID).executePost(list);


            }
        });


    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_CODE:
                    dismissLoading();
                    toast("获取保养工程验收记录成功！");
                    showListDialog();
                    break;
                case ERROR_CODE:
                    toast("获取保养工程验收记录失败！");
                    dismissLoading();
                    break;
            }
        }
    };

    AlertDialog listDialog;
    private List<ProjacceptBean> projacceptListDialogBeens = new ArrayList<ProjacceptBean>();
    public void showListDialog(){
        View dialogView = getLayoutInflater().inflate(R.layout.layout_project_acceptance, null);
        dialogView.setBackgroundColor(getResources().getColor(R.color.gray));
        LinearLayout project_acceptance_rowLayout = (LinearLayout) dialogView.findViewById(R.id.project_acceptance_rowLayout);
        project_acceptance_rowLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        ListView dialogListView = (ListView) dialogView.findViewById(R.id.project_acceptance_listView);
        ProjectAcceptanceListAdapter projectAcceptanceListAdapter = new ProjectAcceptanceListAdapter(mContext);
        projectAcceptanceListAdapter.setData(projacceptListDialogBeens);
        dialogListView.setAdapter(projectAcceptanceListAdapter);

        listDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .show();

        WindowManager.LayoutParams params = listDialog.getWindow().getAttributes();
        params.width = DeviceInfoUtil.getScreenHeight(mContext) - 200;
//                params.height = 200 ;

        listDialog.getWindow().setAttributes(params);
        listDialog.setCanceledOnTouchOutside(true);

        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//响应listview中的item的点击事件

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position,
                                    long arg3) {
                // TODO Auto-generated method stub

                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("是否选择表单编号为" + projacceptListDialogBeens.get(position).getBno() + "的数据？")
                        .setPositiveButton("确定", new DatePickerDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                thisSynchMaintenlogBeens = new ArrayList<>();
                                projacceptBeens.add(projacceptListDialogBeens.get(position));
                                Logger.e("aaa","==="+projacceptBeens.toString());
                                mAdapter.setData(projacceptBeens);
                                mAdapter.notifyDataSetChanged();
//                                if(!TextUtil.isListEmpty(thisSynchMaintenlogBeens)){
//                                    mAdapter.setData(thisSynchMaintenlogBeens.get(0).getMaintenlogDetailList());
//                                    mAdapter.notifyDataSetChanged();
//                                }
//
//                                if(maintenanceoforder_content_ev.getVisibility() == View.VISIBLE){
//                                    maintenanceoforder_content_ev.setVisibility(View.GONE);
//                                    maintenanceoforder_content_Listlayout.setVisibility(View.VISIBLE);
//                                }
                                listDialog.dismiss();

                            }
                        })
                        .setNegativeButton("取消", new DatePickerDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }


}
