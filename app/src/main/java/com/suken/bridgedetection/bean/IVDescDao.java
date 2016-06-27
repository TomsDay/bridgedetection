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
public class IVDescDao {
    private Dao<IVDesc, String> ivDescs = null;
    public IVDescDao() {
        try {
            ivDescs = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(IVDesc.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(IVDesc bean){
        try {
            ivDescs.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<IVDesc> queryAll(){
        try {
            List<IVDesc> fileDescs = ivDescs.queryForAll();
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<IVDesc> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return ivDescs.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(IVDesc bean){
        try {
            ivDescs.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove(IVDesc bean){
        try {
            ivDescs.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
