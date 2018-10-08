/**
 * 
 */
package com.smartlife.smartfleet.domain.security;

import java.util.Date;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class UserRol extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -7113632407877448101L;

	Usuario usuario;
	Rol rol;
	Date fechaAsig;
	String valid;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Date getFechaAsig() {
		return fechaAsig;
	}
	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}

}
