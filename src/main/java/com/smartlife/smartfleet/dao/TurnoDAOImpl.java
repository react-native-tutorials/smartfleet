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

import com.smartlife.smartfleet.domain.Turno;
import com.smartlife.smartfleet.utils.DateUtils;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Repository
public class TurnoDAOImpl extends SmartDAO implements TurnoDAO {

	public TurnoDAOImpl(SessionFactory sf) {
		super(sf);
	}
	
	@Transactional
	public Long saveTurno(Turno turno) {
		return (Long)getCurrentSession().save(turno);
	}

	@Transactional
	public List<Turno> findAll() {
		String sqlQuery = "select T.* from turnos T";
		Query<Turno> query = sessionFactory.getCurrentSession().createNativeQuery(sqlQuery, Turno.class);
		List<Turno> turnos = (List<Turno>) query.list();
		return turnos;
	}

	@Transactional
	public Turno findById(Long id) {
		Turno turno = null;
		String sqlQuery = "select T.* from turnos T where T.id = '" + id + "'";
		Query<Turno> query = getCurrentSession().createNativeQuery(sqlQuery, Turno.class);
		List<Turno> turnos = query.getResultList();
		if(!turnos.isEmpty()) {
			turno = turnos.get(0);
		}
		return turno;
	}

	@Transactional
	public Turno findByName(String name) {
		Turno turno = null;
		String sqlQuery = "select T.* from turnos T where T.nom_turno = '" + name + "'";
		Query<Turno> query = getCurrentSession().createNativeQuery(sqlQuery, Turno.class);
		List<Turno> turnos = query.getResultList();
		if(!turnos.isEmpty()) {
			turno = turnos.get(0);
		}
		return turno;
	}

	@Transactional
	public void deleteTurno(Turno turno) {
		getCurrentSession().delete(turno);
	}

	@Transactional
	public void updateTurno(Turno turno) {
		Date systemDate = DateUtils.getSystemDate();
		turno.setFechaAct(systemDate);
		getCurrentSession().merge(turno);
	}

}
