package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
@DatabaseTable(tableName = "tb_maintenanceoforder")
public class MaintenanceOfOrderBean implements Serializable {
    @DatabaseField(generatedId = true) //主键自增加
    private int id;

    //    @DatabaseField
//    private String orgid;
//    @DatabaseField
//    private String versionno;
//    @DatabaseField
//    private String createBy;
//    @DatabaseField
//    private String creator;
//    @DatabaseField
//    private String createtime;
//    @DatabaseField
//    private String updateBy;
//    @DatabaseField
//    private String updator;
//    @DatabaseField
//    private String updatetime;
//    @DatabaseField
//    private String flag;
    @DatabaseField
    private String userid;
    @DatabaseField
    private String gydwId;

    @DatabaseField
    private String gydwName;
    //    @DatabaseField
//    private String bno;
    @DatabaseField
    private String yhrzid;
    @DatabaseField
    private String yhrzbno;
    @DatabaseField
    private String sgdwid;
    @DatabaseField
    private String sgdwmc;
    @DatabaseField
    private String jcsj;
    @DatabaseField
    private String xcnr;
    @DatabaseField
    private String jcry;
    @DatabaseField
    private String jcdate;
    @DatabaseField
    private String aqgly;
    //    @DatabaseField
//    private String tjsj;
    @DatabaseField
    private String status = "2";
    @DatabaseField
    private String qtqk;
    @DatabaseField
    private String clyj;
    @DatabaseField
    private String aqgldate;
    @DatabaseField
    private String weather;


    List<MaintenanceOfOrderItemBean> safetycheckdetailList = new ArrayList<MaintenanceOfOrderItemBean>();
    List<MaintenanceLogBean> projacceptDetailList = new ArrayList<MaintenanceLogBean>();

    @ForeignCollectionField
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    private ForeignCollection<MaintenanceOfOrderItemBean> maintenanceOfOrderItemBeen;



    public MaintenanceOfOrderBean() {
    }

    @Override
    public String toString() {
        return "MaintenanceOfOrderBean{" +
                "id=" + id +
                ", yhrzid='" + yhrzid + '\'' +
                ", yhrzbno='" + yhrzbno + '\'' +
                ", sgdwid='" + sgdwid + '\'' +
                ", sgdwmc='" + sgdwmc + '\'' +
                ", jcsj='" + jcsj + '\'' +
                ", xcnr='" + xcnr + '\'' +
                ", jcry='" + jcry + '\'' +
                ", jcdate='" + jcdate + '\'' +
                ", aqgly='" + aqgly + '\'' +
                ", status='" + status + '\'' +
                ", qtqk='" + qtqk + '\'' +
                ", aqgldate='" + aqgldate + '\'' +
                ", weather='" + weather + '\'' +
                ", safetycheckdetailList=" + safetycheckdetailList +
                ", maintenanceOfOrderItemBeen=" + maintenanceOfOrderItemBeen +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getClyj() {
        return clyj;
    }

    public void setClyj(String clyj) {
        this.clyj = clyj;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public List<MaintenanceOfOrderItemBean> getSafetycheckdetailList() {
        return safetycheckdetailList;
    }

    public void setSafetycheckdetailList(List<MaintenanceOfOrderItemBean> safetycheckdetailList) {
        this.safetycheckdetailList = safetycheckdetailList;
    }

    public ForeignCollection<MaintenanceOfOrderItemBean> getMaintenanceOfOrderItemBeen() {
        return maintenanceOfOrderItemBeen;
    }

    public void setMaintenanceOfOrderItemBeen(ForeignCollection<MaintenanceOfOrderItemBean> maintenanceOfOrderItemBeen) {
        this.maintenanceOfOrderItemBeen = maintenanceOfOrderItemBeen;
    }

    public List<MaintenanceLogBean> getProjacceptDetailList() {
        return projacceptDetailList;
    }

    public void setProjacceptDetailList(List<MaintenanceLogBean> projacceptDetailList) {
        this.projacceptDetailList = projacceptDetailList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getYhrzid() {
        return yhrzid;
    }

    public void setYhrzid(String yhrzid) {
        this.yhrzid = yhrzid;
    }

    public String getYhrzbno() {
        return yhrzbno;
    }

    public void setYhrzbno(String yhrzbno) {
        this.yhrzbno = yhrzbno;
    }

    public String getSgdwid() {
        return sgdwid;
    }

    public void setSgdwid(String sgdwid) {
        this.sgdwid = sgdwid;
    }

    public String getSgdwmc() {
        return sgdwmc;
    }

    public void setSgdwmc(String sgdwmc) {
        this.sgdwmc = sgdwmc;
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj;
    }

    public String getXcnr() {
        return xcnr;
    }

    public void setXcnr(String xcnr) {
        this.xcnr = xcnr;
    }

    public String getJcry() {
        return jcry;
    }

    public void setJcry(String jcry) {
        this.jcry = jcry;
    }

    public String getJcdate() {
        return jcdate;
    }

    public void setJcdate(String jcdate) {
        this.jcdate = jcdate;
    }

    public String getAqgly() {
        return aqgly;
    }

    public void setAqgly(String aqgly) {
        this.aqgly = aqgly;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQtqk() {
        return qtqk;
    }

    public void setQtqk(String qtqk) {
        this.qtqk = qtqk;
    }

    public String getAqgldate() {
        return aqgldate;
    }

    public void setAqgldate(String aqgldate) {
        this.aqgldate = aqgldate;
    }
}
