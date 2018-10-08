/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartlife.smartfleet.domain.common.Tipo;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Repository
public class TipoDAOImpl extends SmartDAO implements TipoDAO {

	public TipoDAOImpl(SessionFactory sf) {
		super(sf);
	}

	@Transactional
	public void saveTipo(Tipo tipo) {
		getCurrentSession().save(tipo);
	}
	
	@Transactional
	public List<Tipo> findAll() {
		String sqlQuery = "SELECT TB.* from TIPO_BASE TB WHERE TB.ACTIVO='Y'";
        Query<Tipo> query = getCurrentSession().createNativeQuery(sqlQuery, Tipo.class);
        return (List<Tipo>)query.list();
	}

	@Transactional
	public Tipo findTipoById(String id) {
		Tipo tipo = null;
		String sqlQuery = "SELECT TB.* from TIPO_BASE TB WHERE TB.ACTIVO='Y' AND TB.ID='"+id+"'";
		Query<Tipo> query = getCurrentSession().createNativeQuery(sqlQuery, Tipo.class);
		List<Tipo> tipos = query.getResultList();
		if(!tipos.isEmpty()) {
			tipo  = tipos.get(0);
		}
		return tipo;
	}

	@Transactional
	public Tipo findTipoByName(String name) {
		Tipo tipo = null;
		String sqlQuery = "SELECT TB.* from TIPO_BASE TB WHERE TB.ACTIVO='Y' AND TB.TIPO='"+name+"'";
		Query<Tipo> query = getCurrentSession().createNativeQuery(sqlQuery, Tipo.class);
		List<Tipo> tipos = query.getResultList();
		if(!tipos.isEmpty()) {
			tipo  = tipos.get(0);
		}
		return tipo;
	}

	@Transactional
	public void updateTipo(Tipo tipo) {
		getCurrentSession().merge(tipo);
	}

	@Transactional
	public void deleteTipo(Tipo tipo) {
		tipo.setActivo("N");
		getCurrentSession().merge(tipo);
	}

	@Override
	public List<Tipo> findByCategoria(String categoria) {
		String sqlQuery = "SELECT TB.* from TIPO_BASE TB WHERE TB.ACTIVO='Y' AND TB.CATEGORIA='"+categoria+"'";
		Query<Tipo> query = getCurrentSession().createNativeQuery(sqlQuery, Tipo.class);
		List<Tipo> tipos = query.getResultList();
		return tipos;
	}

}
