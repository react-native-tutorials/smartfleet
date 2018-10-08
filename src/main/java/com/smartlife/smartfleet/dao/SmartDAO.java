/**
 * 
 */
package com.smartlife.smartfleet.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class SmartDAO {

	protected final Log logger = LogFactory.getLog(getClass());
	
	protected SessionFactory sessionFactory;
	
	protected Transaction currentTransaction;
	
	public SmartDAO(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected Transaction initTransaction() {
		if(currentTransaction == null) {
			getCurrentSession().beginTransaction();
		}
		return currentTransaction;
	}
	
	protected void handleException(HibernateException he) throws HibernateException{
		currentTransaction.rollback();
		throw new HibernateException("An error occurred at db access level", he);
	}
}
