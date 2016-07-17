package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.List;

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
    /**
     * 删除全部
     */
    public void deleteAll()
    {
        try {
            maintenanceDiseaseBeen.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
