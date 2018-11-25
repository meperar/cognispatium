package upv.etsinf.cognispatium.web;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Pago;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Tarjeta;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimpleConsultaUrgenteManager;
import upv.etsinf.cognispatium.service.SimplePagoManager;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleTarjetaManager;


@Controller
public class PagoController {

	@Autowired
	private SimplePagoManager pagoManager;

	@Autowired
	private SimpleClienteManager clienteManager;

	@Autowired
	private SimpleSolicitudManager solicitudManager;

	@Autowired
	private SimpleTarjetaManager tarjetaManager;

	@Autowired
	private SimpleServicioManager servicioManager;

	@Autowired
	private SimpleConsultaUrgenteManager servicioCUManager;
	
	@Autowired
	private SimplePresupuestoManager simplePresupuestoManager;
	
	private String servicioString;
	
	Map<String, Object> myModel;

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/pagoTarjeta.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> reqPar) throws ServletException, IOException {	
		String titulo = reqPar.get("titulo");
		String descripcion = reqPar.get("descripcion");
		Integer ServiceId = Integer.parseInt(reqPar.get("servicio"));
		Servicio servicioConsulta = servicioManager.getServiciobyId(ServiceId);
		servicioString = servicioConsulta.getNombre();
		ConsultaUrgente consultaUrgente = new ConsultaUrgente();
		Cliente cliente = clienteManager.getClientebyId(WebServiceController.usuarioRegistrado.getId());
		consultaUrgente.setDescripcion(descripcion);
		consultaUrgente.setTitulo(titulo);

		consultaUrgente.setServicioOrigen(servicioConsulta);
		consultaUrgente.setClienteOrigen(cliente);
		consultaUrgente.setEstado(EstadoConsulta.creada);
		
		DateTime fechaFinal = DateTime.now();
		LocalTime tiempoEspera = LocalTime.parse(reqPar.get("tiempoEspera"));
		fechaFinal = fechaFinal.plusMinutes(tiempoEspera.getMinute());
		fechaFinal = fechaFinal.plusHours(tiempoEspera.getHour());
		consultaUrgente.setFechaFin(fechaFinal);

		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("consultaUrgente", consultaUrgente);
		this.myModel = myModel;
		ModelAndView mav = new ModelAndView("pagoTarjeta", "model", myModel);
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
		return mav;

	}

	@PostMapping("/pagoTarjeta.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String, String> reqPar, ModelAndView modelAndView)
			throws Exception {
		ConsultaUrgente consultaUrgente =(ConsultaUrgente)this.myModel.get("consultaUrgente");
		Cliente cliente = consultaUrgente.getClienteOrigen();		
		Tarjeta tarjeta = addTarjeta(reqPar,cliente);
		Pago pago = new Pago();	
		pago.setClienteOrigen(cliente);
		pago.setDescripcion(consultaUrgente.getTitulo());
		pago.setPrecio(2);
		pago.setTarjetaOrigen(tarjeta);
		consultaUrgente.setPago(pago);
		servicioCUManager.addConsultaUrgente(consultaUrgente);
		pagoManager.addPago(pago);
		tarjetaManager.addTarjeta(tarjeta);
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("factura", "model", myModel);

		myModel.put("pago", pago);
		myModel.put("servicioString", servicioString);
		
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
		return mav;
		
	}

	
	@GetMapping(value = "/pagoTarjetaSolicitud.htm")
	public ModelAndView handleRequestSolicitud(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> reqPar) throws ServletException, IOException {

		Presupuesto presupuesto = simplePresupuestoManager.getPresupuestobyId(Integer.parseInt(reqPar.get("presupuestoId")));

		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("presupuesto", presupuesto);
		this.myModel = myModel;
		ModelAndView mav = new ModelAndView("pagoTarjeta", "model", myModel);
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
		return mav;
	}

	@PostMapping("/pagoTarjetaSolicitud.htm")
	protected ModelAndView onSubmitSolicitud(@RequestParam Map<String, String> reqPar, ModelAndView modelAndView)
			throws Exception {
		Presupuesto presupuesto =(Presupuesto)this.myModel.get("presupuesto");
		Cliente cliente = presupuesto.getSolicitudOrigen().getClienteOrigen();
		servicioString = presupuesto.getSolicitudOrigen().getServicioOrigen().getNombre();
		Tarjeta tarjeta = addTarjeta(reqPar,cliente);
		Pago pago = new Pago();	
		pago.setClienteOrigen(cliente);
		pago.setDescripcion(presupuesto.getDescripcion());
		pago.setPrecio(presupuesto.getPrecio());
		pago.setTarjetaOrigen(tarjeta);
		pagoManager.addPago(pago);
		presupuesto.getSolicitudOrigen().setPago(pago);
		presupuesto.getSolicitudOrigen().setEstado(EstadoSolicitud.adjudicada);
		solicitudManager.addSolicitud(presupuesto.getSolicitudOrigen());
		
		presupuesto.getSolicitudOrigen().getPresupuestos().forEach(p->{
			p.setEstado(EstadoPresupuesto.noAceptado);
			simplePresupuestoManager.addPresupuesto(p);
		});
		presupuesto.setEstado(EstadoPresupuesto.aceptado);
		simplePresupuestoManager.addPresupuesto(presupuesto);
		Map<String, Object> myModel = new HashMap<String, Object>();

		ModelAndView mav = new ModelAndView("factura", "model", myModel);

		myModel.put("pago", pago);
		myModel.put("servicioString", servicioString);
		
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
		return mav;
	}

    
    @SuppressWarnings("deprecation")
	private Tarjeta addTarjeta(Map<String, String> reqPar,Cliente cliente) {
        String Stringnumero = reqPar.get("numTarjeta");
        String titular = reqPar.get("titular");
        long numero = Long.parseLong(Stringnumero);
        String Stringcvv = reqPar.get("cvv");
        int cvv = Integer.valueOf(Stringcvv);
        Integer mes = Integer.parseInt(reqPar.get("mes"));
        Integer anyo = Integer.parseInt(reqPar.get("anyo"));
        Date fecha = new Date(anyo, mes, 01);
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setClienteOrigen(cliente);
        tarjeta.setCodigoSeguridad(cvv);
        tarjeta.setFechaCaducidad(fecha);
        tarjeta.setNumero(numero);
        tarjeta.setTitular(titular);
        return tarjeta;
    }
    


}
