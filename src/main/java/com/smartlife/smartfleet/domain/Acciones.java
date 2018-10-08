/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.util.Date;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Acciones extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 7239808089328754812L;

	private Equipo equipo;
	private String accion;
	private Date fechaAsig;
	private Date fechaFin;
	
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Date getFechaAsig() {
		return fechaAsig;
	}
	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
