package com.jlkj.hlnews.bean;

import java.io.Serializable;

public class Comment implements Serializable{
	private Integer id;
	private int sponsor;
	private String content;
	private Integer baoliaoid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getSponsor() {
		return sponsor;
	}
	public void setSponsor(int sponsor) {
		this.sponsor = sponsor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getBaoliaoid() {
		return baoliaoid;
	}
	public void setBaoliaoid(Integer baoliaoid) {
		this.baoliaoid = baoliaoid;
	}
	
}
