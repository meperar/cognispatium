package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Professional;;

public interface ProfessionalManager extends Serializable {
    
    public List<Professional> getProfessionals();
    
    public void increaseBenefits (int increse);
    

}