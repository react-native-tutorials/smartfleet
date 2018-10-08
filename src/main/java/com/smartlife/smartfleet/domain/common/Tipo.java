/**
 * 
 */
package com.smartlife.smartfleet.domain.common;

import java.io.Serializable;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class Tipo implements Serializable{

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -1128321125077997248L;
	
	private String tipoId;
	private String categoria;
	private String tipo;
	private String descripcion;
	private String activo;
	
	public String getTipoId() {
		return tipoId;
	}
	public void setTipoId(final String tipoId) {
		this.tipoId = tipoId;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(final String categoria) {
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(final String activo) {
		this.activo = activo;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object == this) return true;
		if(object instanceof Tipo) {
			Tipo tipo = (Tipo)object;
			return this.tipoId.equals(tipo.getTipoId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.tipoId.hashCode();
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("Tipo [ ")
				.append("entidad: ").append(this.getClass().getName()).append(", ")
				.append("id: ").append(this.tipoId).append(", ")
				.append("categoria: ").append(this.categoria).append(", ")
				.append("tipo: ").append(this.tipo).append(", ")
				.append("descripcion: ").append(this.descripcion)
				.append(" ]")
				.toString();
	}
}
