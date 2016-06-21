package com.suken.bridgedetection.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/21.
 */
public class MaintenanceLogListBean implements Serializable{


    /**
     * createBy : 1
     * createtime : {}
     * creator : 超级管理员
     * flag : 0
     * orgid : 0
     * updateBy : 10000000050039
     * updatetime : {}
     * updator : 李兵
     * versionno : 3
     * gydwId : 10000000220011
     * gydwName : 石家庄养护工区
     * id : 10000001220001
     * dwmc : 石家庄公路养护管理公司
     * dz : 石家庄市师范街28号
     * fzr : 晓谷
     * lxdh : 13888992008
     * lx : 私营公司
     * bz :
     * gzz :
     */

    private String createBy;
    private String createtime;
    private String creator;
    private String flag;
    private String orgid;
    private String updateBy;
    private String updatetime;
    private String updator;
    private String versionno;
    private String gydwId;
    private String gydwName;
    private String id;
    private String dwmc;
    private String dz;
    private String fzr;
    private String lxdh;
    private String lx;
    private String bz;
    private String gzz;

    public MaintenanceLogListBean(String createBy, String createtime, String creator, String flag, String orgid,
                                  String updateBy, String updatetime, String updator, String versionno, String gydwId,
                                  String gydwName, String id, String dwmc, String dz, String fzr, String lxdh, String lx,
                                  String bz, String gzz) {
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.flag = flag;
        this.orgid = orgid;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.id = id;
        this.dwmc = dwmc;
        this.dz = dz;
        this.fzr = fzr;
        this.lxdh = lxdh;
        this.lx = lx;
        this.bz = bz;
        this.gzz = gzz;
    }

    @Override
    public String toString() {
        return "MaintenanceLogListBean{" +
                "createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", flag='" + flag + '\'' +
                ", orgid='" + orgid + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", id='" + id + '\'' +
                ", dwmc='" + dwmc + '\'' +
                ", dz='" + dz + '\'' +
                ", fzr='" + fzr + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", lx='" + lx + '\'' +
                ", bz='" + bz + '\'' +
                ", gzz='" + gzz + '\'' +
                '}';
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
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

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGzz() {
        return gzz;
    }

    public void setGzz(String gzz) {
        this.gzz = gzz;
    }
}
