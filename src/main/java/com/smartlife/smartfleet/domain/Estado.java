/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.util.ArrayList;
import java.util.List;

import com.smartlife.smartfleet.domain.common.Entidad;
import com.smartlife.smartfleet.domain.common.Tipo;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class Estado extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 6060679062189008636L;

	private Tipo categoria;
	private String estado;
	private Integer color;
	private List<Razon> razones;

	private List<EstadoEquipo> allEquiStates;
	
	public Estado() {
		this.razones = new ArrayList<Razon>();
		this.allEquiStates = new ArrayList<>();
	}
	
	public Tipo getCategoria() {
		return categoria;
	}

	public void setCategoria(Tipo categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public List<Razon> getRazones() {
		return razones;
	}

	public void setRazones(List<Razon> razones) {
		this.razones = razones;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}
	
	public List<EstadoEquipo> getAllEquiStates() {
		return allEquiStates;
	}

	public void setAllEquiStates(List<EstadoEquipo> allStates) {
		this.allEquiStates = allStates;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Estado [id=").append(this.getId()).append(",")
				.append("categoria= ").append(this.categoria.getTipo()).append(", ")
				.append("estado= ").append(this.estado).append(", ")
				.append("color= ").append(this.color)
				.append(" ]")
				.toString();
		
	}
}
