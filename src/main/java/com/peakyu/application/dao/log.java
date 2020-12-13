package com.peakyu.application.dao;

import java.sql.Timestamp;


public class log {
    private Integer id;     //日志序号
    private Integer type;   //日志类型根据此查找
    private String operate;     //操作内容
    private Timestamp time;     //操作时间
    private String operator;        //操作者

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public log(Integer id, Integer type, String operate, Timestamp time, String operator) {
        this.id = id;
        this.type = type;
        this.operate = operate;
        this.time = time;
        this.operator = operator;
    }

    public log(Integer type, String operate, Timestamp time, String operator) {
        this.type = type;
        this.operate = operate;
        this.time = time;
        this.operator = operator;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
