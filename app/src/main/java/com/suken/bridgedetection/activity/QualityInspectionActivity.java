package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.ProjectAcceptanceListAdapter;
import com.suken.bridgedetection.adapter.QualityInspectionExpandableListAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjacceptItemBean;
import com.suken.bridgedetection.bean.QualityInspectionBean;
import com.suken.bridgedetection.bean.QualityInspectionItemBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.DeviceInfoUtil;
import com.suken.bridgedetection.util.FileUtils;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;
import com.suken.imageditor.ImageditorActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
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
        showChliditem();
        qualityInspection_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;//返回true 不可 ExpandableListView 收回/展开
            }
        });
        qualityInspection_gydw_ev.setText(BridgeDetectionApplication.mCurrentUser.getDefgqName());
        qualityInspection_gydw_ev.setKeyListener(null);
        qualityInspection_cjr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName()+"");
        setDateTime();

    }

    private String dateTime;
    public void setDateTime(){
        dateTime = DateUtil.getDate();
        String time = qualityInspection_data_ev.toString();
        if (time == null || time.indexOf("年") == -1) {
            Logger.e("aaa", "111111111111111111111111111111111111111111111111111111111111");
            qualityInspection_data_ev.setText(dateTime);
        }

        qualityInspection_data_ev.setKeyListener(null);
        qualityInspection_data_ev.setKeyListener(null);
        qualityInspection_data_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        QualityInspectionActivity.this, dateTime, new DateTimePickDialogUtil.ReturnTime() {
                    @Override
                    public void getTime(String time) {
                        dateTime = time;
                        qualityInspection_data_ev.setText(dateTime);
                    }
                });
            }
        });
    }
    public String getTime(int year,int month,int day){
        return  year + "年" + (month <= 9 ? ("0" + month) : month) + "月" + (day <= 9 ? ("0" + day) : day)+"日";
    }



    /**
     * 显示子项
     */
    public void showChliditem(){
        for(int i = 0; i < mAdapter.getGroupCount(); i++){
            qualityInspection_expandableListView.expandGroup(i);
        }
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

//                        if(!mIsGpsSuccess){
//                            Toast.makeText(mContext, "正在定位...\n" +
//                                    "请您到空旷的地点从新定位，绝就不要在室内", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
                        showLoading("正在上传数据...");
                        QualityInspectionBean bean = new QualityInspectionBean();
                        String cjr = qualityInspection_cjr_ev.getText().toString();
                        String data = qualityInspection_data_ev.getText().toString();
                        String gqzr = qualityInspection_gqzr_ev.getText().toString();
                        String yhkz = qualityInspection_yhkz_ev.getText().toString();
                        bean.setGydwId(BridgeDetectionApplication.mCurrentUser.getDefgqId());
                        bean.setGydwName(BridgeDetectionApplication.mCurrentUser.getDefgqName());
                        bean.setCjr(cjr);
                        bean.setCjrq(data);
                        List<ProjacceptBean> listBean = mAdapter.getData();
                        for (int q = 0; q < listBean.size(); q++) {
                            List<ProjacceptItemBean> projacceptDetailList = listBean.get(q).getProjacceptDetailList();
                            ProjacceptBean projacceptItemBean = listBean.get(q);
                            for(int w = 0; w < listBean.size(); w++){

                                ProjacceptItemBean itemBean = projacceptDetailList.get(w);
                                QualityInspectionItemBean qualityInspectionItemBean = new QualityInspectionItemBean();
                                qualityInspectionItemBean.setYsdid(projacceptItemBean.getId());
                                qualityInspectionItemBean.setYsdno(projacceptItemBean.getBno());
                                qualityInspectionItemBean.setSgdwid(projacceptItemBean.getSgdwid());
                                qualityInspectionItemBean.setSgdwmc(projacceptItemBean.getSgdwmc());
                                qualityInspectionItemBean.setSgks(projacceptItemBean.getSgks());
                                qualityInspectionItemBean.setSgjs(projacceptItemBean.getSgjs());

                                qualityInspectionItemBean.setYsjl(itemBean.getYsjg());
                                qualityInspectionItemBean.setBhid(itemBean.getBhid());
                                qualityInspectionItemBean.setBhmc(itemBean.getBhmc());
                                qualityInspectionItemBean.setYhzh(itemBean.getYhzh()+itemBean.getFx());
                                qualityInspectionItemBean.setDw(itemBean.getDw());
                                qualityInspectionItemBean.setWxsl(itemBean.getWxsl());

                                bean.getSamplingDetailList().add(qualityInspectionItemBean);
                            }
                        }
                        uploadData(bean);


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
    public final int UPLOAD_SUCCESS_CODE = 2;
    public final int UPLOAD_ERROR_CODE = 3;
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
                new HttpTask(onReceivedHttpResponseListener, RequestType.getProjacceptEdByUID).executePost(list);


            }
        });


    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            dismissLoading();
            switch (msg.what) {

                case SUCCESS_CODE:
                    toast("获取保养工程验收记录成功！");
                    showListDialog();
                    break;
                case ERROR_CODE:
                    toast("获取保养工程验收记录失败！");
                    break;
                case UPLOAD_SUCCESS_CODE:
                    toast("上传维修保养工程抽检单信息成功！");
                    finish();
                    break;
                case UPLOAD_ERROR_CODE:
                    toast("上传维修保养工程抽检单信息失败！");
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
                                showChliditem();
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
    private int mGroupPosition,mChildPosition;
    private Uri mOutPutFileUri = null;
    File mPlayerFile;
    //    private FormItemController mEditController;
    public void jumpToMedia(int groupPosition, int childPosition, int requestCode, IVDesc desc) {
//        mEditController = con;
        mGroupPosition = groupPosition;
        mChildPosition = childPosition;
        String path = Environment.getExternalStorageDirectory().toString() + File.separator + getPackageName();
        File path1 = new File(path);
        if (!path1.exists()) {
            path1.mkdirs();
        }
        String name = "";
        if (requestCode == Constants.REQUEST_CODE_CAPTURE ) {
            name = path1 + File.separator + generateMediaName(true);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            name = desc.path;
        } else {
            name = path1 + File.separator + generateMediaName(false);
        }
        mPlayerFile = new File(name);
        mOutPutFileUri = Uri.fromFile(mPlayerFile);
        Intent intent = new Intent();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        if (requestCode == Constants.REQUEST_CODE_CAPTURE) {
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, requestCode);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            intent.setClass(this, ImageditorActivity.class);
            startActivityForResult(intent, requestCode);
        } else {
            // intent.setClass(this, RecorderActivity.class);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);// 参数设置可以省略
            intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, requestCode);
        }
    }

    String videofileName;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Logger.e("aaa", "requestCode===" + requestCode);
        File f = null;
        if(resultCode == RESULT_OK) {
            try {
                f = new File(new URI(mOutPutFileUri.toString()));
//                if (!f.exists()) {
//                f.mkdirs();
//                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        Logger.e("aaa", "requestCode===" + requestCode);
        if (requestCode == Constants.REQUEST_CODE_CAPTURE && resultCode == RESULT_OK) {
            IVDesc desc = new IVDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            Logger.e("aaa", " desc.name===" + desc.name);
            Logger.e("aaa", " desc.path===" + desc.path);
            projacceptBeens.get(mGroupPosition).getProjacceptDetailList().get(mChildPosition).getmImages().add(desc);
            mAdapter.setData(projacceptBeens);
            mAdapter.notifyDataSetChanged();

//            mEditController.updateImg(desc);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            // 保存在原先的图片中所以不处理

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO && resultCode == RESULT_OK) {
            String str = null;
            IVDesc desc = new IVDesc();
            try {
                Log.e("aaa", "333333");
                desc.name = mPlayerFile.getName();
                desc.path = mPlayerFile.getPath();
                Logger.e("aaa", " REQUEST_CODE_VIDEO  +====== desc.name===" + desc.name);
                Logger.e("aaa", " REQUEST_CODE_VIDEO  +====== desc.path===" + desc.path);
                Uri uri = Uri.parse(data.getData().toString());

                ContentResolver cr = this.getContentResolver();

                Cursor cursor = cr.query(uri, null, null, null, null);
                cursor.moveToFirst();
                str = cursor.getString(1);
                videofileName = cursor.getString(2);
                cursor.close();

                File srcfile = new File(str);

                FileUtils.moveFileTo(srcfile, mPlayerFile);

            }
            catch(Exception e) {;

            }
            projacceptBeens.get(mGroupPosition).getProjacceptDetailList().get(mChildPosition).getmVideo().add(desc);
            mAdapter.setData(projacceptBeens);
            mAdapter.notifyDataSetChanged();
//            mEditController.updateVideo(desc);
        }
    }
    public String generateMediaName(boolean isImg) {
        if (isImg) {
            return "pic-" + System.currentTimeMillis() + "-image.png";
        } else {
            return "vdo-" + System.currentTimeMillis() + "-video.3gp";
        }
    }
    public void uploadData(final QualityInspectionBean bean){
        final Gson gson = new Gson();
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa","111111111111"+ result.toString());
                    Message message = new Message();
                    message.what = UPLOAD_SUCCESS_CODE;
                    mHandler.sendMessage(message);
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
                Message message = new Message();
                message.what = UPLOAD_ERROR_CODE;
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
                new HttpTask(onReceivedHttpResponseListener, RequestType.uploadSampling).executePost(list);
            }
        });
    }


}
