package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import com.jlkj.hlnews.bean.Comment;
import com.jlkj.hlnews.dao.CommentDao;
import com.jlkj.hlnews.daoimp.CommentDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class AddCommentAction extends ActionSupport{
	private int sponsor;
	private String content;
	private int baoliaoid;
	private CommentDao dao;
	private InputStream inputStream;
	public void setDao(CommentDao dao){
		this.dao = dao;
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
	public int getBaoliaoid() {
		return baoliaoid;
	}
	public void setBaoliaoid(int baoliaoid) {
		this.baoliaoid = baoliaoid;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String add() throws UnsupportedEncodingException{
		Comment comment = new Comment();
		content = new String(content.getBytes("utf-8"),"utf-8");
		comment.setBaoliaoid(baoliaoid);
		comment.setContent(content);
		comment.setSponsor(sponsor);
		JSONObject json = new JSONObject();
		if(dao.add(comment)){
			json.put("code", 200);
			json.put("msg", "success");
		}else{
			json.put("code", 270);
			json.put("msg", "error");
		}
		inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
		return SUCCESS;
	}
}
