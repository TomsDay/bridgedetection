package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;
import com.suken.bridgedetection.util.LetterUtil;
import com.suken.bridgedetection.util.NumComparator;
import com.suken.bridgedetection.util.TextUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Dao<MaintenanceDiseaseBean, String> getMaintenanceDiseaseBeen(){
        return maintenanceDiseaseBeen;
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

    public MaintenanceDiseaseBean getDataByClid(String clid){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", clid);
            List<MaintenanceDiseaseBean> list = maintenanceDiseaseBeen.queryForFieldValues(map);
            if(!TextUtil.isListEmpty(list)){
                return list.get(0);
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateData(String num,String clid)  {
        String sql="update tb_maintenancedisease set commitNum=? where id=?";
        try {
            maintenanceDiseaseBeen.updateRaw(sql,num,clid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MaintenanceDiseaseBean> queryAll(String... content){
        try {
            List<MaintenanceDiseaseBean> fileDescs = maintenanceDiseaseBeen.queryForAll();
            // 根据提交次数来排序
            Collections.sort(fileDescs, new NumComparator<MaintenanceDiseaseBean>());
            //2017.06.07 从数据库获取完毕后直接过滤数据
            if (content != null && content.length > 0) {
                for (String con : content) {
                    if (!TextUtil.isEmptyString(con)) {
                        for (MaintenanceDiseaseBean bean : fileDescs) {
                            bean.setZmname(bean.getBhmc());
                        }
                        LetterUtil<MaintenanceDiseaseBean> util = new LetterUtil<MaintenanceDiseaseBean>();
                        fileDescs = util.filterData(con, fileDescs);
                    }
                    break;
                }
            }
            return fileDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 删除全部
     */
    public void deleteAll() {
        try {
            maintenanceDiseaseBeen.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<MaintenanceDiseaseBean> queryByYJML(String yjml,String... content){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("yjml", yjml);
            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MaintenanceDiseaseBean> queryByYjmlandEjml(String yjml ,String ejml,String... content){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("yjml", yjml);
            map.put("ejml", ejml);
            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceDiseaseBean> queryByYjmlandEjmlAndBhmc(String yjml ,String ejml,String bhmc,String... content){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("yjml", yjml);
            map.put("ejml", ejml);
            map.put("bhmc", bhmc);
            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceDiseaseBean> queryByBhmc(String bhmc){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bhmc", bhmc);
            return maintenanceDiseaseBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<MaintenanceDiseaseBean> conditionZM(Map<String, Object> map, String[] content) throws SQLException {
        List<MaintenanceDiseaseBean> fileDescs = maintenanceDiseaseBeen.queryForFieldValues(map);
        // 根据提交次数来排序
        Collections.sort(fileDescs, new NumComparator<MaintenanceDiseaseBean>());
        //2017.06.07 从数据库获取完毕后直接过滤数据
        if (content != null && content.length > 0) {
            for (String con : content) {
                if (!TextUtil.isEmptyString(con)) {
                    for (MaintenanceDiseaseBean bean : fileDescs) {
                        bean.setZmname(bean.getBhmc());
                    }
                    LetterUtil<MaintenanceDiseaseBean> util = new LetterUtil<MaintenanceDiseaseBean>();
                    fileDescs = util.filterData(con, fileDescs);
                }
                break;
            }
        }
        return fileDescs;
    }
}
