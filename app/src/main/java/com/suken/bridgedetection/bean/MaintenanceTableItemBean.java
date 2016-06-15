package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
@DatabaseTable(tableName = "tb_maintenancetableitem")
public class MaintenanceTableItemBean implements Serializable{
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    private String diseaseName;
    @DatabaseField
    private String unit;
    @DatabaseField
    private String count;
    @DatabaseField
    private String address;
    @DatabaseField
    private String checkTime;
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

    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceTableBean maintenanceTableBean;

    public MaintenanceTableItemBean() {
    }

    @Override
    public String toString() {
        return "MaintenanceTableItemBean{" +
                "id=" + id +
                ", diseaseName='" + diseaseName + '\'' +
                ", unit='" + unit + '\'' +
                ", count='" + count + '\'' +
                ", address='" + address + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", maintenanceTableBean=" + maintenanceTableBean +
                '}';
    }

    public MaintenanceTableItemBean(int id, String diseaseName, String unit, String count, String address, String checkTime) {
        this.id = id;
        this.diseaseName = diseaseName;
        this.unit = unit;
        this.count = count;
        this.address = address;
        this.checkTime = checkTime;
    }


    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
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

    public MaintenanceTableBean getMaintenanceTableBean() {
        return maintenanceTableBean;
    }

    public void setMaintenanceTableBean(MaintenanceTableBean maintenanceTableBean) {
        this.maintenanceTableBean = maintenanceTableBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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
}
