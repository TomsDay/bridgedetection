package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
public class MaintenanceLogDao {
    private Dao<MaintenanceLogBean, String> maintenanceLogBeen = null;
    private Dao<MaintenanceLogItemBean, String> maintenanceLogItemBeen = null;
    public MaintenanceLogDao() {
        try {
            maintenanceLogBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceLogBean.class);
            maintenanceLogItemBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceLogItemBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(MaintenanceLogBean bean){
        try {
            maintenanceLogBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItem(MaintenanceLogItemBean bean){
        try {
            maintenanceLogItemBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<MaintenanceLogBean> files) {
        for (MaintenanceLogBean bean : files) {
            add(bean);
        }
    }
    public void addItemList(List<MaintenanceLogItemBean> files) {
        for (MaintenanceLogItemBean bean : files) {
            addItem(bean);
        }
    }
    public List<MaintenanceLogBean> queryAll(){
        try {
            List<MaintenanceLogBean> fileDescs = maintenanceLogBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceLogItemBean> queryItemAll(){
        try {
            List<MaintenanceLogItemBean> fileDescs = maintenanceLogItemBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceLogBean> queryByID(long id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return maintenanceLogBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(MaintenanceLogBean bean){
        try {
            maintenanceLogBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateItem(MaintenanceLogItemBean bean){
        try {
            maintenanceLogItemBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
