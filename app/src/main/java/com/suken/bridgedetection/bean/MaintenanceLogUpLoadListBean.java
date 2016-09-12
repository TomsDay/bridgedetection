package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MaintenanceLogUpLoadListBean implements Serializable{
    private String bhid;
    private String bhmc;
    private String fx;
    private String yhzh;
    private String dw;
    private String dj;
    private String wxsl;
    private String clmc;
    private String picattachment;
    private String vidattachment;
    private String state;
    private String tpjd;
    private String tpwd;

    private String clid;
    private String clgg;
    private String clxh;
    private String cldw;
    private String clsl;


    private List<IVDesc> mImages = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo = new ArrayList<IVDesc>();



    public MaintenanceLogUpLoadListBean(String bhid, String bhmc, String fx, String yhzh, String dw, String dj, String wxsl, String clmc) {
        this.bhid = bhid;
        this.bhmc = bhmc;
        this.fx = fx;
        this.yhzh = yhzh;
        this.dw = dw;
        this.dj = dj;
        this.wxsl = wxsl;
        this.clmc = clmc;
    }

    public MaintenanceLogUpLoadListBean() {
    }

    @Override
    public String toString() {
        return "MaintenanceLogUpLoadListBean{" +
                "bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", fx='" + fx + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", dw='" + dw + '\'' +
                ", dj='" + dj + '\'' +
                ", wxsl='" + wxsl + '\'' +
                ", clmc='" + clmc + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", vidattachment='" + vidattachment + '\'' +
                ", state='" + state + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", clid='" + clid + '\'' +
                ", clgg='" + clgg + '\'' +
                ", clxh='" + clxh + '\'' +
                ", cldw='" + cldw + '\'' +
                ", clsl='" + clsl + '\'' +
                ", mImages=" + mImages +
                ", mVideo=" + mVideo +
                '}';
    }



    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getClmc() {
        return clmc;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc;
    }
}
