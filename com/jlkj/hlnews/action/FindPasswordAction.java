package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.jlkj.hlnews.bean.UserInf;
import com.jlkj.hlnews.dao.UserInfDao;
import com.jlkj.hlnews.daoimp.UserInfDaoImp;
import com.jlkj.hlnews.util.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class FindPasswordAction extends ActionSupport{
	private String username;
	private int type;
	private InputStream inputStream;
	private UserInfDao dao;
	
	public void setDao(UserInfDao dao){
		this.dao = dao;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getResult(){
		return inputStream;
	}
	public String findPassword() throws UnknownHostException, IOException{
		if(type==1){
			//验证验证码步骤
			
			if(dao.isRegistered(username)){
				//判断该邮箱有没有被注册
				SendMail mail = new SendMail();
				try {
					mail.send(username);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject json = new JSONObject();
				json.put("code", 200);
				json.put("msg", "success");
				JSONObject json1 = new JSONObject();
				json1.put("verification", mail.getContent());
				json.put("data", json1.toString());
				inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
			}else{
				JSONObject json = new JSONObject();
				json.put("code", 220);
				json.put("msg", "error");
				inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
			}
		}else if(type==2){
			//返回用户信息
			UserInf user = dao.getUserInf(username);
			JSONObject json = new JSONObject();
			JSONObject json1 = new JSONObject();
			json.put("code", 200);
			json.put("msg", "success");
			json1.put("id", user.getId());
			json1.put("username", user.getUsername());
			json1.put("password", user.getPassword());
			json1.put("nickname", user.getNickname());
			json1.put("birthday", user.getBirthday());
			json1.put("sex", user.getSex());
			json1.put("introduction", user.getIntroduction());
			json1.put("imageUrl", user.getImageUrl());
			json.put("data", json1.toString());
			inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
		}
		return SUCCESS;
	}
}
