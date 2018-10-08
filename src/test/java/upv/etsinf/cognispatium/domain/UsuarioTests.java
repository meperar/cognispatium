package upv.etsinf.cognispatium.domain;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTests {

    private Usuario usuario;

    @Before
    public void setUp() throws Exception {
    	usuario = new Usuario();
    }

    @Test
    public void testSetAndGetName() {
        String testName = "aName";
        usuario.setNombre(testName);
        assertEquals(testName, usuario.getNombre());
    }
    
    @Test
    public void testSetAndGetSurname() {
        String testSurname = "aSurname";
        assertNull(usuario.getApellidos());
        usuario.setApellidos(testSurname);
        assertEquals(testSurname, usuario.getApellidos());
    }



}