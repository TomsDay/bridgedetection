package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.ProjectAcceptanceListUpLoadAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;
import com.suken.bridgedetection.bean.UploadFileBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjectAcceptanceListUpLoadActivity extends BaseActivity {
    private Context mContext;
    private ListView project_acceptanceUpload_listView;
    private LinearLayout update_all;
    private List<ProjectAcceptanceBean> projectAcceptanceBeen = new ArrayList<ProjectAcceptanceBean>();
    private ProjectAcceptanceListUpLoadAdapter mAdapter;
    private ProjectAcceptanceDao projectAcceptanceDao;
    private IVDescDao ivDescDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_acceptance_list_up_load);
        mContext = this;
        projectAcceptanceDao = new ProjectAcceptanceDao();
        ivDescDao = new IVDescDao();

        initView();
    }

    private void initView() {
        project_acceptanceUpload_listView = (ListView) findViewById(R.id.project_acceptanceUpload_listView);
        update_all = (LinearLayout) findViewById(R.id.update_all);
        mAdapter = new ProjectAcceptanceListUpLoadAdapter(mContext);
        project_acceptanceUpload_listView.setAdapter(mAdapter);

        project_acceptanceUpload_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,final int position, long l) {
                final String[] names = {"编辑", "上传","删除", "取消"};
                new AlertDialog.Builder(mContext)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                switch (which) {
                                    case 0://编辑
                                        Intent in = new Intent(mContext, ProjectAcceptanceActivity.class);
                                        in.putExtra("id", projectAcceptanceBeen.get(position).getId());
                                        startActivity(in);
                                        break;
                                    case 1://上传
                                        ProjectAcceptanceBean bean = projectAcceptanceBeen.get(position);
                                        uploadData(bean, position, false);
                                        showLoading("正在上传...");
                                        break;
                                    case 2:
                                        projectAcceptanceDao.delete(projectAcceptanceBeen.get(position).getId());

                                        getAllData();
                                        break;
                                    default:
                                        break;
                                }


                            }
                        })
                        .show();
            }
        });

        update_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (projectAcceptanceBeen.size() == 0) {
                    toast("当前没有数据可以上传！");
                    return;
                }

                showLoading("正在上传...");
                uploadData(projectAcceptanceBeen.get(0), 0, true);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllData();
    }

    public void getAllData(){
        projectAcceptanceBeen = projectAcceptanceDao.queryAll();
        mAdapter.setData(projectAcceptanceBeen);
        mAdapter.notifyDataSetChanged();

    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.project_acceptanceUpload_back:
                finish();
                break;
        }
    }
    public void uploadData(final ProjectAcceptanceBean bean, final int position , final boolean isAll){

        final Gson gson = new Gson();
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa","111111111111"+ result.toString());
                Logger.e("aaa","position===="+ position);
                projectAcceptanceDao.delete(bean.getId());
                if(isAll){
                    if (position != projectAcceptanceBeen.size() - 1) {
                        Logger.e("aaa","下一条数据的上传===="+ position);
                        uploadData(projectAcceptanceBeen.get(position + 1), position + 1, true);

                    }else{
                        Logger.e("aaa","取消加载===="+ position);
                        Message message = new Message();
                        message.what = SUCCESS_CODE;
                        mHandler.sendMessage(message);
                    }
//
                }else{
                    Message message = new Message();
                    message.what = SUCCESS_CODE;
                    mHandler.sendMessage(message);
                }




            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
                Message message = new Message();
                message.what = ERROR_CODE;
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
                Logger.e("aaa", "gson======" + gson.toJson(bean));
                pair = new BasicNameValuePair("json", gson.toJson(bean));
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.uploadProjaccept).executePost(list);
            }
        });


    }

    public final int SUCCESS_CODE = 0;
    public final int ERROR_CODE = 1;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_CODE:
                    getAllData();
                    dismissLoading();


                    break;
                case ERROR_CODE:
                    break;
            }
        }
    };

//    public void uploadIV(final MaintenanceTableBean bean,final int position,final boolean isAll){
//
//        final OnReceivedHttpResponseListener listener = new OnReceivedHttpResponseListener() {
//            //
//            @Override
//            public void onRequestSuccess(RequestType type, JSONObject obj) {
////                Logger.e("aaa","obj====="+obj.toString());
//                Logger.e("aaa","type====="+type.toString());
//                Logger.e("aaa","obj====="+obj.toString());
//                com.alibaba.fastjson.JSONArray array = obj.getJSONArray("datas");
//                Gson gson = new Gson();
//                List<UploadFileBean>  mImages = new ArrayList<UploadFileBean>();
//                List<UploadFileBean> mVideos= new ArrayList<UploadFileBean>();;
//                for(int i = 0;i<array.size();i++){
//
//                    String s = array.getString(i);
//                    Logger.e("aaa","UploadFileBean====="+s);
//                    UploadFileBean uploadFileBean = gson.fromJson(s, UploadFileBean.class);
//                    if (uploadFileBean.getFileName().indexOf("pic-") != -1) {
//                        mImages.add(uploadFileBean);
//                    }else{
//                        mVideos.add(uploadFileBean);
//                    }
//                }
//                int typePosition = type.getTypePosition();
//                List<IVDesc> images = tableItemBean.getmImages();
//                List<IVDesc> videos = tableItemBean.getmVideo();
//                for(int i = 0;i<images.size();i++){
//                    ivDescDao.delete(images.get(i).getId());
//                }
//                for(int i = 0;i<videos.size();i++){
//                    ivDescDao.delete(videos.get(i).getId());
//                }
//                StringBuffer imageSB = new StringBuffer();
//                StringBuffer videoSB = new StringBuffer();
//                for(int i = 0;i<mImages.size();i++){
//                    Logger.e("aaa","mImages.get(i).getFileId())====="+mImages.get(i).getFileId());
//                    imageSB.append(mImages.get(i).getFileId());
//                    if (i != mImages.size() - 1) {
//                        imageSB.append(",");
//                    }
//                }
//
//                for(int i = 0;i<mVideos.size();i++){
//                    Logger.e("aaa","mVideos.get(i).getFileId())====="+mVideos.get(i).getFileId());
//                    videoSB.append(mVideos.get(i).getFileId());
//                    if (i != mVideos.size() - 1) {
//                        videoSB.append(",");
//                    }
//                }
//                Logger.e("aaa","imageSB====="+imageSB.toString());
//                Logger.e("aaa","mVideos====="+videoSB.toString());
//
//                String date = sDateFormat.format(new java.util.Date());
//
//
//
//                boolean isUpload = true;
//                for(int i = 0;i<itemBeen.size();i++){
//                    Logger.e("aaa","itemBeen====="+i);
//                    MaintenanceTableItemBean thisTableItemBean = itemBeen.get(i);
//                    String tjsj = thisTableItemBean.getTjsj();
//                    if (tjsj == null || tjsj.length() == 0) {
//                        isUpload = false;
//                        break;
//                    }
//                }
//                if(isUpload){
//                        bean.setMaintenanceTableItemBeen(null);
//                    }
//                    bean.setTjsj(date);
//
//                    uploadData(bean,position,isAll);
////                    Logger.e("aaa","bean====="+bean.toString());
////                    Logger.e("aaa","gson.toJson(data)====="+gson.toJson(bean));
//
//                }
//
//
//            }
//
//            @Override
//            public void onRequestFail(RequestType type, String resultCode, String result) {
//                Logger.e("aaa","type====="+type.toString());
//                Logger.e("aaa","resultCode====="+resultCode);
//                Logger.e("aaa","result====="+result);
//
//            }
//        };
//        BackgroundExecutor.execute(new Runnable() {
//
//            @Override
//            public void run() {
//                List<NameValuePair> list = new ArrayList<NameValuePair>();
//                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
//                list.add(pair);
//                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
//                list.add(pair);
//                for (int j = 0; j < itemBeen.size(); j++) {
//                    MaintenanceTableItemBean tableItemBean = itemBeen.get(j);
//                    List<IVDesc> images = tableItemBean.getmImages();
//                    List<IVDesc> videos = tableItemBean.getmVideo();
//
//                    Logger.e("aaa","images===="+images.toString());
//                    Logger.e("aaa","videos===="+videos.toString());
//
//                    if ((images == null && videos == null)||(images.size() ==0&&videos.size()==0)) {
//                        Logger.e("aaa","无图！！！");
//                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                        String date = sDateFormat.format(new java.util.Date());
//                        boolean isUpload = true;
//                        for (int i = 0; i < itemBeen.size(); i++) {
//                            Logger.e("aaa", "itemBeen=====" + i);
//                            MaintenanceTableItemBean thisTableItemBean = itemBeen.get(i);
//                            String tjsj = thisTableItemBean.getTjsj();
//                            if (tjsj == null || tjsj.length() == 0) {
//                                isUpload = false;
//                                break;
//                            }
//                        }
//                        if (isUpload) {
//                            }
//                            bean.setTjsj(date);
//                            uploadData(bean,position,isAll);
////                    Logger.e("aaa","bean====="+bean.toString());
////                    Logger.e("aaa","gson.toJson(data)====="+gson.toJson(bean));
//                            return;
//                        }
//                        continue;
//                    }
//
//                    String[] pics = new String[images.size()];
//                    for (int i = 0; i < images.size(); i++) {
//                        pics[i] = images.get(i).getPath();
//                    }
//                    String[] vdos = new String[videos.size()];
//                    for (int i = 0; i < videos.size(); i++) {
//                        vdos[i] = videos.get(i).getPath();
//                    }
//
//
//                    String[] attaches = concat(pics, vdos);
//                    for (int i = 0; i < attaches.length; i++) {
//                        Logger.e("aaa", "i====================" + attaches[i]);
//                    }
//                    if (attaches.length > 0) {
//                        new HttpTask(listener, RequestType.uploadFile).uploadFile(list, j,attaches);
//                    }
//                }
//            }
//        });
//    }


    public static String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isLoadingDialogShow()){
                dismissLoading();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
