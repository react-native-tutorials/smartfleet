/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class ApplicationParameter {

	private String code;
	private String creaPor;
	private Date fechaCrea;
	private String description;
	private String textValue;
	private Integer intValue;
	private BigDecimal doubleValue;
	private String startInterval;
	private String endInterval;
	private String activeIndicator;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreaPor() {
		return creaPor;
	}
	public void setCreaPor(String creaPor) {
		this.creaPor = creaPor;
	}
	public Date getFechaCrea() {
		return fechaCrea;
	}
	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}
	public Integer getIntValue() {
		return intValue;
	}
	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}
	public BigDecimal getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(BigDecimal doubleValue) {
		this.doubleValue = doubleValue;
	}
	public String getStartInterval() {
		return startInterval;
	}
	public void setStartInterval(String startInterval) {
		this.startInterval = startInterval;
	}
	public String getEndInterval() {
		return endInterval;
	}
	public void setEndInterval(String endInterval) {
		this.endInterval = endInterval;
	}
	public String getActiveIndicator() {
		return activeIndicator;
	}
	public void setActiveIndicator(String activeIndicator) {
		this.activeIndicator = activeIndicator;
	}
	
	
}
