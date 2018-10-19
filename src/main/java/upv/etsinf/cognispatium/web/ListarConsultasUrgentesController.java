package upv.etsinf.cognispatium.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;



@Controller
public class ListarConsultasUrgentesController {
	
	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;
	
	@Autowired
	private SimpleServicioManager servicioManager;
	
	@Autowired
	private SimpleConsultaUrgenteManager servicioConsultaUrgenteManager;
	
	@RequestMapping("/listadoconsultasurgentes.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadoconsultasurgentes", "model", myModel);
		/*Map<String, Object> consultasUrgentes = new HashMap<String, Object>();
		List<ConsultaUrgente> listaConsultaUrgente = servicioConsultaUrgenteManager.getConsultaUrgentes();
		consultasUrgentes.put("servicios", listaConsultaUrgente);
		mav.addObject("servicios", consultasUrgentes);*/

		Map<String, Object> consultasUrgentes = new HashMap<String, Object>();
		List<ConsultaUrgente> listaConsultaUrgente = servicioConsultaUrgenteManager.getConsultaUrgentes();
		consultasUrgentes.put("consultasUrgentes", listaConsultaUrgente);
		mav.addObject("consultasUrgentes", consultasUrgentes);
		
		return mav;
		//return new ModelAndView("listadoconsultasurgentes");
	}
}
