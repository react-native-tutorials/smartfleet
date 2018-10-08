/**
 * 
 */
package com.smartlife.smartfleet.domain.security;

import java.util.List;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Rol extends Entidad {

	/**
	 * generated serial version;
	 */
	private static final long serialVersionUID = -5742827562019683409L;

	private String rolNom;
	private List<UserRol> userRoles;

	public String getRolNom() {
		return rolNom;
	}

	public void setRolNom(String rolNom) {
		this.rolNom = rolNom;
	}

	public List<UserRol> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRol> userRoles) {
		this.userRoles = userRoles;
	}
}
