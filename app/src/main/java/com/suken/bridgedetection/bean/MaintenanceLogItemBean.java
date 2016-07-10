package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
@DatabaseTable(tableName = "tb_maintenancelogitem")
public class MaintenanceLogItemBean implements Serializable {
    @DatabaseField(generatedId = true) //主键自增加
    private long id;
    @DatabaseField
    private String createBy;
    @DatabaseField
    private String createtime;
    @DatabaseField
    private String creator;
    @DatabaseField
    private String updateBy;
    @DatabaseField
    private String updatetime;
    @DatabaseField
    private String updator;
    @DatabaseField
    private String versionno;
    @DatabaseField
    private String flag;
    @DatabaseField
    private String orgid;
    @DatabaseField
    private String bytzid;
    @DatabaseField
    private String xcbhid;
    @DatabaseField
    private String xcrzno;
    @DatabaseField
    private String bhid;
    @DatabaseField
    private String bhmc;
    @DatabaseField
    private String bhwz;
    @DatabaseField
    private String fx;
    @DatabaseField
    private String yhzh;
    @DatabaseField
    private String dw;
    @DatabaseField
    private String ygsl;
    @DatabaseField
    private String remark;
    @DatabaseField
    private String tjsj;


    /**
     * ==============================================================上传增加的字段================================================================================
     */
    @DatabaseField
    private String bytzidbytzid;
    @DatabaseField
    private String byrzid;
    @DatabaseField
    private String bytzno;
    @DatabaseField
    private String wcbl;
    @DatabaseField
    private String clmc;
    @DatabaseField
    private String picattachment;
    @DatabaseField
    private String vidattachment;
    @DatabaseField
    private String tpjd;
    @DatabaseField
    private String tpwd;





    private boolean isShow;
    private List<IVDesc> mImages = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo = new ArrayList<IVDesc>();
    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceLogBean maintenanceLogBean;



    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    @ForeignCollectionField
    private ForeignCollection<IVDesc> iDescs;
    @ForeignCollectionField
    private ForeignCollection<IVDesc> vDescs;

    @Override
    public String toString() {
        return "MaintenanceLogItemBean{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", flag='" + flag + '\'' +
                ", orgid='" + orgid + '\'' +
                ", bytzid='" + bytzid + '\'' +
                ", xcbhid='" + xcbhid + '\'' +
                ", xcrzno='" + xcrzno + '\'' +
                ", bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", bhwz='" + bhwz + '\'' +
                ", fx='" + fx + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", dw='" + dw + '\'' +
                ", ygsl='" + ygsl + '\'' +
                ", remark='" + remark + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", bytzidbytzid='" + bytzidbytzid + '\'' +
                ", byrzid='" + byrzid + '\'' +
                ", bytzno='" + bytzno + '\'' +
                ", wcbl='" + wcbl + '\'' +
                ", clmc='" + clmc + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", vidattachment='" + vidattachment + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", isShow=" + isShow +
                ", mImages=" + mImages +
                ", mVideo=" + mVideo +
                ", maintenanceLogBean=" + maintenanceLogBean +
                ", iDescs=" + iDescs +
                ", vDescs=" + vDescs +
                '}';
    }

    public MaintenanceLogItemBean() {
    }

    public MaintenanceLogItemBean(Long id, String createBy, String createtime, String creator, String updateBy, String updatetime, String updator, String versionno, String flag, String orgid, String bytzid, String xcbhid, String xcrzno, String bhid, String bhmc, String bhwz, String fx, String yhzh, String dw, String ygsl, String remark, boolean isShow, List<IVDesc> mImages, List<IVDesc> mVideo, MaintenanceLogBean maintenanceLogBean, ForeignCollection<IVDesc> iDescs, ForeignCollection<IVDesc> vDescs) {
        this.id = id;
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.flag = flag;
        this.orgid = orgid;
        this.bytzid = bytzid;
        this.xcbhid = xcbhid;
        this.xcrzno = xcrzno;
        this.bhid = bhid;
        this.bhmc = bhmc;
        this.bhwz = bhwz;
        this.fx = fx;
        this.yhzh = yhzh;
        this.dw = dw;
        this.ygsl = ygsl;
        this.remark = remark;
        this.isShow = isShow;
        this.mImages = mImages;
        this.mVideo = mVideo;
        this.maintenanceLogBean = maintenanceLogBean;
        this.iDescs = iDescs;
        this.vDescs = vDescs;
    }


    public String getBytzidbytzid() {
        return bytzidbytzid;
    }

    public void setBytzidbytzid(String bytzidbytzid) {
        this.bytzidbytzid = bytzidbytzid;
    }

    public String getByrzid() {
        return byrzid;
    }

    public void setByrzid(String byrzid) {
        this.byrzid = byrzid;
    }

    public String getBytzno() {
        return bytzno;
    }

    public void setBytzno(String bytzno) {
        this.bytzno = bytzno;
    }

    public String getWcbl() {
        return wcbl;
    }

    public void setWcbl(String wcbl) {
        this.wcbl = wcbl;
    }

    public String getClmc() {
        return clmc;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc;
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

    public void setId(long id) {
        this.id = id;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
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

    public String getXcrzno() {
        return xcrzno;
    }

    public void setXcrzno(String xcrzno) {
        this.xcrzno = xcrzno;
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

    public String getYgsl() {
        return ygsl;
    }

    public void setYgsl(String ygsl) {
        this.ygsl = ygsl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
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

    public MaintenanceLogBean getMaintenanceLogBean() {
        return maintenanceLogBean;
    }

    public void setMaintenanceLogBean(MaintenanceLogBean maintenanceLogBean) {
        this.maintenanceLogBean = maintenanceLogBean;
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
