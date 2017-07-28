package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.hibernate.Session;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.jlkj.hlnews.bean.UserInf;
import com.jlkj.hlnews.dao.LoginDAO;
import com.jlkj.hlnews.daoimp.LoginDAOImp;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport{
	private String username;
	private String password;
	private LoginDAO dao;
	private InputStream inputStream;
	
	public void setDao(LoginDAO dao) {
		this.dao = dao;
	}

	public InputStream getResult(){
		return inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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
	public String login() throws UnsupportedEncodingException{
		System.out.println("aaa");
		UserInf user = dao.login(username, password);
		System.out.println(user);
		if(user == null){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 230);
			jsonObject.put("msg", "error");
			inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes("utf-8"));
		}else{
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("id", user.getId());
			jsonObject2.put("username", user.getUsername());
			jsonObject2.put("password", user.getPassword());
			jsonObject2.put("nickname", user.getNickname());
			jsonObject2.put("sex", user.getSex());
			jsonObject2.put("birthday", user.getBirthday());
			jsonObject2.put("introduction", user.getIntroduction());
			jsonObject2.put("imageUrl", user.getImageUrl());
			jsonObject1.put("data", jsonObject2.toString());
			jsonObject1.put("msg", "success");
			jsonObject1.put("code", 200);
			inputStream = new ByteArrayInputStream(jsonObject1.toString().getBytes("utf-8"));
		}
		return SUCCESS;
	}
}
