package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.repository.RespuestaDao;

@Component
public class SimpleRespuestaManager implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private RespuestaDao respuestaDao;
	
	
	public List<Respuesta> getRespuestas() {
		
		return respuestaDao.getRespuestaList();
	}
	
	public Respuesta getRespuestabyId(Integer respuestaId) {
		
		return  respuestaDao.getRespuestabyId(respuestaId);
	}
	
	
	public void setRespuestaDao(RespuestaDao respuestaDao) {
        this.respuestaDao = respuestaDao;
    }
	
	public void addRespuesta(Respuesta respuesta) {
		
		respuestaDao.saveRespuesta(respuesta);
	}
	
	public void dropRes(Respuesta respuesta) {
		
		respuestaDao.dropRespuesta(respuesta);
	}

}
