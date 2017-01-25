package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/11.
 */

@DatabaseTable(tableName = "tb_maintenancedisease")
public class MaintenanceDiseaseBean implements Serializable{
    @DatabaseField(generatedId = true)
    private long ids;
    @DatabaseField
    private long id;//记录ID
    @DatabaseField
    private String bhmc;//病害名称
    @DatabaseField
    private String createBy;//创建人ID
    @DatabaseField
    private String createtime;
    @DatabaseField
    private String creator;//创建人
    @DatabaseField
    private String dw;//单位
    @DatabaseField
    private String ejml;//二级分类
    @DatabaseField
    private String flag;//是否有效  0有效  1 无效
    @DatabaseField
    private String gydwId;//管养单位ID
    @DatabaseField
    private String gydwName;//管养单位名称
    @DatabaseField
    private String orgid;
    @DatabaseField
    private String rowIndex;
    @DatabaseField
    private String shlx;//损坏类型
    @DatabaseField
    private String updateBy;//最后更新人ID
    @DatabaseField
    private String updatetime;//最后更新时间
    @DatabaseField
    private String updator;//最后更新人名称
    @DatabaseField
    private String yjml;//一级分类
    @DatabaseField
    private String versionno;//记录版本号
    @DatabaseField
    private String xcms;//现场描述
    @Override
    public String toString() {
        return "MaintenanceDiseaseBean{" +
                "createBy=" + createBy +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", flag=" + flag +
                ", gydwId=" + gydwId +
                ", gydwName='" + gydwName + '\'' +
                ", id=" + id +
                ", yjml='" + yjml + '\'' +
                ", ejml='" + ejml + '\'' +
                ", bhmc='" + bhmc + '\'' +
                ", shlx='" + shlx + '\'' +
                ", updateBy=" + updateBy +
                ", updatetime=" + updatetime +
                ", updator='" + updator + '\'' +
                ", versionno=" + versionno +
                ", dw='" + dw + '\'' +
                ", xcms='" + xcms + '\'' +
                '}';
    }

    public long getIds() {
        return ids;
    }

    public void setIds(long ids) {
        this.ids = ids;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(String rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYjml() {
        return yjml;
    }

    public void setYjml(String yjml) {
        this.yjml = yjml;
    }

    public String getEjml() {
        return ejml;
    }

    public void setEjml(String ejml) {
        this.ejml = ejml;
    }

    public String getBhmc() {
        return bhmc;
    }

    public void setBhmc(String bhmc) {
        this.bhmc = bhmc;
    }

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getXcms() {
        return xcms;
    }

    public void setXcms(String xcms) {
        this.xcms = xcms;
    }

//    public static class CreatetimeBean {
//        private int date;
//        private int day;
//        private int hours;
//        private int minutes;
//        private int month;
//        private int seconds;
//        private long time;
//        private int timezoneOffset;
//        private int year;
//
//        @Override
//        public String toString() {
//            return "CreatetimeBean{" +
//                    "date=" + date +
//                    ", day=" + day +
//                    ", hours=" + hours +
//                    ", minutes=" + minutes +
//                    ", month=" + month +
//                    ", seconds=" + seconds +
//                    ", time=" + time +
//                    ", timezoneOffset=" + timezoneOffset +
//                    ", year=" + year +
//                    '}';
//        }
//
//        public int getDate() {
//            return date;
//        }
//
//        public void setDate(int date) {
//            this.date = date;
//        }
//
//        public int getDay() {
//            return day;
//        }
//
//        public void setDay(int day) {
//            this.day = day;
//        }
//
//        public int getHours() {
//            return hours;
//        }
//
//        public void setHours(int hours) {
//            this.hours = hours;
//        }
//
//        public int getMinutes() {
//            return minutes;
//        }
//
//        public void setMinutes(int minutes) {
//            this.minutes = minutes;
//        }
//
//        public int getMonth() {
//            return month;
//        }
//
//        public void setMonth(int month) {
//            this.month = month;
//        }
//
//        public int getSeconds() {
//            return seconds;
//        }
//
//        public void setSeconds(int seconds) {
//            this.seconds = seconds;
//        }
//
//        public long getTime() {
//            return time;
//        }
//
//        public void setTime(long time) {
//            this.time = time;
//        }
//
//        public int getTimezoneOffset() {
//            return timezoneOffset;
//        }
//
//        public void setTimezoneOffset(int timezoneOffset) {
//            this.timezoneOffset = timezoneOffset;
//        }
//
//        public int getYear() {
//            return year;
//        }
//
//        public void setYear(int year) {
//            this.year = year;
//        }
//    }

//    public static class UpdatetimeBean {
//        private int date;
//        private int day;
//        private int hours;
//        private int minutes;
//        private int month;
//        private int seconds;
//        private long time;
//        private int timezoneOffset;
//        private int year;
//
//        @Override
//        public String toString() {
//            return "UpdatetimeBean{" +
//                    "date=" + date +
//                    ", day=" + day +
//                    ", hours=" + hours +
//                    ", minutes=" + minutes +
//                    ", month=" + month +
//                    ", seconds=" + seconds +
//                    ", time=" + time +
//                    ", timezoneOffset=" + timezoneOffset +
//                    ", year=" + year +
//                    '}';
//        }
//
//        public int getDate() {
//            return date;
//        }
//
//        public void setDate(int date) {
//            this.date = date;
//        }
//
//        public int getDay() {
//            return day;
//        }
//
//        public void setDay(int day) {
//            this.day = day;
//        }
//
//        public int getHours() {
//            return hours;
//        }
//
//        public void setHours(int hours) {
//            this.hours = hours;
//        }
//
//        public int getMinutes() {
//            return minutes;
//        }
//
//        public void setMinutes(int minutes) {
//            this.minutes = minutes;
//        }
//
//        public int getMonth() {
//            return month;
//        }
//
//        public void setMonth(int month) {
//            this.month = month;
//        }
//
//        public int getSeconds() {
//            return seconds;
//        }
//
//        public void setSeconds(int seconds) {
//            this.seconds = seconds;
//        }
//
//        public long getTime() {
//            return time;
//        }
//
//        public void setTime(long time) {
//            this.time = time;
//        }
//
//        public int getTimezoneOffset() {
//            return timezoneOffset;
//        }
//
//        public void setTimezoneOffset(int timezoneOffset) {
//            this.timezoneOffset = timezoneOffset;
//        }
//
//        public int getYear() {
//            return year;
//        }
//
//        public void setYear(int year) {
//            this.year = year;
//        }
//    }
}
