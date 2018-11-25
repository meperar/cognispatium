package upv.etsinf.cognispatium.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tarjeta")

public class Tarjeta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	
	@Column
	private Long numero;
	private Date fechaCaducidad;
	private String titular;
	private Integer codigoSeguridad;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente clienteOrigen;

	@OneToMany(mappedBy = "tarjetaOrigen", cascade = {CascadeType.ALL})
	private List<Pago> pagos ;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Long getNumero() {
		return numero;
	}



	public void setNumero(Long numero) {
		this.numero = numero;
	}



	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}



	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}



	public String getTitular() {
		return titular;
	}



	public void setTitular(String titular) {
		this.titular = titular;
	}



	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}



	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}



	public Cliente getClienteOrigen() {
		return clienteOrigen;
	}



	public void setClienteOrigen(Cliente clienteOrigen) {
		this.clienteOrigen = clienteOrigen;
	}



	public List<Pago> getPagos() {
		return pagos;
	}



	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	



	
	
}
