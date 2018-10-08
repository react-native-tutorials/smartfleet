/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartlife.smartfleet.dao.TipoDAO;
import com.smartlife.smartfleet.domain.common.Tipo;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Service("tipoService")
public class TipoServiceImpl implements TipoService {

	TipoDAO tipoDAO;
	
	@Override
	public void saveTipo(Tipo tipo) {
		tipoDAO.saveTipo(tipo);
	}

	@Override
	public List<Tipo> findAll() {
		return tipoDAO.findAll();
	}

	@Override
	public Tipo findTipoById(String id) {
		return tipoDAO.findTipoById(id);
	}

	@Override
	public Tipo findTipoByName(String name) {
		return tipoDAO.findTipoByName(name);
	}

	@Override
	public void updateTipo(Tipo tipo) {
		tipoDAO.updateTipo(tipo);
	}

	@Override
	public void deleteTipo(Tipo tipo) {
		tipoDAO.deleteTipo(tipo);
	}

	@Override
	public List<Tipo> findByCategoria(String categoria) {
		return tipoDAO.findByCategoria(categoria);
	}

	public TipoDAO getTipoDAO() {
		return tipoDAO;
	}

	public void setTipoDAO(TipoDAO tipoDAO) {
		this.tipoDAO = tipoDAO;
	}

}
