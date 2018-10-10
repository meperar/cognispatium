package upv.etsinf.cognispatium.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="presupuesto")

public class Presupuesto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	
    @Column
	private String descripcion;
	private Integer precio;
	private Integer profesional;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitud")
	private Solicitud solicitudOrigen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profesional")
	private Solicitud profesionalOrigen;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="presupuesto")
	private Factura facturaOrigen;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	
	public Integer getProfesional() {
		return profesional;
	}
	public void setProfesional(Integer profesional) {
		this.profesional = profesional;
	}
	
	
	
	
}

	
	
	

	