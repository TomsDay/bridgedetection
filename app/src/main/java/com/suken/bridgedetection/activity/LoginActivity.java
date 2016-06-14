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
import com.suken.bridgedetection.bean.MaintenanceDao;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.storage.SharePreferenceManager;
import com.suken.bridgedetection.storage.UserInfo;
import com.suken.bridgedetection.storage.UserInfoDao;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.NetWorkUtil;
import com.suken.bridgedetection.util.NetWorkUtil.ConnectType;
import com.suken.bridgedetection.util.UiUtil;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

	private List<UserInfo> mUserInfos = null;

	private EditText mNameView = null;
	private EditText mPwdView = null;
	private UserInfoDao mUserDao;
	private TextView mTextView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		MaintenanceTableDao maintenanceTableDao = new MaintenanceTableDao();
//		MaintenanceTableBean maintenanceTableBean1 = new MaintenanceTableBean(1, "G1232", "10:00-11:00", "2016-07-11", "晴", "日常巡查");
//		MaintenanceTableBean maintenanceTableBean2 = new MaintenanceTableBean(2, "G12324", "12:00-14:00", "2016-04-11", "阴", "日常巡查");
//		MaintenanceTableItemBean maintenanceTableItemBean1 = new MaintenanceTableItemBean(1, "数据1", "米", "231", "北京", "2016年02月12日 16:14");
//		MaintenanceTableItemBean maintenanceTableItemBean2 = new MaintenanceTableItemBean(2, "数据2", "ke", "231", "北京", "2016年02月12日 16:14");
//		MaintenanceTableItemBean maintenanceTableItemBean3 = new MaintenanceTableItemBean(3, "数据3", "三打", "231", "北京", "2016年02月12日 16:14");
//		MaintenanceTableItemBean maintenanceTableItemBean4 = new MaintenanceTableItemBean(4, "数据4", "大", "231", "北京", "2016年02月12日 16:14");
//		MaintenanceTableItemBean maintenanceTableItemBean5 = new MaintenanceTableItemBean(5, "数据5", "二", "231", "北京", "2016年02月12日 16:14");
//
//		maintenanceTableItemBean1.setMaintenanceTableBean(maintenanceTableBean1);
//		maintenanceTableItemBean2.setMaintenanceTableBean(maintenanceTableBean2);
//		maintenanceTableItemBean3.setMaintenanceTableBean(maintenanceTableBean1);
//		maintenanceTableItemBean4.setMaintenanceTableBean(maintenanceTableBean2);
//		maintenanceTableItemBean5.setMaintenanceTableBean(maintenanceTableBean1);
//
//		maintenanceTableDao.add(maintenanceTableBean1);
//		maintenanceTableDao.add(maintenanceTableBean2);
//
//		maintenanceTableDao.addItem(maintenanceTableItemBean1);
//		maintenanceTableDao.addItem(maintenanceTableItemBean2);
//		maintenanceTableDao.addItem(maintenanceTableItemBean3);
//		maintenanceTableDao.addItem(maintenanceTableItemBean4);
//		maintenanceTableDao.addItem(maintenanceTableItemBean5);
//
//		List<MaintenanceTableBean> maintenanceTableBeanList= maintenanceTableDao.queryAll();
//		MaintenanceTableBean bean = maintenanceTableBeanList.get(0);
//		if(maintenanceTableBeanList.size()>0){
//			ForeignCollection<MaintenanceTableItemBean> orders = bean.getMaintenanceTableItemBeen();
//			CloseableIterator<MaintenanceTableItemBean> iterator = orders.closeableIterator();
//			try {
//				while(iterator.hasNext()){
//					MaintenanceTableItemBean b = iterator.next();
//					Logger.e("aaa",b.toString());
//				}
//			} finally {
//				try {
//					iterator.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}else{
//
//		}

		mUserDao = new UserInfoDao();
		mUserInfos = mUserDao.queryAll();
		setContentView(R.layout.activity_login_page);
		mNameView = (EditText) findViewById(R.id.username);
		mPwdView = (EditText) findViewById(R.id.userpwd);
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
			mNameView.setText(info.getAccount());
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
				UserInfo info = obj.getObject("userInfo", UserInfo.class);
				info.setPassword(pwd);
				mUserDao.create(info);
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
