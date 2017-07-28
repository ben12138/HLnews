package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import com.jlkj.hlnews.bean.UserInf;
import com.jlkj.hlnews.dao.UserInfDao;
import com.jlkj.hlnews.daoimp.UserInfDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class EditorNameAction extends ActionSupport{
	private int id;
	private InputStream inputStream;
	private UserInfDao dao;
	
	public void setDao(UserInfDao dao){
		this.dao = dao;
	}
	
	public InputStream getResult(){
		return inputStream;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String get() throws UnsupportedEncodingException{
		UserInf user = dao.getUserInf(id);
		JSONObject json = new JSONObject();
		JSONObject json1 = new JSONObject();
		if(user != null){
			json.put("code", 200);
			json.put("msg", "success");
			json1.put("id", user.getId());
			json1.put("username", user.getUsername());
			json1.put("nickname", user.getNickname());
			json1.put("imageUrl", user.getImageUrl());
			json.put("data", json1.toString());
		}else{
			json.put("code", 300);
			json.put("msg", "error");
		}
		inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
		return SUCCESS;
	}
}
