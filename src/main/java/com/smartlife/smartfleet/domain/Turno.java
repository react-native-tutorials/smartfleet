/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Turno extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8291499290514883799L;

	private String nomTurno;
	private Integer horas;
	private Time horaIni;
	private Time horaFin;
	private List<Horario> horarios;
	
	public Turno() {
		this.horarios = new ArrayList<Horario>();
	}
	
	public String getNomTurno() {
		return nomTurno;
	}
	public void setNomTurno(final String nomTurno) {
		this.nomTurno = nomTurno;
	}
	public Integer getHoras() {
		return horas;
	}
	public void setHoras(final Integer horas) {
		this.horas = horas;
	}
	public Time getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(final Time horaIni) {
		this.horaIni = horaIni;
	}
	public Time getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(final Time horaFin) {
		this.horaFin = horaFin;
	}
	
	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Turno [id=").append(getId()).append(", ")
				.append("nombre=").append(nomTurno).append(", ")
				.append("horas=").append(horas).append(", ")
				.append("hora inicio=").append(horaIni).append(", ")
				.append("horaFin=").append(horaFin)
				.append(" ]")
				.toString();
	}
}
