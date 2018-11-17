package upv.etsinf.cognispatium.service;

import java.util.List;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.repository.MensajeDao;

@Component
public class SimpleMensajeManager implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private MensajeDao mensajeDao;
	
	
	public List<Mensaje> getMensajes() {
		
		return mensajeDao.getMensajeList();
	}

	
	public void addMensaje(Mensaje mensaje) {
		
		mensajeDao.saveMensaje(mensaje);
	}
	
	 public Mensaje getMensajebyId(Integer mensajeId) {
	    	return  mensajeDao.getMensajeById(mensajeId);
		}
	
	 public List<Mensaje> getMensajesByClienteId(Integer clienteId){
		 	return mensajeDao.getMensajeByClienteId(clienteId);
		 
	 }
	
	 public List<Mensaje> getMensajesByProfId(Integer profId){
		 	return mensajeDao.getMensajeByProfId(profId);
		 
	 }
	
	

}