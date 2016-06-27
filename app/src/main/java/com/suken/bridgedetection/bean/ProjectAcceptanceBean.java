package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/27.
 */
@DatabaseTable(tableName = "tb_projectacceptancebean")
public class ProjectAcceptanceBean implements Serializable{
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
    /** 维修保养单位*/
    @DatabaseField
    private String maintenanceUnit;
    /** 施工开始时间*/
    @DatabaseField
    private String constructionStartTime;
    /** 施工结束时间*/
    @DatabaseField
    private String constructionEndTime;
    /** 工程内容*/
    @DatabaseField
    private String content;
    /** 验收结果*/
    @DatabaseField
    private String acceptanceReturn;
    /** 验收负责人*/
    @DatabaseField
    private String acceptancePeople;

    public ProjectAcceptanceBean(int id, String custodyUnit, String serialNumber, String weather, String maintenanceUnit, String constructionStartTime, String constructionEndTime, String content, String acceptanceReturn, String acceptancePeople) {
        this.id = id;
        this.custodyUnit = custodyUnit;
        this.serialNumber = serialNumber;
        this.weather = weather;
        this.maintenanceUnit = maintenanceUnit;
        this.constructionStartTime = constructionStartTime;
        this.constructionEndTime = constructionEndTime;
        this.content = content;
        this.acceptanceReturn = acceptanceReturn;
        this.acceptancePeople = acceptancePeople;
    }

    public ProjectAcceptanceBean() {
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
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

    public String getMaintenanceUnit() {
        return maintenanceUnit;
    }

    public void setMaintenanceUnit(String maintenanceUnit) {
        this.maintenanceUnit = maintenanceUnit;
    }

    public String getConstructionStartTime() {
        return constructionStartTime;
    }

    public void setConstructionStartTime(String constructionStartTime) {
        this.constructionStartTime = constructionStartTime;
    }

    public String getConstructionEndTime() {
        return constructionEndTime;
    }

    public void setConstructionEndTime(String constructionEndTime) {
        this.constructionEndTime = constructionEndTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAcceptanceReturn() {
        return acceptanceReturn;
    }

    public void setAcceptanceReturn(String acceptanceReturn) {
        this.acceptanceReturn = acceptanceReturn;
    }

    public String getAcceptancePeople() {
        return acceptancePeople;
    }

    public void setAcceptancePeople(String acceptancePeople) {
        this.acceptancePeople = acceptancePeople;
    }
}
