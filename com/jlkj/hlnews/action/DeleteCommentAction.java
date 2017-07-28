package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import com.jlkj.hlnews.dao.CommentDao;
import com.jlkj.hlnews.daoimp.CommentDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCommentAction extends ActionSupport{
	private int id;
	private InputStream inputStream;
	private CommentDao dao;
	
	public void setDao(CommentDao dao){
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

	public String delete() throws UnsupportedEncodingException{
		JSONObject json = new JSONObject();
		if(dao.delete(id)){
			json.put("code", 200);
			json.put("msg", "success");
		}else{
			json.put("code", 290);
			json.put("msg", "error");
		}
		inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
		return SUCCESS;
	}
}
