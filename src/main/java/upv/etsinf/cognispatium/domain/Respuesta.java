package upv.etsinf.cognispatium.domain;

import java.io.Serializable;

import java.util.Date;
<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> DEV
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
<<<<<<< HEAD
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
=======
>>>>>>> DEV
import javax.persistence.Table;

@Entity
@Table(name="respuesta")

public class Respuesta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
<<<<<<< HEAD
    @Column
	private String descripcion;
    
    @ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="cliente")
	  private Consulta consultaOrigen;
	
    
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
=======
	
	@Column
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profesional")
	private Profesional profesionalOrigen;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="consulta")
	private Consulta consultaOrigen;
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

>>>>>>> DEV
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
<<<<<<< HEAD
	
	
	
	
}

=======

	public Profesional getProfesionalOrigen() {
		return profesionalOrigen;
	}

	public void setProfesionalOrigen(Profesional profesionalOrigen) {
		this.profesionalOrigen = profesionalOrigen;
	}

	public Consulta getConsultaOrigen() {
		return consultaOrigen;
	}

	public void setConsultaOrigen(Consulta consultaOrigen) {
		this.consultaOrigen = consultaOrigen;
	}
>>>>>>> DEV
	
	
	

<<<<<<< HEAD
	
=======


	
}
>>>>>>> DEV
