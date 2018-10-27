package upv.etsinf.cognispatium.domain;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClienteTests {

    private Cliente cliente;

    @Before
    public void setUp() throws Exception {
    	cliente = new Cliente();
    }

    @Test
    public void testSetAndGetName() {
        String testName = "aName";
        cliente.setNombre(testName);
        assertEquals(testName, cliente.getNombre());
    }
    
    @Test
    public void testSetAndGetSurname() {
        String testSurname = "aSurname";
        assertNull(cliente.getApellidos());
        cliente.setApellidos(testSurname);
        assertEquals(testSurname, cliente.getApellidos());
    }



}