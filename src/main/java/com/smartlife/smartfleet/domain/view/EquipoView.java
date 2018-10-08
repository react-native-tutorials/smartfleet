/**
 * 
 */
package com.smartlife.smartfleet.domain.view;

import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.Material;
import com.smartlife.smartfleet.domain.Razon;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class EquipoView {

	private Long id;
	private TipoEquipo tipo;
	private String codigo;
	private Estado estado;
	private Razon razon;
	private String comentario;
	private Material ley;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoEquipo getTipo() {
		return tipo;
	}
	public void setTipo(TipoEquipo tipo) {
		this.tipo = tipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Razon getRazon() {
		return razon;
	}
	public void setRazon(Razon razon) {
		this.razon = razon;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Material getLey() {
		return ley;
	}
	public void setLey(Material ley) {
		this.ley = ley;
	}
	
}
