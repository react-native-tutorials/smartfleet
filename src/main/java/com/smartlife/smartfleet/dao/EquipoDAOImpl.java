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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartlife.smartfleet.domain.Acciones;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.utils.DateUtils;

/**
 * @author Marius Iulian Grigoras
 *
 */
@SuppressWarnings("deprecation")
@Repository
public class EquipoDAOImpl extends SmartDAO implements EquipoDAO {

	/**
	 * @param sf
	 */
	public EquipoDAOImpl(SessionFactory sf) {
		super(sf);
	}

	@Transactional
	public Long saveEquipo(Equipo equipo) {
		Long id = (Long)getCurrentSession().save(equipo);
		return id;
	}

	@Transactional
	public List<Equipo> findAll() {
		String sqlQuery = "SELECT E.* FROM EQUIPOS E";
		Query<Equipo> query = getCurrentSession().createNativeQuery(sqlQuery, Equipo.class);
		List<Equipo> equipos = (List<Equipo>) query.list();
		return equipos;
	}

	@Transactional
	public Equipo findById(Long id) {
		Equipo equipo = null;
		String sqlQuery = "SELECT E.* FROM EQUIPOS E where E.ID = '" + id + "'";
		Query<Equipo> query = getCurrentSession().createNativeQuery(sqlQuery, Equipo.class);
		List<Equipo> equipos = query.getResultList();
		if(!equipos.isEmpty()) {
			equipo = equipos.get(0);
		}
		return equipo;
	}

	@Transactional
	public Equipo findByCode(String code) {
		Equipo equipo = null;
		String sqlQuery = "SELECT E.* FROM EQUIPOS E where E.CDG_EQUI = '" + code + "'";
		Query<Equipo> query = getCurrentSession().createNativeQuery(sqlQuery, Equipo.class);
		List<Equipo> equipos = query.getResultList();
		if(!equipos.isEmpty()) {
			equipo = equipos.get(0);
		}
		return equipo;
	}

	@Transactional
	public void updateEquipo(Equipo equipo) {
		Date lastUpdate = DateUtils.getSystemDate();
		equipo.setFechaAct(lastUpdate);
		getCurrentSession().merge(equipo);
	}

	@Transactional
	public void deleteEquipo(Equipo equipo) {
		getCurrentSession().delete(equipo);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<EquipmentDetail> findAllEquipments(){
		StringBuilder strQuery = new StringBuilder("select e.id id, t.tipo category, e.cdg_equi code, ");
		strQuery.append("e.marca_equi mark, e.modelo_equi model, e.cap_comb capFuel,e.cap_carg capCharge, e.activo active ");
		strQuery.append("from equipos e inner join tipo_base t on e.categoria = t.id ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(strQuery.toString());
		Query query = (Query)sqlQuery.setResultTransformer(Transformers.aliasToBean(EquipmentDetail.class));
		return query.getResultList();
	}

	@Override
	public void deleteEquipoDisp(EquipoDispositivo item) {
		getCurrentSession().delete(item);
	}
	
	@Override
	public List<EquipoDispositivo> findAllEquiDisp(){
		String sqlQuery = "SELECT E.* FROM equi_disp E";
		Query<EquipoDispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, EquipoDispositivo.class);
		List<EquipoDispositivo> equipos = (List<EquipoDispositivo>) query.list();
		return equipos;
	}
	
	@Override
	public Long saveAction(Acciones accion) {
		return (Long)getCurrentSession().save(accion);
	}
	
	@Override
	public List<Acciones> findAllActions(){
		String sqlQuery = "select a.* from equi_act a";
		Query<Acciones> query = getCurrentSession().createNativeQuery(sqlQuery, Acciones.class);
		List<Acciones> acciones = (List<Acciones>) query.list();
		return acciones;
	}
}
