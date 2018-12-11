package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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
import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoRespuesta;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Respuesta;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleConsultaManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;
import upv.etsinf.cognispatium.service.SimpleRespuestaManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;

@Controller
public class ListadoConsultasDelClienteController {
	
	Consulta miConsulta;

	@Autowired
	private SimpleServicioManager servicioManager;
	
	@Autowired
	private SimpleSolicitudManager servicioSolicitudManager;

	@Autowired
	private SimpleClienteManager simpleClienteManager;

	@Autowired
	private SimplePresupuestoManager simplePresupuestoManager;
	
	@Autowired
	private SimpleConsultaManager simpleConsultaManager;
	
	@Autowired
	private SimpleRespuestaManager simpleRespuestaManager;


	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@GetMapping("/misConsultas.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView("misConsultas");
		Map<String, Object> myModel = new HashMap<String, Object>();

		Cliente clienteSimulado = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());

		List<Consulta> listaConsultas = clienteSimulado.getConsultas();
		List<Consulta> listaAux = new ArrayList<Consulta>();
		for(Consulta x : listaConsultas) {
			if(x instanceof ConsultaUrgente) listaAux.add(x);
		}
		
		myModel.put("consultas", listaConsultas.stream().filter(c->c.getEstado()!= EstadoConsulta.cerrada).collect(Collectors.toList()));
		myModel.put("consultasUrg", listaAux.stream().filter(c->c.getEstado()!= EstadoConsulta.cerrada).collect(Collectors.toList()));

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

	@PostMapping("/misConsultas.htm")
	protected ModelAndView verConsulta(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {

		this.miConsulta = simpleConsultaManager.getConsultabyId(Integer.parseInt(reqPar.get("consultaId")));
						
		boolean info = WebUtils.hasSubmitParameter(request, "info");
		
		if (info) {

		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("diferentesRespuestas");
		
		
		List<Respuesta> respuestas = miConsulta.getRespuestas();
                
		
		
		myModel.put("consulta", miConsulta);
		myModel.put("respuestas", respuestas);
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
			
			miConsulta.setEstado(EstadoConsulta.cerrada);
			simpleConsultaManager.addConsulta(miConsulta);
			
			
			ModelAndView mav = new ModelAndView("misConsultas");
			Map<String, Object> myModel = new HashMap<String, Object>();

			Cliente clienteSimulado = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());

			List<Consulta> listaConsultas = clienteSimulado.getConsultas();
			myModel.put("consultas", listaConsultas.stream().filter(c->c.getEstado()!= EstadoConsulta.cerrada).collect(Collectors.toList()));

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
	
	
	@PostMapping("/guardaUsuarioConsulta.htm")
	protected ModelAndView guardaUsuario(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {
		System.out.println("guardaUsuario()");
		ModelAndView mav = new ModelAndView("misConsultas");
		Map<String, Object> myModel = new HashMap<String, Object>();

		Cliente clienteSimulado = simpleClienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());

		List<Consulta> listaConsultas = clienteSimulado.getConsultas();
		myModel.put("solicitudes", listaConsultas);

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
	
	
	@GetMapping("/diferentesRespuestas.htm")
	protected ModelAndView verRespuestasDeConsulta(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {
		System.out.println("verRespuestasdeConsulta()");
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("diferentesRespuestas");
		
		
		List<Respuesta> respuestas = miConsulta.getRespuestas();
		
	
		myModel.put("consulta", miConsulta);
		myModel.put("respuestas", respuestas);
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

	@PostMapping("/validarRespuesta.htm")
	protected ModelAndView validaRespuesta(@RequestParam Map<String, String> reqPar) throws Exception {
		System.out.println("validaRespuesta()");
		
		miConsulta.setEstado(EstadoConsulta.resuelta);
		
		Respuesta respuesta = simpleRespuestaManager.getRespuestabyId(Integer.parseInt(reqPar.get("respuestaId")));
		System.out.println("respuesta: "+respuesta.getId() +" " +respuesta.getDescripcion());
		//pongo las respuestas a malas
		List<Respuesta> respuestaList = miConsulta.getRespuestas();
        for (Respuesta r: respuestaList ) {
            r.setEstado(EstadoRespuesta.mala);
            System.out.println("respuesta:" + r.getId() + " " + r.getEstado());
            simpleRespuestaManager.addRespuesta(r);
        }
        respuesta.setEstado(EstadoRespuesta.buena);     
        simpleRespuestaManager.addRespuesta(respuesta);
        
        simpleConsultaManager.addConsulta(miConsulta);

        //cargo la vista
        Map<String, Object> myModel = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("diferentesRespuestas");
        List<Respuesta> respuestas = miConsulta.getRespuestas();
        myModel.put("consulta", miConsulta);
        myModel.put("respuestas", respuestas);
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
	
	@GetMapping("/eliminarConsulta.htm")
	public ModelAndView handleRequestEliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("handleRequestEliminar()");
		ModelAndView mav = new ModelAndView("eliminarConfirmacionConsulta");
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
	
	@PostMapping("/eliminarConsulta.htm")
	protected void elimina(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {
		System.out.println("elimina()");
		boolean info = WebUtils.hasSubmitParameter(request, "elimina");
		if (info) {
			miConsulta.setEstado(EstadoConsulta.cerrada);
			//cambio estado de respuestas.
			List<Respuesta> respuestaList = miConsulta.getRespuestas();
			for (Respuesta r: respuestaList ) {
			    r.setEstado(EstadoRespuesta.cerrada);
			    simpleRespuestaManager.addRespuesta(r);
			}
			// fin cambio de estado respuestas
			
			simpleConsultaManager.addConsulta(miConsulta);
		}
	}

}