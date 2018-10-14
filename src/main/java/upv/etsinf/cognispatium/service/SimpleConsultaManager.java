package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.repository.ConsultaDao;

@Component
public class SimpleConsultaManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private ConsultaDao consultaDao;

    public void setConsultaDao(ConsultaDao consultaDao) {
        this.consultaDao = consultaDao;
    }

    public List<Consulta> getConsultas() {
       return consultaDao.getConsultaList();        
    }

    public void addConsulta(Consulta consulta) {
    	consultaDao.saveConsulta(consulta);
	}
}