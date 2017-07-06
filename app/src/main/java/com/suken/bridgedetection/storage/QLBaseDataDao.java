package com.suken.bridgedetection.storage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.util.TextUtil;

public class QLBaseDataDao {

	private Dao<QLBaseData, String> mGXLuXianInfoDao = null;

	public QLBaseDataDao() {
		try {
			mGXLuXianInfoDao = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance())
					.getDao(QLBaseData.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(List<QLBaseData> list) {
		for (QLBaseData info : list) {
			create(info);
		}
	}

	public void create(QLBaseData info) {
		try {
			//如果application的user为空，那么就去sharePreference中取 2017。7。6
			String userid = BridgeDetectionApplication.mCurrentUser.getUserId();
			if (TextUtil.isEmptyObjects(BridgeDetectionApplication.mCurrentUser)&& TextUtil.isEmptyString(userid)) {
				UserInfo user = SharePreferenceManager.getInstance().readUser();
				if (!TextUtil.isEmptyObjects(user) && !TextUtil.isEmptyString(user.getUserId())) {
					userid = user.getUserId();
				}
			}

			info.setUserId(userid);

			info.setUserId(userid);
			mGXLuXianInfoDao.createOrUpdate(info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<QLBaseData> getDataBybyqhbh(String qhbh){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qlbh", qhbh);
			return mGXLuXianInfoDao.queryForFieldValues(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<QLBaseData> queryAll() {
		try {
			return mGXLuXianInfoDao.queryBuilder().orderBy("zxzh", true).where().eq("userId", BridgeDetectionApplication.mCurrentUser.getUserId()).query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public QLBaseData queryById(String value) {
		try {
			return mGXLuXianInfoDao.queryForId(value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countAll(){
		List<QLBaseData> list = queryAll();
		if(list != null){
			return list.size();
		}
		return 0;
	}
	/**
	 * 删除全部
	 */
	public void deleteAll() {
		try {
			mGXLuXianInfoDao.delete(queryAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
