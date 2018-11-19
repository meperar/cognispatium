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
import upv.etsinf.cognispatium.domain.EstadoMensaje;
import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.ProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleMensajeManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

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
	
	@Autowired
	private SimpleMensajeManager mensajeManager;

	private String estadoObtenido;
	private String servicioObtenido;
	
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	// obtengo todos los estados de una solicitud;
	 
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
		
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}

		return mav;

	}

	@GetMapping("/listadosolicitudes.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

	    Map<String, Object> myModel = new HashMap<String, Object>();

	    

		List<Solicitud> listaSolicitudes = new ArrayList<Solicitud>();
		Map<String, Object> servicios = new HashMap<String, Object>();
		
		 servicioObtenido = reqPar.get("servicio");
		 if(servicioObtenido == "") { servicioObtenido = null;}
		 
		estadoObtenido =  reqPar.get("estado");
		if(estadoObtenido == "") { estadoObtenido = null; }
		
		
		 if (servicioObtenido != null ){
		     Integer ServiceId = Integer.parseInt(servicioObtenido);
             Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
             
		      if( estadoObtenido != null ) {
		         listaSolicitudes = servicioSolicitudManager.getSolicitudsbyService(servicioConsulta)
					.stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
				    .filter(sol -> (sol.getEstado()==EstadoSolicitud.valueOf(estadoObtenido)))
				    .collect(Collectors.toList());
		         servicios.put("serviciId", ServiceId);
		        
		     }
		      else {
	               listaSolicitudes = servicioSolicitudManager.getSolicitudsbyService(servicioConsulta)
	                      .stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
	                      .filter(sol -> !(sol.getEstado()==EstadoSolicitud.eliminada))
	                      .collect(Collectors.toList());
	                servicios.put("serviciId", ServiceId);	               
	             }
		 }
		 else {
		     if( estadoObtenido != null ) {
                 listaSolicitudes = servicioSolicitudManager.getSolicituds()
                    .stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
                    .filter(sol -> (sol.getEstado()==EstadoSolicitud.valueOf(estadoObtenido)))
                    .collect(Collectors.toList());                
             }	     
		     else {
		         listaSolicitudes = servicioSolicitudManager.getSolicituds()
					.stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
				    .filter(sol -> !(sol.getEstado()==EstadoSolicitud.eliminada))
				    .collect(Collectors.toList());
		     }
		}
	
		
		ModelAndView mav = new ModelAndView("listadosolicitudes", "model", myModel);
		mav.addObject("estadoObt",estadoObtenido);
		List<Servicio> listaServicios = servicioManager.getServicios();
		servicios.put("servicios", listaServicios);
		mav.addObject("servicios", servicios);

		Map<String, Object> solicitudes = new HashMap<String, Object>();
		solicitudes.put("solicitudes", listaSolicitudes);
		mav.addObject("solicitudes", solicitudes);

		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
		}
		else {	
			mav.addObject("usR", WebServiceController.usuarioRegistrado);	
		}

		return mav;
	}

	
	@PostMapping("/listadosolicitudes.htm")
	protected ModelAndView crearPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		Solicitud miSolicitud = servicioSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("solicitudId")));
		
		ModelAndView mav = new ModelAndView("crearpresupuestoaSolicitud", "model", myModel);
		
		
		myModel.put("solicitud", miSolicitud);
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}

		return mav;
	}
	
	@PostMapping("/crearpresupuestoaSolicitud.htm")
	protected ModelAndView guardarPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {
		//Crear presupuesto
		Presupuesto presupuesto = new Presupuesto();
		presupuesto.setDescripcion(reqPar.get("descripcion"));
		presupuesto.setPrecio(Integer.parseInt(reqPar.get("precio")));
		presupuesto.setEstado(EstadoPresupuesto.propuesto);
		presupuesto.setFechaCreacion(DateTime.now().toDate());
		Solicitud solicitud = servicioSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("solicitudId")));
		presupuesto.setSolicitudOrigen(solicitud);
		
		// Añado el servicio al que doy presupuesto a la base de datos del profesional
		Profesional profesional = simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
		List<Servicio> serviciosProfesional = profesional.getServicios();
		serviciosProfesional.add(solicitud.getServicioOrigen());
		profesional.setServicios(serviciosProfesional);
		
		presupuesto.setProfesionalOrigen(profesional);
		if(solicitud.getEstado() == EstadoSolicitud.creada) {
		solicitud.setEstado(EstadoSolicitud.respondida);
		}
		
		simpleProfesionalManager.addProfesional(profesional);
		servicioSolicitudManager.addSolicitud(solicitud);
		simplePresupuestoManager.addPresupuesto(presupuesto);
		
		
		Mensaje mensaje = new Mensaje();
		mensaje.setDescripcion(reqPar.get("descripcion"));
		mensaje.setAsunto("Presupuesto para solicitud:" + solicitud.getTitulo() );
		mensaje.setUsuarioOrigen(simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId()));
		mensaje.setUsuarioDestino(solicitud.getClienteOrigen());
		mensaje.setEstado(EstadoMensaje.noLeido);
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
		return mav;

		
	}
}