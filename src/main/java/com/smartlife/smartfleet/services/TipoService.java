/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import com.smartlife.smartfleet.domain.common.Tipo;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface TipoService {

	void saveTipo(Tipo tipo);
	List<Tipo> findAll();
	Tipo findTipoById(String id);
	Tipo findTipoByName(String name);
	List<Tipo> findByCategoria(String categoria);
	void updateTipo(Tipo tipo);
	void deleteTipo(Tipo tipo);
}
