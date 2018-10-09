package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Cliente;

public class InMemoryClienteDao implements ClienteDao {

    private List<Cliente> clienteList;

    public InMemoryClienteDao(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void saveCliente(Cliente cliente) {
    }

}