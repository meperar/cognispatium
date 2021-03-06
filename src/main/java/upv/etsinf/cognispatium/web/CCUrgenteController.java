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
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;

import java.io.Console;
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
		
		//*Crear consulta urgente
		DateTime fechaFinal = DateTime.now();
		LocalTime tiempoEspera = LocalTime.parse(reqPar.get("tiempoEspera"));
		fechaFinal = fechaFinal.plusMinutes(tiempoEspera.getMinute());
		fechaFinal = fechaFinal.plusHours(tiempoEspera.getHour());
		String titulo = reqPar.get("titulo");
		String descripcion = reqPar.get("descripcion");
		Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
		Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
		ConsultaUrgente consultaUrgente = new ConsultaUrgente();
		Cliente cliente = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());
		consultaUrgente.setDescripcion(descripcion);
		consultaUrgente.setTitulo(titulo);
		consultaUrgente.setFechaFin(fechaFinal);
		consultaUrgente.setServicioOrigen(servicioConsulta);
		consultaUrgente.setClienteOrigen(cliente);
		consultaUrgente.setEstado(EstadoConsulta.creada);
		servicioCUManager.addConsultaUrgente(consultaUrgente);
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("consultaUrgente", consultaUrgente);
		ModelAndView mav = new ModelAndView("pagoTarjeta", "model", myModel);
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