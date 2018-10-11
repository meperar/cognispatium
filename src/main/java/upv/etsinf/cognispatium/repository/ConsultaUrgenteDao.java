package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.ConsultaUrgente;

public interface ConsultaUrgenteDao {

    public List<ConsultaUrgente> getConsultaUrgenteList();

    public void saveConsultaUrgente(ConsultaUrgente consultaUrgente);

}