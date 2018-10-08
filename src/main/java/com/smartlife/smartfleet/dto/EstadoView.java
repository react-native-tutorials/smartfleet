/**
 * 
 */
package com.smartlife.smartfleet.dto;

/**
 * @author Marius Iulian Grigoras
 *
 * @date 19 jul. 2018
 */
public class EstadoView {

	private Long id;
	
	private String categName;
	
	private String codigoEstado;
	
	private String nombreEstado;
	
	private String descEstado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategName() {
		return categName;
	}

	public void setCategName(String categName) {
		this.categName = categName;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
}
