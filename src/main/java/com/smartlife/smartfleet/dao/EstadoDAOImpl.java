/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.EstadoEquipo;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.dto.StateDetail;
import com.smartlife.smartfleet.utils.DateUtils;

/**
 * @author Marius Iulian Grigoras
 *
 */
@SuppressWarnings("deprecation")
@Repository
public class EstadoDAOImpl extends SmartDAO implements EstadoDAO {

	/**
	 * @param sf
	 */
	public EstadoDAOImpl(SessionFactory sf) {
		super(sf);
	}

	@Transactional
	public Long saveEstado(Estado estado) {
		Long objRet = (Long)getCurrentSession().save(estado);
		return objRet;
	}

	@Transactional
	public List<Estado> findAll() {
		String sqlQuery = "SELECT E.* FROM ESTADOS E";
		Query<Estado> query = getCurrentSession().createNativeQuery(sqlQuery, Estado.class);
		List<Estado> estados = (List<Estado>) query.list();
		return estados;
	}

	@Transactional
	public Estado findEstadoById(Long id) {
		Estado estado = null;
		String sqlQuery = "SELECT E.* FROM ESTADOS E where E.ID = '" + id + "'";
		Query<Estado> query = getCurrentSession().createNativeQuery(sqlQuery, Estado.class);
		List<Estado> estados = query.getResultList();
		if(!estados.isEmpty()) {
			estado = estados.get(0);
		}
		return estado;
	}

	@Transactional
	public Estado findEstadoByName(String name) {
		Estado estado = null;
		String sqlQuery = "SELECT E.* FROM ESTADOS E where E.ESTADO = '" + name + "'";
		Query<Estado> query = getCurrentSession().createNativeQuery(sqlQuery, Estado.class);
		List<Estado> estados = query.getResultList();
		if(!estados.isEmpty()) {
			estado = estados.get(0);
		}
		return estado;
	}

	@Transactional
	public void updateEstado(Estado estado) {
		Date lastUpdate = DateUtils.getSystemDate();
		estado.setFechaAct(lastUpdate);
		getCurrentSession().merge(estado);
	}

	@Transactional
	public void deleteEstado(Estado estado) {
		List<Razon> allReazon = findAllByEstado(estado);
		if(!allReazon.isEmpty()) {
			for(Razon r : allReazon) {
				deleteRazon(r);
			}
		}
		getCurrentSession().delete(estado);
	}

	@Transactional
	public Long saveRazon(Razon razon) {
		Long objRet = (Long)getCurrentSession().save(razon);
		return objRet;
	}

	@Transactional
	public List<Razon> findAllByEstado(Estado estado) {
		final Long estadoId = estado.getId();
		String sqlQuery = "SELECT R.* FROM RAZONES R where R.ESTADO_ID= '" + estadoId + "'";
		Query<Razon> query = getCurrentSession().createNativeQuery(sqlQuery, Razon.class);
		List<Razon> razones = query.getResultList();
		return razones;
	}

	@Transactional
	public Razon findRazonById(Long id) {
		Razon razon = null;
		String sqlQuery = "SELECT R.* FROM RAZONES R where R.ID = '" + id + "'";
		Query<Razon> query = getCurrentSession().createNativeQuery(sqlQuery, Razon.class);
		List<Razon> razones = query.getResultList();
		if(!razones.isEmpty()) {
			razon = razones.get(0);
		}
		return razon;
	}

	@Transactional
	public Razon findRazonByName(String name) {
		Razon razon = null;
		String sqlQuery = "SELECT R.* FROM RAZONES R where R.RAZON = '" + name + "'";
		Query<Razon> query = getCurrentSession().createNativeQuery(sqlQuery, Razon.class);
		List<Razon> razones = query.getResultList();
		if(!razones.isEmpty()) {
			razon = razones.get(0);
		}
		return razon;
	}
	@Transactional
	public void updateRazon(Razon razon) {
		Date systemDate = DateUtils.getSystemDate();
		razon.setFechaAct(systemDate);
		getCurrentSession().merge(razon);
	}

	@Transactional
	public void deleteRazon(Razon razon) {
		getCurrentSession().delete(razon);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<StateDetail> findStatesDetail(){
		StringBuffer strQuery = new StringBuffer("select r.id id, t.tipo categoria, e.estado estado, e.color color,r.razon razon ");
		strQuery.append("from estados e inner join razones r on e.id=r.estado_id ");
		strQuery.append("inner join tipo_base t on t.id=e.categoria");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(strQuery.toString());
		sqlQuery.addScalar("id", StandardBasicTypes.LONG);
		sqlQuery.addScalar("categoria", StandardBasicTypes.STRING);
		sqlQuery.addScalar("estado", StandardBasicTypes.STRING);
		sqlQuery.addScalar("color", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("razon", StandardBasicTypes.STRING);
		Query query = (Query)sqlQuery.setResultTransformer(Transformers.aliasToBean(StateDetail.class));
		return query.list();
	}
	
	@Transactional
	public List<Razon> findAllReasonByState(final String stateName){
		StringBuffer strQuery = new StringBuffer("select r.* from razones r inner join estados e on e.id=r.estado_id where e.estado='"+stateName+"'");
		Query<Razon> query = getCurrentSession().createNativeQuery(strQuery.toString(), Razon.class);
		List<Razon> razones = query.getResultList();
		return razones;
	}
	
	@Transactional
	public Long saveEquiState(EstadoEquipo estadoEquipo) {
		return (Long)getCurrentSession().save(estadoEquipo);
	}

	@Override
	public List<EstadoEquipo> findAllEstadoEquipos() {
		String sqlQuery = "select e.* from equi_state e";
		Query<EstadoEquipo> query = getCurrentSession().createNativeQuery(sqlQuery, EstadoEquipo.class);
		List<EstadoEquipo> estados = (List<EstadoEquipo>) query.list();
		return estados;
	}

	@Override
	public List<EstadoEquipo> findAllEstEquiByEquipo(Long equiId) {
		String sqlQuery = "select e.* from equi_state e where e.equi_id = '" + equiId + "'";
		Query<EstadoEquipo> query = getCurrentSession().createNativeQuery(sqlQuery, EstadoEquipo.class);
		List<EstadoEquipo> estados = query.getResultList();
		return estados;
	}

	@Override
	public EstadoEquipo findLastEstEquiByEquipo(Long equiId) {
		EstadoEquipo estadoEquipo = null;
		String sqlQuery = "select e.* from equi_state e where e.equi_id = '" + equiId + "' order by e.id desc limit 1";
		Query<EstadoEquipo> query = getCurrentSession().createNativeQuery(sqlQuery, EstadoEquipo.class);
		List<EstadoEquipo> estados = query.getResultList();
		if(!estados.isEmpty()) {
			estadoEquipo = estados.get(0);
		}
		return estadoEquipo;
	}

	@Override
	public Estado findEstadoByEquiState(Long equiStateId) {
		Estado estado = null;
		String sqlQuery = "select e.* from equi_state es inner join estados e on es.state_id = e.id where es.id = '" + equiStateId + "'";
		Query<Estado> query = getCurrentSession().createNativeQuery(sqlQuery, Estado.class);
		List<Estado> estados = query.getResultList();
		if(!estados.isEmpty()) {
			estado = estados.get(0);
		}
		return estado;
	}
	
	
}
