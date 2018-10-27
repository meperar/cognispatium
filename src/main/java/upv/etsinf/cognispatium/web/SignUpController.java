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

import upv.etsinf.cognispatium.domain.Registro;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleRegistroManager;
import upv.etsinf.cognispatium.service.SimpleUsuarioManager;



@Controller
public class SignUpController {
	
	
	private Usuario nuevoUsuario;
	
	
	private Registro nuevoRegistro;
	
	@Autowired
	private SimpleUsuarioManager usrMng;
	
	@Autowired
	private SimpleRegistroManager regMng;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/usersignup.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String now = (new Date()).toString();
		logger.info("Returning hello view with " + now);

		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("myString", now);
		
		ModelAndView mav = new ModelAndView("usersignup", "myString", myMap);
		
		return mav;
	}
	
	@PostMapping("/usersignup.htm")
	protected ModelAndView Registrarse(@RequestParam Map<String, String> reqPar) throws Exception {
		nuevoUsuario = new Usuario();
		
		nuevoUsuario.setNombre(reqPar.get("nombre"));
		nuevoUsuario.setEmail(reqPar.get("email"));
		nuevoUsuario.setApellidos(reqPar.get("apellido"));
		nuevoUsuario.setDni(reqPar.get("dninif"));
		nuevoUsuario.setTelefono(Integer.parseInt(reqPar.get("tlf")));
		nuevoUsuario.setDTYPE(reqPar.get("rol"));
		
		nuevoRegistro = new Registro();
		
		nuevoRegistro.setContraseña(reqPar.get("password"));
		nuevoRegistro.setUsername(reqPar.get("username"));
		nuevoRegistro.setUsuario(nuevoUsuario);
		
		
		
		regMng.addRegistro(nuevoRegistro);
		
		
		return new ModelAndView("hello");
	}
	
	
}