package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import com.jlkj.hlnews.bean.Baoliao;
import com.jlkj.hlnews.dao.DataDao;
import com.jlkj.hlnews.daoimp.DataDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class AddDataAction extends ActionSupport {

	private int editorid;
	private String title;
	private String content;
	private String picurl1;
	private String picurl2;
	private String picurl3;
	private DataDao dao;
	private InputStream inputStream;

	public void setDao(DataDao dao){
		this.dao = dao;
	}
	
	public InputStream getResult() {
		return inputStream;
	}

	public int getEditorid() {
		return editorid;
	}

	public void setEditorid(int editorid) {
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

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String addData() throws UnsupportedEncodingException {
		content = new String(content.getBytes("utf-8"), "utf-8");
		title = new String(title.getBytes("utf-8"), "utf-8");
		Baoliao b = new Baoliao();
		b.setEditorid(editorid);
		b.setContent(content);
		b.setPicurl1(picurl1);
		b.setPicurl2(picurl2);
		b.setPicurl3(picurl3);
		b.setTitle(title);
		dao.addData(b);
		JSONObject json = new JSONObject();
		json.put("msg", "success");
		json.put("code", 200);
		inputStream = new ByteArrayInputStream(json.toString()
				.getBytes("utf-8"));
		return SUCCESS;
	}
}
