package com.peakyu.application.dao.weather;

import java.io.Serializable;

public class Forecast implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**　日期 .*/
    private String date;

    /**　最高温度　．*/
    private String high;

    /** 风力 .*/
    private String fengli;

    /**　最低温度 .*/
    private String low;

    /** 风向 .*/
    private String fengxiang;

    /** 天气情况 .*/
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Forecast [date=" + date + ", high=" + high + ", fengli=" + fengli + ", low=" + low + ", fengxiang="
                + fengxiang + ", type=" + type + "]";
    }

}

