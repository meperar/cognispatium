package upv.etsinf.cognispatium.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class ConsultaUrgente extends Consulta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = DateTimeSerializer.class)
	private DateTime fechaFin;	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cobro")
	private Cobro cobroOrigen;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name="pago")
	private Pago pago;

	
	
	
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

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	public String getFechaFinFormateada(String format) {
		DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
		return fmt.print(dt);
	}
}
