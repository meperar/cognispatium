package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.repository.ServicioDao;

@Component
public class SimpleServicioManager implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioDao servicioDao;
	
	
	public List<Servicio> getServicios() {
		
		return servicioDao.getServicioList();
	}
	
	public void setServicioDao(ServicioDao servicioDao) {
        this.servicioDao = servicioDao;
    }
	
	public void addServicio(Servicio servicio) {
		
		servicioDao.saveServicio(servicio);
	}

	public Servicio getServiciobyId(Integer serviceId) {
		
		return  servicioDao.getServicioById(serviceId);
	}
	
	public List<Profesional> getProfsbyServicio(Integer serviceId){
		return servicioDao.getProfsbyServicio(serviceId);
		
	}
	
	public List<Servicio> getServiciosbyAmbito(String ambitoId) {
		return servicioDao.getServiciobyAmbito(ambitoId);
	}
    public List<String> getAmbitos() {
		
		return servicioDao.getAmbitos();
	}
	
}
