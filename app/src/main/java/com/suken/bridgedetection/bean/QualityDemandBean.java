package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by liangshuai on 2017/3/16.
 */
@DatabaseTable(tableName = "tb_qualitydemand")
public class QualityDemandBean implements Serializable {
//    {
//         "createBy": 1, //记录创建人ID
//            "createtime": {}, //记录创建时间
//            "creator": "超级管理员", //记录创建人
//            "flag": 0, //记录是否有效 0 有效  1无效
//            "orgid": 0, //归属组织机构，暂不用
//            "updateBy": 10000000050039, //记录最后更新人ID
//            "updatetime": {}, //记录最后更新时间
//            "updator": "李兵", //记录最后更新人
//            "versionno": 3, //记录更新版本号

//            "gydwId": 10000000220011, //管养单位ID
//            "gydwName": "石家庄养护工区", //管养单位名称

//            "id": 10000001220001, //记录ID
//            "bhzl": "要求1", //病害种类
//            "yqnr": "不得有遗漏，路面平整"//要求内容

    //    }
@DatabaseField(generatedId = true) //主键自增加
    private int ids;

    @DatabaseField
    private String id;
    @DatabaseField
    private String createBy;
    @DatabaseField
    private String creator;
    @DatabaseField
    private String flag;
    @DatabaseField
    private String orgid;
    @DatabaseField
    private String updateBy;
    @DatabaseField
    private String updatetime;
    @DatabaseField
    private String updator;
    @DatabaseField
    private String versionno;
    @DatabaseField
    private String gydwId;
    @DatabaseField
    private String gydwName;
//    @DatabaseField
//    private String clno;
//    @DatabaseField
//    private String gg;
//    @DatabaseField
//    private String xh;
//    @DatabaseField
//    private String dw;
     @DatabaseField
    private String bhzl;
    @DatabaseField
    private String yqnr;

    private boolean isChecked;

    public QualityDemandBean() {
    }

    public QualityDemandBean(int ids, String id, String createBy, String creator, String flag, String orgid, String updateBy, String updatetime, String updator, String versionno, String gydwId, String gydwName, String bhzl, String yqnr) {
        this.ids = ids;
        this.id = id;
        this.createBy = createBy;
        this.creator = creator;
        this.flag = flag;
        this.orgid = orgid;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.bhzl = bhzl;
        this.yqnr = yqnr;
    }

    @Override
    public String toString() {
        return "QualityDemandBean{" +
                "ids=" + ids +
                ", id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", creator='" + creator + '\'' +
                ", flag='" + flag + '\'' +
                ", orgid='" + orgid + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", bhzl='" + bhzl + '\'' +
                ", yqnr='" + yqnr + '\'' +
                '}';
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
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

    public String getBhzl() {
        return bhzl;
    }

    public void setBhzl(String bhzl) {
        this.bhzl = bhzl;
    }

    public String getYqnr() {
        return yqnr;
    }

    public void setYqnr(String yqnr) {
        this.yqnr = yqnr;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
