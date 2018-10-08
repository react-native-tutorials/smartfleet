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
public class EquiDetailHistory extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -2953104687074506714L;

	private Equipo equipo;
	private Horario horario;
	private Date fechaAbast;
	private String cantComb;
	private String horometro;
	private String hodometro;
	private String kilometraje;
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	public Date getFechaAbast() {
		return fechaAbast;
	}
	public void setFechaAbast(Date fechaAbast) {
		this.fechaAbast = fechaAbast;
	}
	public String getCantComb() {
		return cantComb;
	}
	public void setCantComb(String cantComb) {
		this.cantComb = cantComb;
	}
	public String getHorometro() {
		return horometro;
	}
	public void setHorometro(String horometro) {
		this.horometro = horometro;
	}
	public String getHodometro() {
		return hodometro;
	}
	public void setHodometro(String hodometro) {
		this.hodometro = hodometro;
	}
	public String getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}
	
	
}
