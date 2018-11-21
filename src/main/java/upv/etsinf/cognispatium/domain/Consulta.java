package upv.etsinf.cognispatium.domain;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
@Inheritance(strategy = InheritanceType.JOINED)
public class Consulta implements Serializable {

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
	private EstadoConsulta estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente clienteOrigen;

	@OneToMany(mappedBy = "consultaOrigen")
	private List<Respuesta> respuestas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio")
	private Servicio servicioOrigen;

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

	public EstadoConsulta getEstado() {
		return estado;
	}

	public void setEstado(EstadoConsulta estado) {
		this.estado = estado;
	}

	public Cliente getClienteOrigen() {
		return clienteOrigen;
	}

	public void setClienteOrigen(Cliente clienteOrigen) {
		this.clienteOrigen = clienteOrigen;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Servicio getServicioOrigen() {
		return servicioOrigen;
	}

	public void setServicioOrigen(Servicio servicioOrigen) {
		this.servicioOrigen = servicioOrigen;
	}

}