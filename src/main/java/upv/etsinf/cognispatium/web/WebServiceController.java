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
import org.hibernate.Hibernate;
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
import upv.etsinf.cognispatium.service.SimpleUsuarioManager;

@Controller
public class WebServiceController {

	protected final Log logger = LogFactory.getLog(getClass());

	Map<String, Object> serviciosPorAmbito;

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
	private SimpleUsuarioManager simpleUsuarioManager;

	@Autowired
	private SimpleConsultaManager consultaManager;
	


	@Autowired
	private SimpleRegistroManager simpleRegistroManager;

	public static Usuario usuarioRegistrado = null;

	public boolean registradoB = false;

	@RequestMapping(value = "/hello.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String now = (new Date()).toString();
		logger.info("Returning hello view with " + now);

		List<String> listaAmbitos = servicioManager.getAmbitos();
		List<Servicio> listaServicios = servicioManager.getServicios();
		serviciosPorAmbito = new HashMap<String, Object>();

		listaAmbitos.forEach(ambito -> {
			String amb = ambito;
			List<Servicio> lista = new ArrayList<Servicio>();
			listaServicios.forEach(serv -> {
				if (serv.getAmbito().equals(amb)) {
					lista.add(serv);
				}
			});
			serviciosPorAmbito.put(ambito, lista);
		});

		ModelAndView mav = new ModelAndView("hello");

		mav.addObject("serviciosPorAmbito", serviciosPorAmbito);

		if (usuarioRegistrado == null) {
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

			registradoB = false;

			mav.addObject("regis", registradoB);

		}

		else {

			mav.addObject("usR", usuarioRegistrado);
			registradoB = true;
		}

		// Map<String, Object> usR = new HashMap<String, Object>();
		// usR.put("usuarioRegistrado", usuarioRegistrado);

		listaAmbitos.forEach(a -> {

			mav.addObject(a, serviciosPorAmbito.get(a));
		});
		return mav;

	}

	@RequestMapping("/login.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("login", "model", myModel);
		mav.addObject("serviciosPorAmbito", this.serviciosPorAmbito);
		return mav;

	}

	@GetMapping("/listProf.htm")
	protected ModelAndView listarProf(@RequestParam Map<String, String> reqPar) throws Exception {

		List<Profesional> miProfesional = null;
		List<Consulta> miConsulta = null;
		Servicio miServicio = null;
		ModelAndView mav = new ModelAndView("");

		serviciosPorAmbito.clear();
		final List<String> listaAmbitos = servicioManager.getAmbitos();
		final List<Servicio> listaServicios = servicioManager.getServicios();

		listaAmbitos.forEach(ambito -> {
			String amb = ambito;
			List<Servicio> lista = new ArrayList<Servicio>();
			listaServicios.forEach(serv -> {
				if (serv.getAmbito().equals(amb)) {
					lista.add(serv);
				}
			});
			serviciosPorAmbito.put(ambito, lista);
		});

		mav.addObject("serviciosPorAmbito", serviciosPorAmbito);
		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Object> servicio = new HashMap<String, Object>();

		if (reqPar.get("serviceId") != null) {
			miServicio = servicioManager.getServiciobyId(Integer.parseInt(reqPar.get("serviceId")));
			//Hibernate.initialize(miServicio.getProfesionales());
			miProfesional = miServicio.getProfesionales();
			myModel.put("profesional", miProfesional);

			servicio.put("servicio", miServicio);
			mav.setViewName("listaprofesionales");
			mav.addObject("model", myModel);

			if (usuarioRegistrado == null) {
				Usuario userAux = new Usuario();

				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);

			}

			else {

				mav.addObject("usR", usuarioRegistrado);

			}
			mav.addObject("servicio", servicio);

		} else if (reqPar.get("serviceIdC") != null) {
			miServicio = servicioManager.getServiciobyId(Integer.parseInt(reqPar.get("serviceIdC")));

			miConsulta = consultaManager.getConsultasbyServicio(Integer.parseInt(reqPar.get("serviceIdC")));
			myModel.put("consulta", miConsulta);

			servicio.put("servicio", miServicio);

			mav.setViewName("listaconsultas");
			mav.addObject("model", myModel);

			if (usuarioRegistrado == null) {
				Usuario userAux = new Usuario();

				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);

			}

			else {

				mav.addObject("usR", usuarioRegistrado);

			}
			mav.addObject("servicio", servicio);

		} else {

			String username = reqPar.get("username");

			String contraseña = reqPar.get("password");

			List<Registro> registros = simpleRegistroManager.getRegistrobyInfo(username, contraseña);

			usuarioRegistrado = registros.get(0).getUsuario();

			mav.setViewName("hello");
			mav.addObject("model", myModel);

			mav.addObject("usR", usuarioRegistrado);
		}

		return mav;
	}

	@PostMapping("/login.htm")
	protected ModelAndView crearMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

		List<Profesional> miProfesional = null;
		List<Consulta> miConsulta = null;
		Servicio miServicio = null;
		ModelAndView mav = new ModelAndView();

		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Object> servicio = new HashMap<String, Object>();

		if (reqPar.get("serviceId") != null) {

			mav.setViewName("listaprofesionales");
			mav.addObject("model", myModel);

			if (usuarioRegistrado == null) {
				Usuario userAux = new Usuario();

				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);

			}

			else {

				mav.addObject("usR", usuarioRegistrado);

			}
			mav.addObject("servicio", servicio);

		} else if (reqPar.get("serviceIdC") != null) {
			mav.setViewName("listaconsultas");
			mav.addObject("model", myModel);
			if (usuarioRegistrado == null) {
				Usuario userAux = new Usuario();

				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);

			}

			else {

				mav.addObject("usR", usuarioRegistrado);

			}
			mav.addObject("servicio", servicio);

		} else {

			String username = reqPar.get("username");

			String contraseña = reqPar.get("password");

			List<Registro> registros = simpleRegistroManager.getRegistrobyInfo(username, contraseña);

			if (registros.size() == 0) {

				mav.setViewName("login");
				mav.addObject("model", myModel);

				String str = "Error : Usuario no registrado.";

				mav.addObject("error", str);

				return mav;

			}
			usuarioRegistrado = registros.get(0).getUsuario();
			usuarioRegistrado.setDesactivado(0);
			
			simpleUsuarioManager.addUsuario(usuarioRegistrado);
			
			mav.setViewName("hello");
			mav.addObject("model", myModel);

			mav.addObject("usR", usuarioRegistrado);
			this.serviciosPorAmbito.clear();
			final List<String> listaAmbitos = servicioManager.getAmbitos();
			final List<Servicio> listaServicios = servicioManager.getServicios();

			listaAmbitos.forEach(ambito -> {
				String amb = ambito;
				List<Servicio> lista = new ArrayList<Servicio>();
				listaServicios.forEach(serv -> {
					if (serv.getAmbito().equals(amb)) {
						lista.add(serv);
					}
				});
				serviciosPorAmbito.put(ambito, lista);
			});

			mav.addObject("serviciosPorAmbito", serviciosPorAmbito);

		}
		return mav;
	}

	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	public void setClienteManager(ClienteManager clienteManager) {
		this.clienteManager = clienteManager;
	}

}