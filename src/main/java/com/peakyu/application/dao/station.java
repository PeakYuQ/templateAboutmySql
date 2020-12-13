package com.peakyu.application.dao;


public class station {

  private Integer id;
  private String name;
  private String interduce;

  public station(Integer id, String name, String interduce) {
    this.id = id;
    this.name = name;
    this.interduce = interduce;
  }

  public station(String name, String interduce) {
    this.name = name;
    this.interduce = interduce;
  }

  @Override
  public String toString() {
    return "Station{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", interduce='" + interduce + '\'' +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getInterduce() {
    return interduce;
  }

  public void setInterduce(String interduce) {
    this.interduce = interduce;
  }

}
