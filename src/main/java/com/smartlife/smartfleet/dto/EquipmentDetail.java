/**
 * 
 */
package com.smartlife.smartfleet.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class EquipmentDetail implements Comparable<EquipmentDetail>{

	private BigInteger id;
	private String category;
	private String code;
	private String mark;
	private String model;
	private BigDecimal capFuel;
	private BigDecimal capCharge;
	private Character active = 'Y';
	
	public EquipmentDetail() {
		
	}
	
	public EquipmentDetail(EquipmentDetail det) {
		this.id=det.getId();
		this.category = det.getCategory();
		this.code = det.getCode();
		this.mark = det.getMark();
		this.model = det.getModel();
		this.capFuel = det.getCapFuel();
		this.capCharge = det.getCapCharge();
		this.active = det.getActive();
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger equipmentId) {
		this.id = equipmentId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public BigDecimal getCapFuel() {
		return capFuel;
	}
	public void setCapFuel(BigDecimal capFuel) {
		this.capFuel = capFuel;
	}
	public BigDecimal getCapCharge() {
		return capCharge;
	}
	public void setCapCharge(BigDecimal capCharge) {
		this.capCharge = capCharge;
	}
	public Character getActive() {
		return active;
	}
	public void setActive(Character active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "[Equipment Detail:{ "
				+ "ID: "+this.id+", "
				+ "Category: "+this.category+", "
				+ "Code: "+this.code+", "
				+ "Mark: "+this.mark+", "
				+ "Model: "+this.model+", "
				+ "Cap. Comb: "+this.capFuel+", "
				+ "Cap. Charge: "+this.capCharge+", "
				+ "Active "+this.active+" "
				+ "}]";
	}

	@Override
	public int compareTo(EquipmentDetail o) {
		return this.code.compareTo(o.getCode());
	}
	
}
