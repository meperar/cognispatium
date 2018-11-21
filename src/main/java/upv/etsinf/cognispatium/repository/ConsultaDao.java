package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Consulta;

public interface ConsultaDao {

    public List<Consulta> getConsultaList();

    public void saveConsulta(Consulta consulta);
    
    public void dropConsulta(Consulta consulta);
    
    public List<Consulta> getConsultasbyServicio(Integer serviceId);
        
    public List<Consulta> getConsultasByCli(int cliId);
     

}