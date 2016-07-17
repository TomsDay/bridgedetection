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
@DatabaseTable(tableName = "tb_projacceptitembean")
public class ProjacceptItemBean implements Serializable{
    @DatabaseField(generatedId = true) //主键自增加
    private int ids;
    @DatabaseField
    private String bhid;
    @DatabaseField
    private String bhmc;
    @DatabaseField
    private String fx;
    @DatabaseField
    private String yhzh;
    @DatabaseField
    private String dw;
    @DatabaseField
    private String dj;
    @DatabaseField
    private String wxsl;
    @DatabaseField
    private String ysjg ;
    @DatabaseField
    private String picattachment;
    @DatabaseField
    private String vidattachment;
    @DatabaseField
    private String tpjd;
    @DatabaseField
    private String tpwd;

    private int state;

    private List<IVDesc> mImages = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo = new ArrayList<IVDesc>();

    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private ProjectAcceptanceBean projectAcceptanceBean;

    @ForeignCollectionField
    private ForeignCollection<IVDesc> iDescs;
    @ForeignCollectionField
    private ForeignCollection<IVDesc> vDescs;

    public String toString() {
        return "ProjacceptItemBean{" +
                "ids=" + ids +
                ", bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", fx='" + fx + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", dw='" + dw + '\'' +
                ", dj='" + dj + '\'' +
                ", wxsl='" + wxsl + '\'' +
                ", ysjg='" + ysjg + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", vidattachment='" + vidattachment + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", state=" + state +
                ", mImages=" + mImages +
                ", mVideo=" + mVideo +
                ", projectAcceptanceBean=" + projectAcceptanceBean +
                ", iDescs=" + iDescs +
                ", vDescs=" + vDescs +
                '}';
    }

    public ProjacceptItemBean() {
    }

    public ProjacceptItemBean(int ids, String bhid, String bhmc, String fx, String yhzh, String dw, String dj, String wxsl, String ysjg, String picattachment, String vidattachment, String tpjd, String tpwd, int state, List<IVDesc> mImages, List<IVDesc> mVideo, ProjectAcceptanceBean projectAcceptanceBean, ForeignCollection<IVDesc> iDescs, ForeignCollection<IVDesc> vDescs) {
        this.ids = ids;
        this.bhid = bhid;
        this.bhmc = bhmc;
        this.fx = fx;
        this.yhzh = yhzh;
        this.dw = dw;
        this.dj = dj;
        this.wxsl = wxsl;
        this.ysjg = ysjg;
        this.picattachment = picattachment;
        this.vidattachment = vidattachment;
        this.tpjd = tpjd;
        this.tpwd = tpwd;
        this.state = state;
        this.mImages = mImages;
        this.mVideo = mVideo;
        this.projectAcceptanceBean = projectAcceptanceBean;
        this.iDescs = iDescs;
        this.vDescs = vDescs;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
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

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getWxsl() {
        return wxsl;
    }

    public void setWxsl(String wxsl) {
        this.wxsl = wxsl;
    }

    public String getYsjg() {
        return ysjg;
    }

    public void setYsjg(String ysjg) {
        this.ysjg = ysjg;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public ProjectAcceptanceBean getProjectAcceptanceBean() {
        return projectAcceptanceBean;
    }

    public void setProjectAcceptanceBean(ProjectAcceptanceBean projectAcceptanceBean) {
        this.projectAcceptanceBean = projectAcceptanceBean;
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
