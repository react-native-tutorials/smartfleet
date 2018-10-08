/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import com.smartlife.smartfleet.domain.Acciones;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.dto.EquipmentDetail;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public interface EquipoService {
	Long saveEquipo(Equipo equipo);
	List<Equipo> findAll();
	Equipo findById(Long id);
	Equipo findByCode(String code);
	void updateEquipo(Equipo equipo);
	void deleteEquipo(Equipo equipo);
	List<EquipmentDetail> findAllEquipments();
	void deleteEquipoDisp(EquipoDispositivo item);
	Long saveAction(Acciones accion);
	 List<Acciones> findAllActions();
	 List<EquipoDispositivo> findAllEquiDisp();
}
