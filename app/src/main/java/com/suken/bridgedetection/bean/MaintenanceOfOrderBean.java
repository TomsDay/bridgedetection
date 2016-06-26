package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
@DatabaseTable(tableName = "tb_maintenanceoforder")
public class MaintenanceOfOrderBean implements Serializable {
    @DatabaseField(generatedId = true) //主键自增加
    private int id;
    @DatabaseField
    private String custodyUnit;
    @DatabaseField
    private String checkDate;
    @DatabaseField
    private String weather;
    @DatabaseField
    private String content;
    /** 标志摆放 */
    @DatabaseField
    private String markPut;
    /** 标志服 */
    @DatabaseField
    private String uniforms;
    /** 安全作业 */
    @DatabaseField
    private String safeOperation;
    /** 施工作业 */
    @DatabaseField
    private String constructionWork;
    /** 安全行驶 */
    @DatabaseField
    private String safeDriving;
    /** 是否有安全管理员 */
    @DatabaseField
    private String haveSecurityAdministrator;
    /** 其它情况 */
    @DatabaseField
    private String otherSituations;
    /** 出现安全隐患处理意见 */
    @DatabaseField
    private String processingOpinion;
    /** 检查人 */
    @DatabaseField
    private String checkPeople;
    /** 安全管理员 */
    @DatabaseField
    private String SecurityAdministrator;

    private List<IVDesc> mImages1 = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo1 = new ArrayList<IVDesc>();
    private List<IVDesc> mImages2 = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo2 = new ArrayList<IVDesc>();
    private List<IVDesc> mImages3 = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo3 = new ArrayList<IVDesc>();
    private List<IVDesc> mImages4 = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo4 = new ArrayList<IVDesc>();
    private List<IVDesc> mImages5 = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo5 = new ArrayList<IVDesc>();
    private List<IVDesc> mImages6 = new ArrayList<IVDesc>();
    private List<IVDesc> mVideo6 = new ArrayList<IVDesc>();



    public MaintenanceOfOrderBean() {
    }

    public MaintenanceOfOrderBean(int id, String custodyUnit, String checkDate, String weather, String content, String markPut, String uniforms, String safeOperation, String constructionWork, String safeDriving, String haveSecurityAdministrator, String otherSituations, String processingOpinion, String checkPeople, String securityAdministrator) {
        this.id = id;
        this.custodyUnit = custodyUnit;
        this.checkDate = checkDate;
        this.weather = weather;
        this.content = content;
        this.markPut = markPut;
        this.uniforms = uniforms;
        this.safeOperation = safeOperation;
        this.constructionWork = constructionWork;
        this.safeDriving = safeDriving;
        this.haveSecurityAdministrator = haveSecurityAdministrator;
        this.otherSituations = otherSituations;
        this.processingOpinion = processingOpinion;
        this.checkPeople = checkPeople;
        SecurityAdministrator = securityAdministrator;
    }

    @Override
    public String toString() {
        return "MaintenanceOfOrderBean{" +
                "id=" + id +
                ", custodyUnit='" + custodyUnit + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", weather='" + weather + '\'' +
                ", content='" + content + '\'' +
                ", markPut='" + markPut + '\'' +
                ", uniforms='" + uniforms + '\'' +
                ", safeOperation='" + safeOperation + '\'' +
                ", constructionWork='" + constructionWork + '\'' +
                ", safeDriving='" + safeDriving + '\'' +
                ", haveSecurityAdministrator='" + haveSecurityAdministrator + '\'' +
                ", otherSituations='" + otherSituations + '\'' +
                ", processingOpinion='" + processingOpinion + '\'' +
                ", checkPeople='" + checkPeople + '\'' +
                ", SecurityAdministrator='" + SecurityAdministrator + '\'' +
                '}';
    }

    public List<IVDesc> getmImages1() {
        return mImages1;
    }

    public void setmImages1(List<IVDesc> mImages1) {
        this.mImages1 = mImages1;
    }

    public List<IVDesc> getmVideo1() {
        return mVideo1;
    }

    public void setmVideo1(List<IVDesc> mVideo1) {
        this.mVideo1 = mVideo1;
    }

    public List<IVDesc> getmImages2() {
        return mImages2;
    }

    public void setmImages2(List<IVDesc> mImages2) {
        this.mImages2 = mImages2;
    }

    public List<IVDesc> getmVideo2() {
        return mVideo2;
    }

    public void setmVideo2(List<IVDesc> mVideo2) {
        this.mVideo2 = mVideo2;
    }

    public List<IVDesc> getmImages3() {
        return mImages3;
    }

    public void setmImages3(List<IVDesc> mImages3) {
        this.mImages3 = mImages3;
    }

    public List<IVDesc> getmVideo3() {
        return mVideo3;
    }

    public void setmVideo3(List<IVDesc> mVideo3) {
        this.mVideo3 = mVideo3;
    }

    public List<IVDesc> getmImages4() {
        return mImages4;
    }

    public void setmImages4(List<IVDesc> mImages4) {
        this.mImages4 = mImages4;
    }

    public List<IVDesc> getmVideo4() {
        return mVideo4;
    }

    public void setmVideo4(List<IVDesc> mVideo4) {
        this.mVideo4 = mVideo4;
    }

    public List<IVDesc> getmImages5() {
        return mImages5;
    }

    public void setmImages5(List<IVDesc> mImages5) {
        this.mImages5 = mImages5;
    }

    public List<IVDesc> getmVideo5() {
        return mVideo5;
    }

    public void setmVideo5(List<IVDesc> mVideo5) {
        this.mVideo5 = mVideo5;
    }

    public List<IVDesc> getmImages6() {
        return mImages6;
    }

    public void setmImages6(List<IVDesc> mImages6) {
        this.mImages6 = mImages6;
    }

    public List<IVDesc> getmVideo6() {
        return mVideo6;
    }

    public void setmVideo6(List<IVDesc> mVideo6) {
        this.mVideo6 = mVideo6;
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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMarkPut() {
        return markPut;
    }

    public void setMarkPut(String markPut) {
        this.markPut = markPut;
    }

    public String getUniforms() {
        return uniforms;
    }

    public void setUniforms(String uniforms) {
        this.uniforms = uniforms;
    }

    public String getSafeOperation() {
        return safeOperation;
    }

    public void setSafeOperation(String safeOperation) {
        this.safeOperation = safeOperation;
    }

    public String getConstructionWork() {
        return constructionWork;
    }

    public void setConstructionWork(String constructionWork) {
        this.constructionWork = constructionWork;
    }

    public String getSafeDriving() {
        return safeDriving;
    }

    public void setSafeDriving(String safeDriving) {
        this.safeDriving = safeDriving;
    }

    public String getHaveSecurityAdministrator() {
        return haveSecurityAdministrator;
    }

    public void setHaveSecurityAdministrator(String haveSecurityAdministrator) {
        this.haveSecurityAdministrator = haveSecurityAdministrator;
    }

    public String getOtherSituations() {
        return otherSituations;
    }

    public void setOtherSituations(String otherSituations) {
        this.otherSituations = otherSituations;
    }

    public String getProcessingOpinion() {
        return processingOpinion;
    }

    public void setProcessingOpinion(String processingOpinion) {
        this.processingOpinion = processingOpinion;
    }

    public String getCheckPeople() {
        return checkPeople;
    }

    public void setCheckPeople(String checkPeople) {
        this.checkPeople = checkPeople;
    }

    public String getSecurityAdministrator() {
        return SecurityAdministrator;
    }

    public void setSecurityAdministrator(String securityAdministrator) {
        SecurityAdministrator = securityAdministrator;
    }
}
