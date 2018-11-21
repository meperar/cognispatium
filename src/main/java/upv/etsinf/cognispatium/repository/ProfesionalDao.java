package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;

public interface ProfesionalDao {

    public List<Profesional> getProfesionalList();
    /*public List<Profesional> getProfesionalesbyServicio(Integer serviceId);*/
    public void saveProfesional(Profesional profesional);
    
    public void dropProfesional(Profesional profesional);
    //GetAdminby??
    
    public Profesional getProfesionalById(Integer profesionalId);
    

}