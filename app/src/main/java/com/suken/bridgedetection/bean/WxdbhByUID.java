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
    private String id;
    private String bhid;
    private String bhmc;
    private String bhwz;
    private String bno;
    private String createBy;
    private String createtime;
    private String creator;
    private String dw;
    private String flag;
    private String fx;
    private String gydwId;
    private String gydwName;
    private String isxd;
    private String iszd;
    private String jcrq;
    private String jcsj;

    private String lxbh;
    private String lxid;
    private String lxmc;
    private String noticeId;
    private String orgid;
    private String picattachment;
    private String remark;
    private String rowIndex;
    private String tpjd;
    private String tpwd;
    private String tzdzt;
    private String updateBy;
    private String updatetime;
    private String updator;
    private String versionno;
    private String vidattachment;
    private String xcry;
    private String ygsl;
    private String yhInspId;
    private String yhInspNo;
    private String yhzh;
    private String yhzt;
    private String zhfw;
    private String xcld;

    public WxdbhByUID() {
    }

    public WxdbhByUID(String ids, String id, String bhid, String bhmc, String bhwz, String bno, String createBy, String createtime, String creator, String dw, String flag, String fx, String gydwId, String gydwName, String isxd, String iszd, String jcrq, String jcsj, String lxbh, String lxid, String lxmc, String noticeId, String orgid, String picattachment, String remark, String rowIndex, String tpjd, String tpwd, String tzdzt, String updateBy, String updatetime, String updator, String versionno, String vidattachment, String xcry, String ygsl, String yhInspId, String yhInspNo, String yhzh, String yhzt, String zhfw, String xcld) {
        this.ids = ids;
        this.id = id;
        this.bhid = bhid;
        this.bhmc = bhmc;
        this.bhwz = bhwz;
        this.bno = bno;
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.dw = dw;
        this.flag = flag;
        this.fx = fx;
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.isxd = isxd;
        this.iszd = iszd;
        this.jcrq = jcrq;
        this.jcsj = jcsj;
        this.lxbh = lxbh;
        this.lxid = lxid;
        this.lxmc = lxmc;
        this.noticeId = noticeId;
        this.orgid = orgid;
        this.picattachment = picattachment;
        this.remark = remark;
        this.rowIndex = rowIndex;
        this.tpjd = tpjd;
        this.tpwd = tpwd;
        this.tzdzt = tzdzt;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.vidattachment = vidattachment;
        this.xcry = xcry;
        this.ygsl = ygsl;
        this.yhInspId = yhInspId;
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
                ", id='" + id + '\'' +
                ", bhid='" + bhid + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", bhwz='" + bhwz + '\'' +
                ", bno='" + bno + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", dw='" + dw + '\'' +
                ", flag='" + flag + '\'' +
                ", fx='" + fx + '\'' +
                ", gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", isxd='" + isxd + '\'' +
                ", iszd='" + iszd + '\'' +
                ", jcrq='" + jcrq + '\'' +
                ", jcsj='" + jcsj + '\'' +
                ", lxbh='" + lxbh + '\'' +
                ", lxid='" + lxid + '\'' +
                ", lxmc='" + lxmc + '\'' +
                ", noticeId='" + noticeId + '\'' +
                ", orgid='" + orgid + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", remark='" + remark + '\'' +
                ", rowIndex='" + rowIndex + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", tzdzt='" + tzdzt + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", vidattachment='" + vidattachment + '\'' +
                ", xcry='" + xcry + '\'' +
                ", ygsl='" + ygsl + '\'' +
                ", yhInspId='" + yhInspId + '\'' +
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
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

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
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

    public String getIsxd() {
        return isxd;
    }

    public void setIsxd(String isxd) {
        this.isxd = isxd;
    }

    public String getIszd() {
        return iszd;
    }

    public void setIszd(String iszd) {
        this.iszd = iszd;
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

    public String getLxbh() {
        return lxbh;
    }

    public void setLxbh(String lxbh) {
        this.lxbh = lxbh;
    }

    public String getLxid() {
        return lxid;
    }

    public void setLxid(String lxid) {
        this.lxid = lxid;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getPicattachment() {
        return picattachment;
    }

    public void setPicattachment(String picattachment) {
        this.picattachment = picattachment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(String rowIndex) {
        this.rowIndex = rowIndex;
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

    public String getTzdzt() {
        return tzdzt;
    }

    public void setTzdzt(String tzdzt) {
        this.tzdzt = tzdzt;
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

    public String getVidattachment() {
        return vidattachment;
    }

    public void setVidattachment(String vidattachment) {
        this.vidattachment = vidattachment;
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

    public String getYhInspId() {
        return yhInspId;
    }

    public void setYhInspId(String yhInspId) {
        this.yhInspId = yhInspId;
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
