package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import com.jlkj.hlnews.bean.UserInf;
import com.jlkj.hlnews.dao.UserInfDao;
import com.jlkj.hlnews.daoimp.UserInfDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class RegisteAction extends ActionSupport{
	private String username;
	private String password;
	private String nickname;
	private String birthday;
	private String introduction;
	private int sex;
	private String imageUrl;
	private InputStream inputStream;
	private UserInfDao dao;
	
	public void setDao(UserInfDao dao){
		this.dao = dao;
	}
	
	public InputStream getResult(){
		return inputStream;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String registe() throws UnsupportedEncodingException{
		UserInf user = new UserInf();
		introduction = new String(introduction.getBytes("utf-8"),"utf-8");
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setBirthday(birthday);
		user.setSex(sex);
		user.setIntroduction(introduction);
		user.setImageUrl(imageUrl);
		JSONObject json = new JSONObject();
		if(dao.addUserInf(user)){
			json.put("code", 200);
			json.put("msg", "success");
		}else{
			json.put("code", 240);
			json.put("msg", "error");
		}
		inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
		return SUCCESS;
	}
	
}
