package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.repository.ProfesionalDao;

@Component
public class SimpleProfesionalManager implements ProfesionalManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private ProfesionalDao profesionalDao;

    public void setProfesionalDao(ProfesionalDao profesionalDao) {
        this.profesionalDao = profesionalDao;
    }

    public List<Profesional> getProfesionales() {
    	
       return profesionalDao.getProfesionalList();        
    }
    
   /* public List<Profesional> getProfesionalesbyServicio(Integer serviceId) {
    	
        return profesionalDao.getProfesionalesbyServicio(serviceId);        
     }*/
    
    public void addProfesional(Profesional profesional) {
    profesionalDao.saveProfesional(profesional);
	}
    
    public void dropProf(Profesional profesional) {
    	
        profesionalDao.dropProfesional(profesional);
        
    }
    
    public Profesional getProfesionalById(Integer profesionalId) {
    	
    	return profesionalDao.getProfesionalById(profesionalId);
    }
}