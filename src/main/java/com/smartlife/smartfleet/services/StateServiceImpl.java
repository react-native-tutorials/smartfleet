/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartlife.smartfleet.dao.EstadoDAO;
import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.EstadoEquipo;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.dto.StateDetail;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Service("stateService")
public class StateServiceImpl implements StateService{

	EstadoDAO estadoDAO;

	@Override
	public List<StateDetail> findStateDetails() {
		return estadoDAO.findStatesDetail();
	}

	@Override
	public Long saveEstado(Estado estado) {
		return estadoDAO.saveEstado(estado);
	}

	@Override
	public List<Estado> findAll() {
		return estadoDAO.findAll();
	}

	@Override
	public Estado findEstadoById(Long id) {
		return estadoDAO.findEstadoById(id);
	}

	@Override
	public Estado findEstadoByName(String name) {
		return estadoDAO.findEstadoByName(name);
	}

	@Override
	public void updateEstado(Estado estado) {
		estadoDAO.updateEstado(estado);
	}

	@Override
	public void deleteEstado(Estado estado) {
		estadoDAO.deleteEstado(estado);
	}

	@Override
	public Long saveRazon(Razon razon) {
		return estadoDAO.saveRazon(razon);
	}

	@Override
	public List<Razon> findAllByEstado(Estado estado) {
		return estadoDAO.findAllByEstado(estado);
	}

	@Override
	public Razon findRazonById(Long id) {
		return estadoDAO.findRazonById(id);
	}

	@Override
	public Razon findRazonByName(String name) {
		return estadoDAO.findRazonByName(name);
	}

	@Override
	public void updateRazon(Razon razon) {
		estadoDAO.updateRazon(razon);
	}

	@Override
	public void deleteRazon(Razon razon) {
		estadoDAO.deleteRazon(razon);
	}
	
	@Override
	public List<Razon> findAllReasonByState(final String stateName){
		return estadoDAO.findAllReasonByState(stateName);
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	@Override
	public Long saveEquiState(EstadoEquipo estadoEquipo) {
		return estadoDAO.saveEquiState(estadoEquipo);
	}

	@Override
	public List<EstadoEquipo> findAllEstadoEquipos() {
		return estadoDAO.findAllEstadoEquipos();
	}

	@Override
	public List<EstadoEquipo> findAllEstEquiByEquipo(Long equiId) {
		return estadoDAO.findAllEstEquiByEquipo(equiId);
	}

	@Override
	public EstadoEquipo findLastEstEquiByEquipo(Long equiId) {
		return estadoDAO.findLastEstEquiByEquipo(equiId);
	}

	@Override
	public Estado findEstadoByEquiState(Long equiStateId) {
		return estadoDAO.findEstadoByEquiState(equiStateId);
	}

}
