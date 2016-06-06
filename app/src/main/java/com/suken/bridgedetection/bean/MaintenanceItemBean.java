package com.suken.bridgedetection.bean;

import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/6.
 */
@DatabaseTable(tableName = "tb_maintenaceitem")
public class MaintenanceItemBean implements Serializable {
    private String diseaseName;
    private String unit;
    private String number;
    private String address;
    private String checkTime;
    private String  Inspection;

}
