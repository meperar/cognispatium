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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;

@Controller
public class ListadoSolicitudesDelClienteController {
	
	Solicitud miSolicitud;

	@Autowired
	private SimpleServicioManager servicioManager;
	
	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;

	@Autowired
	private SimpleClienteManager simpleClienteManager;

	@Autowired
	private SimplePresupuestoManager simplePresupuestoManager;



	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/misSolicitudes.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView("misSolicitudes");
		Map<String, Object> myModel = new HashMap<String, Object>();

		Cliente clienteSimulado = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());

		List<Solicitud> listaSolicitudes = clienteSimulado.getSolicitudes()
				.stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
			    .filter(sol -> !(sol.getEstado()==EstadoSolicitud.eliminada))
			    .collect(Collectors.toList());;
		myModel.put("solicitudes", listaSolicitudes);

		mav.addObject("model", myModel);
		
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
			
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;

	}

	@PostMapping("/misSolicitudes.htm")
	protected ModelAndView verSolicitud(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {

		this.miSolicitud = servicioSolicitudManager.getSolicitudbyId(Integer.parseInt(reqPar.get("solicitudId")));
		
		System.out.println(miSolicitud.getDescripcion()+ "\n");
		boolean info = WebUtils.hasSubmitParameter(request, "info");
		
		if(WebUtils.hasSubmitParameter(request, "valorarProfesional")) {
		    Presupuesto presupuesto = simplePresupuestoManager.getPresupuestoAceptadoBySolicitud(miSolicitud);
			int profesionalId = presupuesto.getProfesionalOrigen().getId();
			ModelAndView mav = new ModelAndView("redirect:/votarProfesional.htm?profesionalId=" + profesionalId);
			WebServiceController.listaAmbitos.forEach(a -> {

				mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
			});
			
			mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
			return mav;
			//return new ModelAndView("redirect:/hello.htm");
		}
		
		else if (info) {
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("diferentesPresupuestos");
		
		
		List<Presupuesto> presupuestos = miSolicitud.getPresupuestos()
                .stream()
                .filter(sol -> !(sol.getEstado()==EstadoPresupuesto.rechazado))
                .collect(Collectors.toList());
	
		
		myModel.put("solicitud", miSolicitud);
		myModel.put("presupuestos", presupuestos);
		mav.addObject("model", myModel);
		
		if(WebServiceController.usuarioRegistrado == null) {
			Usuario userAux = new Usuario();
			
			userAux.setNombre("Usuario no registrado");
			mav.addObject("usR", userAux);
			
		}
		
		else {
		    
			mav.addObject("usR", WebServiceController.usuarioRegistrado);
			
		}
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		return mav;
		
	
		}
		
		else {
		    Presupuesto presupuesto = simplePresupuestoManager.getPresupuestoAceptadoBySolicitud(miSolicitud);
            //System.out.println("presupuesto: "+ presupuesto.getDescripcion() + " estado:" + presupuesto.getEstado());
		
		    if (WebUtils.hasSubmitParameter(request, "borrar")) {
			
			miSolicitud.setEstado(EstadoSolicitud.eliminada);
			servicioSolicitudManager.addSolicitud(miSolicitud);
			servicioManager.eliminarPresupuestos(miSolicitud);
		    }
		    
		    else {
		        
	           if(miSolicitud.getEstado()==EstadoSolicitud.adjudicada) {
	               miSolicitud.setEstado(EstadoSolicitud.aceptado_cliente);
	               presupuesto.setEstado(EstadoPresupuesto.aceptado_cliente);
	           }
	           else if(miSolicitud.getEstado()==EstadoSolicitud.aceptado_profesional) {
	               miSolicitud.setEstado(EstadoSolicitud.resuelta);
	               presupuesto.setEstado(EstadoPresupuesto.resuelto);
	           }
	           
	           servicioSolicitudManager.addSolicitud(miSolicitud);
	           simplePresupuestoManager.addPresupuesto(presupuesto);
	       
	          
	        }
		
		 ModelAndView mav = new ModelAndView("misSolicitudes");
         Map<String, Object> myModel = new HashMap<String, Object>();

         Cliente clienteSimulado = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());

         List<Solicitud> listaSolicitudes = clienteSimulado.getSolicitudes()
                 .stream().sorted(Comparator.comparing(Solicitud::getId).reversed())
                 .filter(sol -> !(sol.getEstado()==EstadoSolicitud.eliminada))
                 .collect(Collectors.toList());
         myModel.put("solicitudes", listaSolicitudes);

         mav.addObject("model", myModel);
         
         if(WebServiceController.usuarioRegistrado == null) {
             Usuario userAux = new Usuario();
             
             userAux.setNombre("Usuario no registrado");
             mav.addObject("usR", userAux);
             
         }
         
         else {
             
             mav.addObject("usR", WebServiceController.usuarioRegistrado);
             
         }
         WebServiceController.listaAmbitos.forEach(a -> {

             mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
         });
         
         
         mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
         return mav;
		}
		
	}
	
	@RequestMapping(value="/valorarProfesional", method=RequestMethod.POST, params="valorarProfesional")
	protected ModelAndView votarProfesional(@RequestParam(value="valorarProfesional") Boolean valorarProfesional) {
		
		System.out.println("votarProfesional()");
		ModelAndView mav = new ModelAndView("misSolicitudes");
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		if (WebServiceController.usuarioRegistrado == null) {
            Usuario userAux = new Usuario();

            userAux.setNombre("Usuario no registrado");
            mav.addObject("usR", userAux);

        }

        else {

            mav.addObject("usR", WebServiceController.usuarioRegistrado);

        }
		return mav;
	}
	
	@PostMapping("/guardaUsuario.htm")
	protected ModelAndView guardaUsuario(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {
		System.out.println("guardaUsuario()");
		ModelAndView mav = new ModelAndView("misSolicitudes");
		Map<String, Object> myModel = new HashMap<String, Object>();

		Cliente clienteSimulado = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());

		List<Solicitud> listaSolicitudes = clienteSimulado.getSolicitudes();
		myModel.put("solicitudes", listaSolicitudes);

		mav.addObject("model", myModel);
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		if (WebServiceController.usuarioRegistrado == null) {
            Usuario userAux = new Usuario();

            userAux.setNombre("Usuario no registrado");
            mav.addObject("usR", userAux);

        }

        else {

            mav.addObject("usR", WebServiceController.usuarioRegistrado);

        }
		return mav;
		
		}
	
	
	@GetMapping("/diferentesPresupuestos.htm")
	protected ModelAndView verPresupuestosDeSolicitud(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {
		System.out.println("verPresupuestosDeSolicitud()");
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("diferentesPresupuestos");
		
		
		List<Presupuesto> presupuestos = miSolicitud.getPresupuestos();
	
		myModel.put("solicitud", miSolicitud);
		myModel.put("presupuestos", presupuestos);
		mav.addObject("model", myModel);
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		if (WebServiceController.usuarioRegistrado == null) {
            Usuario userAux = new Usuario();

            userAux.setNombre("Usuario no registrado");
            mav.addObject("usR", userAux);

        }

        else {

            mav.addObject("usR", WebServiceController.usuarioRegistrado);

        }
		return mav;

		
	}

	@PostMapping("/validarPresupuesto.htm")
	protected ModelAndView validaPresupuesto(@RequestParam Map<String, String> reqPar) throws Exception {
		System.out.println("validaPresupuesto()");
		Map<String, Object> myModel = new HashMap<String, Object>();

		Presupuesto presupuesto = simplePresupuestoManager
				.getPresupuestobyId(Integer.parseInt(reqPar.get("presupuestoId")));

		myModel.put("presupuesto", presupuesto);

		ModelAndView mav = new ModelAndView("presupuesto", "model", myModel);
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		if (WebServiceController.usuarioRegistrado == null) {
            Usuario userAux = new Usuario();

            userAux.setNombre("Usuario no registrado");
            mav.addObject("usR", userAux);

        }

        else {

            mav.addObject("usR", WebServiceController.usuarioRegistrado);

        }
		return mav;
	}
	
	@GetMapping("/eliminar.htm")
	public ModelAndView handleRequestEliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("handleRequestEliminar()");
		ModelAndView mav = new ModelAndView("eliminarConfirmacion");
		WebServiceController.listaAmbitos.forEach(a -> {

			mav.addObject(a, WebServiceController.serviciosPorAmbito.get(a));
		});
		
		mav.addObject("serviciosXAmbito", BarraSuperiorController.barraSuperior(servicioManager));
		if (WebServiceController.usuarioRegistrado == null) {
            Usuario userAux = new Usuario();

            userAux.setNombre("Usuario no registrado");
            mav.addObject("usR", userAux);

        }

        else {

            mav.addObject("usR", WebServiceController.usuarioRegistrado);

        }
		return mav;

	}
	
	@PostMapping("/eliminar.htm")
	protected void elimina(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {
		System.out.println("elimina()");
		boolean info = WebUtils.hasSubmitParameter(request, "elimina");
		if (info) {
			miSolicitud.setEstado(EstadoSolicitud.cerrada);
			servicioSolicitudManager.addSolicitud(miSolicitud);
		}
	}

}