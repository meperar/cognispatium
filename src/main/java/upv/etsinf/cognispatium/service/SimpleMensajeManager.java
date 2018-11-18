package upv.etsinf.cognispatium.service;

import java.util.List;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Usuario;
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


	public List<Mensaje> getMensajesByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return mensajeDao.getMensajesbyUsuario(usuario);
	}


	public List<Mensaje> getMensajesNoLeidosByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return mensajeDao.getMensajesNoLeidosbyUsuario(usuario);
	}


	public List<Mensaje> getMensajesLeidosByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return mensajeDao.getMensajesLeidosbyUsuario(usuario);
	}
	
	public List<Mensaje> getMensajesEliminadosbyUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return mensajeDao.getMensajesEliminadosbyUsuario(usuario);
	}
	
	 public List<Mensaje> getMensajesByClienteId(Integer clienteId){
		 	return mensajeDao.getMensajeByClienteId(clienteId);
		 
	 }
	
	 public List<Mensaje> getMensajesByProfId(Integer profId){
		 	return mensajeDao.getMensajeByProfId(profId);
		 
	 }
	
	

}