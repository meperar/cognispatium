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
import org.springframework.web.util.WebUtils;

import upv.etsinf.cognispatium.domain.EstadoPresupuesto;
import upv.etsinf.cognispatium.domain.EstadoSolicitud;
import upv.etsinf.cognispatium.domain.Presupuesto;
import upv.etsinf.cognispatium.domain.Profesional;
import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Usuario;
import upv.etsinf.cognispatium.service.SimplePresupuestoManager;
import upv.etsinf.cognispatium.service.SimpleProfesionalManager;
import upv.etsinf.cognispatium.service.SimpleServicioManager;
import upv.etsinf.cognispatium.service.SimpleSolicitudManager;

@Controller
public class ListadoPresupuestoController {
    
    @Autowired
    private SimplePresupuestoManager presupuestoManager;
    
    @Autowired
    private SimpleProfesionalManager simpleProfesionalManager;
    
    @Autowired
	private SimpleServicioManager servicioManager;
    
    @Autowired
    private SimpleSolicitudManager solicitudManager;

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    
    @GetMapping("/listadoPresupuestos.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ModelAndView mav = new ModelAndView("listadoPresupuestos");
        Map<String, Object> myModel = new HashMap<String, Object>();

        Profesional profesional_resgistrado = simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
        List<Presupuesto> listaPresupuestos = profesional_resgistrado.getPresupuestos()
                .stream().sorted(Comparator.comparing(Presupuesto::getId).reversed())
                .collect(Collectors.toList());
        myModel.put("presupuestos", listaPresupuestos);
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
    @PostMapping("/listadoPresupuestos.htm")
    protected ModelAndView crearPresupueusto(@RequestParam Map<String, String> reqPar,HttpServletRequest request) throws Exception {

        Map<String, Object> myModel = new HashMap<String, Object>();
        Presupuesto miPresupuesto = presupuestoManager.getPresupuestobyId(Integer.parseInt(reqPar.get("presupuestoId")));
        Solicitud solicitudOrigen = miPresupuesto.getSolicitudOrigen();
        
        if(WebUtils.hasSubmitParameter(request, "info")) {
        ModelAndView mav = new ModelAndView("presupuestoDelProfesional", "model", myModel);
        myModel.put("presupuesto", miPresupuesto);
        myModel.put("solicitud", solicitudOrigen);
        
     // Gestion usuario registrado y barra superior
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
        
 /* pulso el boton de resolver presupuesto */
        else {
            
            if (miPresupuesto.getEstado() == EstadoPresupuesto.aceptado) {
                miPresupuesto.setEstado(EstadoPresupuesto.aceptado_profesional);
                solicitudOrigen.setEstado(EstadoSolicitud.aceptado_profesional);
            }
            else if(miPresupuesto.getEstado() == EstadoPresupuesto.aceptado_cliente) {
                miPresupuesto.setEstado(EstadoPresupuesto.resuelto);
                solicitudOrigen.setEstado(EstadoSolicitud.resuelta);
            }
            presupuestoManager.addPresupuesto(miPresupuesto);
            solicitudManager.addSolicitud(solicitudOrigen);
 /* Fin funcionalidad resolver presupuesto*/
            
            ModelAndView mav = new ModelAndView("listadoPresupuestos");

            Profesional profesional_resgistrado = simpleProfesionalManager.getProfesionalById(WebServiceController.usuarioRegistrado.getId());
            List<Presupuesto> listaPresupuestos = profesional_resgistrado.getPresupuestos()
                    .stream().sorted(Comparator.comparing(Presupuesto::getId).reversed())
                    .collect(Collectors.toList());
            myModel.put("presupuestos", listaPresupuestos);
            mav.addObject("model", myModel);
            
         // Gestion usuario registrado y barra superior
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
  
}
