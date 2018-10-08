/**
 * 
 */
package com.smartlife.smartfleet.services;

import org.springframework.stereotype.Service;

import com.smartlife.smartfleet.dao.ApplicationParameterDAO;
import com.smartlife.smartfleet.domain.ApplicationParameter;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Service
public class ApplicationServiceImpl implements ApplicationService{

	ApplicationParameterDAO applicationParameterDAO;
	
	@Override
	public ApplicationParameter findParameterByCode(String code) {
		return applicationParameterDAO.findParameterByCode(code);
	}

	public ApplicationParameterDAO getApplicationParameterDAO() {
		return applicationParameterDAO;
	}

	public void setApplicationParameterDAO(ApplicationParameterDAO applicationParameterDAO) {
		this.applicationParameterDAO = applicationParameterDAO;
	}

}
