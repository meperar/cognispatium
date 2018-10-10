package upv.etsinf.cognispatium.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Profesional extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@OneToMany(mappedBy = "presupuestoOrigen")
	private List<Presupuesto> presupuestos;

}
