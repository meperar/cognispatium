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
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;

import java.io.IOException;
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
public class ListadoSolicitudesController {

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

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/listadosolicitudes.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadosolicitudes", "model", myModel);
		Map<String, Object> servicios = new HashMap<String, Object>();
		List<Servicio> listaServicios = servicioManager.getServicios();
		servicios.put("servicios", listaServicios);
		mav.addObject("servicios", servicios);

		Map<String, Object> solicitudes = new HashMap<String, Object>();
		List<Solicitud> listaSolicitudes = servicioSolicitudManager.getSolicituds();
		solicitudes.put("solicitudes", listaSolicitudes);
		mav.addObject("solicitudes", solicitudes);

		return mav;

	}

	@GetMapping("/listadosolicitudes.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		List<Solicitud> listaSolicitudes = new ArrayList<Solicitud>();
		if (reqPar.get("servicio") != null) {
			Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
			Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
			listaSolicitudes = servicioSolicitudManager.getSolicitudsbyService(servicioConsulta);
		} else {
			listaSolicitudes = servicioSolicitudManager.getSolicituds();
		}
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadosolicitudes", "model", myModel);
		Map<String, Object> servicios = new HashMap<String, Object>();
		List<Servicio> listaServicios = servicioManager.getServicios();
		servicios.put("servicios", listaServicios);
		mav.addObject("servicios", servicios);

		Map<String, Object> solicitudes = new HashMap<String, Object>();
		solicitudes.put("solicitudes", listaSolicitudes);
		mav.addObject("solicitudes", solicitudes);

		return mav;
	}

	
	@PostMapping("/listadosolicitudes.htm")
	protected ModelAndView crearPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		Solicitud miSolicitud = servicioSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("solicitudId")));
		
		ModelAndView mav = new ModelAndView("crearpresupuestoaSolicitud", "model", myModel);
		
		
		myModel.put("solicitud", miSolicitud);

		return mav;
	}
	
	@PostMapping("/crearpresupuestoaSolicitud.htm")
	protected ModelAndView guardarPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {

		Presupuesto presupuesto = new Presupuesto();
		presupuesto.setDescripcion(reqPar.get("descripcion"));
		presupuesto.setPrecio(Integer.parseInt(reqPar.get("precio")));
		
		
		presupuesto.setSolicitudOrigen(servicioSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("solicitudId"))));
		
		presupuesto.setProfesionalOrigen(simpleProfesionalManager.getProfesionales().get(0));
		
		simplePresupuestoManager.addPresupuesto(presupuesto);
				
		return new ModelAndView("hello");

		
	}
}