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
@DatabaseTable(tableName = "tb_maintenancetableitem")
public class MaintenanceTableItemBean implements Serializable{
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    private String fx;
    @DatabaseField
    private String yhzh;
    @DatabaseField
    private String zhfw;
    @DatabaseField
    private String bhid;
    @DatabaseField
    private String bhmc;
    @DatabaseField
    private String bhwz;
    @DatabaseField
    private String dw;
    @DatabaseField
    private String ygsl;
    @DatabaseField
    private String jcsj;
    @DatabaseField
    private String remark;
    @DatabaseField
    private String picattachment;
    @DatabaseField
    private String vidattachment;
    @DatabaseField
    private String tjsj;
    @DatabaseField
    private String tpjd;
    @DatabaseField
    private String tpwd;
    @DatabaseField
    private String yhzt= "1";
    @DatabaseField
    private String cus1="";
    @DatabaseField
    private String isxd="1";

    private boolean isShow;

    private List<IVDesc> mImages = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo = new ArrayList<IVDesc>();

    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceTableBean maintenanceTableBean;

    @ForeignCollectionField
    private ForeignCollection<IVDesc> iDescs;
    @ForeignCollectionField
    private ForeignCollection<IVDesc> vDescs;

    public MaintenanceTableItemBean(int id, String fx, String yhzh, String zhfw, String bhid, String bhmc, String bhwz, String dw, String ygsl, String jcsj, String remark, String picattachment, String vidattachment, String tjsj, String tpjd, String tpwd, String yhzt, String cus1, String isxd, String remark1, boolean isShow) {
        this.id = id;
        this.fx = fx;
        this.yhzh = yhzh;
        this.zhfw = zhfw;
        this.bhid = bhid;
        this.bhmc = bhmc;
        this.bhwz = bhwz;
        this.dw = dw;
        this.ygsl = ygsl;
        this.jcsj = jcsj;
        this.remark = remark;
        this.picattachment = picattachment;
        this.vidattachment = vidattachment;
        this.tjsj = tjsj;
        this.tpjd = tpjd;
        this.tpwd = tpwd;
        this.yhzt = yhzt;
        this.cus1 = cus1;
        this.isxd = isxd;
        this.remark = remark1;
        this.isShow = isShow;
    }


    public MaintenanceTableItemBean() {
    }

    @Override
    public String toString() {
        return "MaintenanceTableItemBean{" +
                "id=" + id +
                ", fx='" + fx + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", zhfw='" + zhfw + '\'' +
                ", bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", bhwz='" + bhwz + '\'' +
                ", dw='" + dw + '\'' +
                ", ygsl='" + ygsl + '\'' +
                ", jcsj='" + jcsj + '\'' +
                ", remark='" + remark + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", vidattachment='" + vidattachment + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", yhzt='" + yhzt + '\'' +
                ", cus1='" + cus1 + '\'' +
                ", isxd='" + isxd + '\'' +
                ", isShow=" + isShow +
                ", mImages=" + mImages +
                ", mVideo=" + mVideo +
                ", maintenanceTableBean=" + maintenanceTableBean +
                ", iDescs=" + iDescs +
                ", vDescs=" + vDescs +
                '}';
    }

    public String getIsxd() {
        return isxd;
    }

    public void setIsxd(String isxd) {
        this.isxd = isxd;
    }

    public String getCus1() {
        return cus1;
    }

    public void setCus1(String cus1) {
        this.cus1 = cus1;
    }

    public String getZhfw() {
        return zhfw;
    }

    public void setZhfw(String zhfw) {
        this.zhfw = zhfw;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public String getBhid() {
        return bhid;
    }

    public void setBhid(String bhid) {
        this.bhid = bhid;
    }

    public String getBhmc() {
        return bhmc;
    }

    public void setBhmc(String bhmc) {
        this.bhmc = bhmc;
    }

    public String getBhwz() {
        return bhwz;
    }

    public void setBhwz(String bhwz) {
        this.bhwz = bhwz;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getYgsl() {
        return ygsl;
    }

    public void setYgsl(String ygsl) {
        this.ygsl = ygsl;
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPicattachment() {
        return picattachment;
    }

    public void setPicattachment(String picattachment) {
        this.picattachment = picattachment;
    }

    public String getVidattachment() {
        return vidattachment;
    }

    public void setVidattachment(String vidattachment) {
        this.vidattachment = vidattachment;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }

    public String getTpjd() {
        return tpjd;
    }

    public void setTpjd(String tpjd) {
        this.tpjd = tpjd;
    }

    public String getTpwd() {
        return tpwd;
    }

    public void setTpwd(String tpwd) {
        this.tpwd = tpwd;
    }

    public String getYhzt() {
        return yhzt;
    }

    public void setYhzt(String yhzt) {
        this.yhzt = yhzt;
    }

    public List<IVDesc> getmImages() {
        return mImages;
    }

    public void setmImages(List<IVDesc> mImages) {
        this.mImages = mImages;
    }

    public List<IVDesc> getmVideo() {
        return mVideo;
    }

    public void setmVideo(List<IVDesc> mVideo) {
        this.mVideo = mVideo;
    }

    public MaintenanceTableBean getMaintenanceTableBean() {
        return maintenanceTableBean;
    }

    public void setMaintenanceTableBean(MaintenanceTableBean maintenanceTableBean) {
        this.maintenanceTableBean = maintenanceTableBean;
    }

    public ForeignCollection<IVDesc> getiDescs() {
        return iDescs;
    }

    public void setiDescs(ForeignCollection<IVDesc> iDescs) {
        this.iDescs = iDescs;
    }

    public ForeignCollection<IVDesc> getvDescs() {
        return vDescs;
    }

    public void setvDescs(ForeignCollection<IVDesc> vDescs) {
        this.vDescs = vDescs;
    }
}
