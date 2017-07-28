package com.jlkj.hlnews.dbhelper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DatabaseHelper {
	private SessionFactory sessionFactory;
	private Session session;
	private  Transaction transaction;
	public DatabaseHelper(){
		init();
	}
	public void init(){
		Configuration configuration=new Configuration().configure();
		ServiceRegistry serviceRegistry=
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		session=sessionFactory.openSession();
	}
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
}
