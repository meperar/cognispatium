package upv.etsinf.cognispatium.repository;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import upv.etsinf.cognispatium.domain.Cliente;


public class JPAClienteDaoTests {

    private ApplicationContext context;
    private ClienteDao clienteDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        clienteDao = (ClienteDao) context.getBean("clienteDao");
    }

    @Test
    public void testGetClienteList() {
        List<Cliente> clientes = clienteDao.getClienteList();
        assertEquals(clientes.size(), 3, 0);	   
    }

    @Test
    public void testSaveUsario() {
        List<Cliente> clientes = clienteDao.getClienteList();

        Cliente c = clientes.get(0);
        c.setApellidos("Teresa");
        clienteDao.saveCliente(c);

        List<Cliente> updatedClientes = clienteDao.getClienteList();
        Cliente c2 = updatedClientes.get(0);
        assertEquals(c2.getApellidos(), "Teresa", 0);

        c2.setApellidos("Marta");
        clienteDao.saveCliente(c);
    }
}