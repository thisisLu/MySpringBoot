package com.example.demo.model;


public class BUsers {

  private long uid;
  private String username;
  private String password;
  private java.sql.Date jointime;
  private String iPsource;
  private long state;


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


  public java.sql.Date getJointime() {
    return jointime;
  }

  public void setJointime(java.sql.Date jointime) {
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

}
