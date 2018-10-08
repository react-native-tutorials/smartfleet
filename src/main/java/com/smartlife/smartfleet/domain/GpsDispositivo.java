/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.util.Date;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class GpsDispositivo extends Entidad{

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -6934715459496901949L;

	private String latitude;
	private String longitud;
	private String altitud;
	private String velocidad;
	private String xUtm;
	private String yUtm;
	private Date fechaGps = new Date();
	private Dispositivo dispositivo;
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getAltitud() {
		return altitud;
	}

	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public Date getFechaGps() {
		return fechaGps;
	}

	public void setFechaGps(Date fechaGps) {
		this.fechaGps = fechaGps;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getxUtm() {
		return xUtm;
	}

	public void setxUtm(String xUtm) {
		this.xUtm = xUtm;
	}

	public String getyUtm() {
		return yUtm;
	}

	public void setyUtm(String yUtm) {
		this.yUtm = yUtm;
	}
	
	
}
