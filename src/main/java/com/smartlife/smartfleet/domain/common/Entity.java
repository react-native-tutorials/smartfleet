/**
 * 
 */
package com.smartlife.smartfleet.domain.common;

import java.io.Serializable;
import java.util.Date;

/**
 *  
 * @author Marius-Iulian Grigoras
 *
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = 433833076183775093L;
	
	private Long id;
	private Integer version;
	private Date dateCreated = new Date();
	private Date dateUpdated;
	private String createdBy = "SISTEM";
	private String updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(final Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
