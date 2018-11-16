package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Solicitud;
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

    public void dropPres(Presupuesto presupuesto) {
    	presupuestoDao.dropPresupuesto(presupuesto);
    }
	public Presupuesto getPresupuestobyId(Integer presupuestoId) {
		
		return  presupuestoDao.getPresupuestoById(presupuestoId);
	}
    public Presupuesto getPresupuestoAceptadoBySolicitud(Solicitud solicitud) {
    	Iterator<Presupuesto> presupuestos = this.getPresupuestos().iterator();
    	Presupuesto res = null;
    	while(presupuestos.hasNext()) {
    		Presupuesto presupuesto = presupuestos.next();
    		if (presupuesto.getSolicitudOrigen().equals(solicitud) /*&& presupuesto.getEstado()==EstadoPresupuesto.aceptado*/) {
    			res = presupuesto;
    		}
    	}
    	return res;
    }
}