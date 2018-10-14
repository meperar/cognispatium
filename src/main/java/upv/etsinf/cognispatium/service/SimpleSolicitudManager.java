package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.repository.SolicitudDao;

@Component
public class SimpleSolicitudManager implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private SolicitudDao solicitudDao;
	
	
	public List<Solicitud> getSolicituds() {
		
		return solicitudDao.getSolicitudList();
	}
	
	public void setSolicitudDao(SolicitudDao solicitudDao) {
        this.solicitudDao = solicitudDao;
    }
	
	public void addSolicitud(Solicitud solicitud) {
		
		solicitudDao.saveSolicitud(solicitud);
	}

	public List<Solicitud> getSolicitudsbyService(Servicio servicioConsulta) {
		return solicitudDao.getSolicitudListbyService(servicioConsulta);
	}

	public Solicitud getSolicitudbyId(int solicitudId) {
		
		return solicitudDao.getSolicitudById(solicitudId);
	
	}

}
