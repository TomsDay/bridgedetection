package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/6/24.
 */
@DatabaseTable(tableName = "tb_ivdesc")
public class IVDesc {
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    public String name;
    @DatabaseField
    public String path;

    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceLogItemBean imageMaintenanceLogItemBean;
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceLogItemBean videoMaintenanceLogItemBean;

    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceTableItemBean imageMaintenanceTableItemBean;
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceTableItemBean videoMaintenanceTableItemBean;

    public IVDesc() {
    }

    public IVDesc(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString() {
        return "IVDesc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", imageMaintenanceLogItemBean=" + imageMaintenanceLogItemBean +
                ", videoMaintenanceLogItemBean=" + videoMaintenanceLogItemBean +
                ", imageMaintenanceTableItemBean=" + imageMaintenanceTableItemBean +
                ", videoMaintenanceTableItemBean=" + videoMaintenanceTableItemBean +
                '}';
    }

    public MaintenanceTableItemBean getImageMaintenanceTableItemBean() {
        return imageMaintenanceTableItemBean;
    }

    public void setImageMaintenanceTableItemBean(MaintenanceTableItemBean imageMaintenanceTableItemBean) {
        this.imageMaintenanceTableItemBean = imageMaintenanceTableItemBean;
    }

    public MaintenanceTableItemBean getVideoMaintenanceTableItemBean() {
        return videoMaintenanceTableItemBean;
    }

    public void setVideoMaintenanceTableItemBean(MaintenanceTableItemBean videoMaintenanceTableItemBean) {
        this.videoMaintenanceTableItemBean = videoMaintenanceTableItemBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MaintenanceLogItemBean getImageMaintenanceLogItemBean() {
        return imageMaintenanceLogItemBean;
    }

    public void setImageMaintenanceLogItemBean(MaintenanceLogItemBean imageMaintenanceLogItemBean) {
        this.imageMaintenanceLogItemBean = imageMaintenanceLogItemBean;
    }

    public MaintenanceLogItemBean getVideoMaintenanceLogItemBean() {
        return videoMaintenanceLogItemBean;
    }

    public void setVideoMaintenanceLogItemBean(MaintenanceLogItemBean videoMaintenanceLogItemBean) {
        this.videoMaintenanceLogItemBean = videoMaintenanceLogItemBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
