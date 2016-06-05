package com.suken.bridgedetection.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.fragment.HomePageFragment;
import com.suken.bridgedetection.fragment.LeftFragment;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.storage.SharePreferenceManager;
import com.suken.bridgedetection.util.UiUtil;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.ECVoIPCallManager;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.OnMeetingListener;
import com.yuntongxun.ecsdk.SdkErrorCode;
import com.yuntongxun.ecsdk.VideoRatio;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;
import com.yuntongxun.ecsdk.meeting.intercom.ECInterPhoneMeetingMsg;
import com.yuntongxun.ecsdk.meeting.video.ECVideoMeetingMsg;
import com.yuntongxun.ecsdk.meeting.voice.ECVoiceMeetingMsg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
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
        if (!TextUtils.isEmpty(time)) {
            if (System.currentTimeMillis() - Long.parseLong(time) < 24 * 60 * 60 * 1000) {
                needSync = false;
            }
        }
        mFragManager = getSupportFragmentManager();
        mHomeFragment = (HomePageFragment) mFragManager.findFragmentById(R.id.home_fragment);
        mGpsFragment = mFragManager.findFragmentById(R.id.gps_fragment);
        mIpFragment = mFragManager.findFragmentById(R.id.ip_fragment);
        if ((flag && needSync) || !BridgeDetectionApplication.mHasCacheUser) {
            UiUtil.syncData(this, false, mHomeFragment);
            SharePreferenceManager.getInstance().updateString(BridgeDetectionApplication.mCurrentUser.getAccount() + "lastSyncTime", "" + System.currentTimeMillis());
        } else {
            mHomeFragment.onSyncFinished(true);
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

    /**
     * 云融sdk聊天初始化
     */
    private void initYunRong() {
        //第一步：初始化SDK
        // 判断SDK是否已经初始化，如果已经初始化则可以直接调用登陆接口
        // 没有初始化则先进行初始化SDK，然后调用登录接口注册SDK
        if (!ECDevice.isInitialized()) {
            ECDevice.initial(HomePageActivity.this, new ECDevice.InitListener() {
                @Override
                public void onInitialized() {
                    // SDK已经初始化成功

                }

                @Override
                public void onError(Exception exception) {
                    // SDK 初始化失败,可能有如下原因造成
                    // 1、可能SDK已经处于初始化状态
                    // 2、SDK所声明必要的权限未在清单文件（AndroidManifest.xml）里配置、
                    //    或者未配置服务属性android:exported="false";
                    // 3、当前手机设备系统版本低于ECSDK所支持的最低版本（当前ECSDK支持
                    //    Android Build.VERSION.SDK_INT 以及以上版本）
                }
            });
        }

        //第二步：设置注册参数、设置通知回调监听
        // 构建注册所需要的参数信息
        //5.0.3的SDK初始参数的方法：ECInitParams params = new ECInitParams();
        //5.1.*以上版本如下：
        ECInitParams params = ECInitParams.createParams();
        //自定义登录方式：
        //测试阶段Userid可以填写手机
        params.setUserid("用户开发的app的登录账号");
        params.setAppKey("应用ID");
        params.setToken("应用Token");
        // 设置登陆验证模式（是否验证密码）NORMAL_AUTH-自定义方式
        params.setAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
        // 1代表用户名+密码登陆（可以强制上线，踢掉已经在线的设备）
        // 2代表自动重连注册（如果账号已经在其他设备登录则会提示异地登陆）
        // 3 LoginMode（强制上线：FORCE_LOGIN  默认登录：AUTO）
        params.setMode(ECInitParams.LoginMode.FORCE_LOGIN);

        //voip账号+voip密码方式：
        params.setUserid("voip账号");
        params.setPwd("voip密码");
        params.setAppKey("应用ID");
        params.setToken("应用Token");
        // 设置登陆验证模式（是否验证密码）PASSWORD_AUTH-密码登录方式
        params.setAuthType(ECInitParams.LoginAuthType.PASSWORD_AUTH);
        // 1代表用户名+密码登陆（可以强制上线，踢掉已经在线的设备）
        // 2代表自动重连注册（如果账号已经在其他设备登录则会提示异地登陆）
        // 3 LoginMode（强制上线：FORCE_LOGIN  默认登录：AUTO）
        params.setMode(ECInitParams.LoginMode.FORCE_LOGIN);

        // 如果是v5.1.8r开始版本建议使用
        // ECDevice.setOnDeviceConnectListener（new ECDevice.OnECDeviceConnectListener()）
        // 如果是v5.1.8r以前版本设置登陆状态回调如下
        params.setOnDeviceConnectListener(new ECDevice.OnECDeviceConnectListener() {
            public void onConnect() {
                // 兼容4.0，5.0可不必处理
            }

            @Override
            public void onDisconnect(ECError error) {
                // 兼容4.0，5.0可不必处理
            }

            @Override
            public void onConnectState(ECDevice.ECConnectState state, ECError error) {
                if (state == ECDevice.ECConnectState.CONNECT_FAILED) {
                    if (error.errorCode == SdkErrorCode.SDK_KICKED_OFF) {
                        //账号异地登陆
                    } else {
                        //连接状态失败
                    }
                    return;
                } else if (state == ECDevice.ECConnectState.CONNECT_SUCCESS) {
                    // 登陆成功
                }
            }
        });

        // 如果是v5.1.8r版本 ECDevice.setOnChatReceiveListener(new OnChatReceiveListener())
        // 5.1.7r及以前版本设置SDK接收消息回调
        params.setOnChatReceiveListener(new OnChatReceiveListener() {
            @Override
            public void OnReceivedMessage(ECMessage msg) {
                // 收到新消息
            }

            @Override
            public void onReceiveMessageNotify(ECMessageNotify ecMessageNotify) {

            }

            @Override
            public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage ecGroupNoticeMessage) {

            }


            @Override
            public void onOfflineMessageCount(int count) {
                // 登陆成功之后SDK回调该接口通知账号离线消息数
            }

            @Override
            public int onGetOfflineMessage() {
                return 0;
            }

            @Override
            public void onReceiveOfflineMessage(List msgs) {
                // SDK根据应用设置的离线消息拉去规则通知应用离线消息
            }

            @Override
            public void onReceiveOfflineMessageCompletion() {
                // SDK通知应用离线消息拉取完成
            }

            @Override
            public void onServicePersonVersion(int version) {
                // SDK通知应用当前账号的个人信息版本号
            }

            @Override
            public void onReceiveDeskMessage(ECMessage ecMessage) {

            }

            @Override
            public void onSoftVersion(String s, int i) {

            }
        });

        // 获得SDKVoIP呼叫接口
        // 注册VoIP呼叫事件回调监听
        ECVoIPCallManager callInterface = ECDevice.getECVoIPCallManager();
        if (callInterface != null) {
            callInterface.setOnVoIPCallListener(new ECVoIPCallManager.OnVoIPListener() {
                @Override
                public void onVideoRatioChanged(VideoRatio videoRatio) {

                }

                @Override
                public void onSwitchCallMediaTypeRequest(String s, ECVoIPCallManager.CallType callType) {

                }

                @Override
                public void onSwitchCallMediaTypeResponse(String s, ECVoIPCallManager.CallType callType) {

                }

                @Override
                public void onDtmfReceived(String s, char c) {

                }

                @Override
                public void onCallEvents(ECVoIPCallManager.VoIPCall voipCall) {
                    // 处理呼叫事件回调
                    if (voipCall == null) {
                        Log.e("SDKCoreHelper", "handle call event error , voipCall null");
                        return;
                    }
                    // 根据不同的事件通知类型来处理不同的业务
                    ECVoIPCallManager.ECCallState callState = voipCall.callState;
                    switch (callState) {
                        case ECCALL_PROCEEDING:
                            // 正在连接服务器处理呼叫请求
                            break;
                        case ECCALL_ALERTING:
                            // 呼叫到达对方客户端，对方正在振铃
                            break;
                        case ECCALL_ANSWERED:
                            // 对方接听本次呼叫
                            break;
                        case ECCALL_FAILED:
                            // 本次呼叫失败，根据失败原因播放提示音
                            break;
                        case ECCALL_RELEASED:
                            // 通话释放[完成一次呼叫]
                            break;
                        default:
                            Log.e("SDKCoreHelper", "handle call event error , callState " + callState);
                            break;
                    }
                }
            });
        }

        // 注册会议消息处理监听
        if (ECDevice.getECMeetingManager() != null) {
            ECDevice.getECMeetingManager().setOnMeetingListener(new OnMeetingListener() {
                @Override
                public void onVideoRatioChanged(VideoRatio videoRatio) {

                }

                @Override
                public void onReceiveInterPhoneMeetingMsg(ECInterPhoneMeetingMsg msg) {
                    // 处理实时对讲消息Push
                }

                @Override
                public void onReceiveVoiceMeetingMsg(ECVoiceMeetingMsg msg) {
                    // 处理语音会议消息push
                }

                @Override
                public void onReceiveVideoMeetingMsg(ECVideoMeetingMsg msg) {
                    // 处理视频会议消息Push（暂未提供）
                }
            });
        }


        //第三步：验证参数是否正确，注册SDK
        if (params.validate()) {
            // 判断注册参数是否正确
            ECDevice.login(params);
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
