package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.storage.SqliteOpenHelper;
import com.suken.bridgedetection.util.LetterUtil;
import com.suken.bridgedetection.util.NumComparator;
import com.suken.bridgedetection.util.TextUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/1.
 */
public class GeteMaterialDao {
    private Dao<GeteMaterialBean, String> geteMaterialBeen = null;

    public GeteMaterialDao() {
        try {
            geteMaterialBeen = SqliteOpenHelper.getHelper(BridgeDetectionApplication.getInstance()).getDao(GeteMaterialBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<GeteMaterialBean, String> getGeteMaterialDao(){
        return geteMaterialBeen;
    }

    public void add(GeteMaterialBean bean) {
        try {
            geteMaterialBeen.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addList(List<GeteMaterialBean> files) {
        for (GeteMaterialBean bean : files) {
            add(bean);
        }
    }

    public GeteMaterialBean getDataByClid(String clid){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", clid);
            List<GeteMaterialBean> list = geteMaterialBeen.queryForFieldValues(map);
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
        String sql="update tb_getematerial set commitNum=? where id=?";
        try {
            geteMaterialBeen.updateRaw(sql,num,clid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<GeteMaterialBean> queryAll(String... content) {
        try {
            List<GeteMaterialBean> fileDescs = geteMaterialBeen.queryForAll();

            // 根据提交次数来排序
            Collections.sort(fileDescs, new NumComparator<GeteMaterialBean>());

            //2017.06.07 从数据库获取完毕后直接过滤数据
            if (content != null && content.length > 0) {
                for (String con : content) {
                    if (!TextUtil.isEmptyString(con)) {
                        for (GeteMaterialBean bean : fileDescs) {
                            bean.setZmname(bean.getClmc());
                        }
                        LetterUtil<GeteMaterialBean> util = new LetterUtil<GeteMaterialBean>();
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

    public List<GeteMaterialBean> queryByID(int id) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GeteMaterialBean> queryByLB(String xmlb) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GeteMaterialBean> queryByLBandxmmc(String xmlb, String xmmc) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xmlb", xmlb);
            map.put("xmmc", xmmc);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GeteMaterialBean> queryByCLMC(String clmc) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("clmc", clmc);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(GeteMaterialBean bean) {
        try {
            geteMaterialBeen.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(GeteMaterialBean bean) {
        try {
            geteMaterialBeen.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            DeleteBuilder<GeteMaterialBean, String> deleteBuilder = geteMaterialBeen.deleteBuilder();
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
            geteMaterialBeen.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GeteMaterialBean> queryByYJML(String yjml, String... content) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (!TextUtil.isEmptyString(yjml)) {
                map.put("yjml", yjml);
            }
            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GeteMaterialBean> queryByYjmlandEjml(String yjml, String ejml, String... content) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (!TextUtil.isEmptyString(yjml)) {
                map.put("yjml", yjml);
            }
            if (!TextUtil.isEmptyString(ejml)) {
                map.put("ejml", ejml);
            }

            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GeteMaterialBean> queryByYjmlandEjmlXimmc(String yjml, String ejml, String ximmc, String... content) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (!TextUtil.isEmptyString(yjml)) {
                map.put("yjml", yjml);
            }
            if (!TextUtil.isEmptyString(ejml)) {
                map.put("ejml", ejml);
            }
            if (!TextUtil.isEmptyString(ximmc)) {
                map.put("clmc", ximmc);
            }


            return conditionZM(map, content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<GeteMaterialBean> conditionZM(Map<String, Object> map, String[] content) throws SQLException {
        List<GeteMaterialBean> fileDescs = geteMaterialBeen.queryForFieldValues(map);
        // 2017。06。08根据提交次数来排序
        Collections.sort(fileDescs, new NumComparator<GeteMaterialBean>());
        //2017.06.07 从数据库获取完毕后直接过滤数据
        if (content != null && content.length > 0) {
            for (String con : content) {
                if (!TextUtil.isEmptyString(con)) {
                    for (GeteMaterialBean bean : fileDescs) {
                        bean.setZmname(bean.getClmc());
                    }
                    LetterUtil<GeteMaterialBean> util = new LetterUtil<GeteMaterialBean>();
                    fileDescs = util.filterData(con, fileDescs);
                }
                break;
            }
        }
        return fileDescs;
    }

    public List<GeteMaterialBean> queryByClon(String bhmc) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("clmc", bhmc);
            return geteMaterialBeen.queryForFieldValues(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
