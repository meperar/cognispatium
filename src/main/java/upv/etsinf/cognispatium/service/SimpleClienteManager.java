package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.repository.ClienteDao;

@Component
public class SimpleClienteManager implements ClienteManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private ClienteDao clienteDao;

    public void setUsuarioDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<Cliente> getClientes() {
       return clienteDao.getClienteList();        
    }

    
    public Cliente getClientebyId(Integer clienteId) {
    	return  clienteDao.getClienteById(clienteId);
	}
    
}