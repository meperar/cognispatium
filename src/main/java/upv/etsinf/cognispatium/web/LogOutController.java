package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleServicioManager;

@Controller
public class LogOutController {

	@Autowired
	SimpleServicioManager servicioManager;
	
	
	public static Map<String, Object>  serviciosPorAmbito = null;
	
	
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping("/logout.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("hello");
		
		mav.setViewName("hello");
		mav.addObject("model", myModel);
		

		WebServiceController.serviciosPorAmbito.clear();
		final List<String> listaAmbitos = servicioManager.getAmbitos();
		final List<Servicio> listaServicios = servicioManager.getServicios();

		listaAmbitos.forEach(ambito -> {
			String amb = ambito;
			List<Servicio> lista = new ArrayList<Servicio>();
			listaServicios.forEach(serv -> {
				if (serv.getAmbito().equals(amb)) {
					lista.add(serv);
				}
			});
			WebServiceController.serviciosPorAmbito.put(ambito, lista);
		});

		mav.addObject("serviciosPorAmbito", WebServiceController.serviciosPorAmbito);
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});

		
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

		
		
		return mav;
	}	
}
