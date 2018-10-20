package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.repository.ConsultaUrgenteDao;

@Component
public class SimpleConsultaUrgenteManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private ConsultaUrgenteDao consultaUrgenteDao;

    public void setConsultaUrgenteDao(ConsultaUrgenteDao consultaUrgenteDao) {
        this.consultaUrgenteDao = consultaUrgenteDao;
    }

    public List<ConsultaUrgente> getConsultaUrgentes() {
       return consultaUrgenteDao.getConsultaUrgenteList();        
    }

    public void addConsultaUrgente(ConsultaUrgente consultaUrgente) {
		
		consultaUrgenteDao.saveConsultaUrgente(consultaUrgente);
	}
    
	public ConsultaUrgente getConsultaUrgentebyId(int consultaUrgenteId) {
		
		return consultaUrgenteDao.getConsultaUrgenteById(consultaUrgenteId);
	
	}
}