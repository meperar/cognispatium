package upv.etsinf.cognispatium.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.service.SimpleServicioManager;

@Controller
public class GuiaDeUso {
	@Autowired
	private SimpleServicioManager servicioManager;
	
	@RequestMapping("/guiaDeUso.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("guiaDeUso");
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}
	
}
