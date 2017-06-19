package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangshuai on 2017/5/9.
 */
@DatabaseTable(tableName = "tb_uploadUpkeepnoticeBean")
public class UploadUpkeepnoticeBean implements Serializable {
    /**
     * {
     "gldwId": 10000000220011, //管理单位ID
     "gldwName": "青银高速石家庄养护工区", //管理单位名称
     "tzld": "628.300.10-640.373.56", //路段  取第一笔病害的巡查路段
     "wxlx": "2", //维修类型 1自养 2 外包  参考业务字典分类ID：10000006260011  选择
     "wxbmid": "10000005572385", //维修部门id  选取外协单位基本信息id
     "wxbmmc": "石家庄公路养护管理公司", //维修部门名称选取外协单位基本信息 单位名称
     "wxks": "2016-06-16 17:28:30", //维修期限开始 填写
     "wxjs": "2016-06-26 17:28:32", //维修期限结束 填写
     "weather": "晴", //天气 填写
     "qfry": "张学智", //签发人 带入用户名称
     "qfrq": "2016-06-12 00:00:00", //签发日期  带入当前日期
     "zlyq": "不得有遗漏，路面平整", //质量要求  选取质量要求信息 可选择多笔 用分号连接
     "bcsm": "", //补充说明 填写
     "tzdzt": "1",// 通知单状态 1 通知下达 2 维修保养 3 检查验收 参考业务字典分类ID：10000006260010  默认填写 1

     根据选择的多笔病害记录生成多笔明细
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
     }
     ]

     */
    @DatabaseField(generatedId = true) //主键自增加
    private int id;

    @DatabaseField
    private String userid;
    @DatabaseField
    private String gydwId;
    @DatabaseField
    private String gydwName;
    @DatabaseField
    private String tzld;
    @DatabaseField
    private String wxlx;
    @DatabaseField
    private String wxbmid;
    @DatabaseField
    private String wxbmmc;
    @DatabaseField
    private String wxks;
    @DatabaseField
    private String wxjs;
    @DatabaseField
    private String weather;
    @DatabaseField
    private String qfry;
    @DatabaseField
    private String qfrq;
    @DatabaseField
    private String zlyq;
    @DatabaseField
    private String zlyqid;
    @DatabaseField
    private String bcsm;
    @DatabaseField
    private String tzdzt;


    private List<UpkeepdiseaseListBean> upkeepdiseaseList = new ArrayList<UpkeepdiseaseListBean>();


    @ForeignCollectionField
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    private ForeignCollection<UpkeepdiseaseListBean> upkeepdiseaseListBeen;

    public UploadUpkeepnoticeBean(int id, String gldwId, String gldwName, String tzld, String wxlx, String wxbmid, String wxbmmc, String wxks, String wxjs, String qfry, String qfrq, String zlyq, String bcsm, String tzdzt, List<UpkeepdiseaseListBean> upkeepdiseaseList) {
        this.id = id;
        this.gydwId = gldwId;
        this.gydwName = gldwName;
        this.tzld = tzld;
        this.wxlx = wxlx;
        this.wxbmid = wxbmid;
        this.wxbmmc = wxbmmc;
        this.wxks = wxks;
        this.wxjs = wxjs;
        this.qfry = qfry;
        this.qfrq = qfrq;
        this.zlyq = zlyq;
        this.bcsm = bcsm;
        this.tzdzt = tzdzt;
        this.upkeepdiseaseList = upkeepdiseaseList;
    }

    public UploadUpkeepnoticeBean() {
    }

    @Override
    public String toString() {
        return "UploadUpkeepnoticeBean{" +
                "id=" + id +
                ", gldwId='" + gydwId + '\'' +
                ", gldwName='" + gydwName + '\'' +
                ", tzld='" + tzld + '\'' +
                ", wxlx='" + wxlx + '\'' +
                ", wxbmid='" + wxbmid + '\'' +
                ", wxbmmc='" + wxbmmc + '\'' +
                ", wxks='" + wxks + '\'' +
                ", wxjs='" + wxjs + '\'' +
                ", qfry='" + qfry + '\'' +
                ", qfrq='" + qfrq + '\'' +
                ", zlyq='" + zlyq + '\'' +
                ", bcsm='" + bcsm + '\'' +
                ", tzdzt='" + tzdzt + '\'' +
                ", upkeepdiseaseList=" + upkeepdiseaseList +
                '}';
    }

    public ForeignCollection<UpkeepdiseaseListBean> getUpkeepdiseaseListBeen() {
        return upkeepdiseaseListBeen;
    }

    public void setUpkeepdiseaseListBeen(ForeignCollection<UpkeepdiseaseListBean> upkeepdiseaseListBeen) {
        this.upkeepdiseaseListBeen = upkeepdiseaseListBeen;
    }

    public String getZlyqid() {
        return zlyqid;
    }

    public void setZlyqid(String zlyqid) {
        this.zlyqid = zlyqid;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGldwId() {
        return gydwId;
    }

    public void setGldwId(String gldwId) {
        this.gydwId = gldwId;
    }

    public String getGldwName() {
        return gydwName;
    }

    public void setGldwName(String gldwName) {
        this.gydwName = gldwName;
    }

    public String getTzld() {
        return tzld;
    }

    public void setTzld(String tzld) {
        this.tzld = tzld;
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

    public String getWxks() {
        return wxks;
    }

    public void setWxks(String wxks) {
        this.wxks = wxks;
    }

    public String getWxjs() {
        return wxjs;
    }

    public void setWxjs(String wxjs) {
        this.wxjs = wxjs;
    }

    public String getQfry() {
        return qfry;
    }

    public void setQfry(String qfry) {
        this.qfry = qfry;
    }

    public String getQfrq() {
        return qfrq;
    }

    public void setQfrq(String qfrq) {
        this.qfrq = qfrq;
    }

    public String getZlyq() {
        return zlyq;
    }

    public void setZlyq(String zlyq) {
        this.zlyq = zlyq;
    }

    public String getBcsm() {
        return bcsm;
    }

    public void setBcsm(String bcsm) {
        this.bcsm = bcsm;
    }

    public String getTzdzt() {
        return tzdzt;
    }

    public void setTzdzt(String tzdzt) {
        this.tzdzt = tzdzt;
    }

    public List<UpkeepdiseaseListBean> getUpkeepdiseaseList() {
        return upkeepdiseaseList;
    }

    public void setUpkeepdiseaseList(List<UpkeepdiseaseListBean> upkeepdiseaseList) {
        this.upkeepdiseaseList = upkeepdiseaseList;
    }
}
