package com.jlkj.hlnews.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.hlnews.bean.Baoliao;
import com.jlkj.hlnews.bean.Comment;
import com.jlkj.hlnews.dao.CommentDao;
import com.jlkj.hlnews.dbhelper.DatabaseHelper;

public class CommentDaoImp implements CommentDao{
	@Override
	public boolean add(Comment comment) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		Transaction tran = session.beginTransaction();
		
		try {
			session.save(comment);
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
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		Transaction tran = session.beginTransaction();
		
		try {
			Object obj = session.get(Comment.class, new Integer(id));
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
	public List<Comment> getComments(int id) {
		// TODO Auto-generated method stub
		Session session = new DatabaseHelper().getSession();
		try{
			String sql = "select * from comment where baoliaoid="+id;
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Comment> comments = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					Comment comment = new Comment();
					comment.setId((Integer)obj[0]);
					comment.setSponsor((int)obj[1]);
					comment.setContent((String)obj[2]);
					comment.setBaoliaoid((Integer)obj[3]);
					comments.add(comment);
				}
				return comments;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
}
