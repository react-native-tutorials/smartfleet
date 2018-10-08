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
public class Usuario extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -2055936183323041523L;

	private String usuNom;
	private String usuApe;
	private String usuDoc;
	private String usuAcc;
	private String usuPass;
	private String activo;
	private List<UserRol> userRoles;
	
	public String getUsuNom() {
		return usuNom;
	}
	public void setUsuNom(String usuNom) {
		this.usuNom = usuNom;
	}
	public String getUsuApe() {
		return usuApe;
	}
	public void setUsuApe(String usuApe) {
		this.usuApe = usuApe;
	}
	public String getUsuDoc() {
		return usuDoc;
	}
	public void setUsuDoc(String usuDoc) {
		this.usuDoc = usuDoc;
	}
	public String getUsuAcc() {
		return usuAcc;
	}
	public void setUsuAcc(String usuAcc) {
		this.usuAcc = usuAcc;
	}
	public String getUsuPass() {
		return usuPass;
	}
	public void setUsuPass(String usuPass) {
		this.usuPass = usuPass;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public List<UserRol> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRol> userRoles) {
		this.userRoles = userRoles;
	}
	
	
}