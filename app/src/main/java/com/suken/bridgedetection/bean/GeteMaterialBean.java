package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/1.
 */
@DatabaseTable(tableName = "tb_getematerial")
public class GeteMaterialBean implements Serializable {
    @DatabaseField(generatedId = true) //主键自增加
    private int ids;
    /**记录创建人ID */
    @DatabaseField
    private String createBy;
    /**记录创建时间 */
    @DatabaseField
    private String createtime;
    /**记录创建人 */
    @DatabaseField
    private String creator;
    /**记录是否有效 0 有效  1无效 */
    @DatabaseField
    private String flag;
    /**归属组织机构，暂不用 */
    @DatabaseField
    private String orgid;
    /**记录最后更新人ID */
    @DatabaseField
    private String updateBy;
    /**记录最后更新时间 */
    @DatabaseField
    private String updatetime;
    /**记录最后更新人 */
    @DatabaseField
    private String updator;
    /**记录更新版本号 */
    @DatabaseField
    private String versionno;
    /**管养单位ID */
    @DatabaseField
    private String gydwId;
    /**管养单位名称 */
    @DatabaseField
    private String gydwName;
    /**记录ID */
    @DatabaseField
    private String id;
    /**一级分类 */
    @DatabaseField
    private String yjml;
    /**二级分类 */
    @DatabaseField
    private String ejml;
    /**材料名称 */
    @DatabaseField
    private String clmc;
    /**损坏类型 */
    @DatabaseField
    private String shlx;
    /**单位 */
    @DatabaseField
    private String dw;
    /**现场描述 */
    @DatabaseField
    private String xcms;

    public GeteMaterialBean() {
    }

    public GeteMaterialBean(int ids, String createBy, String createtime, String creator, String flag, String orgid, String updateBy, String updatetime, String updator, String versionno, String gydwId, String gydwName, String id, String yjml, String ejml, String clmc, String shlx, String dw, String xcms) {
        this.ids = ids;
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
        this.id = id;
        this.yjml = yjml;
        this.ejml = ejml;
        this.clmc = clmc;
        this.shlx = shlx;
        this.dw = dw;
        this.xcms = xcms;
    }

    @Override
    public String toString() {
        return "GeteMaterialBean{" +
                "ids=" + ids +
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
                ", id='" + id + '\'' +
                ", yjml='" + yjml + '\'' +
                ", ejml='" + ejml + '\'' +
                ", clmc='" + clmc + '\'' +
                ", shlx='" + shlx + '\'' +
                ", dw='" + dw + '\'' +
                ", xcms='" + xcms + '\'' +
                '}';
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYjml() {
        return yjml;
    }

    public void setYjml(String yjml) {
        this.yjml = yjml;
    }

    public String getEjml() {
        return ejml;
    }

    public void setEjml(String ejml) {
        this.ejml = ejml;
    }

    public String getClmc() {
        return clmc;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc;
    }

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
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
