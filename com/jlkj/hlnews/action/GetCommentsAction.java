package com.jlkj.hlnews.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jlkj.hlnews.bean.Comment;
import com.jlkj.hlnews.dao.CommentDao;
import com.jlkj.hlnews.daoimp.CommentDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetCommentsAction extends ActionSupport{
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
	
	public String get() throws UnsupportedEncodingException{
		List<Comment> comments = dao.getComments(id);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(comments == null){
			json.put("code", 280);
			json.put("msg", "error");
		}else{
			json.put("code", 200);
			json.put("msg", "success");
			for(Comment temp:comments){
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("id", temp.getId());
				jsonobj.put("sponsor", temp.getSponsor());
				jsonobj.put("content", temp.getContent());
				jsonobj.put("baoliaoid", temp.getBaoliaoid());
				jsonArray.put(jsonobj);
			}
			json.put("data", jsonArray.toString());
		}
		inputStream = new ByteArrayInputStream(json.toString().getBytes("utf-8"));
		return SUCCESS;
	}
	
}
