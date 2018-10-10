package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Pago;

public interface PagoManager extends Serializable {
    
    public List<Pago> getPagos();
    
    public void addPago(Pago pago);

}
