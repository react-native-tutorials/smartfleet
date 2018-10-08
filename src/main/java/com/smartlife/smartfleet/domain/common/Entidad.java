/**
 * 
 */
package com.smartlife.smartfleet.domain.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase padre de todas las entidades. Una entidad es un clase que tiene un
 * identificador �nico y que no depende del contenido de sus atributos para
 * diferenciarla. Los atributos de la clase pueden cambiar pero su identidad
 * siempre es la misma. Ej. Persona, Transaccion, Equipo, etc.
 * 
 * @author Marius Iulian Grigoras
 *
 * @date 19 jul. 2018
 */
public class Entidad implements Serializable {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 433833076183775093L;
	
	/**
	 * identificador unico del registro
	 */
	private Long id;

	/**
	 * la versi�n del registro
	 */
	private Integer version;
	
	/**
	 * fecha de creaci�n del registro
	 */
	private Date fechaCrea = new Date();
	
	/**
	 * fecha de actualizaci�n del registro
	 */
	private Date fechaAct;
	
	/**
	 * el usuario/sistema que ha creado el registro
	 */
	private String creaPor = "SISTEMA";
	
	/**
	 * el usuario/sistema que ha actualizado el registro
	 */
	private String actPor;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(final Integer version) {
		this.version = version;
	}

	/**
	 * @return the fechaCrea
	 */
	public Date getFechaCrea() {
		return fechaCrea;
	}

	/**
	 * @param fechaCrea the fechaCrea to set
	 */
	public void setFechaCrea(final Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	/**
	 * @return the fechaAct
	 */
	public Date getFechaAct() {
		return fechaAct;
	}

	/**
	 * @param fechaAct the fechaAct to set
	 */
	public void setFechaAct(final Date fechaAct) {
		this.fechaAct = fechaAct;
	}

	/**
	 * @return the creado
	 */
	public String getCreaPor() {
		return creaPor;
	}

	/**
	 * @param creado the creado to set
	 */
	public void setCreaPor(final String creado) {
		this.creaPor = creado;
	}

	/**
	 * @return the actualizado
	 */
	public String getActPor() {
		return actPor;
	}

	/**
	 * @param actualizado the actualizado to set
	 */
	public void setActPor(final String actualizado) {
		this.actPor = actualizado;
	}
}
