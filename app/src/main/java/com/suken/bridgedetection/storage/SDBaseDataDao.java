package com.suken.bridgedetection.storage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;

public class SDBaseDataDao {

    private Dao<SDBaseData, String> mGXLuXianInfoDao = null;

    public SDBaseDataDao() {
        try {
            mGXLuXianInfoDao = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance())
                    .getDao(SDBaseData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dao<SDBaseData, String> getSDBaseDataDao(){
        return mGXLuXianInfoDao;
    }

    public void create(List<SDBaseData> list) {
        for (SDBaseData info : list) {
            create(info);
        }
    }

    public void create(SDBaseData info) {
        try {
            info.setUserId(BridgeDetectionApplication.mCurrentUser.getUserId());
            mGXLuXianInfoDao.createOrUpdate(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SDBaseData> queryAll() {
        try {
            return mGXLuXianInfoDao.queryBuilder().orderBy("zxzh", true).where().eq("userId", BridgeDetectionApplication.mCurrentUser.getUserId()).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SDBaseData queryById(String value) {
        try {
            return mGXLuXianInfoDao.queryForId(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<SDBaseData> getDataBybySDbh(String qhbh){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sdbh", qhbh);
            return mGXLuXianInfoDao.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countAll() {
        List<SDBaseData> list = queryAll();
        if (list != null) {
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
