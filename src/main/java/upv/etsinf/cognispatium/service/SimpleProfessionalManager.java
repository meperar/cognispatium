package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Professional;
import upv.etsinf.cognispatium.repository.ProfessionalDao;

@Component
public class SimpleProfessionalManager implements ProfessionalManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private ProfessionalDao professionalDao;

    public void setProfessionalDao(ProfessionalDao professionalDao) {
        this.professionalDao = professionalDao;
    }

    public List<Professional> getProfessionals() {
       return professionalDao.getProfessionalList();        
    }
    

	public void increaseBenefits(int increase) {
		 List<Professional> professionals = professionalDao.getProfessionalList();
		 if (professionals != null) {
	            for (Professional professional : professionals) {
	                int benefits = professional.getBenefits() * 
	                                    (100 + increase)/100;
	                professional.setBenefits(benefits);;
	                professionalDao.saveProfessional(professional);
	            }
	        }  
		
	}

	public void setProfessionals(List<Professional> professionals) {
		// TODO Auto-generated method stub
		
	}
}