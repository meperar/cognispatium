package upv.etsinf.cognispatium.service;

import static org.junit.Assert.*;

import upv.etsinf.cognispatium.repository.InMemoryUsuarioDao;
import upv.etsinf.cognispatium.repository.UsuarioDao;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import upv.etsinf.cognispatium.domain.Usuario;

public class SimpleUsuarioManagerTests {

    private SimpleUsuarioManager usuarioManager;
    
    
    
    private List<Usuario> usuarios;
    
    private static int USUARIOS_COUNT = 2;
    
    private static String HELEN_NAME = "Helen";
    private static String HELEN_SURNAME = "Chair";
    
    private static String PAUL_NAME = "Paul";
    private static String PAUL_SURNAME = "Table"; 
    
    @Before
    public void setUp() throws Exception {
    	usuarioManager = new SimpleUsuarioManager();
    	usuarios = new ArrayList<Usuario>();
        
        // stub up a list of products
    	Usuario usuario = new Usuario();
    	usuario.setApellidos("Chair");
    	usuario.setNombre(HELEN_NAME);
    	usuarios.add(usuario);
        
    	usuario = new Usuario();
    	usuario.setApellidos("Table");
    	usuario.setNombre(PAUL_NAME);
    	usuarios.add(usuario);
        
    	UsuarioDao usuarioDao = new InMemoryUsuarioDao(usuarios);
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
        List<Usuario> usuarios = usuarioManager.getUsuarios();
        assertNotNull(usuarios);        
        assertEquals(USUARIOS_COUNT, usuarioManager.getUsuarios().size());
    
        Usuario usuario = usuarios.get(0);
        assertEquals(HELEN_NAME, usuario.getNombre());
        assertEquals(HELEN_SURNAME, usuario.getApellidos());
        
        usuario = usuarios.get(1);
        assertEquals(PAUL_NAME, usuario.getNombre());
        assertEquals(PAUL_SURNAME, usuario.getApellidos());     
    }
    
}



   

