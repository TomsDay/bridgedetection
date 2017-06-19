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
 * Created by liangshuai on 2017/5/9.
 */

public class UploadUpkeepnoticeDao {
    private Dao<UploadUpkeepnoticeBean, String> uploadUpkeepnoticeBeen = null;
    private Dao<UpkeepdiseaseListBean, String> upkeepdiseaseListBeen = null;

    public UploadUpkeepnoticeDao() {
        try {
            uploadUpkeepnoticeBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(UploadUpkeepnoticeBean.class);
            upkeepdiseaseListBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(UpkeepdiseaseListBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dao<UploadUpkeepnoticeBean, String> getUploadUpkeepnoticeBeen(){
        return uploadUpkeepnoticeBeen;
    }
    public  Dao<UpkeepdiseaseListBean, String> getUpkeepdiseaseListBeen(){
        return upkeepdiseaseListBeen;
    }


    public void add(UploadUpkeepnoticeBean bean){
        try {
            uploadUpkeepnoticeBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItem(UpkeepdiseaseListBean bean){
        try {
            upkeepdiseaseListBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<UploadUpkeepnoticeBean> files) {
        for (UploadUpkeepnoticeBean bean : files) {
            add(bean);
        }
    }
    public void addItemList(List<UpkeepdiseaseListBean> files) {
        for (UpkeepdiseaseListBean bean : files) {
            addItem(bean);
        }
    }
    public List<UploadUpkeepnoticeBean> queryAll(){
        try {
            List<UploadUpkeepnoticeBean> fileDescs = uploadUpkeepnoticeBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<UpkeepdiseaseListBean> queryItemAll(){
        try {
            List<UpkeepdiseaseListBean> fileDescs = upkeepdiseaseListBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<UploadUpkeepnoticeBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return uploadUpkeepnoticeBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<UploadUpkeepnoticeBean> queryByuserID(String userid){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userid", userid);
            return uploadUpkeepnoticeBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(UploadUpkeepnoticeBean bean){
        try {
            uploadUpkeepnoticeBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateItem(UpkeepdiseaseListBean bean){
        try {
            upkeepdiseaseListBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateItemList(List<UpkeepdiseaseListBean> files) {
        for (UpkeepdiseaseListBean bean : files) {
            updateItem(bean);
        }
    }

    public void delete(int id) {
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<UploadUpkeepnoticeBean, String> deleteBuilder = uploadUpkeepnoticeBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id){
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<UpkeepdiseaseListBean, String> deleteBuilder = upkeepdiseaseListBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除全部
     */
    public void deleteAll() {
        try {
            uploadUpkeepnoticeBeen.delete(queryAll());
            upkeepdiseaseListBeen.delete(queryItemAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
