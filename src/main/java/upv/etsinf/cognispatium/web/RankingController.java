package upv.etsinf.cognispatium.web;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
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
import upv.etsinf.cognispatium.web.dto.ProfesionalDto;

@Controller
public class RankingController {

	@Autowired
	private SimpleServicioManager servicioManager;
	
	@Autowired
	private SimpleProfesionalManager profesionalManager;	
	
	@Autowired
	private SimpleConsultaUrgenteManager consultaUManager;
	

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/ranking.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		//Profesional prof = simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
		List<Servicio> listaServicios = servicioManager.getServicios();
		List<Profesional> listaProfesional = new ArrayList<Profesional>();
		
		Integer ServiceId;
		Servicio servicioElegido;
		
		Map<String, Object> Mymodel = new HashMap<String, Object>();
		
		if (reqPar.get("serviceId") != null) {
			ServiceId = Integer.parseInt(reqPar.get("serviceId"));
			servicioElegido = servicioManager.getServiciobyId(ServiceId);
			listaProfesional = servicioElegido.getProfesionales();
			          
			Mymodel.put("serviciId", ServiceId);
			Mymodel.put("servicioElegido", servicioElegido);

		} else if (reqPar.get("servicio") != null) {
			ServiceId = Integer.parseInt(reqPar.get("servicio"));
			servicioElegido = servicioManager.getServiciobyId(ServiceId);
			listaProfesional = servicioElegido.getProfesionales();
			          
			Mymodel.put("serviciId", ServiceId);
			Mymodel.put("servicioElegido", servicioElegido);

		} else {
			
			for(Servicio s : listaServicios) {
				listaProfesional.addAll(s.getProfesionales());
	 		}
		}
		
		Collections.sort(listaProfesional, (a, b) -> a.getValoracionMedia() < b.getValoracionMedia() ? 1 : a.getValoracionMedia() == b.getValoracionMedia() ? 0 : -1);
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("ranking", "model", myModel);
		
		Mymodel.put("servicios", listaServicios);
		String encoding = "data:image/png;base64,";
		Base64.Encoder encoder = Base64.getEncoder();
		myModel.put("encoding", encoding);
		myModel.put("encoder", Base64.getEncoder());
		List<ProfesionalDto> profesionales = new ArrayList<ProfesionalDto>(); 
				listaProfesional.forEach(p->{
					ProfesionalDto prof= new ProfesionalDto(p.getNombre(),p.getApellidos(),p.getImagen()!=null?encoder.encodeToString(p.getImagen()):null, p.getValoracionMediaRedondeada());
					profesionales.add(prof);
				});
		Mymodel.put("profesionales", profesionales);
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
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

}