package upv.etsinf.cognispatium.domain;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
	private Integer cliente;
	
	
	
	
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

	
	
	public int getCliente() {
		return cliente;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}



	public void setPrecio(Integer precio) {
		this.precio = precio;
	}



	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}



	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Descripción: " + descripcion + ";");
		buffer.append("Precio: " + precio);
		buffer.append("Cliente:" + cliente);
		return buffer.toString();
	}
	
	
}
