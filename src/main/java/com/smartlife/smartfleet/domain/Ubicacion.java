/**
 * 
 */
package com.smartlife.smartfleet.domain;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Ubicacion extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -3008320718612070981L;

	private String tipo;
	private String nombre;
	private String ubiCoordX;
	private String ubiCoordY;
	private String ubiCoordZ;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbiCoordX() {
		return ubiCoordX;
	}

	public void setUbiCoordX(String ubiCoordX) {
		this.ubiCoordX = ubiCoordX;
	}

	public String getUbiCoordY() {
		return ubiCoordY;
	}

	public void setUbiCoordY(String ubiCoordY) {
		this.ubiCoordY = ubiCoordY;
	}

	public String getUbiCoordZ() {
		return ubiCoordZ;
	}

	public void setUbiCoordZ(String ubiCoordZ) {
		this.ubiCoordZ = ubiCoordZ;
	}

}
