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

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/bandejamensajes.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		List<Mensaje> listaMensajes = mensajeManager.getMensajes();
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);
		return mav;

	}
	
	@GetMapping("/bandejamensajes.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
		
			listaMensajes = mensajeManager.getMensajes();

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);

		return mav;
	}

	
	@PostMapping("/bandejamensajes.htm")
	protected ModelAndView crearMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		Mensaje miMensaje = mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId")));
		
		ModelAndView mav = new ModelAndView("respondermensajes", "model", myModel);
		
		
		myModel.put("mensaje", miMensaje);

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
				
		return new ModelAndView("hello");

		
	}
}