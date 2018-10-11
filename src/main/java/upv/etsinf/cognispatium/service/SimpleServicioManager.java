package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
