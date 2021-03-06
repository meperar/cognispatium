package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Pago;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.repository.PagoDao;
import upv.etsinf.cognispatium.repository.UsuarioDao;

@Component
public class SimpleUsuarioManager implements UsuarioManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private PagoDao pagoDao;

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public List<Usuario> getUsuarios() {
       return usuarioDao.getUsuarioList();        
    }

	@Override
	public void increaseBenefits(int increse) {
		// TODO Auto-generated method stub
		
	}
	
	public Usuario getUsuariobyId(Integer usuarioId) {
		
		return usuarioDao.getUsuariobyId(usuarioId);
		
	}

	public void addUsuario(Usuario usuario) {
		
		usuarioDao.saveUsuario(usuario);
	}
	
	public void dropUser(Usuario usuario) {
		
		usuarioDao.dropUsuario(usuario);
	}
	
	
	
    
}