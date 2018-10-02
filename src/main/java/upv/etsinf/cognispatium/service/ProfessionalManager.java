package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Usuario;

public interface ProfessionalManager extends Serializable {
    
    public List<Usuario> getProfessionals();
    
    public void increaseBenefits (int increse);
    

}