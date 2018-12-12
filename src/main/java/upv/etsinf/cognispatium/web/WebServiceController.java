package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
//<<<<<<< HEAD
import org.springframework.core.io.DefaultResourceLoader;
//=======
import org.springframework.scheduling.annotation.EnableAsync;
//>>>>>>> DEV
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Registro;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.AsynchronousService;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimpleRegistroManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleUsuarioManager;

@Controller
public class WebServiceController {

	protected final Log logger = LogFactory.getLog(getClass());

	public static Map<String, Object>  serviciosPorAmbito = null;
	
	public static List<String> listaAmbitos = null;



	@Autowired
	private SimpleServicioManager servicioManager;
	
	@Autowired
	private SimpleUsuarioManager simpleUsuarioManager;

	@Autowired
	private SimpleConsultaManager consultaManager;
	
	@Autowired
	private AsynchronousService asynchronousService;

	@Autowired
	private SimpleRegistroManager simpleRegistroManager;

	public static Usuario usuarioRegistrado = null;

	public boolean registradoB = false;
	
	public static boolean arranque = false;

	@RequestMapping(value = "/hello.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {

		String now = (new Date()).toString();
		logger.info("Returning hello view with " + now);

		List<String> listaAmbitosAux = servicioManager.getAmbitos();
		List<Servicio> listaServicios = servicioManager.getServicios();
		Map<String, Object> serviciosPorAmbitoAux = new HashMap<String, Object>();
		if(!arranque) {
		asynchronousService.checkCU();
		arranque = !arranque;
		}
		listaAmbitosAux.forEach(ambito -> {
			String amb = ambito;
			List<Servicio> lista = new ArrayList<Servicio>();
			listaServicios.forEach(serv -> {
				if (serv.getAmbito().equals(amb)) {
					lista.add(serv);
				}
			});
			serviciosPorAmbitoAux.put(ambito, lista);
		});
		
		WebServiceController.serviciosPorAmbito = serviciosPorAmbitoAux;
		WebServiceController.listaAmbitos = listaAmbitosAux;

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

		listaAmbitosAux.forEach(a -> {

			mav.addObject(a, serviciosPorAmbito.get(a));
		});
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}

	@SuppressWarnings("static-access")
	@RequestMapping("/login.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("login", "model", myModel);
		mav.addObject("serviciosPorAmbito", this.serviciosPorAmbito);
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
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
			
			Collections.sort(miProfesional, (a, b) -> a.getValoracionMedia() < b.getValoracionMedia() ? 1 : a.getValoracionMedia() == b.getValoracionMedia() ? 0 : -1);
			
			myModel.put("profesional", miProfesional);
			myModel.put("servicios", servicioManager.getServicios());

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
			servicio.put("servicio", miServicio);
			
			miConsulta = consultaManager.getConsultasbyServicio(Integer.parseInt(reqPar.get("serviceIdC")));
			/*
			for(Consulta con : miConsulta) {
				if(con.getEstado() == EstadoConsulta.cerrada) miConsulta.remove(con);
			}
			*/
			miConsulta.stream()
			.filter(sol -> (sol.getEstado()!= EstadoConsulta.cerrada))
            .collect(Collectors.toList());  
			myModel.put("consulta", miConsulta);

			

			mav.setViewName("listaconsultas");
			mav.addObject("model", myModel);

			if (usuarioRegistrado == null) {
				Usuario userAux = new Usuario();

				userAux.setNombre("Usuario no registrado");
				mav.addObject("usR", userAux);

			}else {

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
		
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	@SuppressWarnings("static-access")
	@PostMapping("/login.htm")
	protected ModelAndView crearMensaje(@RequestParam Map<String, String> reqPar) throws Exception {

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
				WebServiceController.listaAmbitos.forEach(a -> {

					mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
				});
				
				mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
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
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	 @GetMapping(value = "/guiaDeUso.htm")
	    public void getFile(HttpServletResponse response) {
	        try {
	            DefaultResourceLoader loader = new DefaultResourceLoader();
	            InputStream is = loader.getResource("classpath:META-INF/resources/guia_de_uso.pdf").getInputStream();
	            IOUtils.copy(is, response.getOutputStream());
	            response.setHeader("Content-Disposition", "attachment; filename=Accepted.pdf");
	            response.flushBuffer();
	        } catch (IOException ex) {
	            throw new RuntimeException("IOError writing file to output stream");
	        }
	    }
}