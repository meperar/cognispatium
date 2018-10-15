package upv.etsinf.cognispatium.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Consulta;
//import net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Simple;  /*Si quitas el comentario da error*/
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Controller
public class RCUrgenteController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private SimpleConsultaManager simpleConsultaManager;
    
    @Autowired
    private SimpleProfesionalManager simpleProfesionalManager;
    
    @RequestMapping("/responderconsultaurgente.htm")
    public String RespuestaCUForm(Model model) {
       // model.addAttribute("product", new Product());
    	System.out.println("Hola RCUrgenteController RespuestaCUForm");
    	this.crearRespuesta();
        return "ResponderConsultaUrgente";
    }
    
	/*@PostMapping("/responderconsultaurgente.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String,String> reqPar) throws Exception {
		System.out.println("Hola RCUrgenteController onSubmit");
		return new ModelAndView("hello");
	}*/
    
    private void crearRespuesta() {
    	System.out.println("Hola RCUrgenteController crearRespuesta");
		Respuesta respuesta = new Respuesta();
		respuesta.setDescripcion("descripción");
		//System.out.println("1");
		simpleConsultaManager.getConsultas();
		//System.out.println(listCons.toString());
		//respuesta.setConsultaOrigen(simpleConsultaManager.getConsultas().get(2));
		//System.out.println("2");
		respuesta.setProfesionalOrigen(simpleProfesionalManager.getProfesionales().get(1));
		//System.out.println("3");
	}
	
	
    
}