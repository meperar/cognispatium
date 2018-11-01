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
import upv.etsinf.cognispatium.service.SimpleRegistroManager;
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
	private SimpleRegistroManager registroManager;
	
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
		
		Usuario usuario = usuarioManager.getUsuariobyId(3); // <-------- Aquí es donde cojo el usuario, Adri (Quita los comentarios cuando acabes).
		Registro registro = registroManager.getRegistrobyId(3);// <-------- Aquí cojo el registro de ese usuario.
		
		Boolean esProfesional = usuario instanceof Profesional;
		listaConTodasLasConsultas = consultaManager.getConsultas();
		int valoracion = 0;
		
		if(esProfesional) {
			valoracion = ((Profesional) usuario).getValoracion();
		}
		
		for(Consulta consulta : listaConTodasLasConsultas) {
			String dniClienteConsulta = consulta.getClienteOrigen().getDni();
			String dniUsuario = usuario.getDni();
			
			if(dniClienteConsulta.equals(dniUsuario)) listaConsultas.add(consulta);
		}
		
		myModel.put("usuario", usuario);
		myModel.put("registro", registro);
		boolModel.put("esProfesional", esProfesional);
		boolModel.put("errorUsername", false);
		myModel.put("consultas", listaConsultas);
		intModel.put("numConsultas", listaConsultas.size());
		intModel.put("valoracion", valoracion);

		ModelAndView mav = new ModelAndView("perfil","model",myModel);
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);
		
		
		return mav;

	}
	
	@PostMapping("/perfil.htm")
	protected ModelAndView editar(@RequestParam Map<String, String> reqPar) throws Exception {
		
		Usuario usuario = usuarioManager.getUsuariobyId(3);
		Registro registro = registroManager.getRegistrobyId(3);
		Boolean errorUsername = false;
		
		List<Registro> registrosBD = registroManager.getRegistroByUsername(reqPar.get("apodo"));
		
		
		if(registrosBD.size() == 0 || registro.getUsername().equals(reqPar.get("apodo"))) {
			
			usuario = usuarioManager.getUsuariobyId(3); // <-------- Aquí también.
	
			usuario.setNombre(reqPar.get("nombre"));
			usuario.setApellidos(reqPar.get("apellidos"));
			usuario.setEdad(Integer.parseInt(reqPar.get("edad")));
			usuario.setDni(reqPar.get("dni"));
			usuario.setEmail(reqPar.get("email"));
			usuario.setTelefono(Integer.parseInt(reqPar.get("telefono")));
			
			usuarioManager.addUsuario(usuario);
			
			
			registro = registroManager.getRegistrobyId(3); // <-------- Aquí también.
			
			registro.setUsername(reqPar.get("apodo"));
			registro.setContraseña(reqPar.get("contrasena"));
			
			registroManager.addRegistro(registro);
			
		}else {
			errorUsername = true;
		}
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Integer> intModel = new HashMap<String, Integer>();
		Map<String, Boolean> boolModel = new HashMap<String, Boolean>();
		
		List<Consulta> listaConsultas = new ArrayList<Consulta>();
		List<Consulta> listaConTodasLasConsultas = new ArrayList<Consulta>();
		

		Boolean esProfesional = usuario instanceof Profesional;
		
		
		listaConTodasLasConsultas = consultaManager.getConsultas();
		int valoracion = 0;
		
		if(esProfesional) {
			valoracion = ((Profesional) usuario).getValoracion();
		}
		
		for(Consulta consulta : listaConTodasLasConsultas) {
			String dniClienteConsulta = consulta.getClienteOrigen().getDni();
			String dniUsuario = usuario.getDni();
			
			if(dniClienteConsulta.equals(dniUsuario)) listaConsultas.add(consulta);
		}
		
		
		myModel.put("usuario", usuario);
		myModel.put("registro", registro);
		boolModel.put("esProfesional", esProfesional);
		boolModel.put("errorUsername", errorUsername);
		myModel.put("consultas", listaConsultas);
		intModel.put("numConsultas", listaConsultas.size());
		intModel.put("valoracion", valoracion);

		ModelAndView mav = new ModelAndView("perfil","model",myModel);
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);
		
		return mav;
	}
	
	
	public void setServicioManager(SimpleServicioManager servicioManager) {
		this.servicioManager = servicioManager;
	}

}