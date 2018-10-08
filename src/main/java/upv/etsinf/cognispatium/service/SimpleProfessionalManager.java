package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.repository.ProfessionalDao;

@Component
public class SimpleProfessionalManager implements ProfessionalManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private ProfessionalDao professionalDao;

    public void setProfessionalDao(ProfessionalDao professionalDao) {
        this.professionalDao = professionalDao;
    }

    public List<Usuario> getProfessionals() {
       return professionalDao.getProfessionalList();        
    }

	@Override
	public void increaseBenefits(int increse) {
		// TODO Auto-generated method stub
		
	}
    

//	public void increaseBenefits(int increase) {
//<<<<<<< HEAD
//		 List<Usuario> professionals = professionalDao.getProfessionalList();
//		 if (professionals != null) {
//	            for (Usuario professional : professionals) {
//	                int benefits = professional.getEmail() * 
//	                                    (100 + increase)/100;
//	                professional.setEmail(benefits);;
//	                professionalDao.saveProfessional(professional);
//	            }
//	        }  
//		
//=======
//		 List<Professional> professionals = professionalDao.getProfessionalList();
//		 if (professionals != null) {
//	            for (Professional professional : professionals) {
//	                int benefits = professional.getBenefits() * 
//	                                    (100 + increase)/100;
//	                professional.setBenefits(benefits);;
//	                professionalDao.saveProfessional(professional);
//	            }
//	        }  
//		
//>>>>>>> DEV
//	}
//
//	public void setProfessionals(List<Usuario> professionals) {
//		// TODO Auto-generated method stub
//		
//	}
}