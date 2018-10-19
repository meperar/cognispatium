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
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadoconsultasurgentes", "model", myModel);
		/*Map<String, Object> consultasUrgentes = new HashMap<String, Object>();
		List<ConsultaUrgente> listaConsultaUrgente = servicioConsultaUrgenteManager.getConsultaUrgentes();
		consultasUrgentes.put("servicios", listaConsultaUrgente);
		mav.addObject("servicios", consultasUrgentes);*/

		Map<String, Object> consultasUrgentes = new HashMap<String, Object>();
		List<ConsultaUrgente> listaConsultaUrgente = servicioConsultaUrgenteManager.getConsultaUrgentes();
		
		
		
		System.out.println("listaConsultaUrgente = " + toStr(listaConsultaUrgente));
		consultasUrgentes.put("consultasUrgentes", listaConsultaUrgente);
		mav.addObject("consultasUrgentes", consultasUrgentes);
		
		System.out.println("consultasUrgentes.get(consultasUrgentes) = " + consultasUrgentes.get("consultasUrgentes"));
		System.out.println("mav = " + mav.toString());
		return mav;
		//return new ModelAndView("listadoconsultasurgentes");
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
