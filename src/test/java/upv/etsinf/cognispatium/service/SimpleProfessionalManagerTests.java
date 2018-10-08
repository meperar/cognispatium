package upv.etsinf.cognispatium.service;

import static org.junit.Assert.*;

import upv.etsinf.cognispatium.repository.InMemoryUsuarioDao;
import upv.etsinf.cognispatium.repository.UsuarioDao;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import upv.etsinf.cognispatium.domain.Usuario;

public class SimpleProfessionalManagerTests {

    private SimpleUsuarioManager usuarioManager;
    
    
    
    private List<Usuario> professionals;
    
    private static int PROFESSIONAL_COUNT = 2;
    
    private static String HELEN_NAME = "Helen";
    private static String HELEN_SURNAME = "Chair";
    
    private static String PAUL_NAME = "Paul";
    private static String PAUL_SURNAME = "Table"; 
    
    @Before
    public void setUp() throws Exception {
    	usuarioManager = new SimpleUsuarioManager();
    	professionals = new ArrayList<Usuario>();
        
        // stub up a list of products
    	Usuario professional = new Usuario();
    	professional.setApellidos("Chair");
    	professional.setNombre(HELEN_NAME);
    	professionals.add(professional);
        
    	professional = new Usuario();
    	professional.setApellidos("Table");
    	professional.setNombre(PAUL_NAME);
    	professionals.add(professional);
        
    	UsuarioDao usuarioDao = new InMemoryUsuarioDao(professionals);
    	usuarioManager.setUsuarioDao(usuarioDao);
        //productManager.setProducts(products);

    }
    
   
    @Test
    public void testGetProductsWithNoProducts() {
    	usuarioManager = new SimpleUsuarioManager();
    	usuarioManager.setUsuarioDao(new InMemoryUsuarioDao(null));
        assertNull(usuarioManager.getUsuarios());
    }
    @Test
    public void testGetProducts() {
        List<Usuario> professionals = usuarioManager.getUsuarios();
        assertNotNull(professionals);        
        assertEquals(PROFESSIONAL_COUNT, usuarioManager.getUsuarios().size());
    
        Usuario professional = professionals.get(0);
        assertEquals(HELEN_NAME, professional.getNombre());
        assertEquals(HELEN_SURNAME, professional.getApellidos());
        
        professional = professionals.get(1);
        assertEquals(PAUL_NAME, professional.getNombre());
        assertEquals(PAUL_SURNAME, professional.getApellidos());     
    }
    
}



   

