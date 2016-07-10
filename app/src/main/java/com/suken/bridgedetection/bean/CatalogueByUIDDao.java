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
 * Created by Administrator on 2016/7/8.
 */
public class CatalogueByUIDDao {
    private Dao<CatalogueByUIDBean, String> catalogueByUIDBeen = null;
    public CatalogueByUIDDao() {
        try {
            catalogueByUIDBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(CatalogueByUIDBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(CatalogueByUIDBean bean){
        try {
            catalogueByUIDBeen.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<CatalogueByUIDBean> files) {
        for (CatalogueByUIDBean bean : files) {
            add(bean);
        }
    }
    public List<CatalogueByUIDBean> queryAll(){
        try {
            List<CatalogueByUIDBean> fileDescs = catalogueByUIDBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CatalogueByUIDBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return catalogueByUIDBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CatalogueByUIDBean> queryByLB(String xmlb){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            return catalogueByUIDBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CatalogueByUIDBean> queryByLBandxmmc(String xmlb ,String xmmc){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            map.put("xmmc", xmmc);
            return catalogueByUIDBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(CatalogueByUIDBean bean){
        try {
            catalogueByUIDBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove(CatalogueByUIDBean bean){
        try {
            catalogueByUIDBeen.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            DeleteBuilder<CatalogueByUIDBean, String> deleteBuilder = catalogueByUIDBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
