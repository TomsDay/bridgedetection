package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by liangshuai on 2017/3/31.
 */

public class SDXCDao {

    private Dao<SDXCBean, String> mSDXCBean = null;

    public SDXCDao() {
        try {
            mSDXCBean = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance())
                    .getDao(SDXCBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dao<SDXCBean, String> getSDXCDao(){
        return mSDXCBean;
    }

    public void create(List<SDXCBean> list) {
        for (SDXCBean info : list) {
            create(info);
        }
    }

    public void create(SDXCBean info) {
        try {
            info.setUserId(BridgeDetectionApplication.mCurrentUser.getUserId());
            mSDXCBean.createOrUpdate(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SDXCBean> queryAll() {
        try {
            return mSDXCBean.queryBuilder().orderBy("zxzh", true).where().eq("userId", BridgeDetectionApplication.mCurrentUser.getUserId()).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SDXCBean queryById(String value) {
        try {
            return mSDXCBean.queryForId(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countAll() {
        List<SDXCBean> list = queryAll();
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
            mSDXCBean.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
