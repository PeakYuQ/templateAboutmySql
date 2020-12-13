package com.peakyu.application.dao;

public class operatemessage {
    private Integer id;
    private String Email;
    private Integer mid;

    @Override
    public String toString() {
        return "deletemessage{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", mid=" + mid +
                '}';
    }

    public operatemessage(String email, Integer mid,Integer id ) {
        this.id = id;
        Email = email;
        this.mid = mid;
    }

    public operatemessage( String email, Integer mid) {
        Email = email;
        this.mid = mid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}
