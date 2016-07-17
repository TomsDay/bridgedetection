package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
@DatabaseTable(tableName = "tb_projectacceptancebean")
public class ProjectAcceptanceBean implements Serializable{
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    private String gydwId;
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
    @DatabaseField
    private String picattachment;
    @DatabaseField
    private String tpjd;
    @DatabaseField
    private String tpwd;

    private byte[] qmtp1;
    private byte[] qmtp2;
    private byte[] qmtp3;
    private byte[] qmtp4;
    private byte[] qmtp5;


    private List<ProjacceptItemBean> projacceptDetailList = new ArrayList<ProjacceptItemBean>();
    @ForeignCollectionField
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    private ForeignCollection<ProjacceptItemBean> projacceptItemBeen;


    private List<IVDesc> mImages = new ArrayList<IVDesc>();

    @ForeignCollectionField
    private ForeignCollection<IVDesc> iDescs;


    @Override
    public String toString() {
        return "ProjectAcceptanceBean{" +
                "id=" + id +
                ", gydwId='" + gydwId + '\'' +
                ", yhtzid='" + yhtzid + '\'' +
                ", yhtzdno='" + yhtzdno + '\'' +
                ", gydwName='" + gydwName + '\'' +
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
                ", weather='" + weather + '\'' +
                ", picattachment='" + picattachment + '\'' +
                ", tpjd='" + tpjd + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", projacceptDetailList=" + projacceptDetailList +
                ", projacceptItemBeen=" + projacceptItemBeen +
                ", mImages=" + mImages +
                ", iDescs=" + iDescs +
                '}';
    }

    public ProjectAcceptanceBean() {
    }

    public ProjectAcceptanceBean(int id, String gydwId, String yhtzid, String yhtzdno, String gydwName, String sgdwid, String sgdwmc, String sgks, String sgjs, String ysjg, String qrzs, String ysry, String ysrq, String sgfzry, String sgfzdate, String tjsj, String status, String weather, String picattachment, String tpjd, String tpwd, byte[] qmtp1, byte[] qmtp2, byte[] qmtp3, byte[] qmtp4, byte[] qmtp5, List<ProjacceptItemBean> projacceptDetailList, ForeignCollection<ProjacceptItemBean> projacceptItemBeen, List<IVDesc> mImages, ForeignCollection<IVDesc> iDescs) {
        this.id = id;
        this.gydwId = gydwId;
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
        this.weather = weather;
        this.picattachment = picattachment;
        this.tpjd = tpjd;
        this.tpwd = tpwd;
        this.qmtp1 = qmtp1;
        this.qmtp2 = qmtp2;
        this.qmtp3 = qmtp3;
        this.qmtp4 = qmtp4;
        this.qmtp5 = qmtp5;
        this.projacceptDetailList = projacceptDetailList;
        this.projacceptItemBeen = projacceptItemBeen;
        this.mImages = mImages;
        this.iDescs = iDescs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGydwId() {
        return gydwId;
    }

    public void setGydwId(String gydwId) {
        this.gydwId = gydwId;
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

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPicattachment() {
        return picattachment;
    }

    public void setPicattachment(String picattachment) {
        this.picattachment = picattachment;
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

    public byte[] getQmtp1() {
        return qmtp1;
    }

    public void setQmtp1(byte[] qmtp1) {
        this.qmtp1 = qmtp1;
    }

    public byte[] getQmtp2() {
        return qmtp2;
    }

    public void setQmtp2(byte[] qmtp2) {
        this.qmtp2 = qmtp2;
    }

    public byte[] getQmtp3() {
        return qmtp3;
    }

    public void setQmtp3(byte[] qmtp3) {
        this.qmtp3 = qmtp3;
    }

    public byte[] getQmtp4() {
        return qmtp4;
    }

    public void setQmtp4(byte[] qmtp4) {
        this.qmtp4 = qmtp4;
    }

    public byte[] getQmtp5() {
        return qmtp5;
    }

    public void setQmtp5(byte[] qmtp5) {
        this.qmtp5 = qmtp5;
    }

    public List<ProjacceptItemBean> getProjacceptDetailList() {
        return projacceptDetailList;
    }

    public void setProjacceptDetailList(List<ProjacceptItemBean> projacceptDetailList) {
        this.projacceptDetailList = projacceptDetailList;
    }

    public ForeignCollection<ProjacceptItemBean> getProjacceptItemBeen() {
        return projacceptItemBeen;
    }

    public void setProjacceptItemBeen(ForeignCollection<ProjacceptItemBean> projacceptItemBeen) {
        this.projacceptItemBeen = projacceptItemBeen;
    }

    public List<IVDesc> getmImages() {
        return mImages;
    }

    public void setmImages(List<IVDesc> mImages) {
        this.mImages = mImages;
    }

    public ForeignCollection<IVDesc> getiDescs() {
        return iDescs;
    }

    public void setiDescs(ForeignCollection<IVDesc> iDescs) {
        this.iDescs = iDescs;
    }
}
