package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Profesional;

public interface ProfesionalDao {

    public List<Profesional> getProfesionalList();
    public List<Profesional> getProfesionalesbyServicio(Integer serviceId);
    public void saveProfesional(Profesional profesional);
    
    //GetAdminby??

}