package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;

@Controller
public class HistorialController {

	@Autowired
	private SimplePresupuestoManager presupuestoManager;

	@Autowired
	private SimpleSolicitudManager simpleSolicitudManager;

	@Autowired
	private SimpleServicioManager simpleServicioManager;

	@Autowired
	private SimpleProfesionalManager simpleProfesionalManager;

	@Autowired
	private SimpleClienteManager simpleClienteManager;

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/historial.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> reqPar) throws ServletException, IOException {

		// Creamos el mav y el modelo donde añadiremos la información
		ModelAndView mav = new ModelAndView("listadohistorial");
		Map<String, Object> myModel = new HashMap<String, Object>();
		Map<String, Object> servicios = new HashMap<String, Object>();

		// Comprobamos de que tipo es el usuario registrado
		String tipoUsuario = WebServiceController.usuarioRegistrado.getDTYPE();
		// Si es un profesional se añadirán al mav sus presupuestos finalizados
		if (tipoUsuario.equals("profesional")) {
			Profesional profesional_resgistrado = simpleProfesionalManager
					.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
			List<Presupuesto> listaPresupuestos = profesional_resgistrado.getPresupuestos().stream()
					.sorted(Comparator.comparing(Presupuesto::getId).reversed())
					.filter(pre -> pre.getEstado() == EstadoPresupuesto.resuelto).collect(Collectors.toList());
			if (reqPar.get("servicio") != null && !reqPar.get("servicio").equals("")) {
				Integer servicioObtenido = Integer.parseInt(reqPar.get("servicio"));
				if (servicioObtenido != null) {
					listaPresupuestos = listaPresupuestos.stream()
							.filter(presu -> presu.getSolicitudOrigen().getServicioOrigen().getId() == servicioObtenido)
							.collect(Collectors.toList());
					servicios.put("serviciId", servicioObtenido);
				}
			}
			myModel.put("historial", listaPresupuestos);
			mav.addObject("model", myModel);
			// Añadir servicios del filtro
			List<Servicio> listaServicios = profesional_resgistrado.getServicios();

			servicios.put("servicios", listaServicios);

			mav.addObject("servicios", servicios);

		} else {
			// Si es un cliente se añadiran al mav sus solicitudes finalizadas
			Cliente cliente_resgistrado = simpleClienteManager
					.getClientebyId(WebServiceController.usuarioRegistrado.getId());
			List<Solicitud> listaSolicitud = cliente_resgistrado.getSolicitudes().stream()
					.sorted(Comparator.comparing(Solicitud::getId).reversed())
					.filter(sol -> sol.getEstado() == EstadoSolicitud.resuelta)
					.collect(Collectors.toList());
			if (reqPar.get("servicio") != null && !reqPar.get("servicio").equals("")) {
				Integer servicioObtenido = Integer.parseInt(reqPar.get("servicio"));
				if (servicioObtenido != null) {
					listaSolicitud = listaSolicitud.stream()
							.filter(presu -> presu.getServicioOrigen().getId() == servicioObtenido)
							.collect(Collectors.toList());
					servicios.put("serviciId", servicioObtenido);
				}
			}
			myModel.put("historial", listaSolicitud);
			mav.addObject("model", myModel);
			// Añadir servicios del filtro
			List<Servicio> listaServicios = simpleServicioManager.getServicios();

			servicios.put("servicios", listaServicios);
			mav.addObject("servicios", servicios);
		}

		// BARRA SUPERIOR
		// Añadimos el profesional o cliente a la barra superior
		if (WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
		}

		else {
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
		}
		// Añadirmos los servicios por ambito a la barra superior
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		// FIN BARRA SUPERIOR
		// Datos del filtro por servicio

		// Devolvemos vista con modelo
		return mav;

	}

	@PostMapping("/historial.htm")
	protected ModelAndView crearPresupueusto(@RequestParam Map<String, String> reqPar) throws Exception {

		Map<String, Object> myModel = new HashMap<String, Object>();
		String tipoUsuario = WebServiceController.usuarioRegistrado.getDTYPE();
		// Si es un profesional se añadirán al mav sus presupuestos finalizados
		if (tipoUsuario.equals("profesional")) {
		
			Presupuesto presupuesto = presupuestoManager.getPresupuestobyId(Integer.parseInt(reqPar.get("hisId")));
			myModel.put("presupuesto", presupuesto);
			myModel.put("solicitud", presupuesto.getSolicitudOrigen());
			

		} else {
			// Si es un cliente se añadiran al mav sus solicitudes finalizadas
			Solicitud solicitud = simpleSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("hisId")));
			myModel.put("solicitud", solicitud);
			myModel.put("presupuesto", solicitud.getPresupuestos().stream()
					.filter(p->p.getEstado()==EstadoPresupuesto.resuelto)
					.collect(Collectors.toList()).get(0));
	
		}
		ModelAndView mav = new ModelAndView("datosHistorial", "model", myModel);
		mav.addObject("usR", WebServiceController.usuarioRegistrado);
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		return mav;
	}

}
