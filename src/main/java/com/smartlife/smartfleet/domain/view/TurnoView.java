/**
 * 
 */
package com.smartlife.smartfleet.domain.view;

import java.util.Date;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class TurnoView {

	private Long horaId;
	private String turnoName;
	private String operCode;
	private Date fechaAsig;
	public Long getHoraId() {
		return horaId;
	}
	public void setHoraId(Long horaId) {
		this.horaId = horaId;
	}
	public String getTurnoName() {
		return turnoName;
	}
	public void setTurnoName(String turnoName) {
		this.turnoName = turnoName;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	public Date getFechaAsig() {
		return fechaAsig;
	}
	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}
	
}
