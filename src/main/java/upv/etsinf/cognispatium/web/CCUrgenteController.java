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

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;

import java.io.Console;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

@Controller
public class CCUrgenteController {
	
	

	@Autowired
	private SimpleServicioManager servicioManager;

	@Autowired
	private SimpleConsultaUrgenteManager servicioCUManager;
	
	
	@Autowired
	private SimpleClienteManager simpleClienteManager;
	
	
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping("/crearconsultaurgente.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,@ModelAttribute ConsultaUrgente consultaUrgente)
			throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("crearconsultaurgente", "model", myModel);
		 Map< String, Object > servicios = new HashMap<String, Object>();
		List<Servicio> listaServicios = servicioManager.getServicios();
		
		servicios.put("servicios", listaServicios);
         
        mav.addObject("servicios", servicios);
		return mav;
		
	}

	protected Map referenceData(HttpServletRequest request) throws Exception{
		List<Servicio> listaServicios = servicioManager.getServicios();
		Map referenceData = new HashMap();
		Map<Integer, String> servicios = new LinkedHashMap<Integer, String>();
		listaServicios.forEach(servicio -> servicios.put(servicio.getId(), servicio.getNombre()));
		referenceData.put("servicios", servicios);
		return referenceData;
	}
	
	@PostMapping("/crearconsultaurgente.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String,String> reqPar) throws Exception {	
		
		
		DateTime fechaFinal = DateTime.now();
		LocalTime tiempoEspera = LocalTime.parse(reqPar.get("tiempoEspera"));
		fechaFinal = fechaFinal.plusMinutes(tiempoEspera.getMinute());
		fechaFinal = fechaFinal.plusHours(tiempoEspera.getHour());
		String titulo = reqPar.get("titulo");
		String descripcion = reqPar.get("descripcion");
		Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
		Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
		ConsultaUrgente consultaUrgente = new ConsultaUrgente();
		Cliente cliente = simpleClienteManager.getClientes().get(0);
		consultaUrgente.setDescripcion(descripcion);
		consultaUrgente.setTitulo(titulo);
		consultaUrgente.setFechaFin(fechaFinal);
		consultaUrgente.setServicioOrigen(servicioConsulta);
		consultaUrgente.setCreadoConsulta(null);
		consultaUrgente.setCreadoConsulta(cliente);
		consultaUrgente.setEstado(EstadoConsulta.CREADA);
		servicioCUManager.addConsultaUrgente(consultaUrgente);
		//System.out.println("Hola CCUrgenteController");

		return new ModelAndView("hello");
	}
	
	
}