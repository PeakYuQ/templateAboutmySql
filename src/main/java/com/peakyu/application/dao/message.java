package com.peakyu.application.dao;

import java.sql.Timestamp;


public class message {
    private Integer id;     //信件标号
    private String Email;       //接受人
    private String txt;            //信件内容
    private Integer active;         //是否已读0未读1已读
    private String title;           //标题
    private Timestamp time;         //发送时间
    private String role;            //发送人

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getrole() {
        return role;
    }

    public void setrole(String role) {
        this.role = role;
    }

    public message(Integer id, String email, String txt, Integer active, String title, Timestamp time, String role) {
        this.id = id;
        Email = email;
        this.txt = txt;
        this.active = active;
        this.title = title;
        this.time = time;
        this.role = role;
    }

    public message(String email, String txt, Integer active, String title, Timestamp time, String role) {
        Email = email;
        this.txt = txt;
        this.active = active;
        this.title = title;
        this.time = time;
        this.role = role;
    }
}
