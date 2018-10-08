/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartlife.smartfleet.dao.OperadorDAO;
import com.smartlife.smartfleet.domain.Operador;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Service("operatorService")
public class OperatorServiceImpl implements OperatorService {

	OperadorDAO operadorDAO;
	
	@Override
	public Long saveOperador(final Operador operador) {
		return operadorDAO.saveOperador(operador);
	}

	@Override
	public Operador findOperadorById(final Long id) {
		return operadorDAO.findOperadorById(id);
	}

	@Override
	public Operador findOperadorByCdg(final String cdg) {
		return operadorDAO.findOperadorByCdg(cdg);
	}

	@Override
	public Operador findOperadorByName(final String name) {
		return operadorDAO.findOperadorByName(name);
	}

	@Override
	public List<Operador> findAllOperadores() {
		return operadorDAO.findAllOperadores();
	}

	@Override
	public void updateOperador(final Operador operador) {
		operadorDAO.updateOperador(operador);
	}

	@Override
	public void deleteOperador(final Operador operador) {
		operadorDAO.deleteOperador(operador);
	}

	public OperadorDAO getOperadorDAO() {
		return operadorDAO;
	}

	public void setOperadorDAO(OperadorDAO operadorDAO) {
		this.operadorDAO = operadorDAO;
	}
}
