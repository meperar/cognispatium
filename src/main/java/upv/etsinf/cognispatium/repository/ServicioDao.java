package upv.etsinf.cognispatium.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;


public interface ServicioDao {

    public List<Servicio> getServicioList();

    public void saveServicio(Servicio servicio);

	public Servicio getServicioById(Integer serviceId);
	
	public List<String> getAmbitos();
	
	public List<Servicio> getServiciobyAmbito(String ambitoId);
	
	public List<Profesional> getProfsbyServicio(Integer serviceId);

	List<Profesional> getProfesionales(Integer servicioId);
	
	@Transactional(readOnly = true)
    public List<Servicio> getServicioByProfesional(Integer profesionalId);

}