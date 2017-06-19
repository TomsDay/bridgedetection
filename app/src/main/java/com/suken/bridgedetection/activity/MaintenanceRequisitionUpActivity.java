package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceRequisitionUpAdapter;
import com.suken.bridgedetection.adapter.MaintenanceTableListAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.UpkeepdiseaseListBean;
import com.suken.bridgedetection.bean.UploadUpkeepnoticeBean;
import com.suken.bridgedetection.bean.UploadUpkeepnoticeDao;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRequisitionUpActivity extends BaseActivity {

    private ListView maintenance_requisition_up_listView;
    private MaintenanceRequisitionUpAdapter mAdapter;
    List<UploadUpkeepnoticeBean> uploadUpkeepnoticeBeens = new ArrayList<UploadUpkeepnoticeBean>();
    private UploadUpkeepnoticeDao uploadUpkeepnoticeDao;
    private Context mContext;
    private LinearLayout update_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_requisition_up);
        mContext = this;
        uploadUpkeepnoticeDao = new UploadUpkeepnoticeDao();
        initView();

    }

    private void initView() {
        update_all = (LinearLayout) findViewById(R.id.update_all);
        maintenance_requisition_up_listView = (ListView) findViewById(R.id.maintenance_requisition_up_listView);
        mAdapter = new MaintenanceRequisitionUpAdapter(mContext);
        mAdapter.setUploadRequisitionData(new MaintenanceRequisitionUpAdapter.UploadRequisitionData() {
            @Override
            public void upload(int position) {
                showLoading("正在上传...");
                uploadData(uploadUpkeepnoticeBeens.get(position), position, false);
            }
        });
        maintenance_requisition_up_listView.setAdapter(mAdapter);
        update_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtil.isListEmpty(uploadUpkeepnoticeBeens)) {
                    toast("当前没有数据可以上传！");
                    return;
                }

                showLoading("正在上传...");
                uploadData(uploadUpkeepnoticeBeens.get(0), 0, true);

//                for(int i = 0;i<maintenanceTableBeanList.size();i++){
//                    MaintenanceTableBean bean = maintenanceTableBeanList.get(i);
//                    uploadIV(bean, i, true);
//                }
            }
        });
        maintenance_requisition_up_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final String[] names = {"编辑", "删除", "取消"};
                AlertDialog dialog = new AlertDialog.Builder(mContext, R.style.NOmengceng_dialog)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                switch (which) {
                                    case 0://编辑
                                        Intent in = new Intent(mContext, MaintenanceRequisitionActivity.class);
                                        in.putExtra("id", uploadUpkeepnoticeBeens.get(position).getId());
                                        startActivity(in);
                                        break;
                                    case 1:
                                        uploadUpkeepnoticeDao.delete(uploadUpkeepnoticeBeens.get(position).getId());
                                        for (int i = 0; i < uploadUpkeepnoticeBeens.get(position).getUpkeepdiseaseList().size(); i++) {
                                            uploadUpkeepnoticeDao.deleteItem(uploadUpkeepnoticeBeens.get(position).getUpkeepdiseaseList().get(i).getId());
                                        }
                                        getAllData();
                                        break;
                                    default:
                                        break;
                                }


                            }
                        })
                        .show();

                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = 500;
//                params.height = 200 ;

                dialog.getWindow().setAttributes(params);


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllData();
    }
    public void getAllData() {
        uploadUpkeepnoticeBeens = uploadUpkeepnoticeDao.queryByuserID(BridgeDetectionApplication.mCurrentUser.getUserId());

        if(!TextUtil.isListEmpty(uploadUpkeepnoticeBeens)){
            for(int i = 0;i<uploadUpkeepnoticeBeens.size();i++){
                UploadUpkeepnoticeBean bean = uploadUpkeepnoticeBeens.get(i);
                ForeignCollection<UpkeepdiseaseListBean> orders = bean.getUpkeepdiseaseListBeen();
                CloseableIterator<UpkeepdiseaseListBean> iterator = orders.closeableIterator();
                List<UpkeepdiseaseListBean> itemBeanList = new ArrayList<UpkeepdiseaseListBean>();
                try {
                    while(iterator.hasNext()){
                        UpkeepdiseaseListBean b = iterator.next();
                        itemBeanList.add(b);
                        Logger.e("aaa",b.toString());
                    }
                } finally {
                    bean.setUpkeepdiseaseList(itemBeanList);
                    uploadUpkeepnoticeBeens.set(i, bean);
                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        Logger.e("aaa", "--------" + uploadUpkeepnoticeBeens.toString());


        mAdapter.setData(uploadUpkeepnoticeBeens);
        mAdapter.notifyDataSetChanged();
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_trequisition_up_back:
                finish();
                break;
        }
    }
    public void uploadData(final UploadUpkeepnoticeBean bean,final int position ,final boolean isAll){

        final Gson gson = new Gson();
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa","111111111111"+ result.toString());
                Logger.e("aaa","position===="+ position);
                uploadUpkeepnoticeDao.delete(bean.getId());
                for(int i = 0;i<bean.getUpkeepdiseaseList().size();i++){
                    uploadUpkeepnoticeDao.deleteItem(bean.getUpkeepdiseaseList().get(i).getId());
                }
                if(isAll){
                    if (position != uploadUpkeepnoticeBeens.size() - 1) {
                        Logger.e("aaa","下一条数据的上传===="+ position);
                        uploadData(uploadUpkeepnoticeBeens.get(position + 1), position + 1, true);

                    }else{
                        Logger.e("aaa","取消加载===="+ position);
                        Message message = new Message();
                        message.what = Constants.SUCCESS_CODE;
                        mHandler.sendMessage(message);
                    }
//
                }else{
                    Message message = new Message();
                    message.what = Constants.SUCCESS_CODE;
                    mHandler.sendMessage(message);
                }




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
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);

                bean.setUpkeepdiseaseListBeen(null);
                for (int i = 0; i < bean.getUpkeepdiseaseList().size(); i++) {

                    bean.getUpkeepdiseaseList().get(i).setUploadUpkeepnoticeBean(null);
                }

                Logger.e("aaa", "gson======" + gson.toJson(bean));

                pair = new BasicNameValuePair("json", gson.toJson(bean));
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.uploadUpkeepnotice).executePost(list);
            }
        });


    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.SUCCESS_CODE:
                    getAllData();
                    dismissLoading();
                    toast("上传日常巡查日志记录信息成功！");

                    break;
                case Constants.ERROR_CODE:
                    getAllData();
                    dismissLoading();
                    toast("上传失败！");
                    break;
            }
        }
    };
}
