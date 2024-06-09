package com.naw.personalfinance.common;

import org.hibernate.SessionFactory;

public interface IDataAccessManager {
	public SessionFactory getSessionFactory();

	public void closeSessionFactory();

	public void begin();

	public void commit();

	public void close();

}
