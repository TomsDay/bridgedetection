package com.suken.bridgedetection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
//@DatabaseTable(tableName = "tb_maintenaceitem")
public class MaintenanceItemBean implements Serializable {
    private String diseaseName;
    private String unit;
    private String number;
    private String address;
    private String checkTime;
    private String  Inspection;
    private boolean isShow;

    private List<ImageDesc> mImages = new ArrayList<ImageDesc>();
    private List<VideoDesc> mVideo = new ArrayList<VideoDesc>();
    public static class ImageDesc {
        public String name;
        public String path;
    }

    public static class VideoDesc {
        public String name;
        public String path;
    }

    public MaintenanceItemBean() {
    }

    public MaintenanceItemBean(String diseaseName, String unit, String number, String address, String checkTime, String inspection, List<ImageDesc> mImages, List<VideoDesc> mVideo) {
        this.diseaseName = diseaseName;
        this.unit = unit;
        this.number = number;
        this.address = address;
        this.checkTime = checkTime;
        Inspection = inspection;
        this.mImages = mImages;
        this.mVideo = mVideo;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getInspection() {
        return Inspection;
    }

    public void setInspection(String inspection) {
        Inspection = inspection;
    }

    public List<ImageDesc> getmImages() {
        return mImages;
    }

    public void setmImages(List<ImageDesc> mImages) {
        this.mImages = mImages;
    }

    public List<VideoDesc> getmVideo() {
        return mVideo;
    }

    public void setmVideo(List<VideoDesc> mVideo) {
        this.mVideo = mVideo;
    }
}
