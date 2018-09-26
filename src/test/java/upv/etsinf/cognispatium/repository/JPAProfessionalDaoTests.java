package upv.etsinf.cognispatium.repository;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import upv.etsinf.cognispatium.domain.Professional;


public class JPAProfessionalDaoTests {

    private ApplicationContext context;
    private ProfessionalDao professionalDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        professionalDao = (ProfessionalDao) context.getBean("professionalDao");
    }

    @Test
    public void testGetProfessionalList() {
        List<Professional> professionals = professionalDao.getProfessionalList();
        assertEquals(professionals.size(), 3, 0);	   
    }

    @Test
    public void testSaveProfessional() {
        List<Professional> professionals = professionalDao.getProfessionalList();

        Professional p = professionals.get(0);
        String surname = p.getSurname();
        p.setSurname("Teresa");
        professionalDao.saveProfessional(p);

        List<Professional> updatedProfessinals = professionalDao.getProfessionalList();
        Professional p2 = updatedProfessinals.get(0);
        assertEquals(p2.getSurname(), "Teresa", 0);

        p2.setSurname("Marta");
        professionalDao.saveProfessional(p2);
    }
}