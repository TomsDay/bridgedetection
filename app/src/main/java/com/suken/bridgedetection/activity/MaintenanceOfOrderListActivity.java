package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceOfOrderListAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderDao;
import com.suken.bridgedetection.bean.MaintenanceOfOrderItemBean;
import com.suken.bridgedetection.bean.SynchMaintenlogBean;
import com.suken.bridgedetection.bean.UploadFileBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceOfOrderListActivity extends BaseActivity {
    private ListView maintenanceoforderlist_table_listView;
    private Context mContext;
    private MaintenanceOfOrderListAdapter mAdapter;
    private List<MaintenanceOfOrderBean> maintenanceOfOrderBeen = new ArrayList<MaintenanceOfOrderBean>();
    private MaintenanceOfOrderDao maintenanceOfOrderDao;
    private IVDescDao ivDescDao;
    private LinearLayout update_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_of_order_list);
        mContext = this;
        maintenanceOfOrderDao = new MaintenanceOfOrderDao();
        ivDescDao = new IVDescDao();
        initView();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getAllData();
    }

    private void initView() {
        maintenanceoforderlist_table_listView = (ListView) findViewById(R.id.maintenanceoforderlist_table_listView);
        update_all = (LinearLayout) findViewById(R.id.update_all);
        mAdapter = new MaintenanceOfOrderListAdapter(mContext, new MaintenanceOfOrderListAdapter.UpLoadOnceOforderData() {
            @Override
            public void loadData(int position) {
                MaintenanceOfOrderBean bean = maintenanceOfOrderBeen.get(position);
                uploadIV(bean, position, false);
                showLoading("正在上传...");
            }
        });
        mAdapter.setDate(maintenanceOfOrderBeen);
        maintenanceoforderlist_table_listView.setAdapter(mAdapter);

        maintenanceoforderlist_table_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                final String[] names = {"编辑","删除", "取消"};
                AlertDialog dialog=new AlertDialog.Builder(mContext,R.style.NOmengceng_dialog)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                switch (which) {
                                    case 0://编辑
                                        Intent in = new Intent(mContext, MaintenanceOfOrderActivity.class);
                                        in.putExtra("id", maintenanceOfOrderBeen.get(position).getId());
                                        startActivity(in);
                                        break;
//                                    case 1://上传
//                                        MaintenanceOfOrderBean bean = maintenanceOfOrderBeen.get(position);
//                                        uploadData(bean, position, false);
//                                        showLoading("正在上传...");
//                                        break;
                                    case 1:
                                        maintenanceOfOrderDao.delete(maintenanceOfOrderBeen.get(position).getId());
                                        for(int i = 0; i<maintenanceOfOrderBeen.get(position).getSafetycheckdetailList().size(); i++){
                                            maintenanceOfOrderDao.deleteItem(maintenanceOfOrderBeen.get(position).getSafetycheckdetailList().get(i).getId());
//                                            for(int q = 0; q<maintenanceOfOrderBeen.get(position).getSafetycheckdetailList().get(i).getmImages().size(); q++){
//                                                ivDescDao.delete(maintenanceOfOrderBeen.get(position).getSafetycheckdetailList().get(q).getmImages().get(q).getId());
//                                            }
//                                            for(int q = 0; q<maintenanceOfOrderBeen.get(position).getSafetycheckdetailList().get(i).getmVideo().size(); q++){
//                                                ivDescDao.delete(maintenanceOfOrderBeen.get(position).getSafetycheckdetailList().get(q).getmVideo().get(q).getId());
//                                            }
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
        update_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maintenanceOfOrderBeen.size() == 0) {
                    toast("当前没有数据可以上传！");
                    return;
                }

                showLoading("正在上传...");
                uploadIV(maintenanceOfOrderBeen.get(0), 0, true);
            }
        });

//        getAllData();
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenanceoforderlist_table_back:
                finish();
                break;
        }
    }


    public void getAllData(){
        maintenanceOfOrderBeen = maintenanceOfOrderDao.queryAll();
        if(maintenanceOfOrderBeen.size()>0){
            for(int i = 0;i<maintenanceOfOrderBeen.size();i++){
                MaintenanceOfOrderBean bean = maintenanceOfOrderBeen.get(i);
                String xcrz = bean.getXcnr();
                List<SynchMaintenlogBean> synchMaintenlogBeans = JSON.parseArray(xcrz, SynchMaintenlogBean.class);
                bean.setProjacceptDetailList(synchMaintenlogBeans);
                bean.setXcnr("");
                if(!TextUtil.isListEmpty(synchMaintenlogBeans)){
                    bean.setSgdwmc(synchMaintenlogBeans.get(0).getWxbmmc());
                }
                Logger.e("aaa","++++++++++======="+ synchMaintenlogBeans.toString());
                ForeignCollection<MaintenanceOfOrderItemBean> orders = bean.getMaintenanceOfOrderItemBeen();
                CloseableIterator<MaintenanceOfOrderItemBean> iterator = orders.closeableIterator();
                List<MaintenanceOfOrderItemBean> itemBeanList = new ArrayList<MaintenanceOfOrderItemBean>();
                try {
                    while(iterator.hasNext()){
                        MaintenanceOfOrderItemBean b = iterator.next();

                        List<IVDesc> imageDesc = ivDescDao.getImageMaintenanceOfOrderItemBeanByUserId(b.getId());
                        b.setmImages(imageDesc);

                        List<IVDesc> videoDesc = ivDescDao.getVideoMaintenanceOfOrderItemBeanByUserId(b.getId());
                        b.setmVideo(videoDesc);

                        itemBeanList.add(b);
                        Logger.e("aaa",b.toString());
                    }
                } finally {
                    bean.setSafetycheckdetailList(itemBeanList);
                    maintenanceOfOrderBeen.set(i, bean);
                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        mAdapter.setDate(maintenanceOfOrderBeen);
        mAdapter.notifyDataSetChanged();

    }


    public void uploadData(final MaintenanceOfOrderBean bean,final int position ,final boolean isAll){

        final Gson gson = new Gson();
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa","111111111111"+ result.toString());
                Logger.e("aaa","position===="+ position);
                maintenanceOfOrderDao.delete(bean.getId());
                for(int i = 0; i<bean.getSafetycheckdetailList().size(); i++){
                    maintenanceOfOrderDao.deleteItem(bean.getSafetycheckdetailList().get(i).getId());
                }
                if(isAll){
                    if (position != maintenanceOfOrderBeen.size() - 1) {
                        Logger.e("aaa","下一条数据的上传===="+ position);
                        uploadData(maintenanceOfOrderBeen.get(position + 1), position + 1, true);

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
                for(int i = 0; i<bean.getSafetycheckdetailList().size(); i++){
                    bean.getSafetycheckdetailList().get(i).setmImages(null);
                    bean.getSafetycheckdetailList().get(i).setmVideo(null);
                    bean.getSafetycheckdetailList().get(i).setMaintenanceOfOrderBean(null);
                    bean.getSafetycheckdetailList().get(i).setiDescs(null);
                    bean.getSafetycheckdetailList().get(i).setvDescs(null);
                    bean.setMaintenanceOfOrderItemBeen(null);
                    }
                StringBuffer StringID = new StringBuffer();
                StringBuffer StringBNO = new StringBuffer();
                int proSice = bean.getProjacceptDetailList().size();
                for (int i = 0; i < proSice; i++) {
                    SynchMaintenlogBean synchMaintenlogBean = bean.getProjacceptDetailList().get(i);
                    StringID.append(synchMaintenlogBean.getId());
                    StringBNO.append(synchMaintenlogBean.getBno());
                    if (i != proSice - 1) {
                        StringID.append(",");
                        StringBNO.append(",");
                    }

                }
                Logger.e("aaa","StringID.toString()====="+StringID.toString());
                Logger.e("aaa","StringBNO.toString()====="+StringBNO.toString());
                bean.setYhrzid(StringID.toString());
                bean.setYhrzbno(StringBNO.toString());
                bean.setProjacceptDetailList(null);
                Logger.e("aaa", "gson======" + gson.toJson(bean));
                pair = new BasicNameValuePair("json", gson.toJson(bean));
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.uploadSafetycheck).executePost(list);
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
                    toast("上传施工安全检查记录信息成功！");

                    break;
                case ERROR_CODE:
                    getAllData();
                    dismissLoading();
                    toast("上传失败！");
                    break;
            }
        }
    };

    public void uploadIV(final MaintenanceOfOrderBean bean,final int position,final boolean isAll){

        final OnReceivedHttpResponseListener listener = new OnReceivedHttpResponseListener() {
            //
            @Override
            public void onRequestSuccess(RequestType type, JSONObject obj) {
//                Logger.e("aaa","obj====="+obj.toString());
                Logger.e("aaa","type====="+type.toString());
                Logger.e("aaa","obj====="+obj.toString());
                com.alibaba.fastjson.JSONArray array = obj.getJSONArray("datas");
                Gson gson = new Gson();
                List<UploadFileBean>  mImages = new ArrayList<UploadFileBean>();
                List<UploadFileBean> mVideos= new ArrayList<UploadFileBean>();;
                for(int i = 0;i<array.size();i++){

                    String s = array.getString(i);
                    Logger.e("aaa","UploadFileBean====="+s);
                    UploadFileBean uploadFileBean = gson.fromJson(s, UploadFileBean.class);
                    if (uploadFileBean.getFileName().indexOf("pic-") != -1) {
                        mImages.add(uploadFileBean);
                    }else{
                        mVideos.add(uploadFileBean);
                    }
                }
                int typePosition = type.getTypePosition();
                MaintenanceOfOrderItemBean ofOrderItemBean = bean.getSafetycheckdetailList().get(typePosition);
                List<IVDesc> images = ofOrderItemBean.getmImages();
                List<IVDesc> videos = ofOrderItemBean.getmVideo();
                for(int i = 0;i<images.size();i++){
                    ivDescDao.delete(images.get(i).getId());
                }
                for(int i = 0;i<videos.size();i++){
                    ivDescDao.delete(videos.get(i).getId());
                }
                StringBuffer imageSB = new StringBuffer();
                StringBuffer videoSB = new StringBuffer();
                for(int i = 0;i<mImages.size();i++){
                    Logger.e("aaa","mImages.get(i).getFileId())====="+mImages.get(i).getFileId());
                    imageSB.append(mImages.get(i).getFileId());
                    if (i != mImages.size() - 1) {
                        imageSB.append(",");
                    }
                }

                for(int i = 0;i<mVideos.size();i++){
                    Logger.e("aaa","mVideos.get(i).getFileId())====="+mVideos.get(i).getFileId());
                    videoSB.append(mVideos.get(i).getFileId());
                    if (i != mVideos.size() - 1) {
                        videoSB.append(",");
                    }
                }
                Logger.e("aaa","imageSB====="+imageSB.toString());
                Logger.e("aaa","mVideos====="+videoSB.toString());

                bean.getSafetycheckdetailList().get(typePosition).setPicattachment(imageSB.toString());
                bean.getSafetycheckdetailList().get(typePosition).setVidattachment(videoSB.toString());
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date = sDateFormat.format(new java.util.Date());
                bean.getSafetycheckdetailList().get(typePosition).setType(1);



                boolean isUpload = true;
                List<MaintenanceOfOrderItemBean> itemBeen = bean.getSafetycheckdetailList();
                for(int i = 0;i<itemBeen.size();i++){
                    Logger.e("aaa","itemBeen====="+i);
                    MaintenanceOfOrderItemBean thisTableItemBean = itemBeen.get(i);
                    int getType = thisTableItemBean.getType();
                    if (getType == 0) {

                        isUpload = false;
                        break;
                    }
                }
                if(isUpload){
                    for(int i = 0; i<bean.getSafetycheckdetailList().size(); i++){
                        bean.getSafetycheckdetailList().get(i).setmImages(null);
                        bean.getSafetycheckdetailList().get(i).setmVideo(null);
                        bean.getSafetycheckdetailList().get(i).setMaintenanceOfOrderBean(null);
                        bean.getSafetycheckdetailList().get(i).setiDescs(null);
                        bean.getSafetycheckdetailList().get(i).setvDescs(null);
                        bean.setMaintenanceOfOrderItemBeen(null);
                    }
//                    bean.setTjsj(date);

                    uploadData(bean,position,isAll);
//                    Logger.e("aaa","bean====="+bean.toString());
//                    Logger.e("aaa","gson.toJson(data)====="+gson.toJson(bean));

                }


            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa","type====="+type.toString());
                Logger.e("aaa","resultCode====="+resultCode);
                Logger.e("aaa","result====="+result);

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

                List<MaintenanceOfOrderItemBean> itemBeen = bean.getSafetycheckdetailList();
               Logger.e("aaa","itemBeen.size()====="+itemBeen.size());
                for (int j = 0; j < itemBeen.size(); j++) {
                    MaintenanceOfOrderItemBean ofOrderItemBean = itemBeen.get(j);
                    List<IVDesc> images = ofOrderItemBean.getmImages();
                    List<IVDesc> videos = ofOrderItemBean.getmVideo();

                    Logger.e("aaa","images===="+images.toString());
                    Logger.e("aaa","videos===="+videos.toString());

                    if ((images == null && videos == null)||(images.size() ==0&&videos.size()==0)) {
                        Logger.e("aaa","无图！！！");
//                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                        String date = sDateFormat.format(new java.util.Date());
                        bean.getSafetycheckdetailList().get(j).setType(1);
                        boolean isUpload = true;
                        for (int i = 0; i < itemBeen.size(); i++) {
                            Logger.e("aaa", "itemBeen=====" + i);
                            MaintenanceOfOrderItemBean thisOfOrderItemBean = itemBeen.get(i);
                            int getType = thisOfOrderItemBean.getType();
                            if (getType == 0) {

                                isUpload = false;
                                break;
                            }
                        }
                        if (isUpload) {
                            for (int i = 0; i < bean.getSafetycheckdetailList().size(); i++) {
                                bean.getSafetycheckdetailList().get(i).setmImages(null);
                                bean.getSafetycheckdetailList().get(i).setmVideo(null);
                                bean.getSafetycheckdetailList().get(i).setMaintenanceOfOrderBean(null);
                                bean.getSafetycheckdetailList().get(i).setiDescs(null);
                                bean.getSafetycheckdetailList().get(i).setvDescs(null);
                                bean.setMaintenanceOfOrderItemBeen(null);
                            }
//                            bean.setTjsj(date);
                            uploadData(bean,position,isAll);
//                    Logger.e("aaa","bean====="+bean.toString());
//                    Logger.e("aaa","gson.toJson(data)====="+gson.toJson(bean));
                            return;
                        }
                        continue;
                    }

                    String[] pics = new String[images.size()];
                    for (int i = 0; i < images.size(); i++) {
                        pics[i] = images.get(i).getPath();
                    }
                    String[] vdos = new String[videos.size()];
                    for (int i = 0; i < videos.size(); i++) {
                        vdos[i] = videos.get(i).getPath();
                    }


                    String[] attaches = concat(pics, vdos);
                    for (int i = 0; i < attaches.length; i++) {
                        Logger.e("aaa", "i====================" + attaches[i]);
                    }
                    if (attaches.length > 0) {
                        new HttpTask(listener, RequestType.uploadFile).uploadFile(list, j,attaches);
                    }
                }
            }
        });
    }


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
