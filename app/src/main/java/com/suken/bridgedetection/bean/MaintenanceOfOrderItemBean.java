package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
@DatabaseTable(tableName = "tb_maintenanceoforderitem")
public class MaintenanceOfOrderItemBean implements Serializable {
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
//    @DatabaseField
//    private String aqjcid;
    @DatabaseField
    private String jcx;
    @DatabaseField
    private String jczt;
    @DatabaseField
    private String picattachment;
    @DatabaseField
    private String vidattachment;
    @DatabaseField
    private String tpjd;
    @DatabaseField
    private String tpwd;

    private int type;

//    private String tjsj;
//
    private List<IVDesc> mImages = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo = new ArrayList<IVDesc>();


    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceOfOrderBean maintenanceOfOrderBean;

    @ForeignCollectionField
    private ForeignCollection<IVDesc> iDescs;
    @ForeignCollectionField
    private ForeignCollection<IVDesc> vDescs;

    public MaintenanceOfOrderItemBean() {
    }

    @Override
    public String toString() {
        return "MaintenanceOfOrderItemBean{" +
                "id=" + id +
                ", jcx='" + jcx + '\'' +
                ", jczt='" + jczt + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", vidattachment='" + vidattachment + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", type=" + type +
                ", mImages=" + mImages +
                ", mVideo=" + mVideo +
                ", maintenanceOfOrderBean=" + maintenanceOfOrderBean +
                ", iDescs=" + iDescs +
                ", vDescs=" + vDescs +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJcx() {
        return jcx;
    }

    public void setJcx(String jcx) {
        this.jcx = jcx;
    }

    public String getJczt() {
        return jczt;
    }

    public void setJczt(String jczt) {
        this.jczt = jczt;
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

    public MaintenanceOfOrderBean getMaintenanceOfOrderBean() {
        return maintenanceOfOrderBean;
    }

    public void setMaintenanceOfOrderBean(MaintenanceOfOrderBean maintenanceOfOrderBean) {
        this.maintenanceOfOrderBean = maintenanceOfOrderBean;
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
