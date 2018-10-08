/**
 * 
 */
package com.smartlife.smartfleet.services;

import com.smartlife.smartfleet.domain.ApplicationParameter;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface ApplicationService {

	ApplicationParameter findParameterByCode(String code);
	
}
