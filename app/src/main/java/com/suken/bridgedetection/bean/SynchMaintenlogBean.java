package com.suken.bridgedetection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class SynchMaintenlogBean implements Serializable{
    private String createBy;
    private String createtime;
    private String creator;
    private String updateBy;
    private String updatetime;
    private String updator;
    private String versionno;
    private String orgid;
    private String flag;
    private String gldwId;
    private String gldwName;
    private String id;
    private String bno;
    private String wxlx;
    private String wxbmid;
    private String wxbmmc;
    private String wxrq;
    private String weather;
    private String jcry;
    private String fzry;
    private String tjsj;
    private String status;
    private String bytzid;
    private String bytzno;
    private List<SynchMaintenlogListBean> maintenlogDetailList = new ArrayList<>();

    public SynchMaintenlogBean() {
    }

    public SynchMaintenlogBean(String createBy, String createtime, String creator, String updateBy, String updatetime, String updator, String versionno, String orgid, String flag, String gldwId, String gldwName, String id, String bno, String wxlx, String wxbmid, String wxbmmc, String wxrq, String weather, String jcry, String fzry, String tjsj, String status, String bytzid, String bytzno, List<SynchMaintenlogListBean> maintenlogDetailList) {
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.orgid = orgid;
        this.flag = flag;
        this.gldwId = gldwId;
        this.gldwName = gldwName;
        this.id = id;
        this.bno = bno;
        this.wxlx = wxlx;
        this.wxbmid = wxbmid;
        this.wxbmmc = wxbmmc;
        this.wxrq = wxrq;
        this.weather = weather;
        this.jcry = jcry;
        this.fzry = fzry;
        this.tjsj = tjsj;
        this.status = status;
        this.bytzid = bytzid;
        this.bytzno = bytzno;
        this.maintenlogDetailList = maintenlogDetailList;
    }

    @Override
    public String toString() {
        return "SynchMaintenlogBean{" +
                "createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", orgid='" + orgid + '\'' +
                ", flag='" + flag + '\'' +
                ", gldwId='" + gldwId + '\'' +
                ", gldwName='" + gldwName + '\'' +
                ", id='" + id + '\'' +
                ", bno='" + bno + '\'' +
                ", wxlx='" + wxlx + '\'' +
                ", wxbmid='" + wxbmid + '\'' +
                ", wxbmmc='" + wxbmmc + '\'' +
                ", wxrq='" + wxrq + '\'' +
                ", weather='" + weather + '\'' +
                ", jcry='" + jcry + '\'' +
                ", fzry='" + fzry + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", status='" + status + '\'' +
                ", bytzid='" + bytzid + '\'' +
                ", bytzno='" + bytzno + '\'' +
                ", maintenlogDetailList=" + maintenlogDetailList +
                '}';
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

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGldwId() {
        return gldwId;
    }

    public void setGldwId(String gldwId) {
        this.gldwId = gldwId;
    }

    public String getGldwName() {
        return gldwName;
    }

    public void setGldwName(String gldwName) {
        this.gldwName = gldwName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
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

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
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

    public List<SynchMaintenlogListBean> getMaintenlogDetailList() {
        return maintenlogDetailList;
    }

    public void setMaintenlogDetailList(List<SynchMaintenlogListBean> maintenlogDetailList) {
        this.maintenlogDetailList = maintenlogDetailList;
    }
}
