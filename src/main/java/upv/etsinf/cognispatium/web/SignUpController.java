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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Registro;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleRegistroManager;
import upv.etsinf.cognispatium.service.SimpleUsuarioManager;



@Controller
public class SignUpController {
	
	private Cliente nuevoCliente;
	private Profesional nuevoProf;
	private Usuario nuevoUsuario;
	private Registro nuevoRegistro;
	
	@Autowired
	private SimpleUsuarioManager usrMng;
	
	@Autowired
	private SimpleRegistroManager regMng;
	
	@Autowired
	private SimpleClienteManager cliMng;
	
	@Autowired
	private SimpleProfesionalManager profMng;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/usersignup.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String now = (new Date()).toString();
		logger.info("Returning hello view with " + now);

		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("myString", now);
		
		ModelAndView mav = new ModelAndView("usersignup", "myString", myMap);	
		
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}
		
		return mav;
	}
	
	@PostMapping("/usersignup.htm")
	protected ModelAndView Registrarse(@RequestParam Map<String, String> reqPar) throws Exception {
		String err = "";
		if(regMng.getRegistrobyUN(reqPar.get("username")).size() == 0) {
			nuevoCliente = new Cliente();
			nuevoProf = new Profesional();
			nuevoRegistro = new Registro();

			String cli = "cliente";
			String prof = "profesional";

			if(reqPar.get("rol").equals(cli)) {
				nuevoCliente.setNombre(reqPar.get("nombre"));
				nuevoCliente.setEmail(reqPar.get("email"));
				nuevoCliente.setApellidos(reqPar.get("apellido"));
				nuevoCliente.setDni(reqPar.get("dninif"));
				nuevoCliente.setTelefono(Integer.parseInt(reqPar.get("tlf")));
				nuevoCliente.setDTYPE(reqPar.get("rol"));

				nuevoRegistro.setUsuario(nuevoCliente);
			} else if (reqPar.get("rol").equals(prof)) {
				nuevoProf.setNombre(reqPar.get("nombre"));
				nuevoProf.setEmail(reqPar.get("email"));
				nuevoProf.setApellidos(reqPar.get("apellido"));
				nuevoProf.setDni(reqPar.get("dninif"));
				nuevoProf.setTelefono(Integer.parseInt(reqPar.get("tlf")));
				nuevoProf.setDTYPE(reqPar.get("rol"));

				nuevoRegistro.setUsuario(nuevoProf);
			}

			nuevoRegistro.setContraseña(reqPar.get("password"));
			nuevoRegistro.setUsername(reqPar.get("username"));

			regMng.addRegistro(nuevoRegistro);

			return new ModelAndView("hello");
		} else {
			err = "Nombre de usuario no disponible, pruebe con otro.";
			Map<String, Object> myMap = new HashMap<String, Object>();
			myMap.put("err", err);
			
			ModelAndView mav = new ModelAndView("usersignup", "err", myMap);
			if(WebServiceController.usuarioRegistrado == null) {
				Usuario userAux = new Usuario();
				
				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);
				
			}
			
			else {
				
				mav.addObject("usR", WebServiceController.usuarioRegistrado);
				
			}
			return mav;
		}
		
	}
	
	
}