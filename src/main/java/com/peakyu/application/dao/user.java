package com.peakyu.application.dao;

import java.sql.Timestamp;

public class user {
    private Integer id;     //用户序号
    private String Email;       //用户账号
    private String Password;     //用户账号密码
    private  Integer active;         //用户账号状态0未审核，1激活，2审核不通过
    private Timestamp ReTime;        //用户创建时间
    private String phone;            //用户电话
    private  Integer sex;                //用户性别0女，1男
    private String name;                 //用户姓名
    private String license;              //用户车牌号

    public user(String email, String password, Integer active, Timestamp reTime, String phone, Integer sex, String name, String license) {
        Email = email;
        Password = password;
        this.active = active;
        ReTime = reTime;
        this.phone = phone;
        this.sex = sex;
        this.name = name;
        this.license = license;
    }
    public user(Integer id,String emial, String password, Integer active, Timestamp reTime, String phone, String license, String name,Integer sex ) {
        this.id = id;
        Email = emial;
        Password = password;
        this.active = active;
        ReTime = reTime;
        this.phone = phone;
        this.sex = sex;
        this.name = name;
        this.license = license;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", Emial='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", active=" + active +
                ", ReTime=" + ReTime +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                ", license='" + license + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSex() {
        return sex;
    }

    public String getLicense() {
        return license;
    }

    public void setEmial(String emial) {
        Email = emial;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public void setReTime(Timestamp reTime) {
        ReTime = reTime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmial() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Integer getActive() {
        return active;
    }

    public Timestamp getReTime() {
        return ReTime;
    }

    public String getPhone() {
        return phone;
    }


    public String getName() {
        return name;
    }






}
