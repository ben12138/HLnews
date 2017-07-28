package com.jlkj.hlnews.daoimp;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.jlkj.hlnews.bean.UserInf;
import com.jlkj.hlnews.dao.LoginDAO;
import com.jlkj.hlnews.dbhelper.DatabaseHelper;

public class LoginDAOImp implements LoginDAO{
	@Override
	public UserInf login(String username, String password) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		try{
			String sql = "select * from userinf where username = '"+username+"' and password = "+password;
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
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
}
