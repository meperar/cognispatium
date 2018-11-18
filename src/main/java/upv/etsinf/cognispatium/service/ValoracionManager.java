package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Valoracion;

public interface ValoracionManager extends Serializable {
    
    public List<Valoracion> getValoraciones(); 

}