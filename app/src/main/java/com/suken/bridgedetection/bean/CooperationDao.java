package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangshuai on 2017/3/23.
 */

public class CooperationDao {
    private Dao<CooperationBean, String> cooperationBeen = null;
    public CooperationDao() {
        try {
            cooperationBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(CooperationBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(CooperationBean bean){
        try {
            cooperationBeen.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<CooperationBean> files) {
        for (CooperationBean bean : files) {
            add(bean);
        }
    }
    public List<CooperationBean> queryAll(){
        try {
            List<CooperationBean> fileDescs = cooperationBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CooperationBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return cooperationBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(CooperationBean bean){
        try {
            cooperationBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove(CooperationBean bean){
        try {
            cooperationBeen.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            DeleteBuilder<CooperationBean, String> deleteBuilder = cooperationBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除全部
     */
    public void deleteAll()
    {
        try {
            cooperationBeen.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
