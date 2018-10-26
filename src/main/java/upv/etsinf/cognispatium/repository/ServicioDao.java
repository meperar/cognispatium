package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;


public interface ServicioDao {

    public List<Servicio> getServicioList();

    public void saveServicio(Servicio servicio);

	public Servicio getServicioById(Integer serviceId);
	
<<<<<<< HEAD
=======
	public List<String> getAmbitos();
	
>>>>>>> branch 'DEV' of https://github.com/meperar/cognispatium
	public List<Servicio> getServiciobyAmbito(String ambitoId);
	
	public List<Profesional> getProfsbyServicio(Integer serviceId);
<<<<<<< HEAD
	
	public List<String> getAmbitos();
=======
>>>>>>> branch 'DEV' of https://github.com/meperar/cognispatium

}