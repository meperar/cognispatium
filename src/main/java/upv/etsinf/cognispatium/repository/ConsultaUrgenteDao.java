package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Servicio;

public interface ConsultaUrgenteDao {

    public List<ConsultaUrgente> getConsultaUrgenteList();

    public void saveConsultaUrgente(ConsultaUrgente consultaUrgente);

	public List<ConsultaUrgente> getConsultaListbyService(Servicio servicioConsulta);

	public ConsultaUrgente getConsultaUrgentebyId(Integer consultaId);

}