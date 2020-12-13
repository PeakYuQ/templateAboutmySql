package com.peakyu.application.dao;


import java.sql.Timestamp;

public class Traffic {

  private long id;
  private long start;
  private long end;
  private String txt;
  private java.sql.Timestamp time;


  public Traffic(long id, long start, long end, String txt, Timestamp time) {
    this.id = id;
    this.start = start;
    this.end = end;
    this.txt = txt;
    this.time = time;
  }

  public Traffic(long start, long end, String txt, Timestamp time) {
    this.start = start;
    this.end = end;
    this.txt = txt;
    this.time = time;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }


  public long getEnd() {
    return end;
  }

  public void setEnd(long end) {
    this.end = end;
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

}
