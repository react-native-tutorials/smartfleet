/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.domain.GpsDispositivo;
import com.smartlife.smartfleet.utils.DateUtils;

/**
 * @author Marius Iulian Grigoras
 *
 */

@Repository
public class DispositivoDAOImpl extends SmartDAO implements DispositivoDAO {

	public DispositivoDAOImpl(SessionFactory sf) {
		super(sf);
	}

	@Transactional
	@Override
	public Long saveDispositivo(Dispositivo dispositivo) {
		return (Long)getCurrentSession().save(dispositivo);
	}

	@Transactional
	@Override
	public List<Dispositivo> findAll() {
		String sqlQuery = "SELECT D.* FROM DISPOSITIVOS D";
		Query<Dispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, Dispositivo.class);
		return (List<Dispositivo>)query.list();
	}

	@Transactional
	@Override
	public Dispositivo findById(Long id) {
		Dispositivo dispositivo = null;
		String sqlQuery = "SELECT D.* FROM DISPOSITIVOS D where D.id='"+id+"'";
		Query<Dispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, Dispositivo.class);
		List<Dispositivo> results = query.getResultList();
		if(!results.isEmpty()) {
			dispositivo = results.get(0);
		}
		return dispositivo;
	}

	@Transactional
	@Override
	public Dispositivo findByMac(String mac) {
		Dispositivo dispositivo = null;
		String sqlQuery = "SELECT D.* FROM DISPOSITIVOS D where D.mac_disp='"+mac+"'";
		Query<Dispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, Dispositivo.class);
		List<Dispositivo> results = query.getResultList();
		if(!results.isEmpty()) {
			dispositivo = results.get(0);
		}
		return dispositivo;
	}

	@Transactional
	@Override
	public Dispositivo findByIp(String ip) {
		Dispositivo dispositivo = null;
		String sqlQuery = "SELECT D.* FROM DISPOSITIVOS D where D.ip_disp='"+ip+"'";
		Query<Dispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, Dispositivo.class);
		List<Dispositivo> results = query.getResultList();
		if(!results.isEmpty()) {
			dispositivo = results.get(0);
		}
		return dispositivo;
	}

	@Transactional
	public void updateDisp(Dispositivo disp) {
		Date systemDate = DateUtils.getSystemDate();
		disp.setFechaAct(systemDate);
		getCurrentSession().merge(disp);
	}
	
	@Transactional
	@Override
	public void deleteDipositivo(Dispositivo dispositivo) {
		getCurrentSession().delete(dispositivo);

	}

	@Transactional
	@Override
	public void deleteDispositivo(Long id) {
		Dispositivo dispositivo = this.findById(id);
		getCurrentSession().delete(dispositivo);
	}

	@Transactional
	@Override
	public Long saveGpsDispositivo(GpsDispositivo gps) {
		Long id = (Long)getCurrentSession().save(gps);
		return id;
	}
	
	@Transactional
	@Override
	public void saveGps(GpsDispositivo gps) {
		getCurrentSession().saveOrUpdate(gps);
	}

	@Transactional
	@Override
	public List<GpsDispositivo> findGpsByDisp(Dispositivo disp) {
		final Long dispId = disp.getId();
		String sqlQuery = "SELECT G.* FROM gps_disp G WHERE G.disp_id='"+dispId+"'";
		Query<GpsDispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, GpsDispositivo.class);
		List<GpsDispositivo> ubicaciones = query.getResultList();
		return ubicaciones;
	}

	@Transactional
	@Override
	public GpsDispositivo findGpsByDispAndTime(Dispositivo disp, Date crtTime) {
		GpsDispositivo ubicacion = null;
		final Long dispId = disp.getId();
		String sqlQuery = "SELECT G.* FROM gps_disp G WHERE G.disp_id='"+dispId+"' and G.fecha_gps='"+crtTime+"'";
		Query<GpsDispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, GpsDispositivo.class);
		List<GpsDispositivo> ubicaciones = query.getResultList();
		if(!ubicaciones.isEmpty()) {
			ubicacion = ubicaciones.get(0);
		}
		return ubicacion;
	}

	@Transactional
	@Override
	public GpsDispositivo findGpsById(Long id) {
		GpsDispositivo ubicacion = null;
		String sqlQuery = "SELECT G.* FROM gps_disp G WHERE G.id='"+id+"'";
		Query<GpsDispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, GpsDispositivo.class);
		List<GpsDispositivo> ubicaciones = query.getResultList();
		if(!ubicaciones.isEmpty()) {
			ubicacion = ubicaciones.get(0);
		}
		return ubicacion;
	}

	@Transactional
	@Override
	public List<GpsDispositivo> findAllGps() {
		String sqlQuery = "SELECT G.* FROM gps_disp G";
		Query<GpsDispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, GpsDispositivo.class);
		List<GpsDispositivo> ubicaciones = query.getResultList();
		return ubicaciones;
	}

	@Transactional
	@Override
	public List<GpsDispositivo> findAllGpsParam(Long id){
		String sqlQuery = "SELECT G.* FROM gps_disp G WHERE id > '"+id+"' ORDER BY id DESC";
		Query<GpsDispositivo> query = getCurrentSession().createNativeQuery(sqlQuery, GpsDispositivo.class);
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public Dispositivo findDispByEquiAssigned(Long id) {
		StringBuilder strQuery = new StringBuilder("SELECT D.* ");
		strQuery.append("FROM dispositivos D ");
		strQuery.append("INNER JOIN equi_disp ED ON D.id=ED.disp_id ");
		strQuery.append("INNER JOIN equipos E ON E.id=ED.equi_id ");
		strQuery.append("WHERE ED.activo='Y' AND E.id= :id");
		Query<Dispositivo> query = getCurrentSession().createNativeQuery(strQuery.toString(), Dispositivo.class);
		query.setParameter("id", id);	
//		Query query = (Query)sqlQuery.setResultTransformer(Transformers.aliasToBean(StateDetail.class));
		return query.getSingleResult();

	}
	
	@Transactional
	@Override
	public Equipo findEquiByDispAssigned(Long id) {
		StringBuilder strQuery = new StringBuilder("SELECT E.* ");
		strQuery.append("FROM dispositivos D ");
		strQuery.append("INNER JOIN equi_disp ED ON D.id=ED.disp_id ");
		strQuery.append("INNER JOIN equipos E ON E.id=ED.equi_id ");
		strQuery.append("WHERE ED.activo='Y' AND D.id= :id");
		Query<Equipo> query = getCurrentSession().createNativeQuery(strQuery.toString(), Equipo.class);
		query.setParameter("id", id);	
//		Query query = (Query)sqlQuery.setResultTransformer(Transformers.aliasToBean(StateDetail.class));
		return query.getSingleResult();

	}
	
	@Transactional
	@Override
	public Long saveEquiDisp(EquipoDispositivo equiDisp) {
		return (Long)getCurrentSession().save(equiDisp);
	}
}
