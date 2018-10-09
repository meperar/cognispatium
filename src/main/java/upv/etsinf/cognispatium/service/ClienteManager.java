package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Cliente;

public interface ClienteManager extends Serializable {
    
    public List<Cliente> getClientes(); 

}