package com.suken.bridgedetection.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/14.
 */
@DatabaseTable(tableName = "tb_maintenancetable")
public class MaintenanceTableBean implements Serializable {
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    private String custodyUnit;
    @DatabaseField
    private String patrolSection;
    @DatabaseField
    private String timeQuantum;
    @DatabaseField
    private String datetime;
    @DatabaseField
    private String weather;
    @DatabaseField
    private String searchType;
    @DatabaseField
    private String inspectOne;

    @ForeignCollectionField
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    private ForeignCollection<MaintenanceTableItemBean> maintenanceTableItemBeen;

    public MaintenanceTableBean() {
    }

    public MaintenanceTableBean(int id, String patrolSection, String timeQuantum, String datetime, String weather, String searchType) {
        this.id = id;
        this.patrolSection = patrolSection;
        this.timeQuantum = timeQuantum;
        this.datetime = datetime;
        this.weather = weather;
        this.searchType = searchType;
    }

    @Override
    public String toString() {
        return "MaintenanceTableBean{" +
                "id=" + id +
                ", custodyUnit='" + custodyUnit + '\'' +
                ", patrolSection='" + patrolSection + '\'' +
                ", timeQuantum='" + timeQuantum + '\'' +
                ", datetime='" + datetime + '\'' +
                ", weather='" + weather + '\'' +
                ", searchType='" + searchType + '\'' +
                ", inspectOne='" + inspectOne + '\'' +
                ", maintenanceTableItemBeen=" + maintenanceTableItemBeen +
                '}';
    }

    public ForeignCollection<MaintenanceTableItemBean> getMaintenanceTableItemBeen() {
        return maintenanceTableItemBeen;
    }

    public void setMaintenanceTableItemBeen(ForeignCollection<MaintenanceTableItemBean> maintenanceTableItemBeen) {
        this.maintenanceTableItemBeen = maintenanceTableItemBeen;
    }

    public String getInspectOne() {
        return inspectOne;
    }

    public void setInspectOne(String inspectOne) {
        this.inspectOne = inspectOne;
    }

    public String getCustodyUnit() {
        return custodyUnit;
    }

    public void setCustodyUnit(String custodyUnit) {
        this.custodyUnit = custodyUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatrolSection() {
        return patrolSection;
    }

    public void setPatrolSection(String patrolSection) {
        this.patrolSection = patrolSection;
    }

    public String getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(String timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
