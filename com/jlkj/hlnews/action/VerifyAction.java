package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import net.sf.json.JSONObject;

import org.hibernate.boot.archive.internal.ByteArrayInputStreamAccess;

import com.jlkj.hlnews.dao.UserInfDao;
import com.jlkj.hlnews.daoimp.UserInfDaoImp;
import com.jlkj.hlnews.util.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class VerifyAction extends ActionSupport{
	private String email;
	private InputStream inputStream;
	private UserInfDao dao;
	
	public void setDao(UserInfDao dao){
		this.dao = dao;
	}
	
	public InputStream getResult(){
		return inputStream;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String verify() throws UnknownHostException, IOException{
		if(dao.isRegistered(email)){
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			jsonObject1.put("code", "210");
			jsonObject1.put("msg", "error");
			inputStream = new ByteArrayInputStream(jsonObject1.toString().getBytes("utf-8"));
		}else{
			SendMail mail = new SendMail();
			try {
				mail.send(email);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			jsonObject1.put("code", 200);
			jsonObject1.put("msg", "success");
			jsonObject2.put("verification", mail.getContent());
			jsonObject1.put("data", jsonObject2.toString());
			inputStream = new ByteArrayInputStream(jsonObject1.toString().getBytes("utf-8"));
		}
		return SUCCESS;
	}
}
