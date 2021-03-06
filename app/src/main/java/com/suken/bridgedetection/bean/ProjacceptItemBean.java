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

    @DatabaseField
    private String versionno;
    @DatabaseField
    private String createBy;
    @DatabaseField
    private String createtime;
    @DatabaseField
    private String creator;
    @DatabaseField
    private String flag;
    @DatabaseField
    private String gcysid   ;
    @DatabaseField
    private String bytzid;
    @DatabaseField
    private String xcbhid;
    @DatabaseField
    private String byrzid;

    /** 材料id */
    @DatabaseField
    private String clid;
    /** 材料名称  */
    @DatabaseField
    private String clmc;
    /** 材料规格 */
    @DatabaseField
    private String clgg;
    /** 材料型号  */
    @DatabaseField
    private String clxh;
    /** 材料单位  */
    @DatabaseField
    private String cldw;
    /** 材料数量 */
    @DatabaseField
    private String clsl;


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

    @Override
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
                ", versionno='" + versionno + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", flag='" + flag + '\'' +
                ", gcysid='" + gcysid + '\'' +
                ", bytzid='" + bytzid + '\'' +
                ", xcbhid='" + xcbhid + '\'' +
                ", byrzid='" + byrzid + '\'' +
                ", clid='" + clid + '\'' +
                ", clmc='" + clmc + '\'' +
                ", clgg='" + clgg + '\'' +
                ", clxh='" + clxh + '\'' +
                ", cldw='" + cldw + '\'' +
                ", clsl='" + clsl + '\'' +
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


    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getClmc() {
        return clmc;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc;
    }

    public String getClgg() {
        return clgg;
    }

    public void setClgg(String clgg) {
        this.clgg = clgg;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getCldw() {
        return cldw;
    }

    public void setCldw(String cldw) {
        this.cldw = cldw;
    }

    public String getClsl() {
        return clsl;
    }

    public void setClsl(String clsl) {
        this.clsl = clsl;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGcysid() {
        return gcysid;
    }

    public void setGcysid(String gcysid) {
        this.gcysid = gcysid;
    }

    public String getBytzid() {
        return bytzid;
    }

    public void setBytzid(String bytzid) {
        this.bytzid = bytzid;
    }

    public String getXcbhid() {
        return xcbhid;
    }

    public void setXcbhid(String xcbhid) {
        this.xcbhid = xcbhid;
    }

    public String getByrzid() {
        return byrzid;
    }

    public void setByrzid(String byrzid) {
        this.byrzid = byrzid;
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
