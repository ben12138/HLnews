package com.jlkj.hlnews.dao;

import com.jlkj.hlnews.bean.UserInf;

public interface UserInfDao {
	public boolean isRegistered(String email);
	public UserInf getUserInf(String username);
	public UserInf getUserInf(int id);
	public boolean addUserInf(UserInf user);
	public boolean updateUserInf(UserInf user);
}
