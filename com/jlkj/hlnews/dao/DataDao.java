package com.jlkj.hlnews.dao;

import java.util.List;

import com.jlkj.hlnews.bean.Baoliao;

public interface DataDao {
	public List<Baoliao> getData();
	public boolean addData(Baoliao data);
	public boolean deleteData(int id);
	public List<Baoliao> searchData(String key);
}
