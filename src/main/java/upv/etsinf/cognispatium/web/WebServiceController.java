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
import upv.etsinf.cognispatium.domain.Registro;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.AdminManager;

import upv.etsinf.cognispatium.service.ProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleRegistroManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;


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
	
	@Autowired
	private SimpleServicioManager servicioManager;
	
	@Autowired
	private SimpleConsultaManager consultaManager;
	
	@Autowired
	private SimpleRegistroManager simpleRegistroManager;
	

	public Usuario usuarioRegistrado = null;
	

	@RequestMapping(value = "/hello.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String now = (new Date()).toString();
		logger.info("Returning hello view with " + now);

		Map<String, Object> med = new HashMap<String, Object>();
		med.put("serviMed", this.servicioManager.getServiciosbyAmbito("Medicina"));
		
		Map<String, Object> dep = new HashMap<String, Object>();
		dep.put("serviDep", this.servicioManager.getServiciosbyAmbito("Deporte"));
		
		Map<String, Object> cien = new HashMap<String, Object>();
		cien.put("serviCien", this.servicioManager.getServiciosbyAmbito("Ciencia"));
		
		Map<String, Object> tec = new HashMap<String, Object>();
		tec.put("serviTec", this.servicioManager.getServiciosbyAmbito("Técnicos"));
		
		Map<String, Object> leg = new HashMap<String, Object>();
		leg.put("serviLeg", this.servicioManager.getServiciosbyAmbito("Legislación"));
		
		Map<String, Object> artm = new HashMap<String, Object>();
		artm.put("serviArtM", this.servicioManager.getServiciosbyAmbito("Artes Marciales"));
		
		Map<String, Object> cuiho = new HashMap<String, Object>();
		cuiho.put("serviCuiHo", this.servicioManager.getServiciosbyAmbito("Cuidados del Hogar"));
		
		Map<String, Object> art = new HashMap<String, Object>();
		art.put("serviArt", this.servicioManager.getServiciosbyAmbito("Arte"));
		
		Map<String, Object> idio = new HashMap<String, Object>();
		idio.put("serviIdio", this.servicioManager.getServiciosbyAmbito("Idiomas"));
		
		Map<String, Object> est = new HashMap<String, Object>();
		est.put("serviEst", this.servicioManager.getServiciosbyAmbito("Estética"));

		
		ModelAndView mav = new ModelAndView("hello", "med", med);
		
		
		if(usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", usuarioRegistrado);
		}
		//Map<String, Object> usR = new HashMap<String, Object>();
	//	usR.put("usuarioRegistrado", usuarioRegistrado);
		mav.addObject("dep", dep);
		mav.addObject("cien", cien);
		mav.addObject("tec", tec);
		mav.addObject("leg", leg);
		mav.addObject("artm", artm);
		mav.addObject("cuiho", cuiho);
		mav.addObject("art", art);
		mav.addObject("idio", idio);
		mav.addObject("est", est);
		return mav;
	
		
	}
	
	@RequestMapping("/login.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("login", "model", myModel);
		return mav;

	}
		
	
	@PostMapping("/hello.htm")
	protected ModelAndView listarProf(@RequestParam Map<String, String> reqPar) throws Exception {

		List<Profesional> miProfesional = null;	
		List<Consulta> miConsulta = null;
		Servicio miServicio = null;
		ModelAndView mav = null;
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Object> servicio = new HashMap<String, Object>();
		
		if(reqPar.get("serviceId") != null) {
			miServicio = servicioManager.getServiciobyId(Integer.parseInt(reqPar.get("serviceId")));
			
			miProfesional = miServicio.getProfesionales();
			myModel.put("profesional", miProfesional);
			
			servicio.put("servicio", miServicio);
			
			mav = new ModelAndView("listaprofesionales", "model", myModel);
			mav.addObject("servicio", servicio);
			
		} else if (reqPar.get("serviceIdC") != null) {
			miServicio = servicioManager.getServiciobyId(Integer.parseInt(reqPar.get("serviceIdC")));
			
			miConsulta = consultaManager.getConsultasbyServicio(Integer.parseInt(reqPar.get("serviceIdC")));
			myModel.put("consulta", miConsulta);
			
			
			servicio.put("servicio", miServicio);
			
			mav = new ModelAndView("listaconsultas", "model", myModel);
			mav.addObject("servicio", servicio);
			
		} 
		

		return mav;
	}
	
	@PostMapping("/login.htm")
	protected ModelAndView crearMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		String username = reqPar.get("username");
		
		String contraseña = reqPar.get("password");
		
		List<Registro> registros = simpleRegistroManager.getRegistrobyInfo(username, contraseña);
		
		usuarioRegistrado = registros.get(0).getUsuario();
		
		ModelAndView mav = new ModelAndView("hello", "model", myModel);
		
		
		mav.addObject("usRe", usuarioRegistrado);
		
		Map<String, Object> med = new HashMap<String, Object>();
		med.put("serviMed", this.servicioManager.getServiciosbyAmbito("Medicina"));
		
		Map<String, Object> dep = new HashMap<String, Object>();
		dep.put("serviDep", this.servicioManager.getServiciosbyAmbito("Deporte"));
		
		Map<String, Object> cien = new HashMap<String, Object>();
		cien.put("serviCien", this.servicioManager.getServiciosbyAmbito("Ciencia"));
		
		Map<String, Object> tec = new HashMap<String, Object>();
		tec.put("serviTec", this.servicioManager.getServiciosbyAmbito("Técnicos"));
		
		Map<String, Object> leg = new HashMap<String, Object>();
		leg.put("serviLeg", this.servicioManager.getServiciosbyAmbito("Legislación"));
		
		Map<String, Object> artm = new HashMap<String, Object>();
		artm.put("serviArtM", this.servicioManager.getServiciosbyAmbito("Artes Marciales"));
		
		Map<String, Object> cuiho = new HashMap<String, Object>();
		cuiho.put("serviCuiHo", this.servicioManager.getServiciosbyAmbito("Cuidados del Hogar"));
		
		Map<String, Object> art = new HashMap<String, Object>();
		art.put("serviArt", this.servicioManager.getServiciosbyAmbito("Arte"));
		
		Map<String, Object> idio = new HashMap<String, Object>();
		idio.put("serviIdio", this.servicioManager.getServiciosbyAmbito("Idiomas"));
		
		Map<String, Object> est = new HashMap<String, Object>();
		est.put("serviEst", this.servicioManager.getServiciosbyAmbito("Estética"));
		
		mav.addObject("dep", dep);
		mav.addObject("cien", cien);
		mav.addObject("tec", tec);
		mav.addObject("leg", leg);
		mav.addObject("artm", artm);
		mav.addObject("cuiho", cuiho);
		mav.addObject("art", art);
		mav.addObject("idio", idio);
		mav.addObject("est", est);
		mav.addObject("med", med);

		return mav;
	}
	
	

	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	public void setClienteManager(ClienteManager clienteManager) {
		this.clienteManager = clienteManager;
	}
	
	
}