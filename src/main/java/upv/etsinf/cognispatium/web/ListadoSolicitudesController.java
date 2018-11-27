package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.EstadoMensaje;
import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleMensajeManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;

@Controller
public class ListadoSolicitudesController {

	@Autowired
	private SimpleServicioManager servicioManager;

	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;
	
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
		//List<Servicio> listaServicios = servicioManager.getServicios();
		Profesional prof = simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
		System.out.println("PLS" + prof.getId());
		
		List<Servicio> listaServicios = prof.getServicios();
		
		servicios.put("servicios", listaServicios);
		mav.addObject("servicios", servicios);

		Map<String, Object> solicitudes = new HashMap<String, Object>();
		List<Solicitud> listaSolicitudes = new ArrayList<Solicitud>();
		for(Servicio s : listaServicios) {
			listaSolicitudes.addAll(servicioSolicitudManager.getSolicitudsbyService(s));
		}
		
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
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
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
		
		Profesional prof = simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
		List<Servicio> listaServicios = prof.getServicios();
		
		
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
	                      .filter(sol -> !(sol.getEstado()==EstadoSolicitud.eliminada) && !(sol.getEstado()==EstadoSolicitud.adjudicada) )
	                      .collect(Collectors.toList());
	                servicios.put("serviciId", ServiceId);	               
	             }
		 }
		 else {
		     if( estadoObtenido != null ) {
		    	 for(Servicio s : listaServicios) {
		 			listaSolicitudes.addAll(servicioSolicitudManager.getSolicitudsbyService(s));
		 		}
                listaSolicitudes.stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
                    .filter(sol -> (sol.getEstado()==EstadoSolicitud.valueOf(estadoObtenido)))
                    .collect(Collectors.toList());               
             }	     
		     else {
		    	 for(Servicio s : listaServicios) {
			 			listaSolicitudes.addAll(servicioSolicitudManager.getSolicitudsbyService(s));
			 		}
		         listaSolicitudes 
					.stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
				    .filter(sol -> !(sol.getEstado()==EstadoSolicitud.eliminada) && !(sol.getEstado()==EstadoSolicitud.adjudicada))
				    .collect(Collectors.toList());
		     }
		}
	
		
		ModelAndView mav = new ModelAndView("listadosolicitudes", "model", myModel);
		mav.addObject("estadoObt",estadoObtenido);
		
		
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
		/*WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});*/
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
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		return mav;
	}
	
	@PostMapping("/crearpresupuestoaSolicitud.htm")
	protected ModelAndView guardarPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {
		Solicitud solicitud = servicioSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("solicitudId")));
		Profesional profesional =simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
		//Crear presupuesto
		Presupuesto presupuesto = new Presupuesto();
		presupuesto.setDescripcion(reqPar.get("descripcion"));
		presupuesto.setPrecio(Integer.parseInt(reqPar.get("precio")));
		presupuesto.setEstado(EstadoPresupuesto.propuesto);
		presupuesto.setFechaCreacion(DateTime.now().toDate());
		presupuesto.setSolicitudOrigen(solicitud);	
		//Comprobar si la solicitud ya tiene respuestas
		presupuesto.setProfesionalOrigen(profesional);
		if(solicitud.getEstado() == EstadoSolicitud.creada) {
		solicitud.setEstado(EstadoSolicitud.respondida);
		}
		
		servicioSolicitudManager.addSolicitud(solicitud);
		simplePresupuestoManager.addPresupuesto(presupuesto);
		
		//Enviar mensaje de notificacion al cliente
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
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		return mav;

		
	}
}