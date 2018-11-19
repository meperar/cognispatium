package upv.etsinf.cognispatium.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Mensaje;
//import net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Simple;  /*Si quitas el comentario da error*/
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleAdminManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleMensajeManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleRespuestaManager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    
    
    @Autowired
    private SimpleMensajeManager mensajeManager;
    
    
    
    @GetMapping("/responderconsultaurgente.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> reqPar)
			throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		 Map <String, Object> consultaUrg = new HashMap<String, Object>();
		 Integer consultaId = Integer.parseInt(reqPar.get("consultaId"));
		 ConsultaUrgente consultaUrgente = simpleConsultaUrgenteManager.getConsultaUrgentebyId(consultaId);
		myModel.put("consultas", consultaUrgente);
		ModelAndView mav = new ModelAndView("ResponderConsultaUrgente", "model", myModel);
        mav.addObject("consultas", consultaUrgente);
        if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}
        WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		return mav;
    }

   @PostMapping("/responderconsultaurgente.htm")
   protected ModelAndView onSubmit(@RequestParam Map<String,String> reqPar) throws Exception {
	    System.out.println("onSubmit()");
		Respuesta respuesta = new Respuesta();
		Integer consultaId = Integer.parseInt(reqPar.get("consultaId"));
		ConsultaUrgente consultaUrgente = simpleConsultaUrgenteManager.getConsultaUrgentebyId(consultaId);
		respuesta.setDescripcion(reqPar.get("respuesta"));	
		respuesta.setConsultaOrigen(consultaUrgente);
		respuesta.setProfesionalOrigen(simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId()));
		respuestaManager.addRespuesta(respuesta);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setDescripcion(reqPar.get("respuesta"));
		mensaje.setAsunto("Respuesta a su Consulta urgente:" + consultaUrgente.getTitulo() );
		mensaje.setUsuarioOrigen(simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId()));
		mensaje.setUsuarioDestino(consultaUrgente.getClienteOrigen());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long millis=System.currentTimeMillis();
		java.util.Date date=new java.util.Date(millis);
		dateFormat.format(date);
		mensaje.setFecha(date);
		mensajeManager.addMensaje(mensaje);
		
		ModelAndView mav = new ModelAndView("hello");
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}
		ModelAndView mav2 =  new ModelAndView("hello");
		WebServiceController.listaAmbitos.forEach(a -> {

			mav2.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		return mav2;

	}
	
	
    
}