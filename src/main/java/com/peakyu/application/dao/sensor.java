package com.peakyu.application.dao;

import java.sql.Timestamp;

public class sensor {
    private Long id;               //记录序号
    private Integer sensorId;         //传感器账号
    private String sensorName;        //传感器名称
    private String sensorDescribe;    //传感器描述
    private Timestamp time;           //传感器上传数据时间
    private Integer temperature;      //温度
    private Integer humidity;         //湿度
    private Integer starch;           //淀粉含量
    private Integer alcohol;          //酒精含量
    private Integer acidity;          //酸度
    private Integer other1;           //预留项
    private Integer other2;
    private Integer other3;
    private Integer other4;
    private Integer other5;


    //无参构造器
    public sensor() {
    }

    public sensor(Integer sensorId, Timestamp time, Integer temperature, Integer humidity, Integer starch, Integer alcohol, Integer acidity) {
        this.sensorId = sensorId;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.starch = starch;
        this.alcohol = alcohol;
        this.acidity = acidity;
    }


    @Override
    public String toString() {
        return "sensor{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", sensorName='" + sensorName + '\'' +
                ", sensorDescribe='" + sensorDescribe + '\'' +
                ", time=" + time +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", starch=" + starch +
                ", alcohol=" + alcohol +
                ", acidity=" + acidity +
                ", other1=" + other1 +
                ", other2=" + other2 +
                ", other3=" + other3 +
                ", other4=" + other4 +
                ", other5=" + other5 +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorDescribe() {
        return sensorDescribe;
    }

    public void setSensorDescribe(String sensorDescribe) {
        this.sensorDescribe = sensorDescribe;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getStarch() {
        return starch;
    }

    public void setStarch(Integer starch) {
        this.starch = starch;
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
    }

    public Integer getAcidity() {
        return acidity;
    }

    public void setAcidity(Integer acidity) {
        this.acidity = acidity;
    }

    public Integer getOther1() {
        return other1;
    }

    public void setOther1(Integer other1) {
        this.other1 = other1;
    }

    public Integer getOther2() {
        return other2;
    }

    public void setOther2(Integer other2) {
        this.other2 = other2;
    }

    public Integer getOther3() {
        return other3;
    }

    public void setOther3(Integer other3) {
        this.other3 = other3;
    }

    public Integer getOther4() {
        return other4;
    }

    public void setOther4(Integer other4) {
        this.other4 = other4;
    }

    public Integer getOther5() {
        return other5;
    }

    public void setOther5(Integer other5) {
        this.other5 = other5;
    }
}
