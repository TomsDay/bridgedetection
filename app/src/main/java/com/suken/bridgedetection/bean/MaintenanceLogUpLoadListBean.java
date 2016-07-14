package com.suken.bridgedetection.bean;

import java.io.Serializable;

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
