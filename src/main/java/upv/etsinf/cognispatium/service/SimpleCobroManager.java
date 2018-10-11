package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Cobro;
import upv.etsinf.cognispatium.repository.CobroDao;

@Component
public class SimpleCobroManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private CobroDao cobroDao;

    public void setCobroDao(CobroDao cobroDao) {
        this.cobroDao = cobroDao;
    }

    public List<Cobro> getCobros() {
       return cobroDao.getCobroList();        
    }

    public void addCobro(Cobro cobro) {
		cobroDao.saveCobro(cobro);
	}
    
}