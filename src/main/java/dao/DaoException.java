package dao;

import org.hibernate.HibernateException;

public class DaoException extends RuntimeException {

	public DaoException(String message, HibernateException e) {
		super(message, e);
	}
}
