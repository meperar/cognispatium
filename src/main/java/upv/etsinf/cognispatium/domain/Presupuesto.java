package upv.etsinf.cognispatium.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitud")
	private Solicitud solicitudOrigen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profesional")
	private Profesional profesionalOrigen;
	
	@Column(name = "estado", nullable = false, length = 20)
	@Enumerated(value = EnumType.STRING)
	private EstadoPresupuesto estado;
	
	@Column
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;  
	
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
	public Solicitud getSolicitudOrigen() {
		return solicitudOrigen;
	}
	public void setSolicitudOrigen(Solicitud solicitudOrigen) {
		this.solicitudOrigen = solicitudOrigen;
	}
	public Profesional getProfesionalOrigen() {
		return profesionalOrigen;
	}
	public void setProfesionalOrigen(Profesional profesionalOrigen) {
		this.profesionalOrigen = profesionalOrigen;
	}
	public EstadoPresupuesto getEstado() {
		return estado;
	}
	public void setEstado(EstadoPresupuesto estado) {
		this.estado = estado;
	}
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date localDate) {
        this.fechaCreacion = localDate;
    }
	
	
	
}

	
	
	

	