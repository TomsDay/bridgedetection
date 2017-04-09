package com.suken.bridgedetection.bean;

import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/2.
 */

@DatabaseTable(tableName = "tb_wxdbhbyuid")
public class WxdbhByUID implements Serializable {
    /**
     * "bhid": 10000010755693, //病害id
     "bhmc": "标线缺损",//病害名称
     "bhwz": "k10+100",//病害详情
     "bno": "201612800031",//病害编号
     "createBy": 10000010345027,
     "createtime": "2016-12-08 09:19:49",
     "creator": "测试",
     "cus1": "",
     "cus2": "",
     "cus3": "",
     "cus4": 0,
     "cus5": 0,
     "cus6": "",
     "dw": "米", //单位
     "flag": 0,
     "fx": "上行",//方向
     "gydwId": 10000010950726, //管养单位id
     "gydwName": "王瞳工区",//管养单位
     "id": 10000010950726,
     "isxd": "匝道",//是否匝道
     "iszd": "", //无需下单
     "jcrq": "2016-12-08",//巡查日期
     "jcsj": "09:18",//检查时间
     "lxbh": "S67",//路线编号
     "lxid": 10000009232208,//路线id
     "lxmc": "故城支线",//路线名称
     "noticeId": 0,
     "orgid": 0,
     "picattachment": "",//图片附件
     "remark": "",//备注
     "rowIndex": 0,
     "tpjd": "116.2346369328573",//经度
     "tpwd": "40.1954290571038",//纬度
     "tzdzt": "",//通知单状态
     "updateBy": 0,
     "updatetime": "",
     "updator": "",
     "versionno": 1,
     "vidattachment": "",
     "xcry": "测试", //巡查人员
     "ygsl": 0, //预估数量
     "yhInspId": 10000010950725, 巡查日志id
     "yhInspNo": "201612800030", 巡查日志单编号
     "yhzh": "",//桩号
     "yhzt": 1, 显示为 未下单  2 显示为已
     "zhfw": "k0+100~k10+200",//检查范围
     "xcld": "k0+100~k10+200"//巡查路段
     */
    private String ids;
//    private String id;
    private String bhid;
    private String bhmc;
    private String bhwz;
//    private String bno;
//    private String createBy;
//    private String createtime;
//    private String creator;
    private String dw;
//    private String flag;
    private String fx;
//    private String gydwId;
    private String gydwName;
//    private String isxd;
//    private String iszd;
    private String jcrq;
    private String jcsj;

//    private String lxbh;
//    private String lxid;
    private String lxmc;
//    private String noticeId;
//    private String orgid;
//    private String picattachment;
//    private String remark;
//    private String rowIndex;
//    private String tpjd;
//    private String tpwd;
//    private String tzdzt;
//    private String updateBy;
//    private String updatetime;
//    private String updator;
//    private String versionno;
//    private String vidattachment;
    private String xcry;
    private String ygsl;
//    private String yhInspId;
    private String yhInspNo;
    private String yhzh;
    private String yhzt;
    private String zhfw;
    private String xcld;

    public WxdbhByUID() {
    }

    public WxdbhByUID(String ids, String bhid, String bhmc, String bhwz, String dw, String fx, String gydwName, String jcrq, String jcsj, String lxmc, String xcry, String ygsl, String yhInspNo, String yhzh, String yhzt, String zhfw, String xcld) {
        this.ids = ids;
        this.bhid = bhid;
        this.bhmc = bhmc;
        this.bhwz = bhwz;
        this.dw = dw;
        this.fx = fx;
        this.gydwName = gydwName;
        this.jcrq = jcrq;
        this.jcsj = jcsj;
        this.lxmc = lxmc;
        this.xcry = xcry;
        this.ygsl = ygsl;
        this.yhInspNo = yhInspNo;
        this.yhzh = yhzh;
        this.yhzt = yhzt;
        this.zhfw = zhfw;
        this.xcld = xcld;
    }

    @Override
    public String toString() {
        return "WxdbhByUID{" +
                "ids='" + ids + '\'' +
                ", bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", bhwz='" + bhwz + '\'' +
                ", dw='" + dw + '\'' +
                ", fx='" + fx + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", jcrq='" + jcrq + '\'' +
                ", jcsj='" + jcsj + '\'' +
                ", lxmc='" + lxmc + '\'' +
                ", xcry='" + xcry + '\'' +
                ", ygsl='" + ygsl + '\'' +
                ", yhInspNo='" + yhInspNo + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", yhzt='" + yhzt + '\'' +
                ", zhfw='" + zhfw + '\'' +
                ", xcld='" + xcld + '\'' +
                '}';
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
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

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getGydwName() {
        return gydwName;
    }

    public void setGydwName(String gydwName) {
        this.gydwName = gydwName;
    }

    public String getJcrq() {
        return jcrq;
    }

    public void setJcrq(String jcrq) {
        this.jcrq = jcrq;
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getXcry() {
        return xcry;
    }

    public void setXcry(String xcry) {
        this.xcry = xcry;
    }

    public String getYgsl() {
        return ygsl;
    }

    public void setYgsl(String ygsl) {
        this.ygsl = ygsl;
    }

    public String getYhInspNo() {
        return yhInspNo;
    }

    public void setYhInspNo(String yhInspNo) {
        this.yhInspNo = yhInspNo;
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public String getYhzt() {
        return yhzt;
    }

    public void setYhzt(String yhzt) {
        this.yhzt = yhzt;
    }

    public String getZhfw() {
        return zhfw;
    }

    public void setZhfw(String zhfw) {
        this.zhfw = zhfw;
    }

    public String getXcld() {
        return xcld;
    }

    public void setXcld(String xcld) {
        this.xcld = xcld;
    }
}
