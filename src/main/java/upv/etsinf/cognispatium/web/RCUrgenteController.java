package upv.etsinf.cognispatium.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
//import net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Simple;  /*Si quitas el comentario da error*/
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleAdminManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleRespuestaManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private SimpleConsultaUrgenteManager simpleConsultaUrgenteManager;
    
    @Autowired
    private SimpleAdminManager simpleAdminManager;
    
    @Autowired
	private SimpleRespuestaManager respuestaManager;
    
    @Autowired
    private SimpleProfesionalManager simpleProfesionalManager;
    
    
    
    @RequestMapping("/responderconsultaurgente.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,@ModelAttribute ConsultaUrgente consultaUrgente)
			throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		 Map <String, Object> consultaUrg = new HashMap<String, Object>();
		consultaUrgente = simpleConsultaUrgenteManager.getConsultaUrgentes().get(0);
		
		myModel.put("consultas", consultaUrgente);
		ModelAndView mav = new ModelAndView("ResponderConsultaUrgente", "model", myModel);
        mav.addObject("consultas", consultaUrgente);
		return mav;
    }

   @PostMapping("/ResponderConsultaUrgente.htm")
   protected ModelAndView onSubmit(@RequestParam Map<String,String> reqPar) throws Exception {
	   System.out.println("Hola");
		Respuesta respuesta = new Respuesta();
		respuesta.setDescripcion(reqPar.get("respuesta"));	
		respuesta.setConsultaOrigen(simpleConsultaManager.getConsultas().get(2));
		respuesta.setProfesionalOrigen(simpleProfesionalManager.getProfesionales().get(1));
		respuestaManager.addRespuesta(respuesta);
		return new ModelAndView("hello");

	}
   
   
	
	
    
}