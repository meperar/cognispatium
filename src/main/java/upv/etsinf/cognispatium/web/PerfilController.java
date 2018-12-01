package upv.etsinf.cognispatium.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.io.source.ByteArrayOutputStream;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Registro;
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleRegistroManager;
import upv.etsinf.cognispatium.service.SimpleRespuestaManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleUsuarioManager;

@Controller
public class PerfilController {

	@Autowired
	private SimplePresupuestoManager presupuestoManager;

	@Autowired
	private SimpleServicioManager servicioManager;

	@Autowired
	private SimpleUsuarioManager usuarioManager;

	@Autowired
	private SimpleRegistroManager registroManager;

	@Autowired
	private SimpleConsultaManager consultaManager;

	@Autowired
	private SimpleProfesionalManager profManager;

	@Autowired
	private SimpleRespuestaManager resManager;

	@Autowired
	private SimpleClienteManager cliManager;

	@Autowired
	private SimplePresupuestoManager preManager;

	@Autowired
	private SimpleSolicitudManager SManager;

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/perfil.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Integer> intModel = new HashMap<String, Integer>();
		Map<String, Boolean> boolModel = new HashMap<String, Boolean>();

		List<Servicio> listaServicios = new ArrayList<Servicio>();

		Usuario usuario = WebServiceController.usuarioRegistrado;
		Registro registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0);

		Boolean esProfesional = profManager.getProfesionalById(usuario.getId()) != null;

		int valoracion = 0;

		if (esProfesional) {
			Profesional profesional = profManager.getProfesionalById(usuario.getId());
			valoracion = profesional.getValoracion();
			listaServicios = profesional.getServicios();
		}

		List<Servicio> allServices = servicioManager.getServicios();
		// Añadir foto
		if (usuario.getImagen() != null) {
			Base64.Encoder encoder = Base64.getEncoder();
			String encoding = "data:image/png;base64," + encoder.encodeToString(usuario.getImagen());
			myModel.put("foto", encoding);
			myModel.put("tieneFoto", true);
		} else {
			myModel.put("tieneFoto", false);
		}
		// Fin añadir foto

		myModel.put("allServices", allServices);
		myModel.put("usuario", usuario);
		myModel.put("registro", registro);
		boolModel.put("esProfesional", esProfesional);
		boolModel.put("errorUsername", false);
		myModel.put("servicios", listaServicios);
		intModel.put("valoracion", valoracion);

		ModelAndView mav = new ModelAndView("perfil", "model", myModel);
		if (WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

		} else {

			mav.addObject("usR", WebServiceController.usuarioRegistrado);

		}
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}

	@PostMapping("/perfil.htm")
	protected ModelAndView editar(@RequestParam Map<String, String> reqPar, @RequestParam("file") MultipartFile file)
			throws Exception {
		// EDITAR PERFIL

		Usuario usuario = WebServiceController.usuarioRegistrado;
		Registro registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0);
		Boolean errorUsername = false;

		List<Registro> registrosBD = registroManager.getRegistrobyUN(reqPar.get("apodo"));

		if (registrosBD.size() == 0 || registro.getUsername().equals(reqPar.get("apodo"))) {

			usuario = WebServiceController.usuarioRegistrado;

			usuario.setNombre(reqPar.get("nombre"));
			usuario.setApellidos(reqPar.get("apellidos"));
			usuario.setEdad(Integer.parseInt(reqPar.get("edad")));
			usuario.setDni(reqPar.get("dni"));
			usuario.setEmail(reqPar.get("email"));
			usuario.setTelefono(Integer.parseInt(reqPar.get("telefono")));
			// Foto

			if (!file.isEmpty()) {
				usuario.setImagen(file.getBytes());
			}
			// Fin foto

			usuarioManager.addUsuario(usuario);

			registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0);

			registro.setUsername(reqPar.get("apodo"));
			registro.setContraseña(reqPar.get("contrasena"));

			registroManager.addRegistro(registro);

		} else {
			errorUsername = true;
		}

		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Integer> intModel = new HashMap<String, Integer>();
		Map<String, Boolean> boolModel = new HashMap<String, Boolean>();

		List<Servicio> listaServicios = new ArrayList<Servicio>();

		Boolean esProfesional = profManager.getProfesionalById(usuario.getId()) != null;

		int valoracion = 0;

		if (esProfesional) {
			Profesional profesional = profManager.getProfesionalById(usuario.getId());
			valoracion = profesional.getValoracion();
			listaServicios = profesional.getServicios();
		}

		List<Servicio> allServices = servicioManager.getServicios();
		myModel.put("allServices", allServices);
		myModel.put("usuario", usuario);
		myModel.put("registro", registro);
		boolModel.put("esProfesional", esProfesional);
		boolModel.put("errorUsername", errorUsername);
		myModel.put("servicios", listaServicios);
		intModel.put("valoracion", valoracion);
		// Añadir foto
		if (usuario.getImagen() != null) {
			Base64.Encoder encoder = Base64.getEncoder();
			String encoding = "data:image/png;base64," + encoder.encodeToString(usuario.getImagen());
			myModel.put("foto", encoding);
			myModel.put("tieneFoto", true);
		} else {
			myModel.put("tieneFoto", false);
		}
		// Fin añadir foto

		ModelAndView mav = new ModelAndView("perfil", "model", myModel);
		if (WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

		} else {

			mav.addObject("usR", WebServiceController.usuarioRegistrado);

		}
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}

	@PostMapping("/addServicio.htm")
	public ModelAndView añadirServicio(@RequestParam Map<String, String> reqPar) {

		boolean check = false;
		Servicio serviceToAdd = null;

		if (reqPar.get("servicio") != null) {
			Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
			serviceToAdd = servicioManager.getServiciobyId(ServiceId);
		}

		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Integer> intModel = new HashMap<String, Integer>();
		Map<String, Boolean> boolModel = new HashMap<String, Boolean>();

		List<Servicio> listaServicios = new ArrayList<Servicio>();

		Usuario usuario = WebServiceController.usuarioRegistrado;
		Registro registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0);

		Boolean esProfesional = profManager.getProfesionalById(usuario.getId()) != null;

		int valoracion = 0;

		Profesional profesional = profManager.getProfesionalById(usuario.getId());
		valoracion = profesional.getValoracion();
		listaServicios = profesional.getServicios();

		for (Servicio s : listaServicios) {
			if (s.getId() == serviceToAdd.getId())
				check = true;
		}

		if (serviceToAdd != null && check == false) {
			listaServicios.add(serviceToAdd);
			profesional.setServicios(listaServicios);
			profManager.addProfesional(profesional);
		}

		List<Servicio> allServices = servicioManager.getServicios();
		myModel.put("allServices", allServices);
		myModel.put("usuario", usuario);
		myModel.put("registro", registro);
		// Añadir foto
				if (usuario.getImagen() != null) {
					Base64.Encoder encoder = Base64.getEncoder();
					String encoding = "data:image/png;base64," + encoder.encodeToString(usuario.getImagen());
					myModel.put("foto", encoding);
					myModel.put("tieneFoto", true);
				} else {
					myModel.put("tieneFoto", false);
				}
				// Fin añadir foto
		boolModel.put("esProfesional", esProfesional);
		boolModel.put("errorUsername", false);
		myModel.put("servicios", listaServicios);
		intModel.put("valoracion", valoracion);

		ModelAndView mav = new ModelAndView("perfil", "model", myModel);
		if (WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

		} else {

			mav.addObject("usR", WebServiceController.usuarioRegistrado);

		}
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}

	@PostMapping("/quitarServicio.htm")
	public ModelAndView quitarServicio(@RequestParam Map<String, String> reqPar) {
		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Integer> intModel = new HashMap<String, Integer>();
		Map<String, Boolean> boolModel = new HashMap<String, Boolean>();

		List<Servicio> listaServicios = new ArrayList<Servicio>();

		Usuario usuario = WebServiceController.usuarioRegistrado;
		Registro registro = registroManager.getRegistrobyUsuario(usuario.getId()).get(0);

		Boolean esProfesional = profManager.getProfesionalById(usuario.getId()) != null;

		int valoracion = 0;

		Profesional profesional = profManager.getProfesionalById(usuario.getId());
		valoracion = profesional.getValoracion();
		listaServicios = profesional.getServicios();

		// Quitamos el servicio
		Integer idServicio = Integer.parseInt(reqPar.get("servicioAQuitar"));
		Servicio servicio = servicioManager.getServiciobyId(idServicio);

		for (Servicio servicioAuxiliar : listaServicios) {
			System.out.print(" , " + servicioAuxiliar.getNombre() + servicioAuxiliar.getId() + "/" + idServicio);
			if (servicioAuxiliar.getId() == idServicio) {
				servicio = servicioAuxiliar;
			}
		}

		System.out.println("Servicio eliminado: " + servicio.getNombre() + servicio.getId());

		listaServicios.remove(servicio);
		System.out.println("Servicio eliminado: " + servicio.getNombre());

		// rechazo los presupuestos activos asociados al servicio que quito
		List<Presupuesto> presupuestos = profesional.getPresupuestos();
		for (Presupuesto p : presupuestos) {
			System.out.println(p.getSolicitudOrigen().getServicioOrigen().getNombre());
			if (p.getSolicitudOrigen().getServicioOrigen().getNombre().equals(servicio.getNombre())) {
				System.out.println(p.getDescripcion() + " " + p.getEstado());
				p.setEstado(EstadoPresupuesto.rechazado);
				System.out.println("Ahora he cambiado el estado: " + p.getEstado());
				presupuestoManager.addPresupuesto(p);
			}
		}

		profesional.setPresupuestos(presupuestos);
		profesional.setServicios(listaServicios);
		profManager.addProfesional(profesional);
		// Fin quitar servicio

		List<Servicio> allServices = servicioManager.getServicios();
		myModel.put("allServices", allServices);
		myModel.put("usuario", usuario);
		myModel.put("registro", registro);
		// Añadir foto
				if (usuario.getImagen() != null) {
					Base64.Encoder encoder = Base64.getEncoder();
					String encoding = "data:image/png;base64," + encoder.encodeToString(usuario.getImagen());
					myModel.put("foto", encoding);
					myModel.put("tieneFoto", true);
				} else {
					myModel.put("tieneFoto", false);
				}
				// Fin añadir foto
		boolModel.put("esProfesional", esProfesional);
		boolModel.put("errorUsername", false);
		myModel.put("servicios", listaServicios);
		intModel.put("valoracion", valoracion);

		ModelAndView mav = new ModelAndView("perfil", "model", myModel);
		if (WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();

			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);

		} else {

			mav.addObject("usR", WebServiceController.usuarioRegistrado);

		}
		mav.addObject("intModel", intModel);
		mav.addObject("boolModel", boolModel);

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}

	@PostMapping("/eliminarUsuario.htm")
	public ModelAndView eliminarUsuario(@RequestParam Map<String, String> reqPar) {
		Usuario usuEl = usuarioManager.getUsuariobyId(Integer.parseInt(reqPar.get("usridE")));

		Registro regEl = registroManager.getRegistrobyUsuario(usuEl.getId()).get(0);

		registroManager.dropReg(regEl);
		// usuarioManager.dropUser(usuEl);

		ModelAndView mav = new ModelAndView("hello");

		Usuario userAux = new Usuario();

		userAux.setNombre("Usuario no registrado");
		mav.addObject("usR", userAux);

		WebServiceController.usuarioRegistrado = userAux;

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
	}

	@PostMapping("/desactivarPerfil.htm")
	public ModelAndView desactivarPerfil(@RequestParam Map<String, String> reqPar) {

		// DESACTIVAR USUARIO

		Usuario usuEl = usuarioManager.getUsuariobyId(Integer.parseInt(reqPar.get("desacId")));
		usuEl.setDesactivado(1);
		usuarioManager.addUsuario(usuEl);

		if (WebServiceController.usuarioRegistrado.getDTYPE().toString().length() == 7) {
			List<Solicitud> solicitudes = SManager.getSolicitudByCli(usuEl.getId());
			for (Solicitud s : solicitudes) {
				s.setEstado(EstadoSolicitud.eliminada);
				SManager.addSolicitud(s);
			}

			List<Consulta> consultas = consultaManager.getConsultasByCli(usuEl.getId());
			for (Consulta c : consultas) {
				c.setEstado(EstadoConsulta.cerrada);
				consultaManager.addConsulta(c);
			}

		}

		else {

			List<Presupuesto> presupuestos = presupuestoManager.getPresupuestosByProf(usuEl.getId());
			for (Presupuesto p : presupuestos) {
				p.setEstado(EstadoPresupuesto.rechazado);
				presupuestoManager.addPresupuesto(p);
			}

		}
		ModelAndView mav = new ModelAndView("hello");

		Usuario userAux = new Usuario();

		userAux.setNombre("Usuario no registrado");
		mav.addObject("usR", userAux);

		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}

	public void setServicioManager(SimpleServicioManager servicioManager) {
		this.servicioManager = servicioManager;
	}

}