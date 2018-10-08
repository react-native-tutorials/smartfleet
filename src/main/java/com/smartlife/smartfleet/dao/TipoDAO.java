/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.List;

import com.smartlife.smartfleet.domain.common.Tipo;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface TipoDAO {

	void saveTipo(Tipo tipo);
	List<Tipo> findAll();
	List<Tipo> findByCategoria(String categoria);
	Tipo findTipoById(String id);
	Tipo findTipoByName(String name);
	void updateTipo(Tipo tipo);
	void deleteTipo(Tipo tipo);
}
