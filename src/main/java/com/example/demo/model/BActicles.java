package com.example.demo.model;


public class BActicles {

  private long cid;
  private String title;
  private java.sql.Timestamp releasetime;
  private long readcount;
  private long commentcount;
  private String type;
  private String content;
  private String status;
  private String thumblimg;
  private long allowComment;
  private String categories;
  private long authorid;
  private String authorname;


  public long getCid() {
    return cid;
  }

  public void setCid(long cid) {
    this.cid = cid;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public java.sql.Timestamp getReleasetime() {
    return releasetime;
  }

  public void setReleasetime(java.sql.Timestamp releasetime) {
    this.releasetime = releasetime;
  }


  public long getReadcount() {
    return readcount;
  }

  public void setReadcount(long readcount) {
    this.readcount = readcount;
  }


  public long getCommentcount() {
    return commentcount;
  }

  public void setCommentcount(long commentcount) {
    this.commentcount = commentcount;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getThumblimg() {
    return thumblimg;
  }

  public void setThumblimg(String thumblimg) {
    this.thumblimg = thumblimg;
  }


  public long getAllowComment() {
    return allowComment;
  }

  public void setAllowComment(long allowComment) {
    this.allowComment = allowComment;
  }


  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }


  public long getAuthorid() {
    return authorid;
  }

  public void setAuthorid(long authorid) {
    this.authorid = authorid;
  }


  public String getAuthorname() {
    return authorname;
  }

  public void setAuthorname(String authorname) {
    this.authorname = authorname;
  }

}
