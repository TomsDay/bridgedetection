package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
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

    public ProjectAcceptanceDao() {
        try {
            projectAcceptanceBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(ProjectAcceptanceBean.class);
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
    public List<ProjectAcceptanceBean> queryAll(){
        try {
            List<ProjectAcceptanceBean> fileDescs = projectAcceptanceBeen.queryForAll();
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
}
