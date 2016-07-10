package com.suken.bridgedetection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ProjacceptBean implements Serializable {
    private String createBy;
    private String createtime;
    private String creator;
    private String updateBy;
    private String updatetime;
    private String updator;
    private String versionno;
    private String orgid;
    private String flag;
    private String gydwId;
    private String gydwName;
    private String id;
    private String bno;
    private String sgdwid;
    private String sgdwmc;
    private String sgks;
    private String sgjs;
    private String ysjg;
    private String qrzs;
    private String ysry;
    private String ysrq;
    private String sgfzry;
    private String sgfzdate;
    private String tjsj;
    private String status;
    private String yhtzid;
    private String yhtzdno;
    private String signattachment ;

    private List<ProjacceptItemBean> projacceptItemBeen = new ArrayList<ProjacceptItemBean>();

    public ProjacceptBean() {
    }

    public ProjacceptBean(String createBy, String createtime, String creator, String updateBy, String updatetime, String updator, String versionno, String orgid, String flag, String gldwId, String gldwName, String id, String bno, String sgdwid, String sgdwmc, String sgks, String sgjs, String ysjg, String qrzs, String ysry, String ysrq, String sgfzry, String sgfzdate, String tjsj, String status, String yhtzid, String yhtzdno, String signattachment, List<ProjacceptItemBean> projacceptItemBeen) {
        this.createBy = createBy;
        this.createtime = createtime;
        this.creator = creator;
        this.updateBy = updateBy;
        this.updatetime = updatetime;
        this.updator = updator;
        this.versionno = versionno;
        this.orgid = orgid;
        this.flag = flag;
        this.id = id;
        this.bno = bno;
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
        this.yhtzid = yhtzid;
        this.yhtzdno = yhtzdno;
        this.signattachment = signattachment;
        this.projacceptItemBeen = projacceptItemBeen;
    }


    @Override
    public String toString() {
        return "ProjacceptBean{" +
                "createBy='" + createBy + '\'' +
                ", createtime='" + createtime + '\'' +
                ", creator='" + creator + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updator='" + updator + '\'' +
                ", versionno='" + versionno + '\'' +
                ", orgid='" + orgid + '\'' +
                ", flag='" + flag + '\'' +
                ", id='" + id + '\'' +
                ", bno='" + bno + '\'' +
                ", sgdwid='" + sgdwid + '\'' +
                ", sgdwmc='" + sgdwmc + '\'' +
                ", sgks='" + sgks + '\'' +
                ", sgjs='" + sgjs + '\'' +
                ", ysjg='" + ysjg + '\'' +
                ", qrzs='" + qrzs + '\'' +
                ", ysry='" + ysry + '\'' +
                ", ysrq='" + ysrq + '\'' +
                ", sgfzry='" + sgfzry + '\'' +
                ", sgfzdate='" + sgfzdate + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", status='" + status + '\'' +
                ", yhtzid='" + yhtzid + '\'' +
                ", yhtzdno='" + yhtzdno + '\'' +
                ", signattachment='" + signattachment + '\'' +
                ", projacceptItemBeen=" + projacceptItemBeen +
                '}';
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
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

    public String getSignattachment() {
        return signattachment;
    }

    public void setSignattachment(String signattachment) {
        this.signattachment = signattachment;
    }

    public List<ProjacceptItemBean> getProjacceptItemBeen() {
        return projacceptItemBeen;
    }

    public void setProjacceptItemBeen(List<ProjacceptItemBean> projacceptItemBeen) {
        this.projacceptItemBeen = projacceptItemBeen;
    }
}
