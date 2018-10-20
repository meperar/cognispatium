package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Profesional;

public interface ProfesionalManager extends Serializable {
    
    public List<Profesional> getProfesionales(); 
    public List<Profesional> getProfesionalesbyServicio(Integer serviceId); 

}