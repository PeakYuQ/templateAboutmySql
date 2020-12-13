package com.peakyu.application.dao;


import java.sql.Timestamp;

public class orderadvance {

  private int id;
  private String license;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private int firId;
  private int lasId;
  private int status;
  private String operatorIn;
  private String operatorOut;

  @Override
  public String toString() {
    return "orderadvance{" +
            "id=" + id +
            ", license='" + license + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", firId=" + firId +
            ", lasId=" + lasId +
            ", status=" + status +
            ", operatorIn='" + operatorIn + '\'' +
            ", operatorOut='" + operatorOut + '\'' +
            '}';
  }

  public orderadvance(int id, String license, Timestamp startTime, Timestamp endTime, int firId, int lasId, int status, String operatorIn, String operatorOut) {
    this.id = id;
    this.license = license;
    this.startTime = startTime;
    this.endTime = endTime;
    this.firId = firId;
    this.lasId = lasId;
    this.status = status;
    this.operatorIn = operatorIn;
    this.operatorOut = operatorOut;
  }

  public orderadvance(String license, Timestamp startTime, int firId, String operatorIn) {
    this.license = license;
    this.startTime = startTime;
    this.firId = firId;
    this.operatorIn = operatorIn;
  }



  public orderadvance() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }


  public java.sql.Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Timestamp startTime) {
    this.startTime = startTime;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }


  public int getFirId() {
    return firId;
  }

  public void setFirId(int firId) {
    this.firId = firId;
  }


  public int getLasId() {
    return lasId;
  }

  public void setLasId(int lasId) {
    this.lasId = lasId;
  }


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public String getOperatorIn() {
    return operatorIn;
  }

  public void setOperatorIn(String operatorIn) {
    this.operatorIn = operatorIn;
  }


  public String getOperatorOut() {
    return operatorOut;
  }

  public void setOperatorOut(String operatorOut) {
    this.operatorOut = operatorOut;
  }

}
