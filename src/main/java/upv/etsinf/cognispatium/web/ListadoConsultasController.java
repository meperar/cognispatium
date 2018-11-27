package upv.etsinf.cognispatium.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;

@Controller
public class ListadoConsultasController {

	@Autowired
	private SimpleServicioManager servicioManager;
	
	@Autowired
	private SimpleProfesionalManager simpleProfesionalManager;	
	
	@Autowired
	private SimpleConsultaUrgenteManager consultaUManager;
	

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/listadoconsultas.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		Profesional prof = simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
		List<Servicio> listaServicios = prof.getServicios();
		
		List<ConsultaUrgente> listaConsultas = new ArrayList<ConsultaUrgente>();
		
		Map<String, Object> Mymodel = new HashMap<String, Object>();
		
		if (reqPar.get("servicio") != null) {
			Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
			Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
			listaConsultas =consultaUManager.getConsultasbyService(servicioConsulta)
			        .stream().sorted(Comparator.comparing(Consulta::getId).reversed())
                    .filter(sol -> (sol.getEstado()!=EstadoConsulta.cerrada))
                    .collect(Collectors.toList());
			          
			Mymodel.put("serviciId", ServiceId);

		} else {
			
			for(Servicio s : listaServicios) {
	 			listaConsultas.addAll(consultaUManager.getConsultasbyService(s));
	 		}
			
			/*listaConsultas
			        .stream().sorted(Comparator.comparing(Consulta::getId).reversed())
                    .filter(sol -> (sol.getEstado()!=EstadoConsulta.cerrada))
                    .collect(Collectors.toList());*/
		}
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadoconsultas", "model", myModel);
		
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