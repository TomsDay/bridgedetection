package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/22.
 */
public class MaintenanceDiseaseDao {
    private Dao<MaintenanceDiseaseBean, String> maintenanceDiseaseBeen = null;
    public MaintenanceDiseaseDao() {
        try {
            maintenanceDiseaseBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceDiseaseBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(MaintenanceDiseaseBean bean){
        try {
            maintenanceDiseaseBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<MaintenanceDiseaseBean> files) {
        for (MaintenanceDiseaseBean bean : files) {
            add(bean);
        }
    }
    public List<MaintenanceDiseaseBean> queryAll(){
        try {
            List<MaintenanceDiseaseBean> fileDescs = maintenanceDiseaseBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceDiseaseBean> queryByYJML(String yjml){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("yjml", yjml);
            return maintenanceDiseaseBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MaintenanceDiseaseBean> queryByYjmlandEjml(String yjml ,String ejml){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("yjml", yjml);
            map.put("ejml", ejml);
            return maintenanceDiseaseBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceDiseaseBean> queryByBhmc(String bhmc){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bhmc", bhmc);
            return maintenanceDiseaseBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
