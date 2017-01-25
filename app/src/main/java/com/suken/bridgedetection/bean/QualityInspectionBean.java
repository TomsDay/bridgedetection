package com.suken.bridgedetection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class QualityInspectionBean implements Serializable{
    private String gydwId;
    private String gydwName;
    private String cjr;
    private String cjrq;
    private String gqzr;
    private String yhkz;
    private List<QualityInspectionItemBean> samplingDetailList= new ArrayList<QualityInspectionItemBean>();

    public QualityInspectionBean() {
    }

    public QualityInspectionBean(String gydwId, String gydwName, String cjr, String cjrq, List<QualityInspectionItemBean> samplingDetailList) {
        this.gydwId = gydwId;
        this.gydwName = gydwName;
        this.cjr = cjr;
        this.cjrq = cjrq;
        this.samplingDetailList = samplingDetailList;
    }

    @Override
    public String toString() {
        return "QualityInspectionBean{" +
                "gydwId='" + gydwId + '\'' +
                ", gydwName='" + gydwName + '\'' +
                ", cjr='" + cjr + '\'' +
                ", cjrq='" + cjrq + '\'' +
                ", gqzr='" + gqzr + '\'' +
                ", yhkz='" + yhkz + '\'' +
                ", samplingDetailList=" + samplingDetailList +
                '}';
    }

    public String getGqzr() {
        return gqzr;
    }

    public void setGqzr(String gqzr) {
        this.gqzr = gqzr;
    }

    public String getYhkz() {
        return yhkz;
    }

    public void setYhkz(String yhkz) {
        this.yhkz = yhkz;
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

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public List<QualityInspectionItemBean> getSamplingDetailList() {
        return samplingDetailList;
    }

    public void setSamplingDetailList(List<QualityInspectionItemBean> samplingDetailList) {
        this.samplingDetailList = samplingDetailList;
    }
}
