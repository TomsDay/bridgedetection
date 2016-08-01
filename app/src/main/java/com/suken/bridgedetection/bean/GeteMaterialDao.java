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
 * Created by Administrator on 2016/8/1.
 */
public class GeteMaterialDao {
    private Dao<GeteMaterialBean, String> geteMaterialBeen = null;
    public GeteMaterialDao() {
        try {
            geteMaterialBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(GeteMaterialBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(GeteMaterialBean bean){
        try {
            geteMaterialBeen.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<GeteMaterialBean> files) {
        for (GeteMaterialBean bean : files) {
            add(bean);
        }
    }
    public List<GeteMaterialBean> queryAll(){
        try {
            List<GeteMaterialBean> fileDescs = geteMaterialBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<GeteMaterialBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<GeteMaterialBean> queryByLB(String xmlb){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<GeteMaterialBean> queryByLBandxmmc(String xmlb ,String xmmc){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            map.put("xmmc", xmmc);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<GeteMaterialBean> queryByXMMC(String ximmc){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ximmc", ximmc);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(GeteMaterialBean bean){
        try {
            geteMaterialBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove(GeteMaterialBean bean){
        try {
            geteMaterialBeen.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            DeleteBuilder<GeteMaterialBean, String> deleteBuilder = geteMaterialBeen.deleteBuilder();
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
            geteMaterialBeen.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
