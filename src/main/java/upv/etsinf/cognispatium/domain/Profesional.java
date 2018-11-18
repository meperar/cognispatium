package upv.etsinf.cognispatium.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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


	@OneToMany(mappedBy = "profesionalOrigen")
	private List<Presupuesto> presupuestos;

	@OneToMany(mappedBy = "profesionalOrigen")
	private List<Respuesta> respuestas;
	
	@OneToMany(mappedBy = "profesionalOrigen")
	private List<Cobro> cobros ;
	
	@OneToMany(mappedBy = "profesional")
	private List<Valoracion> valoraciones ;
	
	@ManyToMany
	@JoinTable(
	name="servicio_profesional",
	joinColumns=@JoinColumn(name="profesional",referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="servicio",referencedColumnName="id"))
	private List<Servicio> servicios;
		
	private int valoracion;

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public List<Cobro> getCobros() {
		return cobros;
	}

	public void setCobros(List<Cobro> cobros) {
		this.cobros = cobros;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	public double getValoracionMedia() {
		if(valoraciones.size()==0)
			return 0;
		int acum = 0;
		for(int i=0;i<valoraciones.size();i++) 
			acum += valoraciones.get(i).getPuntuacion();
		return (double)acum/valoraciones.size();
	}
	
	public long getValoracionMediaRedondeada() {
		return Math.round(getValoracionMedia());
	}
	
	public String getValoracionMediaFormateada() {
		return String.format("%.1f", getValoracionMedia());
	}
}
