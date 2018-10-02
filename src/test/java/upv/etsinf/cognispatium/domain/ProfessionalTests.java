package upv.etsinf.cognispatium.domain;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfessionalTests {

    private Usuario profesional;

    @Before
    public void setUp() throws Exception {
    	profesional = new Usuario();
    }

    @Test
    public void testSetAndGetName() {
        String testName = "aName";
        profesional.setNombre(testName);
        assertEquals(testName, profesional.getNombre());
    }
    
    @Test
    public void testSetAndGetSurname() {
        String testSurname = "aSurname";
        assertNull(profesional.getApellidos());
        profesional.setApellidos(testSurname);
        assertEquals(testSurname, profesional.getApellidos());
    }



}