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
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Registro;
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleRegistroManager;
import upv.etsinf.cognispatium.service.SimpleRespuestaManager;
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
	
	@Autowired
	private SimpleProfesionalManager profManager;
	
	@Autowired
	private SimpleRespuestaManager resManager;
	
	@Autowired
	private SimpleClienteManager cliManager;
	
	@Autowired
	private SimplePresupuestoManager preManager;
	
	@Autowired
	private SimpleConsultaUrgenteManager CUManager;

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
		
		Usuario usuario = WebServiceController.usuarioRegistrado; 
		Registro registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0); 
		
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
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);
		
		
		return mav;

	}
	
	@PostMapping("/perfil.htm")
	protected ModelAndView editar(@RequestParam Map<String, String> reqPar) throws Exception {
		if(reqPar.get("usridE") != null ) {
			
			Profesional profE = new Profesional();
			Cliente cliE = new Cliente();
			
			
			List<Consulta> listaCe = new ArrayList<Consulta>();
			List<Consulta> listaTodasE = new ArrayList<Consulta>();
			
			List<Respuesta> listaRe = new ArrayList<Respuesta>();
			List<Respuesta> listaTodasRe = new ArrayList<Respuesta>();
			
			List<Presupuesto> listaPe = new ArrayList<Presupuesto>();
			List<Presupuesto> listaTodasPe = new ArrayList<Presupuesto>();
			
			Usuario usuEl = usuarioManager.getUsuariobyId(Integer.parseInt(reqPar.get("usridE")));
			Registro regEl = registroManager.getRegistrobyUsuario(usuEl.getId()).get(0); 
			
			
			Boolean esPe = usuEl instanceof Profesional;
			listaTodasE = consultaManager.getConsultas();
			listaTodasRe = resManager.getRespuestas();
			int valoracion = 0;
			
			if(esPe) {
				valoracion = ((Profesional) usuEl).getValoracion();
				
				profE.setValoracion(valoracion);
				profE.setId(usuEl.getId());
			} else {
				cliE.setId(usuEl.getId());
			}
			
			//Obtener consultas urgentes no resueltas
			for(Consulta consulta : listaTodasE) {
				if(!esPe) {
				Boolean esUr = consulta instanceof ConsultaUrgente;
				
				String dniClienteConsulta = consulta.getClienteOrigen().getDni();
				String dniUsuario = usuEl.getDni();
				
				if(esUr && ((ConsultaUrgente) consulta).getEstado() != EstadoConsulta.resuelta ) {
					if(dniClienteConsulta.equals(dniUsuario)) listaCe.add(consulta);
				}
				}
					
			}
			
			//Obtener respuestas a consultas urgentes no resueltas
			for(Respuesta respuesta : listaTodasRe) {
				if(esPe) {		
					Consulta CR = respuesta.getConsultaOrigen();
					Boolean esRUR = CR instanceof ConsultaUrgente;
					int idProf = respuesta.getProfesionalOrigen().getId();
					int idUsu = usuEl.getId();
					if(esRUR && ((ConsultaUrgente) CR).getEstado() != EstadoConsulta.resuelta ) {
						if(idProf == (idUsu)) listaRe.add(respuesta);
					}	
				}
				
			}
						
			//Obtener presupuestos a solicitudes
			for(Presupuesto presupuesto : listaTodasPe) {
				if(esPe) {
					int idProff = presupuesto.getProfesionalOrigen().getId();
					int idUsuu = usuEl.getId();
					if(idProff == idUsuu) listaPe.add(presupuesto);
				}
			}
			
			
			
			//Eliminar el registro, el usuario, la información de profesional si esPe (la de cliente si no) y las consultas de este.		
			if(esPe) {
				for(Respuesta respuesta: listaRe) {
					resManager.dropRes(respuesta);
				}
				
				for(Presupuesto presupuesto: listaPe) {
					preManager.dropPres(presupuesto);
				}
				
				profManager.dropProf(profE);
			}
			else {
				for(Consulta consulta: listaCe) {
					consultaManager.dropCons(consulta);
				}
				
				cliManager.dropCli(cliE);
			}
			
			registroManager.dropReg(regEl);
			usuarioManager.dropUser(usuEl);
			
			ModelAndView mav = new ModelAndView("hello");
			
			if(WebServiceController.usuarioRegistrado != null) {
				Usuario userAux = new Usuario();

				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);

			}

			else {

				mav.addObject("usR", WebServiceController.usuarioRegistrado);

			}
			return mav;
			
			
		} else {
		
			Usuario usuario = WebServiceController.usuarioRegistrado; 
			Registro registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0);
			Boolean errorUsername = false;
			
			List<Registro> registrosBD = registroManager.getRegistrobyUN(reqPar.get("apodo"));
			
			
			if(registrosBD.size() == 0 || registro.getUsername().equals(reqPar.get("apodo"))) {
				
				usuario = WebServiceController.usuarioRegistrado; 
		
				usuario.setNombre(reqPar.get("nombre"));
				usuario.setApellidos(reqPar.get("apellidos"));
				usuario.setEdad(Integer.parseInt(reqPar.get("edad")));
				usuario.setDni(reqPar.get("dni"));
				usuario.setEmail(reqPar.get("email"));
				usuario.setTelefono(Integer.parseInt(reqPar.get("telefono")));
				
				usuarioManager.addUsuario(usuario);
				
				
				registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0);
				
				registro.setUsername(reqPar.get("apodo"));
				registro.setContraseña(reqPar.get("contrasena"));
				
				registroManager.addRegistro(registro);
				
			}else {
				errorUsername = true;
			}
			
			Map<String, Object> myModel = new HashMap<String, Object>();
			Map<String, Integer> intModel = new HashMap<String, Integer>();
			Map<String, Boolean> boolModel = new HashMap<String, Boolean>();
			
			List<Servicio> listaServicios = new ArrayList<Servicio>();
			
	
			Boolean esProfesional = usuario instanceof Profesional;
			
			
			
			int valoracion = 0;
			
			if(esProfesional) {
				valoracion = ((Profesional) usuario).getValoracion();
				
				listaServicios = servicioManager.getServicioByProfesional(usuario.getId());
				
			}
			
			
			
			
			myModel.put("usuario", usuario);
			myModel.put("registro", registro);
			boolModel.put("esProfesional", esProfesional);
			boolModel.put("errorUsername", errorUsername);
			myModel.put("servicios", listaServicios);
			intModel.put("numServicios", listaServicios.size());
			intModel.put("valoracion", valoracion);
	
			ModelAndView mav = new ModelAndView("perfil","model",myModel);
			if(WebServiceController.usuarioRegistrado == null) {
				Usuario userAux = new Usuario();
				
				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);
				
			}
			
			else {
				
				mav.addObject("usR", WebServiceController.usuarioRegistrado);
				
			}
			mav.addObject("intModel", intModel);
			mav.addObject("boolModel", boolModel);
			
			return mav;
		}
	}
	
	
	public void setServicioManager(SimpleServicioManager servicioManager) {
		this.servicioManager = servicioManager;
	}

}