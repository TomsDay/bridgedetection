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
 * Created by Administrator on 2016/6/26.
 */
public class MaintenanceOfOrderDao {
    private Dao<MaintenanceOfOrderBean, String> maintenanceOfOrderBeen = null;
    private Dao<MaintenanceOfOrderItemBean, String> maintenanceOfOrderItemBeen = null;

    public MaintenanceOfOrderDao() {
        try {
            maintenanceOfOrderBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceOfOrderBean.class);
            maintenanceOfOrderItemBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceOfOrderItemBean.class);
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
    public void addItem(MaintenanceOfOrderItemBean bean){
        try {
            maintenanceOfOrderItemBeen.createOrUpdate(bean);
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

    public List<MaintenanceOfOrderItemBean> queryItemAll(){
        try {
            List<MaintenanceOfOrderItemBean> fileDescs = maintenanceOfOrderItemBeen.queryForAll();
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
    public void updateItem(MaintenanceOfOrderItemBean bean){
        try {
            maintenanceOfOrderItemBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<MaintenanceOfOrderBean, String> deleteBuilder = maintenanceOfOrderBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id){
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<MaintenanceOfOrderItemBean, String> deleteBuilder = maintenanceOfOrderItemBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
