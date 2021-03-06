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
    /**
     * 通过id 获取所有关于ImageMaintenanceTableItemBean 的值
     * @param userid
     * @return
     */
    public List<IVDesc> getImageMaintenanceTableItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("imageMaintenanceTableItemBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<IVDesc> getVideoMaintenanceTableItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("videoMaintenanceTableItemBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<IVDesc> getImageMaintenanceLogItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("imageMaintenanceLogItem_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<IVDesc> getVideoMaintenanceLogItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("videoMaintenanceLogItemBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<IVDesc> getImageMaintenanceOfOrderItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("imageMaintenanceOfOrderItemBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<IVDesc> getVideoMaintenanceOfOrderItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("videoMaintenanceOfOrderItemBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<IVDesc> getImageProjectAcceptanceBeanByUserId(Long userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("imageProjectAcceptanceBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<IVDesc> getImageProjacceptItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("imageProjacceptItemBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<IVDesc> getVideoProjacceptItemBeanByUserId(int userid)
    {
        try {
            return ivDescs.queryBuilder().where().eq("videoProjacceptItemBean_id", userid)
                    .query();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int id){
        try {
            DeleteBuilder<IVDesc, String> deleteBuilder = ivDescs.deleteBuilder();
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
            ivDescs.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
