/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.util.Date;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class EquipoDispositivo extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 2491977942305056419L;
	
	Equipo equipo;
	Dispositivo dispositivo;
	Date fechaAsig = new Date();
	String activo;

	public EquipoDispositivo() {
		
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Date getFechaAsig() {
		return fechaAsig;
	}

	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
	
}
