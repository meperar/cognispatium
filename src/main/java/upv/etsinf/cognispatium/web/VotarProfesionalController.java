package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Usuario;

@Controller
public class VotarProfesionalController {
	
	@GetMapping("/votarProfesional.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView("votarProfesional");
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		mav.addObject("model", myModel);
		
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
	
	@PostMapping("/votarProfesional.htm")
	protected ModelAndView votarProfesional(@RequestParam Map<String, String> reqPar) throws Exception {
		return new ModelAndView("redirect:/hello.htm");
	}
}
