package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.MaintenanceTableListAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.UploadBean;
import com.suken.bridgedetection.bean.UploadFileBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 高速公路维修保养日志(列表)
 */

public class MaintenanceTableListActivity extends BaseActivity {
    private ListView maintenance_table_listView;
    private MaintenanceTableListAdapter mAdapter;
    List<MaintenanceTableBean> maintenanceTableBeanList = new ArrayList<MaintenanceTableBean>();
    private MaintenanceTableDao maintenanceTableDao;
    private IVDescDao ivDescDao;
    private Context mContext;
    private LinearLayout update_all;



    private TextView upload_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_table_list);
        mContext = MaintenanceTableListActivity.this;
        maintenanceTableDao = new MaintenanceTableDao();
        ivDescDao = new IVDescDao();
        initView();
    }

    private void initView() {

        maintenance_table_listView = (ListView) findViewById(R.id.maintenance_table_listView);
        update_all = (LinearLayout) findViewById(R.id.update_all);
        maintenanceTableBeanList= maintenanceTableDao.queryAll();
        mAdapter = new MaintenanceTableListAdapter(MaintenanceTableListActivity.this);
        mAdapter.setData(maintenanceTableBeanList);
        maintenance_table_listView.setAdapter(mAdapter);
        maintenance_table_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                final String[] names = {"编辑", "上传", "取消"};
                new AlertDialog.Builder(mContext)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                switch (which) {
                                    case 0://编辑
                                        Intent in = new Intent(mContext, MaintenanceTableActivity.class);
                                        in.putExtra("id", maintenanceTableBeanList.get(position).getId());
                                        startActivity(in);
                                        break;
                                    case 1://上传
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
                getAllData();
                for(int i = 0;i<maintenanceTableBeanList.size();i++){

                }
            }
        });

    }

    public void getAllData(){
        if(maintenanceTableBeanList.size()>0){
            for(int i = 0;i<maintenanceTableBeanList.size();i++){
                MaintenanceTableBean bean = maintenanceTableBeanList.get(i);
                ForeignCollection<MaintenanceTableItemBean> orders = bean.getMaintenanceTableItemBeen();
                CloseableIterator<MaintenanceTableItemBean> iterator = orders.closeableIterator();
                List<MaintenanceTableItemBean> itemBeanList = new ArrayList<MaintenanceTableItemBean>();
                try {
                    while(iterator.hasNext()){
                        MaintenanceTableItemBean b = iterator.next();

                        List<IVDesc> imageDesc = ivDescDao.getImageMaintenanceTableItemBeanByUserId(b.getId());
                        b.setmImages(imageDesc);

                        List<IVDesc> videoDesc = ivDescDao.getVideoMaintenanceTableItemBeanByUserId(b.getId());
                        b.setmVideo(videoDesc);

                        itemBeanList.add(b);
                        Logger.e("aaa",b.toString());
                    }
                } finally {
                    bean.setInspectLogDetailList(itemBeanList);
                    maintenanceTableBeanList.set(i, bean);
                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.maintenance_table_back:
                finish();
                break;
        }
    }
    public void uploadData(List<NameValuePair> list,final MaintenanceTableBean bean, final List<IVDesc> images, final List<IVDesc> videos){
        final UploadBean uploadBean = new UploadBean();

        final Gson gson = new Gson();
         OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
//
                Logger.e("aaa","111111111111"+ result.toString());
                maintenanceTableDao.delete(bean.getId());
                for(int i = 0;i<bean.getInspectLogDetailList().size();i++){
                    maintenanceTableDao.deleteItem(bean.getInspectLogDetailList().get(i).getId());
                }


            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
            }
        };



        Logger.e("aaa","gson======"+gson.toJson(uploadBean));
        BasicNameValuePair pair = new BasicNameValuePair("json", gson.toJson(bean));
        list.add(pair);

        new HttpTask(onReceivedHttpResponseListener, RequestType.uploadInspectlog).executePost(list);

    }

    List<UploadFileBean> mImages ;
    List<UploadFileBean> mVideos;;
    public void uploadIV(){

        final OnReceivedHttpResponseListener listener = new OnReceivedHttpResponseListener() {
            //
            @Override
            public void onRequestSuccess(RequestType type, JSONObject obj) {
//                Logger.e("aaa","obj====="+obj.toString());
                Logger.e("aaa","type====="+type.toString());
                com.alibaba.fastjson.JSONArray array = obj.getJSONArray("datas");
                Gson gson = new Gson();
                mImages = new ArrayList<UploadFileBean>();
                mVideos= new ArrayList<UploadFileBean>();;
                for(int i = 0;i<array.size();i++){
                    String s = array.getString(i);
                    UploadFileBean uploadFileBean = gson.fromJson(s, UploadFileBean.class);
                    if (uploadFileBean.getFileName().indexOf("pic-") != -1) {
                        mImages.add(uploadFileBean);
                    }else{
                        mVideos.add(uploadFileBean);
                    }
                }




                for(int i = 0;i<images.size();i++){
                    maintenanceTableDao.deleteItem(images.get(i).getId());
                }
                for(int i = 0;i<videos.size();i++){
                    maintenanceTableDao.deleteItem(videos.get(i).getId());
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
                pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);



                String[] pics = new String[images.size()];
                for (int i = 0; i < images.size(); i++) {
                    pics[i] = images.get(i).getPath();
                }
                String[] vdos = new String[videos.size()];
                for (int i = 0; i < videos.size(); i++) {
                    vdos[i] = videos.get(i).getPath();
                }


                String[] attaches = concat(pics, vdos);

                if (attaches.length > 0) {
                    new HttpTask(listener, RequestType.uploadFile).uploadFile(list, attaches);
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
}
