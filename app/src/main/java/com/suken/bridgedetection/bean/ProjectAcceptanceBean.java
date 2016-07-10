package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/27.
 */
@DatabaseTable(tableName = "tb_projectacceptancebean")
public class ProjectAcceptanceBean implements Serializable{
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    private String orgid;
    @DatabaseField
    private String versionno;
    @DatabaseField
    private String createBy;
    @DatabaseField
    private String creator;
    @DatabaseField
    private String createtime;
    @DatabaseField
    private String updateBy;
    @DatabaseField
    private String updator;
    @DatabaseField
    private String updatetime;
    @DatabaseField
    private String flag;
    @DatabaseField
    private String gydwId;
    @DatabaseField
    private String bno;
    @DatabaseField
    private String yhtzid;
    @DatabaseField
    private String yhtzdno;
    @DatabaseField
    private String gydwName;
    @DatabaseField
    private String sgdwid;
    @DatabaseField
    private String sgdwmc;
    @DatabaseField
    private String sgks;
    @DatabaseField
    private String sgjs;
    @DatabaseField
    private String ysjg;
    @DatabaseField
    private String qrzs;
    @DatabaseField
    private String ysry;
    @DatabaseField
    private String ysrq;
    @DatabaseField
    private String sgfzry;
    @DatabaseField
    private String sgfzdate;
    @DatabaseField
    private String tjsj;
    @DatabaseField
    private String status;
    @DatabaseField
    private String weather;

    public ProjectAcceptanceBean() {
    }

    public ProjectAcceptanceBean(int id, String orgid, String versionno, String createBy, String creator, String createtime, String updateBy, String updator, String updatetime, String flag, String gydwId, String bno, String yhtzid, String yhtzdno, String gydwName, String sgdwid, String sgdwmc, String sgks, String sgjs, String ysjg, String qrzs, String ysry, String ysrq, String sgfzry, String sgfzdate, String tjsj, String status) {
        this.id = id;
        this.orgid = orgid;
        this.versionno = versionno;
        this.createBy = createBy;
        this.creator = creator;
        this.createtime = createtime;
        this.updateBy = updateBy;
        this.updator = updator;
        this.updatetime = updatetime;
        this.flag = flag;
        this.gydwId = gydwId;
        this.bno = bno;
        this.yhtzid = yhtzid;
        this.yhtzdno = yhtzdno;
        this.gydwName = gydwName;
        this.sgdwid = sgdwid;
        this.sgdwmc = sgdwmc;
        this.sgks = sgks;
        this.sgjs = sgjs;
        this.ysjg = ysjg;
        this.qrzs = qrzs;
        this.ysry = ysry;
        this.ysrq = ysrq;
        this.sgfzry = sgfzry;
        this.sgfzdate = sgfzdate;
        this.tjsj = tjsj;
        this.status = status;
    }


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGydwId() {
        return gydwId;
    }

    public void setGydwId(String gydwId) {
        this.gydwId = gydwId;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getYhtzid() {
        return yhtzid;
    }

    public void setYhtzid(String yhtzid) {
        this.yhtzid = yhtzid;
    }

    public String getYhtzdno() {
        return yhtzdno;
    }

    public void setYhtzdno(String yhtzdno) {
        this.yhtzdno = yhtzdno;
    }

    public String getGydwName() {
        return gydwName;
    }

    public void setGydwName(String gydwName) {
        this.gydwName = gydwName;
    }

    public String getSgdwid() {
        return sgdwid;
    }

    public void setSgdwid(String sgdwid) {
        this.sgdwid = sgdwid;
    }

    public String getSgdwmc() {
        return sgdwmc;
    }

    public void setSgdwmc(String sgdwmc) {
        this.sgdwmc = sgdwmc;
    }

    public String getSgks() {
        return sgks;
    }

    public void setSgks(String sgks) {
        this.sgks = sgks;
    }

    public String getSgjs() {
        return sgjs;
    }

    public void setSgjs(String sgjs) {
        this.sgjs = sgjs;
    }

    public String getYsjg() {
        return ysjg;
    }

    public void setYsjg(String ysjg) {
        this.ysjg = ysjg;
    }

    public String getQrzs() {
        return qrzs;
    }

    public void setQrzs(String qrzs) {
        this.qrzs = qrzs;
    }

    public String getYsry() {
        return ysry;
    }

    public void setYsry(String ysry) {
        this.ysry = ysry;
    }

    public String getYsrq() {
        return ysrq;
    }

    public void setYsrq(String ysrq) {
        this.ysrq = ysrq;
    }

    public String getSgfzry() {
        return sgfzry;
    }

    public void setSgfzry(String sgfzry) {
        this.sgfzry = sgfzry;
    }

    public String getSgfzdate() {
        return sgfzdate;
    }

    public void setSgfzdate(String sgfzdate) {
        this.sgfzdate = sgfzdate;
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
}
