package com.jlkj.hlnews.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.hlnews.bean.Baoliao;
import com.jlkj.hlnews.bean.UserInf;
import com.jlkj.hlnews.dao.DataDao;
import com.jlkj.hlnews.dbhelper.DatabaseHelper;

public class DataDaoImp implements DataDao{
	@Override
	public boolean addData(Baoliao data) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		//事务的开始
		Transaction tran=session.beginTransaction();
		session.save(data);
	    tran.commit();
	    session.close();
		return true;
	}
	@Override
	public boolean deleteData(int id) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		Transaction tran = session.beginTransaction();
		
		try {
			Object obj = session.get(Baoliao.class, id);
			if(obj==null){
				return false;
			}
			session.delete(obj);
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
	public List<Baoliao> getData() {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		try{
			String sql = "select * from baoliao";
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Baoliao> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					Baoliao b = new Baoliao();
					b.setId((Integer)obj[0]);
					b.setEditorid((Integer)obj[1]);
					b.setTitle((String)obj[2]);
					b.setContent((String)obj[3]);
					b.setPicurl1((String)obj[4]);
					b.setPicurl2((String)obj[5]);
					b.setPicurl3((String)obj[6]);
					data.add(b);
				}
				return data;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public List<Baoliao> searchData(String key) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		try{
			String sql = "select * from baoliao where title like %"+key+"% or content like %"+key+"%";
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Baoliao> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(0);
					Baoliao b = new Baoliao();
					b.setId((Integer)obj[0]);
					b.setEditorid((Integer)obj[1]);
					b.setTitle((String)obj[2]);
					b.setContent((String)obj[3]);
					b.setPicurl1((String)obj[4]);
					b.setPicurl2((String)obj[5]);
					b.setPicurl3((String)obj[6]);
					data.add(b);
				}
				return data;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
}
