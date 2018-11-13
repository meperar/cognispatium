package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.domain.Valoracion;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleValoracionManager;

@Controller
public class VotarProfesionalController {
	
	@Autowired
	private SimpleValoracionManager valoracionManager;
	
	@Autowired
	private SimpleProfesionalManager profesionalManager;
	
	private Profesional profesional;
	private Cliente cliente;
	
	@GetMapping("/votarProfesional.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> reqPar)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView("votarProfesional");
		Map<String, Object> myModel = new HashMap<String, Object>();
		mav.addObject("model", myModel);
		
		Integer profesionalId = Integer.parseInt(reqPar.get("profesionalId"));
		//System.out.println(profesionalId);
		profesional = profesionalManager.getProfesionalById(profesionalId);
		//System.out.println(profesional);
		mav.addObject("profesional", profesional);
		
		Usuario user = WebServiceController.usuarioRegistrado;
		
		if(user!=null && user instanceof Cliente) {
			cliente = (Cliente)user;
			mav.addObject("usR", user);
			//System.out.println(profesional);
			return mav;
		}
		else {
			String referer = request.getHeader("Referer");
			//System.out.println(profesional);
		    return new ModelAndView("redirect:"+ referer);
		}
	}
	
	@PostMapping("/votarProfesional.htm")
	protected ModelAndView votarProfesional(@RequestParam Map<String, String> reqPar) throws Exception {
		System.out.println("1 - votarProfesional()");
		Valoracion val = new Valoracion();
		System.out.println("2 - votarProfesional()");
		int star = Integer.parseInt(reqPar.get("star"));
		System.out.println("3 - votarProfesional()");
		
		val.setProfesional(profesional);
		System.out.println("4 - votarProfesional()");
		//System.out.println(profesional);
		val.setCliente(cliente);
		//System.out.println(profesional);
		System.out.println("5 - votarProfesional()");
		val.setPuntuacion(star);
		System.out.println("6 - votarProfesional()");
		System.out.println("profesional: {" + val.getProfesional() + " }, cliente: {" + val.getCliente() + " } puntuacion: {" + val.getPuntuacion() + " }");
		valoracionManager.addValoracion(val);
		System.out.println("7 - votarProfesional()");
		return new ModelAndView("redirect:/hello.htm");
	}
}
