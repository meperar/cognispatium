package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Consulta;

public interface ConsultaDao {

    public List<Consulta> getConsultaList();

    public void saveConsulta(Consulta consulta);

    public Consulta getConsultaById(int consultaId);
}