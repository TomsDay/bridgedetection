package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
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
 * Created by Administrator on 2016/7/8.
 */
public class CatalogueByUIDDao {
    private Dao<CatalogueByUIDBean, String> catalogueByUIDBeen = null;
    public CatalogueByUIDDao() {
        try {
            catalogueByUIDBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(CatalogueByUIDBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dao<CatalogueByUIDBean, String> getCatalogueByUIDBeen(){
        return catalogueByUIDBeen;
    }

    public void add(CatalogueByUIDBean bean){
        try {
            catalogueByUIDBeen.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addList(List<CatalogueByUIDBean> files) {
        for (CatalogueByUIDBean bean : files) {
            add(bean);
        }
    }



    public CatalogueByUIDBean getDataByClid(String clid){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", clid);
            List<CatalogueByUIDBean> list = catalogueByUIDBeen.queryForFieldValues(map);
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
        String sql="update tb_cataloguebyuid set commitNum=? where id=?";
        try {
            catalogueByUIDBeen.updateRaw(sql,num,clid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CatalogueByUIDBean> queryAll(String... content){
        try {
            List<CatalogueByUIDBean> fileDescs = catalogueByUIDBeen.queryForAll();
            // 根据提交次数来排序
            Collections.sort(fileDescs, new NumComparator<CatalogueByUIDBean>());
            //2017.06.07 从数据库获取完毕后直接过滤数据
            if (content != null && content.length > 0) {
                for (String con : content) {
                    if (!TextUtil.isEmptyString(con)) {
                        for (CatalogueByUIDBean bean : fileDescs) {
                            bean.setZmname(bean.getXimmc());
                        }
                        LetterUtil<CatalogueByUIDBean> util = new LetterUtil<CatalogueByUIDBean>();
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
    public List<CatalogueByUIDBean> queryByID(int id){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return catalogueByUIDBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CatalogueByUIDBean> queryByLB(String xmlb,String... content){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CatalogueByUIDBean> queryByLBandxmmc(String xmlb ,String xmmc,String... content){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            map.put("xmmc", xmmc);
            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CatalogueByUIDBean> queryByLBandXmmcAndXimmc(String xmlb ,String xmmc,String ximmc,String... content){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            map.put("xmmc", xmmc);
            map.put("ximmc", ximmc);
            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public List<CatalogueByUIDBean> queryByXMMC(String ximmc){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ximmc", ximmc);
            return catalogueByUIDBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(CatalogueByUIDBean bean){
        try {
            catalogueByUIDBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove(CatalogueByUIDBean bean){
        try {
            catalogueByUIDBeen.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            DeleteBuilder<CatalogueByUIDBean, String> deleteBuilder = catalogueByUIDBeen.deleteBuilder();
            deleteBuilder.where().eq("id", id);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除全部
     */
    public void deleteAll()
    {
        try {
            catalogueByUIDBeen.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<CatalogueByUIDBean> conditionZM(Map<String, Object> map, String[] content) throws SQLException {
        List<CatalogueByUIDBean> fileDescs = catalogueByUIDBeen.queryForFieldValues(map);
        // 根据提交次数来排序
        Collections.sort(fileDescs, new NumComparator<CatalogueByUIDBean>());
        //2017.06.07 从数据库获取完毕后直接过滤数据
        if (content != null && content.length > 0) {
            for (String con : content) {
                if (!TextUtil.isEmptyString(con)) {
                    for (CatalogueByUIDBean bean : fileDescs) {
                        bean.setZmname(bean.getXimmc());
                    }
                    LetterUtil<CatalogueByUIDBean> util = new LetterUtil<CatalogueByUIDBean>();
                    fileDescs = util.filterData(con, fileDescs);
                }
                break;
            }
        }
        return fileDescs;
    }
}
