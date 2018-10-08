/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import com.smartlife.smartfleet.domain.Operador;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface OperatorService {
	Long saveOperador(final Operador operador);

	Operador findOperadorById(final Long id);

	Operador findOperadorByCdg(final String cdg);

	Operador findOperadorByName(final String name);

	List<Operador> findAllOperadores();

	void updateOperador(final Operador operador);

	void deleteOperador(Operador operador);
}
