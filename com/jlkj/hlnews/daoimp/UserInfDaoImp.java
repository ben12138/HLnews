package com.jlkj.hlnews.daoimp;

import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.hlnews.bean.UserInf;
import com.jlkj.hlnews.dao.UserInfDao;
import com.jlkj.hlnews.dbhelper.DatabaseHelper;

public class UserInfDaoImp implements UserInfDao{
	@Override
	public UserInf getUserInf(String username) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		String sql = "select * from userinf where username = '"+username+"'";
		//执行sql语句返回结果集
		Query q = session.createSQLQuery(sql);
		java.util.List list = (java.util.List) q.list();
		if(list.size() == 0){
			return null;
		}else{
			Object[] obj = (Object[]) list.get(0);
			UserInf userInf = new UserInf();
			userInf.setId((Integer)obj[0]);
			userInf.setUsername((String)obj[1]);
			userInf.setPassword((String)obj[2]);
			userInf.setNickname((String)obj[3]);
			userInf.setBirthday((String)obj[4]);
			userInf.setSex((Integer)obj[5]);
			userInf.setIntroduction((String)obj[6]);
			userInf.setImageUrl((String)obj[7]);
			return userInf;
		}
	}
	@Override
	public boolean isRegistered(String email) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		String sql = "select * from userinf where username = '"+email+"'";
		//执行sql语句返回结果集
		Query q = session.createSQLQuery(sql);
		java.util.List list = (java.util.List) q.list();
		if(list.size() == 0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean addUserInf(UserInf user) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		//事务的开始
		Transaction tran=session.beginTransaction();
		session.save(user);
	    tran.commit();
	    session.close();
		return true;
	}
	@Override
	public boolean updateUserInf(UserInf user) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		Transaction tran = session.beginTransaction();
		
		try {
			session.update(user);
			tran.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tran.rollback();
		}finally{
		    session.close();
		}
		return false;
	}
	@Override
	public UserInf getUserInf(int id) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		String sql = "select * from userinf where id = "+id;
		//执行sql语句返回结果集
		Query q = session.createSQLQuery(sql);
		java.util.List list = (java.util.List) q.list();
		if(list.size() == 0){
			return null;
		}else{
			Object[] obj = (Object[]) list.get(0);
			UserInf userInf = new UserInf();
			userInf.setId((Integer)obj[0]);
			userInf.setUsername((String)obj[1]);
			userInf.setPassword((String)obj[2]);
			userInf.setNickname((String)obj[3]);
			userInf.setBirthday((String)obj[4]);
			userInf.setSex((Integer)obj[5]);
			userInf.setIntroduction((String)obj[6]);
			userInf.setImageUrl((String)obj[7]);
			return userInf;
		}
	}
}
