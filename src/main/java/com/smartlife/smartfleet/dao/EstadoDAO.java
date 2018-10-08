/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.List;

import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.EstadoEquipo;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.dto.StateDetail;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface EstadoDAO {

	Long saveEstado(Estado estado);
	List<Estado> findAll();
	Estado findEstadoById(Long id);
	Estado findEstadoByName(String name);
	void updateEstado(Estado estado);
	void deleteEstado(Estado estado);
	
	Long saveRazon(Razon razon);
	List<Razon> findAllByEstado(Estado estado);
	Razon findRazonById(Long id);
	Razon findRazonByName(String name);
	void updateRazon(Razon razon);
	void deleteRazon(Razon razon);
	List<Razon> findAllReasonByState(final String stateName);
	List<StateDetail> findStatesDetail();
	
	Long saveEquiState(EstadoEquipo estadoEquipo);
	List<EstadoEquipo> findAllEstadoEquipos();
	List<EstadoEquipo> findAllEstEquiByEquipo(Long equiId);
	EstadoEquipo findLastEstEquiByEquipo(Long equiId);
	
	Estado findEstadoByEquiState(Long equiStateId);
}
