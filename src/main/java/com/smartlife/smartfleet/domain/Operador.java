/**
 * 
 */
package com.smartlife.smartfleet.domain;

import java.util.ArrayList;
import java.util.List;

import com.smartlife.smartfleet.domain.common.Entidad;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class Operador extends Entidad {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 4996548417396455606L;

	private String docIdent;
	private String codigoOperador;
	private String nombre;
	private String apellido;
	private List<Horario> horarios;
	private List<OperadorEquipo> equipos;
	
	public Operador() {
		this.horarios = new ArrayList<>();
		this.equipos = new ArrayList<>();
	}
	
	public Operador(String docIdent, String cdg, String nom, String ape) {
		this.docIdent = docIdent;
		this.codigoOperador = cdg;
		this.nombre = nom;
		this.apellido = ape;
		this.horarios = new ArrayList<>();
		this.equipos = new ArrayList<>();
	}

	public String getDocIdent() {
		return docIdent;
	}

	public void setDocIdent(String docIdent) {
		this.docIdent = docIdent;
	}

	public String getCodigoOperador() {
		return codigoOperador;
	}

	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public List<OperadorEquipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<OperadorEquipo> equipos) {
		this.equipos = equipos;
	}

	@Override
	public String toString() {
		return "Operador [id=" + getId() + ", codigo operador=" + codigoOperador + ", nombre=" + nombre + ", apellido="
				+ apellido + "]";
	}
}
