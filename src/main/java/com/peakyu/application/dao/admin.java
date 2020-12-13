package com.peakyu.application.dao;

public class admin {
    private Integer id;  //id是标号
    private String name;  //admin名
    private String psk;     //密码

    public admin(Integer id, String name, String psk) {
        this.id = id;
        this.name = name;
        this.psk = psk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpsk() {
        return psk;
    }

    public void setpsk(String psk) {
        this.psk = psk;
    }

    @Override
    public String toString() {
        return "admin{" +
                "name='" + name + '\'' +
                ", psk='" + psk + '\'' +
                '}';
    }
}
