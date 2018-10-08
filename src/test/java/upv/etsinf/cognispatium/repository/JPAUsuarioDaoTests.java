package upv.etsinf.cognispatium.repository;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import upv.etsinf.cognispatium.domain.Usuario;


public class JPAUsuarioDaoTests {

    private ApplicationContext context;
    private UsuarioDao usuarioDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        usuarioDao = (UsuarioDao) context.getBean("usuarioDao");
    }

    @Test
    public void testGetUsuarioList() {
        List<Usuario> usuarios = usuarioDao.getUsuarioList();
        assertEquals(usuarios.size(), 3, 0);	   
    }

    @Test
    public void testSaveUsario() {
        List<Usuario> usuarios = usuarioDao.getUsuarioList();

        Usuario u = usuarios.get(0);
        String surname = u.getApellidos();
        u.setApellidos("Teresa");
        usuarioDao.saveUsuario(u);

        List<Usuario> updatedUsuarios = usuarioDao.getUsuarioList();
        Usuario u2 = updatedUsuarios.get(0);
        assertEquals(u2.getApellidos(), "Teresa", 0);

        u2.setApellidos("Marta");
        usuarioDao.saveUsuario(u2);
    }
}