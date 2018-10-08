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
public class EquipoPoly extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -5399392683215253726L;

	private Equipo equipo;
	private Poligono poligono;
	private Date fechaAsig;

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Poligono getPoligono() {
		return poligono;
	}

	public void setPoligono(Poligono poligono) {
		this.poligono = poligono;
	}

	public Date getFechaAsig() {
		return fechaAsig;
	}

	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}

}
