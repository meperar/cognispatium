package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Servicio;

public interface ClienteDao {

    public List<Cliente> getClienteList();

    public void saveCliente(Cliente cliente);

 
	public Cliente getClienteById(Integer clienteId);
}