package com.jlkj.hlnews.bean;

import java.io.Serializable;

public class Baoliao implements Serializable{
	private Integer id;
	private Integer editorid;
	private String title;
	private String content;
	private String picurl1;
	private String picurl2;
	private String picurl3;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEditorid() {
		return editorid;
	}
	public void setEditorid(Integer editorid) {
		this.editorid = editorid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicurl1() {
		return picurl1;
	}
	public void setPicurl1(String picurl1) {
		this.picurl1 = picurl1;
	}
	public String getPicurl2() {
		return picurl2;
	}
	public void setPicurl2(String picurl2) {
		this.picurl2 = picurl2;
	}
	public String getPicurl3() {
		return picurl3;
	}
	public void setPicurl3(String picurl3) {
		this.picurl3 = picurl3;
	}
	
	
}
