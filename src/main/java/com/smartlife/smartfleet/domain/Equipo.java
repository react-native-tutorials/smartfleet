/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smartlife.smartfleet.domain.common.Entidad;
import com.smartlife.smartfleet.domain.common.Tipo;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Equipo extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -328456880519523659L;

	private Tipo categoriaEquipo;
	private String codigoEquipo;
	private String marcaEquipo;
	private String modeloEquipo;
	private BigDecimal capCombustible;
	private BigDecimal capCarga;
	private String activo;
	private List<EquipoDispositivo> dispositivos;
	
	private List<EstadoEquipo> equipoStates;
	private List<OperadorEquipo> operadores;

	public Equipo() {
		dispositivos = new ArrayList<>();
		equipoStates = new ArrayList<>();
		operadores = new ArrayList<>();
	}
	
	public Tipo getCategoriaEquipo() {
		return categoriaEquipo;
	}

	public void setCategoriaEquipo(final Tipo categoriaEquipo) {
		this.categoriaEquipo = categoriaEquipo;
	}

	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(final String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public String getMarcaEquipo() {
		return marcaEquipo;
	}

	public void setMarcaEquipo(final String marcaEquipo) {
		this.marcaEquipo = marcaEquipo;
	}

	public String getModeloEquipo() {
		return modeloEquipo;
	}

	public void setModeloEquipo(final String modeloEquipo) {
		this.modeloEquipo = modeloEquipo;
	}

	public BigDecimal getCapCombustible() {
		return capCombustible;
	}

	public void setCapCombustible(final BigDecimal capCombustible) {
		this.capCombustible = capCombustible;
	}

	public BigDecimal getCapCarga() {
		return capCarga;
	}

	public void setCapCarga(final BigDecimal capCarga) {
		this.capCarga = capCarga;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(final String activo) {
		this.activo = activo;
	}

	public List<EquipoDispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<EquipoDispositivo> dipositivos) {
		this.dispositivos = dipositivos;
	}

	public List<EstadoEquipo> getEquipoStates() {
		return equipoStates;
	}

	public void setEquipoStates(List<EstadoEquipo> equipoStates) {
		this.equipoStates = equipoStates;
	}

	public List<OperadorEquipo> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<OperadorEquipo> operadores) {
		this.operadores = operadores;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + getId() + ", categoria=" + categoriaEquipo.getCategoria() + ", codigo=" + codigoEquipo
				+ ", marca=" + marcaEquipo + ", modelo=" + modeloEquipo + ", capacitad combustible=" + capCombustible
				+ ", capacitad carga=" + capCarga + ", activo=" + activo + "]";
	}
}
