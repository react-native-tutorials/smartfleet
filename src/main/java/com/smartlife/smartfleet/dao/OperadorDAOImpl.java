/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.Date;
import java.util.List;

//import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartlife.smartfleet.domain.Operador;
import com.smartlife.smartfleet.utils.DateUtils;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Repository
public class OperadorDAOImpl extends SmartDAO implements OperadorDAO {

	public OperadorDAOImpl(SessionFactory sf) {
		super(sf);
	}

	@Transactional
	public Long saveOperador(Operador operador) {
		Long id = (Long)getCurrentSession().save(operador);
		return id;
	}
	
	@Transactional
	public Operador findOperadorById(Long id) {
		Operador operador = null;
		String sqlQuery = "select o.* from operadores o where o.id = '" + id + "'";
		Query<Operador> query = getCurrentSession().createNativeQuery(sqlQuery, Operador.class);
		List<Operador> operators = query.getResultList();
		if(!operators.isEmpty()) {
			operador = operators.get(0);
		}
		return operador;
	}
	
	@Transactional
	public Operador findOperadorByCdg(String cdg) {
		Operador operador = null;
		String sqlQuery = "select o.* from operadores o where o.cdg_ope = '" + cdg + "'";
		Query<Operador> query = getCurrentSession().createNativeQuery(sqlQuery, Operador.class);
		List<Operador> operators = query.getResultList();
		if(!operators.isEmpty()) {
			operador = operators.get(0);
		}
		return operador;
	}

	@Transactional
	public Operador findOperadorByName(String name) {
		Operador operador = null;
		String sqlQuery = "select o.* from operadores o where o.nom_ope = '" + name + "'";
		Query<Operador> query = getCurrentSession().createNativeQuery(sqlQuery, Operador.class);
		List<Operador> operators = query.getResultList();
		if(!operators.isEmpty()) {
			operador = operators.get(0);
		}
		return operador;
	}

	@Transactional
	public List<Operador> findAllOperadores() {
		String sqlQuery = "select o.* from operadores o";
		Query<Operador> query = sessionFactory.getCurrentSession().createNativeQuery(sqlQuery, Operador.class);
		List<Operador> opers = (List<Operador>) query.list();
		return opers;
	}

	@Transactional
	public void updateOperador(Operador operador) {
		Date systemDate = DateUtils.getSystemDate();
		operador.setFechaAct(systemDate);
		getCurrentSession().merge(operador);
	}

	@Transactional
	public void deleteOperador(Operador operador) {
		getCurrentSession().delete(operador);
	}

	

}
