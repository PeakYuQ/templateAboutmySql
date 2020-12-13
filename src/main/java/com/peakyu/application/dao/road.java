package com.peakyu.application.dao;


public class road {

  private String name;
  private int firId;
  private int lasId;
  private int length;
  private int time;
  private int price;

  public road(String name, int firId, int lasId, int length, int time, int price) {
    this.name = name;
    this.firId = firId;
    this.lasId = lasId;
    this.length = length;
    this.time = time;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getFirId() {
    return firId;
  }

  public void setFirId(int firId) {
    this.firId = firId;
  }


  public int getlasId() {
    return lasId;
  }

  public void setlasId(int lasId) {
    this.lasId = lasId;
  }


  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }


  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }


  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

}
