package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.Where;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.CheckDetail;
import com.suken.bridgedetection.storage.SqliteOpenHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/15.
 */
public class MaintenanceTableDao {
    private Dao<MaintenanceTableBean, String> maintenanceTableBeen = null;
    private Dao<MaintenanceTableItemBean, String> maintenanceTableItemBeen = null;

    public MaintenanceTableDao() {
        try {
            maintenanceTableBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceTableBean.class);
            maintenanceTableItemBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(MaintenanceTableItemBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  Dao<MaintenanceTableItemBean, String> getMaintenanceTableItemBeanDao(){
        return maintenanceTableItemBeen;
    }
    public Dao<MaintenanceTableBean, String> getMaintenanceTableBean(){
        return maintenanceTableBeen;
    }

    public void add(MaintenanceTableBean bean){
        try {
            maintenanceTableBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItem(MaintenanceTableItemBean bean){
        try {
            maintenanceTableItemBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<MaintenanceTableBean> files) {
        for (MaintenanceTableBean bean : files) {
            add(bean);
        }
    }
    public void addItemList(List<MaintenanceTableItemBean> files) {
        for (MaintenanceTableItemBean bean : files) {
            addItem(bean);
        }
    }
    public List<MaintenanceTableBean> queryAll(){
        try {
            List<MaintenanceTableBean> fileDescs = maintenanceTableBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceTableItemBean> queryItemAll(){
        try {
            List<MaintenanceTableItemBean> fileDescs = maintenanceTableItemBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceTableBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return maintenanceTableBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceTableBean> queryByuserID(String userid){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userid", userid);
            return maintenanceTableBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(MaintenanceTableBean bean){
        try {
            maintenanceTableBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateItem(MaintenanceTableItemBean bean){
        try {
            maintenanceTableItemBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<MaintenanceTableBean, String> deleteBuilder = maintenanceTableBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id){
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<MaintenanceTableItemBean, String> deleteBuilder = maintenanceTableItemBeen.deleteBuilder();
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
            maintenanceTableBeen.delete(queryAll());
            maintenanceTableItemBeen.delete(queryItemAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
