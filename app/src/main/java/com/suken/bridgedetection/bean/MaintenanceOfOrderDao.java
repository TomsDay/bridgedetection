package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/26.
 */
public class MaintenanceOfOrderDao {
    private Dao<MaintenanceOfOrderBean, String> maintenanceOfOrderBeen = null;

    public MaintenanceOfOrderDao() {
        try {
            maintenanceOfOrderBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceOfOrderBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(MaintenanceOfOrderBean bean){
        try {
            maintenanceOfOrderBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<MaintenanceOfOrderBean> queryAll(){
        try {
            List<MaintenanceOfOrderBean> fileDescs = maintenanceOfOrderBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceOfOrderBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return maintenanceOfOrderBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(MaintenanceOfOrderBean bean){
        try {
            maintenanceOfOrderBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
