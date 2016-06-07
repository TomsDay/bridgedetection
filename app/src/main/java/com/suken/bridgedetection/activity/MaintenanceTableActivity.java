package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.MaintenanceTableAdapter;
import com.suken.bridgedetection.adapter.MaintenanceTableAdapter.ImageOrVideoClick;
import com.suken.bridgedetection.bean.MaintenanceItemBean;
import com.suken.bridgedetection.widget.ListViewForScrollView;
import com.suken.imageditor.ImageditorActivity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.suken.bridgedetection.util.Logger;

public class MaintenanceTableActivity extends Activity {
    ListViewForScrollView mListView;
    private ArrayList<MaintenanceItemBean> list = new ArrayList<MaintenanceItemBean>();
    private MaintenanceTableAdapter mAdapter;
    private Context mContext;
    private int mPosition;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_table);
        mContext = this;
        initView();
    }

    private void initView() {
        mListView = (ListViewForScrollView) findViewById(R.id.maintenancetable_listview);
        mAdapter = new MaintenanceTableAdapter(MaintenanceTableActivity.this);
        mAdapter.setImageOrVideoClick(mImageOrVideoClick);
        mListView.setAdapter(mAdapter);
        mAdapter.setData(list);
        MaintenanceItemBean bean = new MaintenanceItemBean();
        bean.setShow(true);
        list.add(bean);


    }

    ImageOrVideoClick mImageOrVideoClick = new ImageOrVideoClick() {
        @Override
        public void clickImage(int position) {
            mPosition = position;
        }

        @Override
        public void clickVideo(int position) {
            mPosition = position;
        }
    };
    public void operate(View view){
        switch (view.getId()) {
            case R.id.operateAdd:
                list = mAdapter.getData();
                MaintenanceItemBean bean = new MaintenanceItemBean();
                list.add(bean);
                mAdapter.setData(list);
                mAdapter.notifyDataSetChanged();

                break;
            case R.id.operateDelete:
                list = mAdapter.getData();
                list.remove(list.size() - 1);
                mAdapter.setData(list);
                mAdapter.notifyDataSetChanged();
                break;
        }

    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.maintenancetable_back:
                finish();
                break;
            case R.id.maintenancetable_save:

                break;
        }

    }
    public String generateMediaName(boolean isImg) {
        if (isImg) {
            return "pic-" + System.currentTimeMillis() + "-image.png";
        } else {
            return "vdo-" + System.currentTimeMillis() + "-video.mp4";
        }
    }
    private Uri mOutPutFileUri = null;
//    private FormItemController mEditController;
    public void jumpToMedia(int position ,int requestCode, MaintenanceItemBean.ImageDesc desc) {
//        mEditController = con;
        mPosition = position;
        String path = Environment.getExternalStorageDirectory().toString() + File.separator + getPackageName();
        File path1 = new File(path);
        if (!path1.exists()) {
            path1.mkdirs();
        }
        String name = "";
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            name = path1 + File.separator + generateMediaName(true);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            name = desc.path;
        } else {
            name = path1 + File.separator + generateMediaName(false);
        }
        File file = new File(name);
        mOutPutFileUri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File f = null;
        try {
            f = new File(new URI(mOutPutFileUri.toString()));
            if (!f.exists()) {
                return;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            MaintenanceItemBean.ImageDesc desc = new MaintenanceItemBean.ImageDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            Logger.e("aaa"," desc.name==="+ desc.name);
            Logger.e("aaa"," desc.path==="+ desc.path);
            list.get(mPosition).getmImages().add(desc);
            mAdapter.setData(list);
            mAdapter.notifyDataSetChanged();

//            mEditController.updateImg(desc);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            // 保存在原先的图片中所以不处理

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO) {
            MaintenanceItemBean.VideoDesc desc = new MaintenanceItemBean.VideoDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            list.get(mPosition).getmVideo().add(desc);
            mAdapter.setData(list);
            mAdapter.notifyDataSetChanged();
//            mEditController.updateVideo(desc);
        }
    }

}
