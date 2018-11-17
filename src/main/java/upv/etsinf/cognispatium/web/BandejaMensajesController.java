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
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleMensajeManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
public class BandejaMensajesController {

	@Autowired
	private SimpleMensajeManager mensajeManager;

	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;

	@Autowired
	private SimpleClienteManager simpleClienteManager;
	
	@Autowired
	private SimpleProfesionalManager simpleProfesionalManager;
	
	@Autowired
	private SimplePresupuestoManager simplePresupuestoManager;
	
	@Autowired
	private SimpleServicioManager servicioManager;

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	

	@RequestMapping("/bandejamensajes.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		List<Mensaje> listaMensajes;
		System.out.println(WebServiceController.usuarioRegistrado.getId());
		mav.addObject("prueba", WebServiceController.usuarioRegistrado.getId() );
		if(WebServiceController.usuarioRegistrado.getDTYPE().toString().length()==7) {
			
			listaMensajes = mensajeManager.getMensajesByClienteId(WebServiceController.usuarioRegistrado.getId());
		}
		
		else {
			
			listaMensajes = mensajeManager.getMensajesByProfId(WebServiceController.usuarioRegistrado.getId());
		}
		
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);
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
	
	@GetMapping("/bandejamensajes.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		List<Mensaje> listaMensajes;
		
		
		if(WebServiceController.usuarioRegistrado.getDTYPE().toString().length()==7) {
			
			listaMensajes = mensajeManager.getMensajesByClienteId(WebServiceController.usuarioRegistrado.getId());
		}
		
		else {
			
			listaMensajes = mensajeManager.getMensajesByProfId(WebServiceController.usuarioRegistrado.getId());
		}
		
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);
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

	
	@PostMapping("/bandejamensajes.htm")
	protected ModelAndView crearMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		Mensaje miMensaje = mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId")));
		
		ModelAndView mav = new ModelAndView("respondermensajes", "model", myModel);
		
		
		myModel.put("mensaje", miMensaje);
		
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
	
	@PostMapping("/respondermensajes.htm")
	protected ModelAndView guardarMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

		Mensaje mensaje = new Mensaje();
		mensaje.setDescripcion(reqPar.get("descripcion"));
		mensaje.setAsunto("RE:"+mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId"))).getAsunto());
		mensaje.setProfesional(mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId"))).getProfesional());
		mensaje.setCliente(mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId"))).getCliente());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long millis=System.currentTimeMillis();
		java.util.Date date=new java.util.Date(millis);
		dateFormat.format(date);
		mensaje.setFecha(date);
		mensajeManager.addMensaje(mensaje);
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("hello", "model", myModel);
		
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}
		
		Map<String, Object> med = new HashMap<String, Object>();
		med.put("serviMed", this.servicioManager.getServiciosbyAmbito("Medicina"));
		
		Map<String, Object> dep = new HashMap<String, Object>();
		dep.put("serviDep", this.servicioManager.getServiciosbyAmbito("Deporte"));
		
		Map<String, Object> cien = new HashMap<String, Object>();
		cien.put("serviCien", this.servicioManager.getServiciosbyAmbito("Ciencia"));
		
		Map<String, Object> tec = new HashMap<String, Object>();
		tec.put("serviTec", this.servicioManager.getServiciosbyAmbito("Técnicos"));
		
		Map<String, Object> leg = new HashMap<String, Object>();
		leg.put("serviLeg", this.servicioManager.getServiciosbyAmbito("Legislación"));
		
		Map<String, Object> artm = new HashMap<String, Object>();
		artm.put("serviArtM", this.servicioManager.getServiciosbyAmbito("Artes Marciales"));
		
		Map<String, Object> cuiho = new HashMap<String, Object>();
		cuiho.put("serviCuiHo", this.servicioManager.getServiciosbyAmbito("Cuidados del Hogar"));
		
		Map<String, Object> art = new HashMap<String, Object>();
		art.put("serviArt", this.servicioManager.getServiciosbyAmbito("Arte"));
		
		Map<String, Object> idio = new HashMap<String, Object>();
		idio.put("serviIdio", this.servicioManager.getServiciosbyAmbito("Idiomas"));
		
		Map<String, Object> est = new HashMap<String, Object>();
		est.put("serviEst", this.servicioManager.getServiciosbyAmbito("Estética"));
		
		mav.addObject("dep", dep);
		mav.addObject("cien", cien);
		mav.addObject("tec", tec);
		mav.addObject("leg", leg);
		mav.addObject("artm", artm);
		mav.addObject("cuiho", cuiho);
		mav.addObject("art", art);
		mav.addObject("idio", idio);
		mav.addObject("est", est);
		mav.addObject("med", med);
				
		return mav;

		
	}
}