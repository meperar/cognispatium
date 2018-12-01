package upv.etsinf.cognispatium.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.EstadoMensaje;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleMensajeManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;

@Controller
public class BandejaMensajesController {
	Usuario usuario;

	@Autowired
	private SimpleMensajeManager mensajeManager;
	
	@Autowired
	private SimpleServicioManager servicioManager;


	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/bandejamensajes.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		if (WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

		}

		else {

			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			this.usuario = WebServiceController.usuarioRegistrado;
			List<Mensaje> listaMensajes = mensajeManager.getMensajesByUsuario(usuario);
			mensajes.put("mensajes", listaMensajes);
			mav.addObject("mensajes", mensajes);
			mav.addObject("mensajeTipo", "Todos");

		}
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	@GetMapping("/getTodos.htm")
	protected ModelAndView getTodos(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		mav.addObject("usR", this.usuario);
		List<Mensaje> listaMensajes = mensajeManager.getMensajesByUsuario(this.usuario);
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);
		mav.addObject("mensajeTipo", "Todos");
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	@GetMapping("/getNoLeidos.htm")
	protected ModelAndView getNoLeidos(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		mav.addObject("usR", this.usuario);
		List<Mensaje> listaMensajes = mensajeManager.getMensajesNoLeidosByUsuario(this.usuario);
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);
		mav.addObject("mensajeTipo", "No leidos");
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	@GetMapping("/getLeidos.htm")
	protected ModelAndView getLeidos(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		mav.addObject("usR", this.usuario);
		List<Mensaje> listaMensajes = mensajeManager.getMensajesLeidosByUsuario(this.usuario);
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);
		mav.addObject("mensajeTipo", "Leidos");
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	@GetMapping("/getEliminados.htm")
	protected ModelAndView getEliminados(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
		Map<String, Object> mensajes = new HashMap<String, Object>();
		mav.addObject("usR", this.usuario);
		List<Mensaje> listaMensajes = mensajeManager.getMensajesEliminadosbyUsuario(this.usuario);
		mensajes.put("mensajes", listaMensajes);
		mav.addObject("mensajes", mensajes);
		mav.addObject("mensajeTipo", "Eliminados");
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	@PostMapping("/bandejamensajes.htm")
	protected ModelAndView crearMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		Mensaje miMensaje = mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId")));
		String act = reqPar.get("action");
		if (act.equals("Ver")) {
			if (miMensaje.getEstado() == EstadoMensaje.noLeido) {
				miMensaje.setEstado(EstadoMensaje.leido);
				mensajeManager.addMensaje(miMensaje);

			}

			ModelAndView mav = new ModelAndView("respondermensajes", "model", myModel);
			myModel.put("mensaje", miMensaje);
			mav.addObject("usR", this.usuario);
			WebServiceController.listaAmbitos.forEach(a -> {

				mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
			});
			
			mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
			return mav;
		} else {			
			miMensaje.setEstado(EstadoMensaje.eliminado);
			mensajeManager.addMensaje(miMensaje);
			ModelAndView mav = new ModelAndView("bandejaMensajes", "model", myModel);
			Map<String, Object> mensajes = new HashMap<String, Object>();
			mav.addObject("usR", this.usuario);
			List<Mensaje> listaMensajes = mensajeManager.getMensajesByUsuario(this.usuario);
			mensajes.put("mensajes", listaMensajes);
			mav.addObject("mensajes", mensajes);
			mav.addObject("mensajeTipo", "Todos");
			WebServiceController.listaAmbitos.forEach(a -> {

				mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
			});
			
			mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
			return mav;
		}
	}

	@PostMapping("/respondermensajes.htm")
	protected ModelAndView guardarMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

		Mensaje mensaje = new Mensaje();
		mensaje.setDescripcion(reqPar.get("descripcion"));
		mensaje.setAsunto("RE:" + mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId"))).getAsunto());
		mensaje.setUsuarioOrigen(
				mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId"))).getUsuarioDestino());
		mensaje.setUsuarioDestino(
				mensajeManager.getMensajebyId(Integer.parseInt(reqPar.get("mensajeId"))).getUsuarioOrigen());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long millis = System.currentTimeMillis();
		java.util.Date date = new java.util.Date(millis);
		dateFormat.format(date);
		mensaje.setFecha(date);
		mensaje.setEstado(EstadoMensaje.noLeido);
		mensajeManager.addMensaje(mensaje);

		Map<String, Object> myModel = new HashMap<String, Object>();

		ModelAndView mav = new ModelAndView("hello", "model", myModel);

		if (WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

		}

		else {

			mav.addObject("usR", WebServiceController.usuarioRegistrado);

		}

		// Aquin añadían todos los ambitos/servicios
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}
}