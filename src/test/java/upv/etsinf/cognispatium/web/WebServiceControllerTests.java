package upv.etsinf.cognispatium.web;

import java.util.Map;

import static org.junit.Assert.*;
import java.util.ArrayList;

import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.repository.InMemoryUsuarioDao;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.service.SimpleUsuarioManager;

public class WebServiceControllerTests {
    @Test
    public void testHandleRequestView() throws Exception{		
        WebServiceController controller = new WebServiceController();
        
        SimpleUsuarioManager spu = new SimpleUsuarioManager();
        spu.setUsuarioDao(new InMemoryUsuarioDao(new ArrayList<Usuario>()));
        controller.setProfessionalManager(spu);
        ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        @SuppressWarnings("unchecked")
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);

    }

}