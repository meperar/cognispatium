package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Servicio;
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
    
    public void dropCU(ConsultaUrgente consultaUrgente) {
		
		consultaUrgenteDao.dropConsultaUrgente(consultaUrgente);
	}

	public List<ConsultaUrgente> getConsultasbyService(Servicio servicioConsulta) {
		return consultaUrgenteDao.getConsultaListbyService(servicioConsulta);
	}

	public ConsultaUrgente getConsultaUrgentebyId(Integer consultaId) {
		return consultaUrgenteDao.getConsultaUrgentebyId(consultaId);	}
}