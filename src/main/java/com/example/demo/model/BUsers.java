package com.example.demo.model;


public class BUsers {

  private long uid;
  private String username;
  private String password;
  private java.sql.Timestamp jointime;
  private String iPsource;
  private long state;
  private String iptity;


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public java.sql.Timestamp getJointime() {
    return jointime;
  }

  public void setJointime(java.sql.Timestamp jointime) {
    this.jointime = jointime;
  }


  public String getIPsource() {
    return iPsource;
  }

  public void setIPsource(String iPsource) {
    this.iPsource = iPsource;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public String getIptity() {
    return iptity;
  }

  public void setIptity(String iptity) {
    this.iptity = iptity;
  }

}
