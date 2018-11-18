package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Valoracion;
import upv.etsinf.cognispatium.repository.ValoracionDao;

@Component
public class SimpleValoracionManager implements ValoracionManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private ValoracionDao valoracionDao;

    public void setUsuarioDao(ValoracionDao valoracionDao) {
        this.valoracionDao = valoracionDao;
    }

    public List<Valoracion> getValoraciones() {
       return valoracionDao.getValoracionList();        
    }

    
    public Valoracion getValoracionbyId(Integer valoracionId) {
    	return  valoracionDao.getValoracionById(valoracionId);
	}
    
    public void addValoracion(Valoracion valoracion) {
		
    	valoracionDao.saveValoracion(valoracion);
	}
    
    public void dropValoracion(Valoracion valoracion) {
		
    	valoracionDao.dropValoracion(valoracion);
	}
    
}