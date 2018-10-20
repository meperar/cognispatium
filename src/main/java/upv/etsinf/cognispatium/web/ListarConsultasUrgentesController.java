package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleRespuestaManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;



@Controller
public class ListarConsultasUrgentesController {
	
	@Autowired
	private SimpleConsultaUrgenteManager servicioConsultaUrgenteManager;
	
	@Autowired
	private SimpleConsultaManager simpleConsultaManager;
	
	@Autowired
	private SimpleProfesionalManager simpleProfesionalManager;
	
	@Autowired
	private SimpleRespuestaManager respuestaManager;
	
	@RequestMapping("/listadoconsultasurgentes.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadoconsultasurgentes", "model", myModel);

		Map<String, Object> consultasUrgentes = new HashMap<String, Object>();
		List<ConsultaUrgente> listaConsultaUrgente = servicioConsultaUrgenteManager.getConsultaUrgentes();
		
		consultasUrgentes.put("consultasUrgentes", listaConsultaUrgente);
		mav.addObject("consultasUrgentes", consultasUrgentes);
		
		return mav;
	}
	
	@PostMapping("/listadoconsultasurgentes.htm")
	protected String responderConsultaUrgente(@RequestParam Map<String, String> reqPar) throws Exception {
		
		String id = reqPar.get("consultaUrgenteId");
		return "redirect:/responderconsultaurgente-"+id+".htm";
		
	}
	
	@RequestMapping("/responderconsultaurgente-{id}.htm")
	public ModelAndView indexResponder(@PathVariable int id) throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ConsultaUrgente miConsulta = servicioConsultaUrgenteManager.getConsultaUrgentebyId(id);
		
		myModel.put("consultaUrgente", miConsulta);
		
		ModelAndView mav = new ModelAndView("ResponderConsultaUrgente", "model", myModel);
		
		return mav;
	}
	
	@PostMapping("/responderconsultaurgente-{id}.htm")
	protected String postResponder(@RequestParam Map<String, String> reqPar, @PathVariable int id) throws Exception {
		Respuesta respuesta = new Respuesta();
		respuesta.setDescripcion(reqPar.get("respuesta"));	
		respuesta.setConsultaOrigen(simpleConsultaManager.getConsultaById(id));
		respuesta.setProfesionalOrigen(simpleProfesionalManager.getProfesionales().get(1));
		respuestaManager.addRespuesta(respuesta);
		
		return "redirect:/listadoconsultasurgentes.htm";
	}
	
	private static List<String> toStr(List<ConsultaUrgente> consultas) {
		List<String> res = new ArrayList<String>();
		Iterator<ConsultaUrgente> iterator = consultas.iterator();
		while (iterator.hasNext()) {
			res.add(iterator.next().getTitulo());
		}
		return res;
	}
}
