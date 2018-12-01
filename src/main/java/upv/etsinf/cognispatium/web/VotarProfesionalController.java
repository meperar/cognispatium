package upv.etsinf.cognispatium.web;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.domain.Valoracion;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleValoracionManager;

@Controller
public class VotarProfesionalController {
	
	@Autowired
	private SimpleValoracionManager valoracionManager;
	
	@Autowired
	private SimpleProfesionalManager profesionalManager;
	
	@Autowired
	private SimpleClienteManager clienteManager;
	
	@Autowired
	private SimpleServicioManager servicioManager;
	
	private Profesional profesional;
	private Cliente cliente;
	
	@GetMapping("/votarProfesional.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> reqPar)
			throws ServletException, IOException {
		Integer profesionalId = Integer.parseInt(reqPar.get("profesionalId"));
		profesional = profesionalManager.getProfesionalById(profesionalId);
		ModelAndView mav = new ModelAndView("votarProfesional");
		Map<String, Object> myModel = new HashMap<String, Object>();
		//AÑADIMOS IMAGEN AL MAV
		
		if(profesional.getImagen() != null) {
			Base64.Encoder encoder = Base64.getEncoder();
	        String encoding = "data:image/png;base64," + encoder.encodeToString(profesional.getImagen());
	        myModel.put("foto", encoding);
	        myModel.put("tieneFoto", true);
			}else {
			myModel.put("tieneFoto", false);
			}
        
		//FIN AÑADIR IMAGEN
		mav.addObject("model", myModel);
		
		
		mav.addObject("profesional", profesional);
		
		Usuario user = WebServiceController.usuarioRegistrado;
		
		if(user!=null && user.getDTYPE().equals("cliente")) {
			cliente = clienteManager.getClientebyId(user.getId());
			mav.addObject("usR", user);
			WebServiceController.listaAmbitos.forEach(a -> {

				mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
			});
			
			
			mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
			return mav;
		}
		else {
			String referer = request.getHeader("Referer");
			ModelAndView mav2 = new ModelAndView("redirect:"+ referer);
			WebServiceController.listaAmbitos.forEach(a -> {

				mav2.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
			});
			
			mav2.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
			return mav2;
		   
		}
	}
	
	@PostMapping("/votarProfesional.htm")
	protected ModelAndView votarProfesional(@RequestParam Map<String, String> reqPar) throws Exception {
		Valoracion val = new Valoracion();
		int star = Integer.parseInt(reqPar.get("star"));
		
		val.setProfesional(profesional);
		val.setCliente(cliente);
		val.setPuntuacion(star);
		valoracionManager.addValoracion(val);
		ModelAndView mav = new ModelAndView("redirect:/hello.htm");
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}
}
