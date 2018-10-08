/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.sql.Time;
import java.util.Date;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Horario extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 5829556725557231461L;

	Turno turno;
	Operador operario;
	Date fechaAsig;
	Time horaIni;
	Time horaFin;
	String isClosed;
	
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Operador getOperario() {
		return operario;
	}
	public void setOperario(Operador operario) {
		this.operario = operario;
	}
	public Date getFechaAsig() {
		return fechaAsig;
	}
	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}
	public String getIsClosed() {
		return isClosed;
	}
	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}
	public Time getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(Time horaIni) {
		this.horaIni = horaIni;
	}
	public Time getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}
	
}
