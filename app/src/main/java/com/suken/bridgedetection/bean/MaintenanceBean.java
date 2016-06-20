package com.suken.bridgedetection.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/6.
 */
@DatabaseTable(tableName = "tb_maintenace")
public class MaintenanceBean implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "custodyUnit")
    private String custodyUnit;
    @DatabaseField(columnName = "patrolSection")
    private String patrolSection;
    @DatabaseField(columnName = "timeQuantum")
    private String timeQuantum;
    @DatabaseField(columnName = "date")
    private String date;
    @DatabaseField(columnName = "weather")
    private String weather;
    @DatabaseField(columnName = "formID")
    private String formID;
    @DatabaseField(columnName = "patrolType")
    private String patrolType;

}
