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
@Table(name="mensaje")

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
    @Column
	private String asunto;
    
    @Column
    private String descripcion;
    
    @Column
    private Date fecha;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente cliente;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profesional")
	private Profesional profesional;
    
    
	
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
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public Date getfecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
