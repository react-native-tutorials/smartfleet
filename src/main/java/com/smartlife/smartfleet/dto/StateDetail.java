/**
 * 
 */
package com.smartlife.smartfleet.dto;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class StateDetail {
	
	private Long id;
	private String categoria;
	private String estado;
	private Integer color;
	private String razon;

	/**
	 * 
	 */
	public StateDetail() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("StateDetail [id=").append(this.getId()).append(",")
				.append("categoria= ").append(this.categoria).append(", ")
				.append("estado= ").append(this.estado).append(", ")
				.append("color= ").append(this.color).append(", ")
				.append("razon= ").append(this.razon)
				.append(" ]")
				.toString();
	}
}
