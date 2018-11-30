package upv.etsinf.cognispatium.web.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import upv.etsinf.cognispatium.domain.Cobro;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Valoracion;


public class ProfesionalDto {

	private String nombre;
	
	private String apellidos;
	
	private String fotos;
	 
	private Long valoracionMedia;
	
	
	

	public ProfesionalDto(String nombre, String apellidos, String fotos, Long valoracionMedia) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fotos = fotos;
		this.valoracionMedia = valoracionMedia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public Long getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(Long valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}
	
	
}
