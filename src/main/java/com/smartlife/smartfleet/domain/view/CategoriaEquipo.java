/**
 * 
 */
package com.smartlife.smartfleet.domain.view;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class CategoriaEquipo extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 4874731386886288442L;

	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Categoria Equipo [ id="+getId()+", categoria = "+categoria+"]";
	}
}
