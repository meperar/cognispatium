package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.repository.PresupuestoDao;

@Component
public class SimplePresupuestoManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private PresupuestoDao presupuestoDao;

    public void setPresupuestoDao(PresupuestoDao presupuestoDao) {
        this.presupuestoDao = presupuestoDao;
    }

    public List<Presupuesto> getPresupuestos() {
       return presupuestoDao.getPresupuestoList();        
    }
    
    public void addPresupuesto(Presupuesto presupuesto) {
		
    	presupuestoDao.savePresupuesto(presupuesto);
	}
    
}