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

    @DatabaseField
    private String orgid;
    @DatabaseField
    private String versionno;
    @DatabaseField
    private String createBy;
    @DatabaseField
    private String creator;
    @DatabaseField
    private String createtime;
    @DatabaseField
    private String updateBy;
    @DatabaseField
    private String updator;
    @DatabaseField
    private String updatetime;
    @DatabaseField
    private String flag;
    @DatabaseField
    private String aqjcid;
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

    private String tjsj;

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

    public MaintenanceOfOrderItemBean(int id, String orgid, String versionno, String createBy, String creator, String createtime, String updateBy, String updator, String updatetime, String flag, String aqjcid, String jcx, String jczt, String picattachment, String vidattachment, String tpjd, String tpwd) {
        this.id = id;
        this.orgid = orgid;
        this.versionno = versionno;
        this.createBy = createBy;
        this.creator = creator;
        this.createtime = createtime;
        this.updateBy = updateBy;
        this.updator = updator;
        this.updatetime = updatetime;
        this.flag = flag;
        this.aqjcid = aqjcid;
        this.jcx = jcx;
        this.jczt = jczt;
        this.picattachment = picattachment;
        this.vidattachment = vidattachment;
        this.tpjd = tpjd;
        this.tpwd = tpwd;
    }


    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAqjcid() {
        return aqjcid;
    }

    public void setAqjcid(String aqjcid) {
        this.aqjcid = aqjcid;
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
}
