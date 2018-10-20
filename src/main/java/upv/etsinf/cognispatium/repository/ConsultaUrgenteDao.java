package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Solicitud;

public interface ConsultaUrgenteDao {

    public List<ConsultaUrgente> getConsultaUrgenteList();

    public void saveConsultaUrgente(ConsultaUrgente consultaUrgente);
    
    public ConsultaUrgente getConsultaUrgenteById(int consultaUrgenteId);

}