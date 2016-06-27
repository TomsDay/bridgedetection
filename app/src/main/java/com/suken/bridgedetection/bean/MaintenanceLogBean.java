package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/24.
 */
@DatabaseTable(tableName = "tb_maintenancelog")
public class MaintenanceLogBean implements Serializable{
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    /** 管养单位*/
    @DatabaseField
    private String custodyUnit;
    /** 编号*/
    @DatabaseField
    private String serialNumber;
    /** 天气*/
    @DatabaseField
    private String weather;
    /** 日期*/
    @DatabaseField
    private String date;
    /** 维修部门*/
    @DatabaseField
    private String maintenanceDepartment;
    /** 检查人*/
    @DatabaseField
    private String rummager;
    /** 负责人*/
    @DatabaseField
    private String principal;

    @ForeignCollectionField
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    private ForeignCollection<MaintenanceLogItemBean> maintenanceTableItemBeen;

    public MaintenanceLogBean() {
    }

    public MaintenanceLogBean(int id, String custodyUnit, String serialNumber, String weather, String date, String maintenanceDepartment, String rummager, String principal) {
        this.id = id;
        this.custodyUnit = custodyUnit;
        this.serialNumber = serialNumber;
        this.weather = weather;
        this.date = date;
        this.maintenanceDepartment = maintenanceDepartment;
        this.rummager = rummager;
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "MaintenanceLogBean{" +
                "id=" + id +
                ", custodyUnit='" + custodyUnit + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", weather='" + weather + '\'' +
                ", date='" + date + '\'' +
                ", maintenanceDepartment='" + maintenanceDepartment + '\'' +
                ", rummager='" + rummager + '\'' +
                ", principal='" + principal + '\'' +
                ", maintenanceTableItemBeen=" + maintenanceTableItemBeen +
                '}';
    }

    public ForeignCollection<MaintenanceLogItemBean> getMaintenanceTableItemBeen() {
        return maintenanceTableItemBeen;
    }

    public void setMaintenanceTableItemBeen(ForeignCollection<MaintenanceLogItemBean> maintenanceTableItemBeen) {
        this.maintenanceTableItemBeen = maintenanceTableItemBeen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustodyUnit() {
        return custodyUnit;
    }

    public void setCustodyUnit(String custodyUnit) {
        this.custodyUnit = custodyUnit;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaintenanceDepartment() {
        return maintenanceDepartment;
    }

    public void setMaintenanceDepartment(String maintenanceDepartment) {
        this.maintenanceDepartment = maintenanceDepartment;
    }

    public String getRummager() {
        return rummager;
    }

    public void setRummager(String rummager) {
        this.rummager = rummager;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
