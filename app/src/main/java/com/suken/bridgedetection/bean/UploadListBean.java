package com.suken.bridgedetection.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/3.
 */
public class UploadListBean implements Serializable{
    private String fx;
    private String yhzh;
    private String bhid;
    private String bhmc;
    private String bhwz;
    private String dw;
    private String ygsl;
    private String jcsj;
    private String remark;
    private String picattachment;
    private String vidattachment;
    private String bno;
    private String tjsj;
    private String tpjd;
    private String tpwd;
    private String yhzt;


    @Override
    public String toString() {
        return "UploadListBean{" +
                "fx='" + fx + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", bhwz='" + bhwz + '\'' +
                ", dw='" + dw + '\'' +
                ", ygsl='" + ygsl + '\'' +
                ", jcsj='" + jcsj + '\'' +
                ", remark='" + remark + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", vidattachment='" + vidattachment + '\'' +
                ", bno='" + bno + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", yhzt='" + yhzt + '\'' +
                '}';
    }

    public UploadListBean() {
    }

    public UploadListBean(String fx, String yhzh, String bhid, String bhmc, String bhwz, String dw, String ygsl, String jcsj, String remark, String picattachment, String vidattachment, String tjsj, String tpjd, String tpwd, String yhzt) {
        this.fx = fx;
        this.yhzh = yhzh;
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
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
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
}
