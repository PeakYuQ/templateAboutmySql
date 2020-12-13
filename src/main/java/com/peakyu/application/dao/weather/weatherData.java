package com.peakyu.application.dao.weather;

import java.io.Serializable;
import java.util.List;

public class weatherData implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** 昨日天气情况 .*/
    private Yesterday yesterday;

    /** 未来七日预测 .*/
    private List<Forecast> forecast;

    /** 城市 .*/
    private String city;

    /** 空气指数 .*/
    private String aqi;

    /** 提示 .*/
    private String ganmao;

    /** 温度 .*/
    private String wendu;

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    @Override
    public String toString() {
        return "WeatherData [yesterday=" + yesterday + ", forecast=" + forecast + ", city=" + city + ", aqi=" + aqi
                + ", ganmao=" + ganmao + ", wendu=" + wendu + "]";
    }

}