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

public class QualityDemandDao {
    private Dao<QualityDemandBean, String> cooperationBeen = null;
    public QualityDemandDao() {
        try {
            cooperationBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(QualityDemandBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(QualityDemandBean bean){
        try {
            cooperationBeen.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<QualityDemandBean> files) {
        for (QualityDemandBean bean : files) {
            add(bean);
        }
    }
    public List<QualityDemandBean> queryAll(){
        try {
            List<QualityDemandBean> fileDescs = cooperationBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<QualityDemandBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return cooperationBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(QualityDemandBean bean){
        try {
            cooperationBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove(QualityDemandBean bean){
        try {
            cooperationBeen.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            DeleteBuilder<QualityDemandBean, String> deleteBuilder = cooperationBeen.deleteBuilder();
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
