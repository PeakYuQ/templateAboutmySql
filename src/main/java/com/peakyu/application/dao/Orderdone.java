package com.peakyu.application.dao;

public class Orderdone {

  private long id;
  private long oid;
  private long status;

  public Orderdone(long id, long oid, long status) {
    this.id = id;
    this.oid = oid;
    this.status = status;
  }

  public Orderdone(long oid, long status) {
    this.oid = oid;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getOid() {
    return oid;
  }

  public void setOid(long oid) {
    this.oid = oid;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
