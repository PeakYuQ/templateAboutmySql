package com.peakyu.application.dao;

import java.sql.Timestamp;

public class exam {
    private String Email;   //邮箱
    private Integer result;     //审核结果0不通过，1通过
    private String reason;      //原因 通过null、不通过reason
    private Timestamp ConTime;

    @Override
    public String toString() {
        return "exam{" +
                "Email='" + Email + '\'' +
                ", result=" + result +
                ", reason='" + reason + '\'' +
                ", ConTime=" + ConTime +
                '}';
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getConTime() {
        return ConTime;
    }

    public void setConTime(Timestamp conTime) {
        ConTime = conTime;
    }

    public exam(String email, Integer result, String reason, Timestamp conTime) {
        Email = email;
        this.result = result;
        this.reason = reason;
        ConTime = conTime;
    }
}
