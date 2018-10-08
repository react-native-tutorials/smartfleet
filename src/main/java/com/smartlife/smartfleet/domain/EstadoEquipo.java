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
public class EstadoEquipo extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -389432369844298463L;

	private Equipo equipo;
	private Estado estado;
	private Razon razon;
	private String comentario;
	private Date fechaIni;
	private Date fechaFin;

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Razon getRazon() {
		return razon;
	}

	public void setRazon(Razon razon) {
		this.razon = razon;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
