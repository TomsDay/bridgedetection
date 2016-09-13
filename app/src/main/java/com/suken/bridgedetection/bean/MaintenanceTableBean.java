package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
@DatabaseTable(tableName = "tb_maintenancetable")
public class MaintenanceTableBean implements Serializable {
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    /**  路线*/
    @DatabaseField
    private String lxid;
    /**  路线编号*/
    @DatabaseField
    private String lxbh;
    /**  路线名称*/
    @DatabaseField
    private String lxmc;
    @DatabaseField
    private String xcry;
    @DatabaseField
    private String jcry;
    @DatabaseField
    private String weather;
    @DatabaseField
    private String userid;
    @DatabaseField
    private String gydwId;
    @DatabaseField
    private String gydwName;
    @DatabaseField
    private String jcsj;
    @DatabaseField
    private String jcks;
    @DatabaseField
    private String jcjs;
    @DatabaseField
    private String tjsj;
    @DatabaseField
    private int xclx;
    @DatabaseField
    private String xcld;
    private String status ="2";

    private List<MaintenanceTableItemBean> inspectLogDetailList = new ArrayList<MaintenanceTableItemBean>();

    @ForeignCollectionField
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    private ForeignCollection<MaintenanceTableItemBean> maintenanceTableItemBeen;


    @Override
    public String toString() {
        return "MaintenanceTableBean{" +
                "id=" + id +
                ", lxid='" + lxid + '\'' +
                ", lxbh='" + lxbh + '\'' +
                ", lxmc='" + lxmc + '\'' +
                ", xcry='" + xcry + '\'' +
                ", jcry='" + jcry + '\'' +
                ", weather='" + weather + '\'' +
                ", gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", jcsj='" + jcsj + '\'' +
                ", jcks='" + jcks + '\'' +
                ", jcjs='" + jcjs + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", xclx=" + xclx +
                ", xcld='" + xcld + '\'' +
                ", status='" + status + '\'' +

                '}';
    }

    public MaintenanceTableBean() {
    }

    public MaintenanceTableBean(int id, String lxid, String lxbh, String lxmc, String xcry, String jcry, String weather, String gydwId, String gydwName, String jcsj, String tjsj, int xclx, String xcld, List<MaintenanceTableItemBean> inspectLogDetailList, ForeignCollection<MaintenanceTableItemBean> maintenanceTableItemBeen) {
        this.id = id;
        this.lxid = lxid;
        this.lxbh = lxbh;
        this.lxmc = lxmc;
        this.xcry = xcry;
        this.jcry = jcry;
        this.weather = weather;
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.jcsj = jcsj;
        this.tjsj = tjsj;
        this.xclx = xclx;
        this.xcld = xcld;
        this.inspectLogDetailList = inspectLogDetailList;
        this.maintenanceTableItemBeen = maintenanceTableItemBeen;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getJcks() {
        return jcks;
    }

    public void setJcks(String jcks) {
        this.jcks = jcks;
    }

    public String getJcjs() {
        return jcjs;
    }

    public void setJcjs(String jcjs) {
        this.jcjs = jcjs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLxid() {
        return lxid;
    }

    public void setLxid(String lxid) {
        this.lxid = lxid;
    }

    public String getLxbh() {
        return lxbh;
    }

    public void setLxbh(String lxbh) {
        this.lxbh = lxbh;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getXcry() {
        return xcry;
    }

    public void setXcry(String xcry) {
        this.xcry = xcry;
    }

    public String getJcry() {
        return jcry;
    }

    public void setJcry(String jcry) {
        this.jcry = jcry;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getGydwId() {
        return gydwId;
    }

    public void setGydwId(String gydwId) {
        this.gydwId = gydwId;
    }

    public String getGydwName() {
        return gydwName;
    }

    public void setGydwName(String gydwName) {
        this.gydwName = gydwName;
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }

    public int getXclx() {
        return xclx;
    }

    public void setXclx(int xclx) {
        this.xclx = xclx;
    }

    public String getXcld() {
        return xcld;
    }

    public void setXcld(String xcld) {
        this.xcld = xcld;
    }

    public List<MaintenanceTableItemBean> getInspectLogDetailList() {
        return inspectLogDetailList;
    }

    public void setInspectLogDetailList(List<MaintenanceTableItemBean> inspectLogDetailList) {
        this.inspectLogDetailList = inspectLogDetailList;
    }

    public ForeignCollection<MaintenanceTableItemBean> getMaintenanceTableItemBeen() {
        return maintenanceTableItemBeen;
    }

    public void setMaintenanceTableItemBeen(ForeignCollection<MaintenanceTableItemBean> maintenanceTableItemBeen) {
        this.maintenanceTableItemBeen = maintenanceTableItemBeen;
    }
}
