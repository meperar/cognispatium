package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.service.UsuarioManager;

import upv.etsinf.cognispatium.service.ClienteManager;

import upv.etsinf.cognispatium.service.AdminManager;

import upv.etsinf.cognispatium.service.ProfesionalManager;

@Controller
public class WebServiceController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private ClienteManager clienteManager;
	
	@Autowired
	private AdminManager adminManager;
	
	@Autowired
	private ProfesionalManager profesionalManager;

	@RequestMapping(value = "/hello.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String now = (new Date()).toString();
		logger.info("Returning hello view with " + now);

		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);
		myModel.put("usuarios", this.profesionalManager.getProfesionales());

		return new ModelAndView("hello", "model", myModel);

	}

	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	public void setClienteManager(ClienteManager clienteManager) {
		this.clienteManager = clienteManager;
	}
}