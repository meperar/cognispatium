package upv.etsinf.cognispatium.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="factura")

public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
    @Column
	private String descripcion;
	
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pago")
	private Pago pagoOrigen;
    
    
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="presupuesto")
	private Presupuesto presupuestoOrigen;
	
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
	public Pago getPagoOrigen() {
		return pagoOrigen;
	}
	public void setPagoOrigen(Pago pagoOrigen) {
		this.pagoOrigen = pagoOrigen;
	}
	public Presupuesto getPresupuestoOrigen() {
		return presupuestoOrigen;
	}
	public void setPresupuestoOrigen(Presupuesto presupuestoOrigen) {
		this.presupuestoOrigen = presupuestoOrigen;
	}
	
	
	
	
}

	
	
	

	