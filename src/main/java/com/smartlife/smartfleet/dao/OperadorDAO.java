/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.List;

import com.smartlife.smartfleet.domain.Operador;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface OperadorDAO {

	Long saveOperador(final Operador operador);
	Operador findOperadorById(final Long id);
	Operador findOperadorByCdg(final String cdg);
	Operador findOperadorByName(final String name);
	List<Operador> findAllOperadores();
	void updateOperador(final Operador operador);
	void deleteOperador(Operador operador);
}
