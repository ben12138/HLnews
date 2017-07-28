package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jlkj.hlnews.bean.Baoliao;
import com.jlkj.hlnews.dao.DataDao;
import com.jlkj.hlnews.daoimp.DataDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{
	
	private String key;
	private InputStream inputStream;
	private DataDao dao;
	
	public void setDao(DataDao dao){
		this.dao = dao;
	}
	
	public InputStream getResult(){
		return inputStream;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String search() throws UnsupportedEncodingException{
		key = new String(key.getBytes("utf-8"),"utf-8");
		List<Baoliao> data = dao.getData();
		if(data == null){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 310);
			jsonObject.put("msg", "error");
			inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes("utf-8"));
		}else{
			JSONObject jsonObject1 = new JSONObject();
			JSONArray jsonObject2 = new JSONArray();
			for(Baoliao b:data){
				JSONObject json = new JSONObject();
				json.put("id", b.getId());
				json.put("editorid", b.getEditorid());
				json.put("title", b.getTitle());
				json.put("content", b.getContent());
				json.put("picurl1", b.getPicurl1());
				json.put("picurl2", b.getPicurl2());
				json.put("picurl3", b.getPicurl3());
				jsonObject2.put(json);
			}
			jsonObject1.put("data", jsonObject2.toString());
			jsonObject1.put("msg", "success");
			jsonObject1.put("code", 200);
			inputStream = new ByteArrayInputStream(jsonObject1.toString().getBytes("utf-8"));
		}
		return SUCCESS;
	}
	
}
