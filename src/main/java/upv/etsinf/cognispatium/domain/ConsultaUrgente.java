package upv.etsinf.cognispatium.domain;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class ConsultaUrgente extends Consulta implements Serializable {
=======
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.joda.time.DateTime;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class ConsultaUrgente extends Consulta implements Serializable {

>>>>>>> DEV
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	
	@Column
	private Date fechaFin;

=======

	@Column
	private DateTime fechaFin;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cobro")
	private Cobro cobroOrigen;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pago")
	private Pago pagoOrigen;

	
	
	
	public DateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(DateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Cobro getCobroOrigen() {
		return cobroOrigen;
	}

	public void setCobroOrigen(Cobro cobroOrigen) {
		this.cobroOrigen = cobroOrigen;
	}

	public Pago getPagoOrigen() {
		return pagoOrigen;
	}

	public void setPagoOrigen(Pago pagoOrigen) {
		this.pagoOrigen = pagoOrigen;
	}
	
	
>>>>>>> DEV
}
