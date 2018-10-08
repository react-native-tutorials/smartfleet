/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartlife.smartfleet.dao.TurnoDAO;
import com.smartlife.smartfleet.domain.Turno;

/**
 * @author Marius-Iulian Grigoras
 *
 */
@Service
public class TurnoServiceImpl implements TurnoService {

	TurnoDAO turnoDAO;
	
	@Override
	public Long saveTurno(final Turno turno) {
		return turnoDAO.saveTurno(turno);
	}

	@Override
	public List<Turno> findAll() {
		return turnoDAO.findAll();
	}

	@Override
	public Turno findById(final Long id) {
		return turnoDAO.findById(id);
	}

	@Override
	public Turno findByName(final String name) {
		return turnoDAO.findByName(name);
	}

	@Override
	public void deleteTurno(final Turno turno) {
		turnoDAO.deleteTurno(turno);
	}

	@Override
	public void updateTurno(final Turno turno) {
		turnoDAO.updateTurno(turno);
	}

	public void setTurnoDAO(TurnoDAO turnoDAO) {
		this.turnoDAO = turnoDAO;
	}

}
