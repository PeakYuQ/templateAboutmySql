package com.peakyu.application.dao;


import java.sql.Timestamp;

public class note {

  private long id;
  private String title;
  private String txt;
  private java.sql.Timestamp time;
  private String operator;

  public note(String title, String txt, Timestamp time, String operator) {
    this.title = title;
    this.txt = txt;
    this.time = time;
    this.operator = operator;
  }



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getTxt() {
    return txt;
  }

  public void setTxt(String txt) {
    this.txt = txt;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

}
