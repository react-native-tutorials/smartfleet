/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.util.Date;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Ruta extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 408940282606318312L;

	private Ubicacion ubiIni;
	private Ubicacion ubiFin;
	private Equipo equipo;
	private Long distancia;
	private Date timeIni;
	private Date timeFin;
	private Long timeEstim;

	public Ubicacion getUbiIni() {
		return ubiIni;
	}

	public void setUbiIni(Ubicacion ubiIni) {
		this.ubiIni = ubiIni;
	}

	public Ubicacion getUbiFin() {
		return ubiFin;
	}

	public void setUbiFin(Ubicacion ubiFin) {
		this.ubiFin = ubiFin;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Long getDistancia() {
		return distancia;
	}

	public void setDistancia(Long distancia) {
		this.distancia = distancia;
	}

	public Date getTimeIni() {
		return timeIni;
	}

	public void setTimeIni(Date timeIni) {
		this.timeIni = timeIni;
	}

	public Date getTimeFin() {
		return timeFin;
	}

	public void setTimeFin(Date timeFin) {
		this.timeFin = timeFin;
	}

	public Long getTimeEstim() {
		return timeEstim;
	}

	public void setTimeEstim(Long timeEstim) {
		this.timeEstim = timeEstim;
	}

}
