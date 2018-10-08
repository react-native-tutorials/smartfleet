/**
 * 
 */
package com.smartlife.smartfleet.domain;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Poligono extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 511747067864165973L;

	private String ubiCoordX;
	private String ubiCoordY;
	private String ubiCoordZ;
	private Material material;
	private String nivel;
	private String proyecto;
	private String activo;

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

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}
