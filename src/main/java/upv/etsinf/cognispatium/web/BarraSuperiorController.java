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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleServicioManager;

@Controller
public class BarraSuperiorController {

	@Autowired
	private SimpleServicioManager servicioManager;
	

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/barrasuperior.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		myModel.put("serviMed", this.servicioManager.getServiciosbyAmbito("Medicina"));
		String hello = "hola";
		myModel.put("hi", hello);
		
		ModelAndView mav = new ModelAndView("barrasuperior","model",myModel);
		
		
		List<String> listaAmbitosAux = servicioManager.getAmbitos();
		List<Servicio> listaServicios = servicioManager.getServicios();
		Map<String, Object> serviciosPorAmbitoAux = new HashMap<String, Object>();
		

		for(String a : listaAmbitosAux) {
			List<Servicio> lista = new ArrayList<Servicio>();
			
			for(Servicio s : listaServicios) {
				if(s.getAmbito().equals(a)) {
					lista.add(s);
				}
			}
			serviciosPorAmbitoAux.put(a, lista);
		}
		
		mav.addObject("serviciosXAmbito", serviciosPorAmbitoAux);
		
		/*WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});*/
		
		return mav;

	}
	
	/*@GetMapping("/barrasuperior.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		
		if (reqPar.get("servicio") != null) {
			Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
			Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
		} 
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadoservicios", "model", myModel);
		Map<String, Object> servicios = new HashMap<String, Object>();
		List<Servicio> listaServicios = servicioManager.getServicios();
		servicios.put("servicios", listaServicios);
		mav.addObject("servicios", servicios);

		return mav;
	}*/
	
	public void setServicioManager(SimpleServicioManager servicioManager) {
		this.servicioManager = servicioManager;
	}

	
	/*@PostMapping("/listadoprofesionales.htm")
	protected ModelAndView guardarPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {

		Presupuesto presupuesto = new Presupuesto();
		presupuesto.setDescripcion(reqPar.get("descripcion"));
		presupuesto.setPrecio(Integer.parseInt(reqPar.get("precio")));
		
		
		presupuesto.setSolicitudOrigen(servicioSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("solicitudId"))));
		
		presupuesto.setProfesionalOrigen(simpleProfesionalManager.getProfesionales().get(0));
		
		simplePresupuestoManager.addPresupuesto(presupuesto);
				
		return new ModelAndView("hello");

		
	}*/
	
	public static Map<String,Object> barraSuperior(SimpleServicioManager servicioManager){
		//Map<String, Object> myModel = new HashMap<String, Object>();
		
		/*myModel.put("serviMed", this.servicioManager.getServiciosbyAmbito("Medicina"));
		String hello = "hola";
		myModel.put("hi", hello);*/
		
		//ModelAndView mav = new ModelAndView("barrasuperior","model",myModel);
		
		
		List<String> listaAmbitosAux = servicioManager.getAmbitos();
		List<Servicio> listaServicios = servicioManager.getServicios();
		Map<String, Object> serviciosPorAmbitoAux = new HashMap<String, Object>();
		


		for(String a : listaAmbitosAux) {
			List<Servicio> lista = new ArrayList<Servicio>();
			
			for(Servicio s : listaServicios) {
				if(s.getAmbito().equals(a)) {
					lista.add(s);
				}
			}
			serviciosPorAmbitoAux.put(a, lista);
		}
		
		
		//mav.addObject("serviciosXAmbito", serviciosPorAmbitoAux);
		
		return serviciosPorAmbitoAux;
	}
}