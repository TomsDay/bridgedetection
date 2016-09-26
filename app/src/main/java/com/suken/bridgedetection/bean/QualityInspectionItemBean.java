package com.suken.bridgedetection.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/26.
 */
public class QualityInspectionItemBean implements Serializable{
    private String ysdid;
    private String ysdno;
    private String sgdwid;
    private String sgdwmc;
    private String sgks;
    private String sgjs;
    private String ysjl;
    private String bhid;
    private String bhmc;
    private String yhzh;
    private String dw;
    private String wxsl;

    public QualityInspectionItemBean() {
    }

    public QualityInspectionItemBean(String ysdid, String ysdno, String sgdwid, String sgdwmc, String sgks, String sgjs, String ysjl, String bhid, String bhmc, String yhzh, String dw, String wxsl) {
        this.ysdid = ysdid;
        this.ysdno = ysdno;
        this.sgdwid = sgdwid;
        this.sgdwmc = sgdwmc;
        this.sgks = sgks;
        this.sgjs = sgjs;
        this.ysjl = ysjl;
        this.bhid = bhid;
        this.bhmc = bhmc;
        this.yhzh = yhzh;
        this.dw = dw;
        this.wxsl = wxsl;
    }

    @Override
    public String toString() {
        return "QualityInspectionItemBean{" +
                "ysdid='" + ysdid + '\'' +
                ", ysdno='" + ysdno + '\'' +
                ", sgdwid='" + sgdwid + '\'' +
                ", sgdwmc='" + sgdwmc + '\'' +
                ", sgks='" + sgks + '\'' +
                ", sgjs='" + sgjs + '\'' +
                ", ysjl='" + ysjl + '\'' +
                ", bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", dw='" + dw + '\'' +
                ", wxsl='" + wxsl + '\'' +
                '}';
    }

    public String getYsdid() {
        return ysdid;
    }

    public void setYsdid(String ysdid) {
        this.ysdid = ysdid;
    }

    public String getYsdno() {
        return ysdno;
    }

    public void setYsdno(String ysdno) {
        this.ysdno = ysdno;
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

    public String getSgks() {
        return sgks;
    }

    public void setSgks(String sgks) {
        this.sgks = sgks;
    }

    public String getSgjs() {
        return sgjs;
    }

    public void setSgjs(String sgjs) {
        this.sgjs = sgjs;
    }

    public String getYsjl() {
        return ysjl;
    }

    public void setYsjl(String ysjl) {
        this.ysjl = ysjl;
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

    public String getWxsl() {
        return wxsl;
    }

    public void setWxsl(String wxsl) {
        this.wxsl = wxsl;
    }
}
