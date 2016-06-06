package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.FileDesc;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MaintenanceDao {
    private Dao<MaintenanceBean, String> maintenanceBeen = null;
    private Dao<MaintenanceItemBean, String> maintenanceItemBeen = null;

    public MaintenanceDao() {
        try {
            maintenanceBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(MaintenanceBean bean){
        try {
            maintenanceBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(MaintenanceItemBean bean){
        try {
            maintenanceItemBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<MaintenanceBean> files) {
        for (MaintenanceBean bean : files) {
            add(bean);
        }
    }
    public void addItemList(List<MaintenanceItemBean> files) {
        for (MaintenanceItemBean bean : files) {
            add(bean);
        }
    }
    public List<MaintenanceBean> queryAll(){
        try {
            List<MaintenanceBean> fileDescs = maintenanceBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceItemBean> queryItemAll(){
        try {
            List<MaintenanceItemBean> fileDescs = maintenanceItemBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
