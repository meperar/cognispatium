package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.repository.UsuarioDao;

@Component
public class SimpleProfessionalManager implements ProfessionalManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private UsuarioDao usuarioDao;

    public void setProfessionalDao(UsuarioDao professionalDao) {
        this.usuarioDao = professionalDao;
    }

    public List<Usuario> getProfessionals() {
       return usuarioDao.getProfessionalList();        
    }

	@Override
	public void increaseBenefits(int increse) {
		// TODO Auto-generated method stub
		
	}
    
}