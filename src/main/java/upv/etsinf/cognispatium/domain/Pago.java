package upv.etsinf.cognispatium.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.Table;

@Entity
@Table(name="pago")

public class Pago implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	
	@Column
	private String descripcion;
	private Integer precio;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cliente")
	private Cliente clienteOrigen;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="tarjeta")
	private Tarjeta tarjetaOrigen;
	
	
	public Integer getId() {
		return id;
	}

	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public int getPrecio() {
		return precio;
	}

	
	
	
	public void setId(Integer id) {
		this.id = id;
	}



	public void setPrecio(Integer precio) {
		this.precio = precio;
	}



	public Cliente getClienteOrigen() {
		return clienteOrigen;
	}



	public void setClienteOrigen(Cliente clienteOrigen) {
		this.clienteOrigen = clienteOrigen;
	}



	public Tarjeta getTarjetaOrigen() {
		return tarjetaOrigen;
	}



	public void setTarjetaOrigen(Tarjeta tarjetaOrigen) {
		this.tarjetaOrigen = tarjetaOrigen;
	}



}
