package com.suken.bridgedetection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class UploadBean implements Serializable{
    private String id;
    /**  路线*/
    private String lxid;
    /**  路线编号*/
    private String lxbh;
    /**  路线名称*/
    private String lxmc;

    private String xcry;
    private String jcry;
    private String weather;
    private String gydwId;
    private String gydwName;
    private String jcsj;
    private String tjsj;
    private int xclx;
    private String xcld;

    private List<UploadListBean> inspectLogDetailList = new ArrayList<UploadListBean>();

    @Override
    public String toString() {
        return "UploadBean{" +
                "id='" + id + '\'' +
                ", lxid='" + lxid + '\'' +
                ", lxbh='" + lxbh + '\'' +
                ", lxmc='" + lxmc + '\'' +
                ", xcry='" + xcry + '\'' +
                ", jcry='" + jcry + '\'' +
                ", weather='" + weather + '\'' +
                ", gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", jcsj='" + jcsj + '\'' +
                ", tjsj='" + tjsj + '\'' +
                ", xclx=" + xclx +
                ", xcld='" + xcld + '\'' +
                ", inspectLogDetailList=" + inspectLogDetailList +
                '}';
    }

    public UploadBean() {
    }

    public UploadBean(String id, String lxid, String lxbh, String lxmc, String xcry, String jcry, String weather, String gydwId, String gydwName, String jcsj, int xclx, String xcld) {
        this.id = id;
        this.lxid = lxid;
        this.lxbh = lxbh;
        this.lxmc = lxmc;
        this.xcry = xcry;
        this.jcry = jcry;
        this.weather = weather;
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.jcsj = jcsj;
        this.xclx = xclx;
        this.xcld = xcld;
    }

    public List<UploadListBean> getInspectLogDetailList() {
        return inspectLogDetailList;
    }

    public void setInspectLogDetailList(List<UploadListBean> inspectLogDetailList) {
        this.inspectLogDetailList = inspectLogDetailList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLxid() {
        return lxid;
    }

    public void setLxid(String lxid) {
        this.lxid = lxid;
    }

    public String getLxbh() {
        return lxbh;
    }

    public void setLxbh(String lxbh) {
        this.lxbh = lxbh;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getXcry() {
        return xcry;
    }

    public void setXcry(String xcry) {
        this.xcry = xcry;
    }

    public String getJcry() {
        return jcry;
    }

    public void setJcry(String jcry) {
        this.jcry = jcry;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
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

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }

    public int getXclx() {
        return xclx;
    }

    public void setXclx(int xclx) {
        this.xclx = xclx;
    }

    public String getXcld() {
        return xcld;
    }

    public void setXcld(String xcld) {
        this.xcld = xcld;
    }

    //    /**  归属组织机构*/
//    private String orgid_;
//    /**  版本号*/
//    private String versionno_;
//    /**  创建者ID*/
//    private String create_by_;
//    /**  创建者*/
//    private String creator_;
//    /**  创建时间*/
//    private String createtime_;
//    /**  最后修改者ID*/
//    private String update_by_;
//    /**  最后修改者*/
//    private String updator_;
//    /**  最后修改时间*/
//    private String updatetime_;
//    /**  是否有效*/
//    private String flag_;
//    /**  养护巡查日志记录ID*/
//    private String yh_insp_id_;
//    /**  巡查日志编号*/
//    private String yh_insp_no_;
//    /**  养护通知单id*/
//    private String notice_id_;
//    /**  养护通知单编号*/
//    private String notice_no_;
//
//    /**  桩号范围*/
//    private String zhfw_;
//    /**  方向*/
//    private String fx_;
//    /**  桩号*/
//    private String yhzh_;
//    /**  病害id*/
//    private String bhid_;
//    /**  病害名称*/
//    private String bhmc_;
//    /**  病害位置*/
//    private String bhwz_;
//    /**  单位*/
//    private String dw_;
//    /**  预估数量*/
//    private String ygsl_;
//    /**  检查时间*/
//    private String jcsj_;
//    /**  病害描述*/
//    private String remark_;
//    /**  图片附件IDS*/
//    private String picattachment_;
//    /**  视频附件IDS*/
//    private String vidattachment_;
//    /**  表单编号*/
//    private String bno_;
//    /**  提交时间*/
//    private String tjsj_;
//    /**  图片经度*/
//    private String tpjd_;
//    /**  图片纬度*/
//    private String tpwd_;
//    /**  养护状态(1:发现病害;2:下达养护任务通知单;3:施工单位养护完成;4:巡查验收完成)*/
//    private String yhzt_;
//    /** 养护状态名称 */
//    private String yhztmc_;
//    /**  自订字段1*/
//    private String cus1_;
//    /**  自订字段2*/
//    private String cus2_;
//    /**  自订字段3*/
//    private String cus3_;
//    /** 自订字段4 */
//    private String cus4_;
//    /**  自订字段5*/
//    private String cus5_;
//    /**  自订字段6*/
//    private String cus6_;



    //    private String id_;
//    private String yh_insp_id_;
//    private String zhfw_;
//    private String fx_;
//    private String yhzh_;
//    private String bhid_;
//    private String bhmc_;
//    private String bhwz_;
//    private String dw_;
//    private String ygsl_;
//    private String jcsj_;
//    private String remark_;
//    private String picattachment_;
//    private String vidattachment_;
//    private String tjsj_;
//    private String tpjd_;
//    private String tpwd_;
//    private String yhzt_;
//
//
//
//
//
//    private String id_;
//    private String orgid_;
//    private String versionno_;
//    private String create_by_;
//    private String createtime_;
//    private String update_by_;
//    private String updatetime_;
//    private String flag_;
//    private String gydw_id_;
//    private String lxid_;
//    private String lxbh_;
//    private String xcld_;
//    private String jcks_;
//    private String jcjs_;
//    private String jcsj_;
//    private String weather_;
//    private String xcry_;
//    private String jcry_;
//    private String tjsj_;
//    private String status_;
//    private String bno_;


}
