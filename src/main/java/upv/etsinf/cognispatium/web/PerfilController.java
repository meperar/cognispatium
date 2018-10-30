package upv.etsinf.cognispatium.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleUsuarioManager;
import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;

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

@Controller
public class PerfilController {

	@Autowired
	private SimpleServicioManager servicioManager;

	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;

	@Autowired
	private SimpleClienteManager simpleClienteManager;
	
	@Autowired
	private SimpleProfesionalManager simpleProfesionalManager;
	
	@Autowired
	private SimplePresupuestoManager simplePresupuestoManager;
	
	@Autowired
	private SimpleUsuarioManager usuarioManager;
	
	@Autowired
	private SimpleConsultaManager consultaManager;

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/perfil.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		/*myModel.put("serviMed", this.servicioManager.getServiciosbyAmbito("Medicina"));
		String hello = "hola";
		myModel.put("hi", hello);*/
		
		Usuario usuario = usuarioManager.getUsuarios().get(1);
		myModel.put("usuario", usuario);
		
		ModelAndView mav = new ModelAndView("perfil","model",myModel);
		
		/*List<Consulta> listaConsultas = new ArrayList<Consulta>();
		//Map<String, Object> Mymodel = new HashMap<String, Object>();
		
		
		Usuario usuario = usuarioManager.getUsuarios().get(1);
		Boolean esProfesional = usuario instanceof Profesional;
		listaConsultas = consultaManager.getConsultas();
		
		for(Consulta consulta : listaConsultas) {
			String dniClienteConsulta = consulta.getClienteOrigen().getDni();
			String dniUsuario = usuario.getDni();
			
			if(!dniClienteConsulta.equals(dniUsuario)) listaConsultas.remove(consulta);
		}
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		
		myModel.put("usuario", usuario);
		myModel.put("esProfesional", esProfesional);
		myModel.put("consultas", listaConsultas);
		
		//mav.addObject("model", Mymodel);
		ModelAndView mav = new ModelAndView("perfil", "model", myModel);
		
		*/
		return mav;

	}
	
	/*@GetMapping("/barrasuperior.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar) throws Exception {

		
		List<Consulta> listaConsultas = new ArrayList<Consulta>();
		Map<String, Object> Mymodel = new HashMap<String, Object>();
		
		
		Usuario usuario = usuarioManager.getUsuarios().get(1);
		Boolean esProfesional = usuario instanceof Profesional;
		listaConsultas = consultaManager.getConsultas();
		
		for(Consulta consulta : listaConsultas) {
			String dniClienteConsulta = consulta.getClienteOrigen().getDni();
			String dniUsuario = usuario.getDni();
			
			if(!dniClienteConsulta.equals(dniUsuario)) listaConsultas.remove(consulta);
		}
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("listadoconsultas", "model", myModel);
		
		Mymodel.put("usuario", usuario);
		Mymodel.put("esProfesional", esProfesional);
		Mymodel.put("consultas", listaConsultas);
		
		mav.addObject("model", Mymodel);

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
}