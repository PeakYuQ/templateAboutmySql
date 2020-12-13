package com.peakyu.application.dao;


import java.sql.Timestamp;

public class Stationrecord {

  private long id;
  private String license;
  private java.sql.Timestamp time;
  private long status;
  private long station_id;   //0代表是进站   1代表出战

  public Stationrecord(long id, String license, Timestamp time, long status,long station_id) {
    this.id = id;
    this.license = license;
    this.time = time;
    this.status = status;
    this.station_id=station_id;
  }



  public long getStation_id() {
    return station_id;
  }

  public void setStation_id(long station_id) {
    this.station_id = station_id;
  }

  public Stationrecord(String license, Timestamp time, long status, long station_id) {
    this.license = license;
    this.time = time;
    this.status = status;
    this.station_id=station_id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
