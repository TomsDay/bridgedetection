package com.suken.bridgedetection.activity;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseDao;
import com.suken.bridgedetection.fragment.HomePageFragment;
import com.suken.bridgedetection.fragment.LeftFragment;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.storage.SharePreferenceManager;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.UiUtil;
import com.yuntongxun.ecdemo.ui.chatting.ChattingActivity;
import com.yuntongxun.ecdemo.ui.chatting.ChattingFragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

public class HomePageActivity extends BaseActivity implements DialogInterface.OnClickListener, OnReceivedHttpResponseListener {

    private HomePageFragment mHomeFragment;
    private Fragment mGpsFragment;
    private Fragment mIpFragment;
    private FragmentManager mFragManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mIsFinished) {
            return;
        }
        setContentView(R.layout.activity_home_page);
        boolean flag = getIntent().getBooleanExtra("isOnline", true);
        String time = SharePreferenceManager.getInstance().readString(BridgeDetectionApplication.mCurrentUser.getAccount() + "lastSyncTime", "");
        boolean needSync = true;
        if (!TextUtils.isEmpty(time)) {//如果上次同步时间没有超过一天同步时间，needSync为false
            if (System.currentTimeMillis() - Long.parseLong(time) < 24 * 60 * 60 * 1000) {
                needSync = false;
            }
        }
        mFragManager = getSupportFragmentManager();
        mHomeFragment = (HomePageFragment) mFragManager.findFragmentById(R.id.home_fragment);
        mGpsFragment = mFragManager.findFragmentById(R.id.gps_fragment);
        mIpFragment = mFragManager.findFragmentById(R.id.ip_fragment);
        if ((flag && needSync) || !BridgeDetectionApplication.mHasCacheUser) {
            Logger.e("aaa","正常登录==");
            Logger.e("aaa","userid=="+BridgeDetectionApplication.mCurrentUser.getUserId());
            Logger.e("aaa","token=="+BridgeDetectionApplication.mCurrentUser.getToken());
            UiUtil.syncData(this, false, mHomeFragment);
            SharePreferenceManager.getInstance().updateString(BridgeDetectionApplication.mCurrentUser.getAccount() + "lastSyncTime", "" + System.currentTimeMillis());
        } else {
            Logger.e("aaa","离线登录==");
            List<MaintenanceDiseaseBean> list = new MaintenanceDiseaseDao().queryAll();
//            for(int i =0;i<list.size();i++) {
//                Logger.e("aaa", "======="+list.get(i).toString());
//            }
                    mHomeFragment.onSyncFinished(true);
            if(flag){//正常登录时，同步路段信息
                UiUtil.synchronizationGxlxInfoData(this);
            }

        }

        FragmentTransaction ft = mFragManager.beginTransaction();
        ft.hide(mGpsFragment);
        ft.hide(mIpFragment);
        ft.commit();

    }

    public void updateFragment(int resId) {
        switch (resId) {
            case R.id.left_frag_home: {
                FragmentTransaction ft = mFragManager.beginTransaction();
                ft.hide(mGpsFragment);
                ft.hide(mIpFragment);
                ft.show(mHomeFragment);
                ft.commit();
            }
            break;
            case R.id.left_frag_ip: {
                FragmentTransaction ft = mFragManager.beginTransaction();
                ft.hide(mGpsFragment);
                ft.show(mIpFragment);
                ft.hide(mHomeFragment);
                ft.commit();
            }
            break;
            case R.id.left_frag_video:
                Log.i("aaa", "updateFragment: left_frag_videoleft_frag_videoleft_frag_video");
//
//                Intent in = new Intent(HomePageActivity.this, LauncherActivity.class);
//                HomePageActivity.this.startActivity(in);
                toast("敬请期待。");
                //2017.06.19 修改视屏  只能pc端请求app
//                Intent intent = new Intent(HomePageActivity.this, ChattingActivity.class);

//                intent.putExtra(ChattingFragment.RECIPIENTS, "10000010070010");
//                intent.putExtra(ChattingFragment.CONTACT_USER, "陈旭");
//                intent.putExtra(ChattingFragment.CUSTOMER_SERVICE, false);
//                HomePageActivity.this.startActivity(intent);
                break;
            case R.id.left_frag_update:
                UiUtil.update(this);
                break;
            case R.id.left_frag_exit: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("退出").setMessage("确定退出？").setPositiveButton("确定", this).setNegativeButton("取消", this).show();
            }
            break;
            case R.id.left_frag_gps: {
                FragmentTransaction ft = mFragManager.beginTransaction();
                ft.show(mGpsFragment);
                ft.hide(mIpFragment);
                ft.hide(mHomeFragment);
                ft.commit();
            }
            break;

            default:
                break;
        }
    }



    private void exit() {
        BackgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {
                showLoading("正在注销登录...");
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                new HttpTask(HomePageActivity.this, RequestType.exit).executePost(list);
                dismissLoading();
            }
        });

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            dialog.dismiss();
            BackgroundExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    exit();
                }
            });
        } else {
            dialog.dismiss();
            selectHome();
        }
    }

    public void selectHome() {
        LeftFragment fragment = (LeftFragment) mFragManager.findFragmentById(R.id.left_fragment);
        fragment.selectHome();
    }

    @Override
    public void onRequestSuccess(RequestType type, JSONObject obj) {
        if (type == RequestType.exit) {
            toast("注销成功！");
            finish();
        }
    }

    @Override
    public void onRequestFail(RequestType type, String resultCode, String result) {
        if (type == RequestType.update) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
                    OnClickListener listener = new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == Dialog.BUTTON_POSITIVE) {
                                UiUtil.update(HomePageActivity.this);
                            } else {
                                dialog.dismiss();
                                selectHome();
                            }
                        }
                    };
                    builder.setTitle("更新").setMessage("检测更新失败，是否重试？").setPositiveButton("确定", listener).setNegativeButton("取消", listener).show();
                }
            });
        } else if (type == RequestType.exit) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsNeedTerminal) {
            BridgeDetectionApplication.getInstance().onTerminate();
        }
    }

    public void hidden() {
        mHomeFragment.onSelected();
    }
}
