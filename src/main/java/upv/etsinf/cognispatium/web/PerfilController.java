package upv.etsinf.cognispatium.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleUsuarioManager;
import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Registro;
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
	private SimpleUsuarioManager usuarioManager;
	
	@Autowired
	private SimpleConsultaManager consultaManager;

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/perfil.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Integer> intModel = new HashMap<String, Integer>();
		Map<String, Boolean> boolModel = new HashMap<String, Boolean>();
		
		List<Consulta> listaConsultas = new ArrayList<Consulta>();
		List<Consulta> listaConTodasLasConsultas = new ArrayList<Consulta>();
		
		Usuario usuario = usuarioManager.getUsuarios().get(1); // <-------- Aquí es donde cojo el usuario, Adri (Quita el comentario cuando acabes).
		Boolean esProfesional = usuario instanceof Profesional;
		listaConTodasLasConsultas = consultaManager.getConsultas();
		
		for(Consulta consulta : listaConTodasLasConsultas) {
			String dniClienteConsulta = consulta.getClienteOrigen().getDni();
			String dniUsuario = usuario.getDni();
			
			if(dniClienteConsulta.equals(dniUsuario)) listaConsultas.add(consulta);
		}
		
		myModel.put("usuario", usuario);
		boolModel.put("esProfesional", esProfesional);
		myModel.put("consultas", listaConsultas);
		intModel.put("numConsultas", listaConsultas.size());

		ModelAndView mav = new ModelAndView("perfil","model",myModel);
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);
		
		
		return mav;

	}
	
	@PostMapping("/perfil.htm")
	protected ModelAndView editar(@RequestParam Map<String, String> reqPar) throws Exception {
		Usuario usuario = usuarioManager.getUsuariobyId(1);
		
		usuario.setDni(reqPar.get("dni"));
		
		usuarioManager.addUsuario(usuario);

		
		return new ModelAndView("hello");
	}
	
	
	public void setServicioManager(SimpleServicioManager servicioManager) {
		this.servicioManager = servicioManager;
	}

}