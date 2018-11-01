package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upv.etsinf.cognispatium.domain.Registro;
import upv.etsinf.cognispatium.repository.RegistroDao;

@Component
public class SimpleRegistroManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RegistroDao registroDao;
	
	
	public List<Registro> getRegistros() {
		
		return registroDao.getRegistroList();
	}

	
	public void addRegistro(Registro registro) {
		
		registroDao.saveRegistro(registro);
	}
	
	public Registro getRegistrobyId(Integer registroId) {
	    	return  registroDao.getRegistrobyId(registroId);
	}
	 
	public List<Registro> getRegistrobyInfo(String username, String contraseña) {
	    	return  registroDao.getRegistrobyInfo(username, contraseña);
	}
	
	public List<Registro> getRegistroByUsername(String username) {
	    	return  registroDao.getRegistroByUsername(username);
	}
}
