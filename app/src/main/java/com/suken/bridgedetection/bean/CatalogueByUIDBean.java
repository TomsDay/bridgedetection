package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/8.
 */
@DatabaseTable(tableName = "tb_cataloguebyuid")
public class CatalogueByUIDBean implements Serializable{

    /**
     * createBy : 10000004888245
     * createtime : {}
     * creator : 郭文翔
     * flag : 0
     * orgid : 0
     * updateBy : 10000000050039
     * updatetime : {}
     * updator : 李兵
     * versionno : 2
     * gydwId : 10000000040007
     * gydwName : 河北省高速公路京沪管理处
     * id : 10000006300002
     * xmlb : 路基工程
     * xmmc : 维修砌石防护
     * ximmc : 砌石防护除草、勾缝
     * dj : 0
     * dw : 平方米
     * xcms :
     */
    @DatabaseField(generatedId = true) //主键自增加
    private Long id;

    @DatabaseField
    private String createBy;
    @DatabaseField
    private String createtime;
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
    private String xmlb;
    @DatabaseField
    private String xmmc;
    @DatabaseField
    private String ximmc;
    @DatabaseField
    private String dj;
    @DatabaseField
    private String dw;
    @DatabaseField
    private String xcms;

    public CatalogueByUIDBean() {
    }

    public CatalogueByUIDBean(Long id, String createBy, String createtime, String creator, String flag, String orgid, String updateBy, String updatetime, String updator, String versionno, String gydwId, String gydwName, String xmlb, String xmmc, String ximmc, String dj, String dw, String xcms) {
        this.id = id;
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.flag = flag;
        this.orgid = orgid;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.xmlb = xmlb;
        this.xmmc = xmmc;
        this.ximmc = ximmc;
        this.dj = dj;
        this.dw = dw;
        this.xcms = xcms;
    }

    @Override
    public String toString() {
        return "CatalogueByUIDBean{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", flag='" + flag + '\'' +
                ", orgid='" + orgid + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", xmlb='" + xmlb + '\'' +
                ", xmmc='" + xmmc + '\'' +
                ", ximmc='" + ximmc + '\'' +
                ", dj='" + dj + '\'' +
                ", dw='" + dw + '\'' +
                ", xcms='" + xcms + '\'' +
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXmlb() {
        return xmlb;
    }

    public void setXmlb(String xmlb) {
        this.xmlb = xmlb;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getXimmc() {
        return ximmc;
    }

    public void setXimmc(String ximmc) {
        this.ximmc = ximmc;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getXcms() {
        return xcms;
    }

    public void setXcms(String xcms) {
        this.xcms = xcms;
    }


}
