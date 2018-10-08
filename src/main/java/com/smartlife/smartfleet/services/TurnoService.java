/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import com.smartlife.smartfleet.domain.Turno;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public interface TurnoService {
	Long saveTurno(Turno turno);
	List<Turno> findAll();
	Turno findById(Long id);
	Turno findByName(String name);
	void deleteTurno(Turno turno);
	void updateTurno(Turno turno);
}
