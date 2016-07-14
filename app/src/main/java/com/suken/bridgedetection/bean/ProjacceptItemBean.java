package com.suken.bridgedetection.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ProjacceptItemBean implements Serializable{
    private String createBy;
    private String createtime;
    private String creator;
    private String updateBy;
    private String updatetime;
    private String updator;
    private String versionno;
    private String flag;
    private String id;
    private String orgid;
    private String bhid;
    private String bhmc;
    private String fx;
    private String yhzh;
    private String dw;
    private String dj;
    private String wxsl;
    private String ysjg ;
    private String picattachment;
    private String vidattachment;
    private String tpjd;
    private String tpwd;

    @Override
    public String toString() {
        return "ProjacceptItemBean{" +
                "createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", flag='" + flag + '\'' +
                ", id='" + id + '\'' +
                ", orgid='" + orgid + '\'' +
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
                '}';
    }

    public ProjacceptItemBean() {
    }

    public ProjacceptItemBean(String createBy, String createtime, String creator, String updateBy, String updatetime, String updator, String versionno, String flag, String id, String orgid, String bhid, String bhmc, String fx, String yhzh, String dw, String dj, String wxsl, String ysjg, String picattachment, String vidattachment, String tpjd, String tpwd) {
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.flag = flag;
        this.id = id;
        this.orgid = orgid;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
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
}
