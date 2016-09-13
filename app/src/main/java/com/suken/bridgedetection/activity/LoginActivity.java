package com.suken.bridgedetection.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceDao;
import com.suken.bridgedetection.bean.MaintenanceLogDao;
import com.suken.bridgedetection.bean.MaintenanceOfOrderDao;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.storage.SharePreferenceManager;
import com.suken.bridgedetection.storage.UserInfo;
import com.suken.bridgedetection.storage.UserInfoDao;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.NetWorkUtil;
import com.suken.bridgedetection.util.NetWorkUtil.ConnectType;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.util.UiUtil;
import com.yuntongxun.ecdemo.common.CCPAppManager;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;
import com.yuntongxun.ecdemo.core.ClientUser;
import com.yuntongxun.ecdemo.ui.SDKCoreHelper;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.SdkErrorCode;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

	private List<UserInfo> mUserInfos = null;

	private EditText mNameView = null;
	private EditText mPwdView = null;
	private UserInfoDao mUserDao;
	private TextView mTextView = null;
	private String oldName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//

		mUserDao = new UserInfoDao();
		mUserInfos = mUserDao.queryAll();
		setContentView(R.layout.activity_login_page);
		mNameView = (EditText) findViewById(R.id.username);
		mPwdView = (EditText) findViewById(R.id.userpwd);
		mNameView.setText("zhangxuezhi");
		mPwdView.setText("1");
		mTextView = (TextView) findViewById(R.id.login_desc);
		if(mUserInfos != null && mUserInfos.size() > 0){
			BridgeDetectionApplication.mHasCacheUser = true;
			UserInfo info = mUserInfos.get(0);
			String lastUserId = SharePreferenceManager.getInstance().readString("lastloginuser", info.getUserId());
			if(!TextUtils.equals(lastUserId, info.getUserId())){
				for(UserInfo ui : mUserInfos){
					if(TextUtils.equals(ui.getUserId(), lastUserId)){
						info = ui;
					}
				}
			}
			oldName = info.getAccount();
			mNameView.setText(oldName);
			mPwdView.setText(info.getPassword());
		}
		PackageInfo info;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
			String versionName = info.versionName;
			mTextView.setText("当前版本：" + versionName + "   " + "设备号：" + UiUtil.genDeviceId());
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		if(NetWorkUtil.getConnectType(this) == ConnectType.CONNECT_TYPE_WIFI){
			long lastTime = Long.parseLong(SharePreferenceManager.getInstance().readString("updateCheckTime", "-1"));
			if(System.currentTimeMillis() - lastTime > 24 * 60 * 60 *1000){
				UiUtil.update(this);
			}
			SharePreferenceManager.getInstance().updateString("updateCheckTime", System.currentTimeMillis() + "");
		}
	}

	public void onlineLogin(View view) {
		String name = mNameView.getText().toString();
		String pwd = mPwdView.getText().toString();
		if (TextUtils.isEmpty(name)) {
			toast("用户名不能为空");
			return;
		}
		if (TextUtils.isEmpty(pwd)) {
			toast("密码不能为空");
			return;
		}
		login(name, pwd);
	}


	public void setIp(View view){
		startActivity(new Intent(this, IpSettingActivity.class));
	}

	public void offlineLogin(View view) {
		if (mUserInfos == null || mUserInfos.size() == 0) {
			toast("请先联网登陆一次");
			return;
		}
		String name = mNameView.getText().toString();
		String pwd = mPwdView.getText().toString();
		if (TextUtils.isEmpty(name)) {
			toast("用户名不能为空");
			return;
		}
		if (TextUtils.isEmpty(pwd)) {
			toast("密码不能为空");
			return;
		}
		UserInfo info = null;
		for (UserInfo user : mUserInfos) {
			if (TextUtils.equals(name, user.getAccount()) && TextUtils.equals(pwd, user.getPassword())) {
				info = user;
				break;
			}
		}
		if (info != null) {
			jumpToHome(info, false);
		} else {
			toast("用户名或密码不正确");
		}
	}

	private void jumpToHome(UserInfo info, boolean isOnline) {
		if(isOnline){
			SharePreferenceManager.getInstance().updateString("lastLogin", UiUtil.formatNowTime("yyyy-MM-dd"));
		}
		BridgeDetectionApplication.mCurrentUser = info;
		BridgeDetectionApplication.mIsOffline = !isOnline;
		SharePreferenceManager.getInstance().updateString("lastloginuser", info.getUserId());
		boolean flag = SharePreferenceManager.getInstance().readBoolean(Constants.GPS_SWITCH, false);
		if(flag){
			LocationManager.getInstance().startRecordLocation();
		}
		Intent intent = new Intent(this, HomePageActivity.class);
		intent.putExtra("userInfo", info);
		intent.putExtra("isOnline", isOnline);
		startActivity(intent);
		finish();
	}

	private void login(final String name, final String pwd) {
		final OnReceivedHttpResponseListener listener = new OnReceivedHttpResponseListener() {

			@Override
			public void onRequestSuccess(RequestType type, JSONObject obj) {
				final UserInfo info = obj.getObject("userInfo", UserInfo.class);
				info.setPassword(pwd);
 //				Logger.e("aaa", "getAccount list==" + mUserInfos.get(0).getAccount());
//				Logger.e("aaa", "getAccount111==" + info.getAccount());
//				Logger.e("aaa", "oldName==" + oldName);
//				if (!TextUtil.isEmptyString(oldName) && !oldName.equals(info.getAccount())) {
//					Logger.e("aaa", "11111111111");
//					new IVDescDao().deleteAll();
//					new MaintenanceLogDao().deleteAll();
//					new MaintenanceTableDao().deleteAll();
//					new MaintenanceOfOrderDao().deleteAll();
//					new ProjectAcceptanceDao().deleteAll();
//
//				}

				mUserDao.create(info);


				String mobile = "ooo";
				String appKey = "20150314000000110000000000000010";
				String token = "17E24E5AFDB6D0C1EF32F3533494502B";
				ClientUser clientUser = new ClientUser(mobile);
				clientUser.setAppKey(appKey);
				clientUser.setAppToken(token);
				clientUser.setLoginAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
				CCPAppManager.setClientUser(clientUser);
				SDKCoreHelper.init(BridgeDetectionApplication.getInstance(), ECInitParams.LoginMode.FORCE_LOGIN);

//
//				06-18 21:27:05.687 31571-31571/com.yuntongxun.ecdemo I/aaa: appKey: 20150314000000110000000000000010
//				06-18 21:27:05.688 31571-31571/com.yuntongxun.ecdemo I/aaa: token: 17E24E5AFDB6D0C1EF32F3533494502B
//				06-18 21:27:05.690 31571-31571/com.yuntongxun.ecdemo I/aaa: mLoginAuthType: NORMAL_AUTH
//				06-18 21:27:05.691 31571-31571/com.yuntongxun.ecdemo I/aaa: mobile: qpqp

				jumpToHome(info, true);
				finish();

			}

			@Override
			public void onRequestFail(RequestType type, String resultCode, String result) {
				toast(result + "(" + resultCode + ")");
			}
		};

		BackgroundExecutor.execute(new Runnable() {

			@Override
			public void run() {
				showLoading("登录中...");
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				BasicNameValuePair pair = new BasicNameValuePair("account", name);
				list.add(pair);
				pair = new BasicNameValuePair("password", pwd);
				list.add(pair);
				pair = new BasicNameValuePair("did", BridgeDetectionApplication.mDeviceId);
				list.add(pair);
				new HttpTask(listener, RequestType.login).executePost(list);
				dismissLoading();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
