/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartlife.smartfleet.dao.EquipoDAO;
import com.smartlife.smartfleet.domain.Acciones;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.dto.EquipmentDetail;

/**
 * @author Marius-Iulian Grigoras
 *
 */
@Service
public class EquipoServiceImpl implements EquipoService {

	EquipoDAO equipoDAO;
	
	@Override
	public Long saveEquipo(Equipo equipo) {
		return equipoDAO.saveEquipo(equipo);
	}
	
	@Override
	public List<Equipo> findAll() {
		return equipoDAO.findAll();
	}

	@Override
	public Equipo findById(Long id) {
		return equipoDAO.findById(id);
	}

	@Override
	public Equipo findByCode(String code) {
		return equipoDAO.findByCode(code);
	}

	@Override
	public void updateEquipo(Equipo equipo) {
		equipoDAO.updateEquipo(equipo);
	}

	@Override
	public void deleteEquipo(Equipo equipo) {
		equipoDAO.deleteEquipo(equipo);
	}

	@Override
	public List<EquipmentDetail> findAllEquipments(){
		return equipoDAO.findAllEquipments();
	}
	
	@Override
	public void deleteEquipoDisp(EquipoDispositivo item) {
		equipoDAO.deleteEquipoDisp(item);
	}

	public void setEquipoDAO(EquipoDAO equipoDAO) {
		this.equipoDAO = equipoDAO;
	}

	@Override
	public Long saveAction(final Acciones accion) {
		return equipoDAO.saveAction(accion);
	}

	@Override
	public List<Acciones> findAllActions() {
		return equipoDAO.findAllActions();
	}

	@Override
	public List<EquipoDispositivo> findAllEquiDisp() {
		return equipoDAO.findAllEquiDisp();
	}
	
	
	
}
