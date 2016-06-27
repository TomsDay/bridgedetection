package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
@DatabaseTable(tableName = "tb_maintenancelogitem")
public class MaintenanceLogItemBean implements Serializable {
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    private String projectName;
    @DatabaseField
    private String materialName;
    @DatabaseField
    private String unit;
    @DatabaseField
    private String count;
    @DatabaseField
    private String address;
    @DatabaseField
    private String checkTime;

    private boolean isShow;
    private List<IVDesc> mImages = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo = new ArrayList<IVDesc>();
    /**
     * foreign = true:说明这是一个外部引用关系
     * foreignAutoRefresh = true：当对象被查询时，外部属性自动刷新（暂时我也没看懂其作用）
     *
     */
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private MaintenanceLogBean maintenanceLogBean;



    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    @ForeignCollectionField
    private ForeignCollection<IVDesc> iDescs;
    @ForeignCollectionField
    private ForeignCollection<IVDesc> vDescs;

    public MaintenanceLogItemBean() {
    }

    public MaintenanceLogItemBean(int id, String projectName, String materialName, String unit, String count, String address, String checkTime, boolean isShow, List<IVDesc> mImages, List<IVDesc> mVideo) {
        this.id = id;
        this.projectName = projectName;
        this.materialName = materialName;
        this.unit = unit;
        this.count = count;
        this.address = address;
        this.checkTime = checkTime;
        this.isShow = isShow;
        this.mImages = mImages;
        this.mVideo = mVideo;
    }

    @Override
    public String toString() {
        return "MaintenanceLogItemBean{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", unit='" + unit + '\'' +
                ", count='" + count + '\'' +
                ", address='" + address + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", isShow=" + isShow +
                ", mImages=" + mImages +
                ", mVideo=" + mVideo +
                ", maintenanceLogBean=" + maintenanceLogBean +
                '}';
    }

    public ForeignCollection<IVDesc> getvDescs() {
        return vDescs;
    }

    public void setvDescs(ForeignCollection<IVDesc> vDescs) {
        this.vDescs = vDescs;
    }

    public ForeignCollection<IVDesc> getiDescs() {
        return iDescs;
    }

    public void setiDescs(ForeignCollection<IVDesc> iDescs) {
        this.iDescs = iDescs;
    }

    public MaintenanceLogBean getMaintenanceLogBean() {
        return maintenanceLogBean;
    }

    public void setMaintenanceLogBean(MaintenanceLogBean maintenanceLogBean) {
        this.maintenanceLogBean = maintenanceLogBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public List<IVDesc> getmImages() {
        return mImages;
    }

    public void setmImages(List<IVDesc> mImages) {
        this.mImages = mImages;
    }

    public List<IVDesc> getmVideo() {
        return mVideo;
    }

    public void setmVideo(List<IVDesc> mVideo) {
        this.mVideo = mVideo;
    }
}
