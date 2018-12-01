package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;

@Controller
public class CSPresupuestoController {
	
	

	@Autowired
	private SimpleServicioManager servicioManager;

	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;
	
	
	@Autowired
	private SimpleClienteManager simpleClienteManager;
	
	
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping("/crearsolicitudpresupuesto.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("crearsolicitudpresupuesto", "model", myModel);
		
		Map< String, Object > servicios = new HashMap<String, Object>();
		 Map< String, Object > serviciosPorAmbito = new HashMap<String, Object>();
		 List<String> listaAmbitos = servicioManager.getAmbitos();
		List<Servicio> listaServicios = servicioManager.getServicios();
		
		listaAmbitos.forEach(ambito-> {
			String amb = ambito;
			List<Servicio> lista = new ArrayList<Servicio>();
			listaServicios.forEach(serv->{
				if(serv.getAmbito().equals(amb)) {
					lista.add(serv);
				}
			});
			serviciosPorAmbito.put(ambito, lista);
		});
		
		servicios.put("ambitos", listaAmbitos);
		servicios.put("serviciosxambitos", serviciosPorAmbito);
       mav.addObject("servicios", servicios);
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
       
       mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
       return mav;
		
	}
	
	@PostMapping("/crearsolicitudpresupuesto.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String,String> reqPar) throws Exception {	
		
		String titulo = reqPar.get("titulo");
		String descripcion = reqPar.get("descripcion");
		Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
		Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
		Solicitud solicitud = new Solicitud();
		Cliente cliente = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());
		solicitud.setDescripcion(descripcion);
		solicitud.setTitulo(titulo);
		solicitud.setServicioOrigen(servicioConsulta);
		solicitud.setClienteOrigen(cliente);
		solicitud.setEstado(EstadoSolicitud.creada);
		solicitud.setFechaCreacion(DateTime.now().toDate());
		servicioSolicitudManager.addSolicitud(solicitud);
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
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}
	
	
}