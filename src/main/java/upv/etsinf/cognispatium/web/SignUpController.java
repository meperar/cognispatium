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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.service.UsuarioManager;

import upv.etsinf.cognispatium.service.ClienteManager;
import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.AdminManager;

import upv.etsinf.cognispatium.service.ProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;


@Controller
public class SignUpController {

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
	
	
}