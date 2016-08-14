package com.suken.bridgedetection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MaintenanceLogUpLoadBean implements Serializable{

    /**
     * wxlx : 2
     * wxbmid : 10000005572385
     * wxbmmc : 石家庄公路养护管理公司
     * wxrq : 2016-06-03 16:28:26
     * weather : 晴
     * jcry : 超级管理员
     * fzry : 张学智
     * status : 2
     * bytzid : 10000007030001
     * bytzno : 2016061600002
     * byrzzt : 1
     * aaa : 111
     * maintenlogdetailList : [{"bhid":10000006740001,"bhmc":"路面损坏","fx":"上行内侧","yhzh":111.11,"dw":"米","dj":0,"wxsl":11,"clmc":"贵重材料"}]
     */

    private String gydwId;
    private String gydwName;
    private String wxlx;
    private String wxbmid;
    private String wxbmmc;
    private String wxrq;
    private String weather;
    private String jcry;
    private String fzry;
    private String status;
    private String bytzid;
    private String bytzno;
    private String byrzzt;
    /**
     * bhid : 10000006740001
     * bhmc : 路面损坏
     * fx : 上行内侧
     * yhzh : 111.11
     * dw : 米
     * dj : 0
     * wxsl : 11
     * clmc : 贵重材料
     */

    private List<MaintenanceLogUpLoadListBean> maintenlogdetailList = new ArrayList<MaintenanceLogUpLoadListBean>();

    @Override
    public String toString() {
        return "MaintenanceLogUpLoadBean{" +
                "gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", wxlx='" + wxlx + '\'' +
                ", wxbmid='" + wxbmid + '\'' +
                ", wxbmmc='" + wxbmmc + '\'' +
                ", wxrq='" + wxrq + '\'' +
                ", weather='" + weather + '\'' +
                ", jcry='" + jcry + '\'' +
                ", fzry='" + fzry + '\'' +
                ", status='" + status + '\'' +
                ", bytzid='" + bytzid + '\'' +
                ", bytzno='" + bytzno + '\'' +
                ", byrzzt='" + byrzzt + '\'' +
                ", maintenlogdetailList=" + maintenlogdetailList +
                '}';
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

    public String getWxlx() {
        return wxlx;
    }

    public void setWxlx(String wxlx) {
        this.wxlx = wxlx;
    }

    public String getWxbmid() {
        return wxbmid;
    }

    public void setWxbmid(String wxbmid) {
        this.wxbmid = wxbmid;
    }

    public String getWxbmmc() {
        return wxbmmc;
    }

    public void setWxbmmc(String wxbmmc) {
        this.wxbmmc = wxbmmc;
    }

    public String getWxrq() {
        return wxrq;
    }

    public void setWxrq(String wxrq) {
        this.wxrq = wxrq;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getJcry() {
        return jcry;
    }

    public void setJcry(String jcry) {
        this.jcry = jcry;
    }

    public String getFzry() {
        return fzry;
    }

    public void setFzry(String fzry) {
        this.fzry = fzry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBytzid() {
        return bytzid;
    }

    public void setBytzid(String bytzid) {
        this.bytzid = bytzid;
    }

    public String getBytzno() {
        return bytzno;
    }

    public void setBytzno(String bytzno) {
        this.bytzno = bytzno;
    }

    public String getByrzzt() {
        return byrzzt;
    }

    public void setByrzzt(String byrzzt) {
        this.byrzzt = byrzzt;
    }

    public List<MaintenanceLogUpLoadListBean> getMaintenlogdetailList() {
        return maintenlogdetailList;
    }

    public void setMaintenlogdetailList(List<MaintenanceLogUpLoadListBean> maintenlogdetailList) {
        this.maintenlogdetailList = maintenlogdetailList;
    }


}
