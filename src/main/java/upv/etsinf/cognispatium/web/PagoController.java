package upv.etsinf.cognispatium.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Tarjeta;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.Pago;
import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;
import upv.etsinf.cognispatium.service.SimpleTarjetaManager;
import upv.etsinf.cognispatium.service.SimpleClienteManager;
import upv.etsinf.cognispatium.service.SimplePagoManager;

import java.io.Console;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import java.io.*; 
import com.lowagie.text.DocumentException; 
import org.xhtmlrenderer.pdf.ITextRenderer;

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
	
	
	
	
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/pagoTarjeta.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("pagoTarjeta", "model", myModel);

		return mav;
	}
	
	@PostMapping("/pagoTarjeta.htm")
	protected ModelAndView onSubmit(@RequestParam Map<String,String> reqPar) throws Exception {	
		Cliente cliente = clienteManager.getClientes().get(0);
		Solicitud solicitud = solicitudManager.getSolicituds().get(0);
		
			String Stringnumero = reqPar.get("numTarjeta");
			String titular =reqPar.get("titular");
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
			tarjetaManager.addTarjeta(tarjeta);
	
		
		
		Pago pago = new Pago();
		
		//el cliente asociado al pago no es real.
		pago.setClienteOrigen(cliente);
		System.out.println(cliente.getNombre());
		//la solicitud asociada al pago no es real.
		pago.setDescripcion(solicitud.getDescripcion());
		System.out.println(solicitud.getDescripcion());
		//pongo un pago generico.
		pago.setPrecio(50);
		System.out.println(pago.getPrecio());
		
		//añado la tarjeta al pago
		pago.setTarjetaOrigen(tarjeta);
		pagoManager.addPago(pago);
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		ModelAndView mav = new ModelAndView("factura", "model", myModel);
		
		
		myModel.put("pago", pago);

		return mav;
	}
	
	

}
