package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by liangshuai on 2017/3/16.
 */

@DatabaseTable(tableName = "tb_cooperation")
public class CooperationBean implements Serializable {
    //    {
//        "createBy": 1, //记录创建人ID
//            "createtime": {}, //记录创建时间
//        "creator": "超级管理员", //记录创建人
//            "flag": 0, //记录是否有效 0 有效  1无效
//            "orgid": 0, //归属组织机构，暂不用
//            "updateBy": 10000000050039, //记录最后更新人ID
//            "updatetime": {}, //记录最后更新时间
//        "updator": "李兵", //记录最后更新人
//            "versionno": 3, //记录更新版本号
//            "gydwId": 10000000220011, //管养单位ID
//            "gydwName": "石家庄养护工区", //管养单位名称
//            "id": 10000001220001, //记录ID
//            "dwmc": "石家庄公路养护管理公司", //单位名称
//            "dz": "石家庄市师范街28号", //地址
//            "fzr": "晓谷", //负责人
//            "lxdh": "13888992008", //联系电话
//            "lx": "私营公司", //类型
//            "bz": "", //备注
//            "gzz": "" //工作组
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
    @DatabaseField
    private String dwmc;
    @DatabaseField
    private String dz;
    @DatabaseField
    private String fzr;
    @DatabaseField
    private String lxdh;
    @DatabaseField
    private String lx;
    @DatabaseField
    private String bz;
    @DatabaseField
    private String gzz;

    public CooperationBean() {
    }

    public CooperationBean(int ids, String id, String createBy, String creator, String flag, String orgid, String updateBy, String updatetime, String updator, String versionno, String gydwId, String gydwName, String dwmc, String dz, String fzr, String lxdh, String lx, String bz, String gzz) {
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
        this.dwmc = dwmc;
        this.dz = dz;
        this.fzr = fzr;
        this.lxdh = lxdh;
        this.lx = lx;
        this.bz = bz;
        this.gzz = gzz;
    }

    @Override
    public String toString() {
        return "CooperationBean{" +
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
                ", dwmc='" + dwmc + '\'' +
                ", dz='" + dz + '\'' +
                ", fzr='" + fzr + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", lx='" + lx + '\'' +
                ", bz='" + bz + '\'' +
                ", gzz='" + gzz + '\'' +
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

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGzz() {
        return gzz;
    }

    public void setGzz(String gzz) {
        this.gzz = gzz;
    }
}
