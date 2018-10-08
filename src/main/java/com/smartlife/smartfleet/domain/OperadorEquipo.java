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
public class OperadorEquipo extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -5193706856565985769L;

	
	private Operador operador;
	private Equipo equipo;
	private Date fechaAsig;
	private String tipoAsig;
	private Date fechaInicio;
	private Date fechaFin;
	
	public OperadorEquipo() {
		
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Date getFechaAsig() {
		return fechaAsig;
	}

	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}

	public String getTipoAsig() {
		return tipoAsig;
	}

	public void setTipoAsig(String tipoAsig) {
		this.tipoAsig = tipoAsig;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	
	
}
