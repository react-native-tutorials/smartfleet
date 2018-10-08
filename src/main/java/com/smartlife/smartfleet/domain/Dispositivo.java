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
public class Dispositivo extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -1682994245671284377L;

	private String ipDispositivo;
	private String gateway;
	private String macDispositivo;
	private String tipoDispositivo;
	private String portDisp;
	private List<GpsDispositivo> ubicaciones;
	private List<EquipoDispositivo> equipos;

	public Dispositivo() {
		ubicaciones = new ArrayList<>();
		equipos = new ArrayList<>();
	}
	
	public String getIpDispositivo() {
		return ipDispositivo;
	}

	public void setIpDispositivo(final String ipDispositivo) {
		this.ipDispositivo = ipDispositivo;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(final String gateway) {
		this.gateway = gateway;
	}

	public String getMacDispositivo() {
		return macDispositivo;
	}

	public void setMacDispositivo(final String macDispositivo) {
		this.macDispositivo = macDispositivo;
	}

	public String getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(final String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public List<GpsDispositivo> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<GpsDispositivo> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public List<EquipoDispositivo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoDispositivo> equipos) {
		this.equipos = equipos;
	}

	public String getPortDisp() {
		return portDisp;
	}

	public void setPortDisp(String portDisp) {
		this.portDisp = portDisp;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Dispositivo [id=").append(this.getId()).append(", ")
				.append("tipo= ").append(this.tipoDispositivo).append(", ")
				.append("ip= ").append(this.ipDispositivo).append(", ")
				.append("gateway= ").append(this.gateway).append(", ")
				.append("mac= " ).append(this.macDispositivo).append(", ")
				.append("port= " ).append(this.portDisp)
				.append(" ]")
				.toString();
	}
}
