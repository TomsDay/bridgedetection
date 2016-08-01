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
 * Created by Administrator on 2016/6/27.
 */
public class ProjectAcceptanceDao {
    private Dao<ProjectAcceptanceBean, String> projectAcceptanceBeen = null;
    private Dao<ProjacceptItemBean, String> projacceptItemBeen = null;


    public ProjectAcceptanceDao() {
        try {
            projectAcceptanceBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(ProjectAcceptanceBean.class);
            projacceptItemBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(ProjacceptItemBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(ProjectAcceptanceBean bean){
        try {
            projectAcceptanceBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItem(ProjacceptItemBean bean){
        try {
            projacceptItemBeen.createOrUpdate(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<ProjectAcceptanceBean> files) {
        for (ProjectAcceptanceBean bean : files) {
            add(bean);
        }
    }
    public void addItemList(List<ProjacceptItemBean> files) {
        for (ProjacceptItemBean bean : files) {
            addItem(bean);
        }
    }


    public List<ProjectAcceptanceBean> queryAll(){
        try {
            List<ProjectAcceptanceBean> fileDescs = projectAcceptanceBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<ProjacceptItemBean> queryItemAll(){
        try {
            List<ProjacceptItemBean> fileDescs = projacceptItemBeen.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<ProjectAcceptanceBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return projectAcceptanceBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(ProjectAcceptanceBean bean){
        try {
            projectAcceptanceBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(ProjacceptItemBean bean){
        try {
            projacceptItemBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<ProjectAcceptanceBean, String> deleteBuilder = projectAcceptanceBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteItem(long id){
        try {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<ProjacceptItemBean, String> deleteBuilder = projacceptItemBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
