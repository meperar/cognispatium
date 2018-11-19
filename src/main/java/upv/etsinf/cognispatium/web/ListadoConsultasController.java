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

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleMensajeManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
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
public class ListadoConsultasController {

	@Autowired
	private SimpleServicioManager servicioManager;

	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;

	@Autowired
	private SimpleClienteManager simpleClienteManager;
	
	@Autowired
	private SimpleProfesionalManager simpleProfesionalManager;
	
	@Autowired
	private SimplePresupuestoManager simplePresupuestoManager;
	
	@Autowired
	private SimpleMensajeManager mensajeManager;
	
	@Autowired
	private SimpleConsultaUrgenteManager consultaUManager;
	

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/listadoconsultas.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		List<ConsultaUrgente> listaConsultas = new ArrayList<ConsultaUrgente>();
		Map<String, Object> Mymodel = new HashMap<String, Object>();
		if (reqPar.get("servicio") != null) {
			Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
			Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
			listaConsultas = consultaUManager.getConsultasbyService(servicioConsulta);
			Mymodel.put("serviciId", ServiceId);

		} else {
			listaConsultas = consultaUManager.getConsultaUrgentes();
		}
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadoconsultas", "model", myModel);
		List<Servicio> listaServicios = servicioManager.getServicios();
		Mymodel.put("consultas", listaConsultas);
		Mymodel.put("servicios", listaServicios);
		mav.addObject("model", Mymodel);
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

	
	@PostMapping("/listadoconsultas.htm")
	protected ModelAndView crearPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ConsultaUrgente miconsultaUrgente = consultaUManager.getConsultaUrgentebyId(Integer.parseInt(reqPar.get("consultaId")));
		
		ModelAndView mav = new ModelAndView("crearrespuestaaSolicitud", "model", myModel);
		
		
		myModel.put("consulta", miconsultaUrgente);
		
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

}