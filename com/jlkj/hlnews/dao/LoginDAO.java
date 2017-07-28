package com.jlkj.hlnews.dao;

import com.jlkj.hlnews.bean.UserInf;

public interface LoginDAO {
	public UserInf login(String username,String password);
}
