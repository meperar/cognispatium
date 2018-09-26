package upv.etsinf.cognispatium.domain;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfessionalTests {

    private Professional profesional;

    @Before
    public void setUp() throws Exception {
    	profesional = new Professional();
    }

    @Test
    public void testSetAndGetName() {
        String testName = "aName";
        assertNull(profesional.getName());
        profesional.setName(testName);
        assertEquals(testName, profesional.getName());
    }
    
    @Test
    public void testSetAndGetSurname() {
        String testSurname = "aSurname";
        assertNull(profesional.getSurname());
        profesional.setSurname(testSurname);
        assertEquals(testSurname, profesional.getSurname());
    }



}