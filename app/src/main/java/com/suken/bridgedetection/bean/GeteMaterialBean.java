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

    /**材料名称 */
    @DatabaseField
    private String clmc;
    /**材料编号 */
    @DatabaseField
    private String clno;
    /**记录创建人ID */
    @DatabaseField
    private String createBy;
    /**记录创建时间 */
    @DatabaseField
    private String createtime;
    /**记录创建人 */
    @DatabaseField
    private String creator;
    /**单位 */
    @DatabaseField
    private String dw;
    /*记录是否有效 0 有效  1无效 */
    @DatabaseField
    private String flag;
    /**规格 */
    @DatabaseField
    private String gg;
    /**管养单位ID */
    @DatabaseField
    private String gydwId;
    /**管养单位名称 */
    @DatabaseField
    private String gydwName;
    /**记录ID */
    @DatabaseField
    private String id;
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
    /**型号 */
    @DatabaseField
    private String xh;
    /**材料数量 */
    private String clsl;

    /**二级分类 */
    @DatabaseField
    private String ejml;
    /**一级分类 */
    @DatabaseField
    private String yjml;

    public GeteMaterialBean() {
    }

    public GeteMaterialBean(int ids, String clmc, String clno, String createBy, String createtime, String creator, String dw, String flag, String gg, String gydwId, String gydwName, String id, String orgid, String updateBy, String updatetime, String updator, String versionno, String xh) {
        this.ids = ids;
        this.clmc = clmc;
        this.clno = clno;
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.dw = dw;
        this.flag = flag;
        this.gg = gg;
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.id = id;
        this.orgid = orgid;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.xh = xh;
    }

    public String getEjml() {
        return ejml;
    }

    public void setEjml(String ejml) {
        this.ejml = ejml;
    }

    public String getYjml() {
        return yjml;
    }

    public void setYjml(String yjml) {
        this.yjml = yjml;
    }

    public String getClsl() {
        return clsl;
    }

    public void setClsl(String clsl) {
        this.clsl = clsl;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getClmc() {
        return clmc;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc;
    }

    public String getClno() {
        return clno;
    }

    public void setClno(String clno) {
        this.clno = clno;
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

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    @Override
    public String toString() {
        return "GeteMaterialBean{" +
                "ids=" + ids +
                ", clmc='" + clmc + '\'' +
                ", clno='" + clno + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", dw='" + dw + '\'' +
                ", flag='" + flag + '\'' +
                ", gg='" + gg + '\'' +
                ", gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", id='" + id + '\'' +
                ", orgid='" + orgid + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", xh='" + xh + '\'' +
                '}';
    }
}
