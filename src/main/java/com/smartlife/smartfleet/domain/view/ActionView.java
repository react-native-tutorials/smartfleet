/**
 * 
 */
package com.smartlife.smartfleet.domain.view;

import java.util.Date;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class ActionView {

	private Long actionId;
	private String codEqui;
	private String message;
	private Date dateSent;

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public String getCodEqui() {
		return codEqui;
	}

	public void setCodEqui(String codEqui) {
		this.codEqui = codEqui;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
}
