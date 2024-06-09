package com.naw.personalfinance.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DataAccessManager implements IDataAccessManager {
	private static SessionFactory sessionFactory = null;
	private Session session = null;
	private Transaction transaction = null;

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}

	public void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	public void begin() {
		sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

	}

	public void commit() {
		transaction.commit();
	}

	public void close() {
		session.close();
	}

}
