/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.EstadoEquipo;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.dto.StateDetail;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface StateService {
	List<StateDetail> findStateDetails();
	
	Long saveEstado(Estado estado);
	List<Estado> findAll();
	Estado findEstadoById(Long id);
	Estado findEstadoByName(String name);
	Estado findEstadoByEquiState(Long equiStateId);
	void updateEstado(Estado estado);
	void deleteEstado(Estado estado);
	
	Long saveRazon(Razon razon);
	List<Razon> findAllByEstado(Estado estado);
	Razon findRazonById(Long id);
	Razon findRazonByName(String name);
	void updateRazon(Razon razon);
	void deleteRazon(Razon razon);
	List<Razon> findAllReasonByState(final String stateName);
	
	Long saveEquiState(EstadoEquipo estadoEquipo);
	List<EstadoEquipo> findAllEstadoEquipos();
	List<EstadoEquipo> findAllEstEquiByEquipo(Long equiId);
	EstadoEquipo findLastEstEquiByEquipo(Long equiId);
}
