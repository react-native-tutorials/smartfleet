/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.util.ArrayList;
import java.util.List;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class Razon extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -3533296939669760874L;

	private Estado estado;
	
	private String razon;
	
	private List<EstadoEquipo> equiReasons;
	
	public Razon() {
		equiReasons = new ArrayList<>();
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}
	
	public List<EstadoEquipo> getEquiReasons() {
		return equiReasons;
	}

	public void setEquiReasons(List<EstadoEquipo> equiReasons) {
		this.equiReasons = equiReasons;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Razon [id= ").append(getId()).append(", ")
				.append("estado=").append(this.estado.getEstado()).append(", ")
				.append("razon= ").append(this.razon)
				.append(" ]")
				.toString();
	}
}
