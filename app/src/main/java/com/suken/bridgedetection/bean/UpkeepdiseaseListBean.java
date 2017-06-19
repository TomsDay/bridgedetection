package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by liangshuai on 2017/5/9.
 */
@DatabaseTable(tableName = "tb_upkeepdiseaseListBean")
public class UpkeepdiseaseListBean implements Serializable {
    /**
     * 根据选择的多笔病害记录生成多笔明细
     "upkeepdiseaseList": [  //检查项目明细表，多条集合
     {
     " xcbhid": 10000006740001, //巡查日志病害记录id  自动带入 id
     " xcrzno": "201606100011", //巡查日志单号  自动带yhInspNo
     " bhid": "10000003260001", //病害id自动带入bhid
     " bhmc": "路面损坏", //病害名称自动带入 bhmc
     " bhwz": "", //病害详情自动带入 bhwz
     " fx": "上行内侧", //方向自动带入 fx
     " yhzh": "111.11", //桩号自动带入 yhzh
     " dw": "米", //单位自动带入 dw
     " ygsl": "11",  //预估数量自动带入 ygsl
     " remark": ""  /病害简述 自动带入 remark
     }
     ]
     */
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
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

    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private UploadUpkeepnoticeBean uploadUpkeepnoticeBean;

    public UpkeepdiseaseListBean(int id, String xcbhid, String xcrzno, String bhid, String bhmc, String bhwz, String fx, String yhzh, String dw, String ygsl, String remark) {
        this.id = id;
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
    }

    public UpkeepdiseaseListBean() {
    }

    @Override
    public String toString() {
        return "UpkeepdiseaseListBean{" +
                "id=" + id +
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
                '}';
    }



    public UploadUpkeepnoticeBean getUploadUpkeepnoticeBean() {
        return uploadUpkeepnoticeBean;
    }

    public void setUploadUpkeepnoticeBean(UploadUpkeepnoticeBean uploadUpkeepnoticeBean) {
        this.uploadUpkeepnoticeBean = uploadUpkeepnoticeBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

