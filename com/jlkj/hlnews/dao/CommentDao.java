package com.jlkj.hlnews.dao;

import java.util.List;

import com.jlkj.hlnews.bean.Comment;

public interface CommentDao {
	public boolean add(Comment comment);
	public boolean delete(int id);
	public List<Comment> getComments(int id);
}
