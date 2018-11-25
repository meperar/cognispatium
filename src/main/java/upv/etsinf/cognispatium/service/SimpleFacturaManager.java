package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Factura;
import upv.etsinf.cognispatium.repository.FacturaDao;

@Component
public class SimpleFacturaManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private FacturaDao facturaDao;

    public void setFacturaDao(FacturaDao facturaDao) {
        this.facturaDao = facturaDao;
    }

    public List<Factura> getFacturas() {
       return facturaDao.getFacturaList();        
    }

    public void addFactura(Factura factura) {
		
    	facturaDao.saveFactura(factura);
	}
    
  
}