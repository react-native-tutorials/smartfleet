/**
 * 
 */
package com.smartlife.smartfleet.dao;

import com.smartlife.smartfleet.domain.ApplicationParameter;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface ApplicationParameterDAO {

	ApplicationParameter findParameterByCode(String code);
	void saveParameter(ApplicationParameter appParameter);
}
