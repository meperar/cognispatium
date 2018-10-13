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
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cobro")
	private Cobro cobroOrigen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente clienteOrigen;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pago")
	private Pago pagoOrigen ;
	
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

	public Pago getPagoOrigen() {
		return pagoOrigen;
	}

	public void setPagoOrigen(Pago pagoOrigen) {
		this.pagoOrigen = pagoOrigen;
	}

	public Servicio getServicioOrigen() {
		return servicioOrigen;
	}

	public void setServicioOrigen(Servicio servicioOrigen) {
		this.servicioOrigen = servicioOrigen;
	}
	
}
	

