/**
 * 
 */
package com.smartlife.smartfleet.domain.view;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class CategoriaEstado extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -5470217117905486947L;

	private String categoriaEstado;

	public String getCategoriaEstado() {
		return categoriaEstado;
	}

	public void setCategoriaEstado(String categoriaEstado) {
		this.categoriaEstado = categoriaEstado;
	}
	
	@Override
	public String toString() {
		return "CategoriaEstado [id= "+getId()+", categoria= "+categoriaEstado+"]";
	}
}
