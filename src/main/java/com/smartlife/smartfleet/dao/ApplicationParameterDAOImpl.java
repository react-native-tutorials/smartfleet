/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartlife.smartfleet.domain.ApplicationParameter;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Repository
public class ApplicationParameterDAOImpl extends SmartDAO implements ApplicationParameterDAO{

	public ApplicationParameterDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Transactional
	@Override
	public ApplicationParameter findParameterByCode(String code) {
		ApplicationParameter parameter = null;
		String sqlQuery = "SELECT A.* FROM APPLICATIONPARAMETER A where A.code='"+code+"'";
		Query<ApplicationParameter> query = getCurrentSession().createNativeQuery(sqlQuery, ApplicationParameter.class);
		List<ApplicationParameter> results = query.getResultList();
		if(!results.isEmpty()) {
			parameter = results.get(0);
		}
		return parameter;
	}

	
	@Transactional
	@Override
	public void saveParameter(ApplicationParameter appParameter) {
		getCurrentSession().saveOrUpdate(appParameter);
	}

}
