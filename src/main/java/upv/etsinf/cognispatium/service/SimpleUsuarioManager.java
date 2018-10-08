package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.repository.UsuarioDao;

@Component
public class SimpleUsuarioManager implements UsuarioManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private UsuarioDao usuarioDao;

    public void setUsuarioDao(UsuarioDao professionalDao) {
        this.usuarioDao = professionalDao;
    }

    public List<Usuario> getUsuarios() {
       return usuarioDao.getUsuarioList();        
    }

	@Override
	public void increaseBenefits(int increse) {
		// TODO Auto-generated method stub
		
	}
    
}