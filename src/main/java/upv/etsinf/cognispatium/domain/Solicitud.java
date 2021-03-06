package upv.etsinf.cognispatium.domain;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;

@Entity
@Table(name="solicitud")

public class Solicitud implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	
    @Column
	private String descripcion;
	private String titulo;
	
	@Column(name = "estado", nullable = false, length = 20)
	@Enumerated(value = EnumType.STRING)
	private EstadoSolicitud estado;
	
	@Column
	@Temporal(TemporalType.DATE)
    private Date fechaCreacion;  
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cobro")
	private Cobro cobroOrigen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente clienteOrigen;
	
	
	@OneToOne(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="pago")
	private Pago pago ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="servicio")
	private Servicio servicioOrigen;
	
	
	@OneToMany(mappedBy = "solicitudOrigen")
	private List<Presupuesto> presupuestos;

	
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


	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public Cobro getCobroOrigen() {
		return cobroOrigen;
	}

	public void setCobroOrigen(Cobro cobroOrigen) {
		this.cobroOrigen = cobroOrigen;
	}

	public Cliente getClienteOrigen() {
		return clienteOrigen;
	}

	public void setClienteOrigen(Cliente clienteOrigen) {
		this.clienteOrigen = clienteOrigen;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public Servicio getServicioOrigen() {
		return servicioOrigen;
	}

	public void setServicioOrigen(Servicio servicioOrigen) {
		this.servicioOrigen = servicioOrigen;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}
	
	@Override
	public boolean equals(Object solicitud) {
		if(solicitud instanceof Solicitud) {
			Solicitud solicitudTemp = (Solicitud) solicitud;
			return this.getId().equals(solicitudTemp.getId());
		} else {
			return false;
		}
		/*System.out.println((solicitud instanceof Solicitud) + "AND" + this.getId().equals(solicitud.getId()));
		return solicitud instanceof Solicitud && this.getId().equals(solicitud.getId());*/
	}

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


	
	
	
}
	

