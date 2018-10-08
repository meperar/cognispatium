package upv.etsinf.cognispatium.repository;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import upv.etsinf.cognispatium.domain.Usuario;


public class JPAProfessionalDaoTests {

    private ApplicationContext context;
    private UsuarioDao usuarioDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        usuarioDao = (UsuarioDao) context.getBean("usuarioDao");
    }

    @Test
    public void testGetProfessionalList() {
        List<Usuario> professionals = usuarioDao.getUsuarioList();
        assertEquals(professionals.size(), 3, 0);	   
    }

    @Test
    public void testSaveProfessional() {
        List<Usuario> professionals = usuarioDao.getUsuarioList();

        Usuario p = professionals.get(0);
        String surname = p.getApellidos();
        p.setApellidos("Teresa");
        usuarioDao.saveUsuario(p);

        List<Usuario> updatedProfessinals = usuarioDao.getUsuarioList();
        Usuario p2 = updatedProfessinals.get(0);
        assertEquals(p2.getApellidos(), "Teresa", 0);

        p2.setApellidos("Marta");
        usuarioDao.saveUsuario(p2);
    }
}