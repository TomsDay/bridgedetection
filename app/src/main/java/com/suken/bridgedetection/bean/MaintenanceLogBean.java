package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
@DatabaseTable(tableName = "tb_maintenancelog")
public class MaintenanceLogBean implements Serializable{
    @DatabaseField(generatedId = true) //主键自增加
    private Long id;

    @DatabaseField
    private String createBy;
    @DatabaseField
    private String createtime;
    @DatabaseField
    private String creator;
    @DatabaseField
    private String updateBy;
    @DatabaseField
    private String updatetime;
    @DatabaseField
    private String updator;
    @DatabaseField
    private String versionno;
    @DatabaseField
    private String orgid;
    @DatabaseField
    private String flag;
    @DatabaseField
    /** 管养单位id*/
    private String gldwId;
    @DatabaseField
    /** 管养单位*/
    private String gldwName;
    /** 路段 */
    @DatabaseField
    private String tzld;
    /** 表单编号*/
    @DatabaseField
    private String bno;
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
    private String tjsj;
    @DatabaseField
    private String status = "2";
    @DatabaseField
    private String zlyq;
    @DatabaseField
    private String bcsm;
    @DatabaseField
    private String tzdzt;


    /**
     * ==============================================================上传增加的字段================================================================================
     */
    @DatabaseField
    private String jcry;
    @DatabaseField
    private String fzry;
    @DatabaseField
    private String bytzid;
    @DatabaseField
    private String wxrq;
    @DatabaseField
    /** '保养日志状态  1 填写保养日志  2 检查 验收 3为待上传*/
    private String byrzzt;
    @DatabaseField
    private String bytzidbytzid;
    @DatabaseField
    private String bytzno;




    List<MaintenanceLogItemBean> maintenlogdetailList = new ArrayList<MaintenanceLogItemBean>();

    List<MaintenanceLogItemBean> upkeepdiseaseList = new ArrayList<MaintenanceLogItemBean>();






    @ForeignCollectionField
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    private ForeignCollection<MaintenanceLogItemBean> maintenanceLogItemBeen;




    public MaintenanceLogBean() {
    }

    public MaintenanceLogBean(Long id, String createBy, String createtime, String creator, String updateBy, String updatetime, String updator, String versionno, String orgid, String flag, String gldwId, String gldwName, String tzld, String bno, String wxlx, String wxbmid, String wxbmmc, String wxks, String wxjs, String weather, String qfry, String qfrq, String tjsj, String status, String zlyq, String bcsm, String tzdzt, List<MaintenanceLogItemBean> upkeepdiseaseList, ForeignCollection<MaintenanceLogItemBean> maintenanceLogItemBeen) {
        this.id = id;
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
        this.tzld = tzld;
        this.bno = bno;
        this.wxlx = wxlx;
        this.wxbmid = wxbmid;
        this.wxbmmc = wxbmmc;
        this.wxks = wxks;
        this.wxjs = wxjs;
        this.weather = weather;
        this.qfry = qfry;
        this.qfrq = qfrq;
        this.tjsj = tjsj;
        this.status = status;
        this.zlyq = zlyq;
        this.bcsm = bcsm;
        this.tzdzt = tzdzt;
        this.maintenlogdetailList = upkeepdiseaseList;
        this.maintenanceLogItemBeen = maintenanceLogItemBeen;
    }

    @Override
    public String toString() {
        return "MaintenanceLogBean{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
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
                ", tzld='" + tzld + '\'' +
                ", bno='" + bno + '\'' +
                ", wxlx='" + wxlx + '\'' +
                ", wxbmid='" + wxbmid + '\'' +
                ", wxbmmc='" + wxbmmc + '\'' +
                ", wxks='" + wxks + '\'' +
                ", wxjs='" + wxjs + '\'' +
                ", weather='" + weather + '\'' +
                ", qfry='" + qfry + '\'' +
                ", qfrq='" + qfrq + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", status='" + status + '\'' +
                ", zlyq='" + zlyq + '\'' +
                ", bcsm='" + bcsm + '\'' +
                ", tzdzt='" + tzdzt + '\'' +
                ", jcry='" + jcry + '\'' +
                ", fzry='" + fzry + '\'' +
                ", bytzid='" + bytzid + '\'' +
                ", wxrq='" + wxrq + '\'' +
                ", byrzzt='" + byrzzt + '\'' +
                ", bytzidbytzid='" + bytzidbytzid + '\'' +
                ", maintenanceLogItemBeen=" + maintenanceLogItemBeen +
                '}';
    }

    public List<MaintenanceLogItemBean> getUpkeepdiseaseList() {
        return upkeepdiseaseList;
    }

    public void setUpkeepdiseaseList(List<MaintenanceLogItemBean> upkeepdiseaseList) {
        this.upkeepdiseaseList = upkeepdiseaseList;
    }

    public String getBytzno() {
        return bytzno;
    }

    public void setBytzno(String bytzno) {
        this.bytzno = bytzno;
    }

    public List<MaintenanceLogItemBean> getMaintenlogdetailList() {
        return maintenlogdetailList;
    }

    public void setMaintenlogdetailList(List<MaintenanceLogItemBean> maintenlogdetailList) {
        this.maintenlogdetailList = maintenlogdetailList;
    }

    public String getBytzidbytzid() {
        return bytzidbytzid;
    }

    public void setBytzidbytzid(String bytzidbytzid) {
        this.bytzidbytzid = bytzidbytzid;
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

    public String getBytzid() {
        return bytzid;
    }

    public void setBytzid(String bytzid) {
        this.bytzid = bytzid;
    }


    public String getWxrq() {
        return wxrq;
    }

    public void setWxrq(String wxrq) {
        this.wxrq = wxrq;
    }

    public String getByrzzt() {
        return byrzzt;
    }

    public void setByrzzt(String byrzzt) {
        this.byrzzt = byrzzt;
    }

    public String getTzld() {
        return tzld;
    }

    public void setTzld(String tzld) {
        this.tzld = tzld;
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

    public String getWxbmmc() {
        return wxbmmc;
    }

    public void setWxbmmc(String wxbmmc) {
        this.wxbmmc = wxbmmc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public ForeignCollection<MaintenanceLogItemBean> getMaintenanceLogItemBeen() {
        return maintenanceLogItemBeen;
    }

    public void setMaintenanceLogItemBeen(ForeignCollection<MaintenanceLogItemBean> maintenanceLogItemBeen) {
        this.maintenanceLogItemBeen = maintenanceLogItemBeen;
    }
}
