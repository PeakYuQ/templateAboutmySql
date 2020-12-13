package com.peakyu.application.dao;


public class employee {

  private long id;
  private String sign;
  private String pwd;
  private String name;
  private long stationId;

  public employee(long id, String sign, String pwd, String name, long stationId) {
    this.id = id;
    this.sign = sign;
    this.pwd = pwd;
    this.name = name;
    this.stationId = stationId;
  }

  public employee(String sign, String pwd, String name, long stationId) {
    this.sign = sign;
    this.pwd = pwd;
    this.name = name;
    this.stationId = stationId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getStationId() {
    return stationId;
  }

  public void setStationId(long stationId) {
    this.stationId = stationId;
  }

}
