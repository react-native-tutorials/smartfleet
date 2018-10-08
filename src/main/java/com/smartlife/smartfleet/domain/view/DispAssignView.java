/**
 * 
 */
package com.smartlife.smartfleet.domain.view;

import java.util.Date;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class DispAssignView {

	private Long assignId;
	private String equiCode;
	private String dispIp;
	private Date fechaAsig;
	private String active;
	public Long getAssignId() {
		return assignId;
	}
	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}
	public String getEquiCode() {
		return equiCode;
	}
	public void setEquiCode(String equiCode) {
		this.equiCode = equiCode;
	}
	public String getDispIp() {
		return dispIp;
	}
	public void setDispIp(String dispIp) {
		this.dispIp = dispIp;
	}
	public Date getFechaAsig() {
		return fechaAsig;
	}
	public void setFechaAsig(Date fechaAsig) {
		this.fechaAsig = fechaAsig;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
}
