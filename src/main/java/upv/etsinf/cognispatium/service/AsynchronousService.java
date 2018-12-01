package upv.etsinf.cognispatium.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.EstadoConsulta;
import upv.etsinf.cognispatium.domain.EstadoMensaje;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.web.WebServiceController;

@Service
public class AsynchronousService {
	@Autowired
	private SimpleConsultaUrgenteManager consultaUManager;
	
	@Autowired
	private SimpleAdminManager AdminManager;
	
	@Autowired
	private SimpleMensajeManager mensajeManager;

	@Async
	public void checkCU() throws InterruptedException{
		while (true) {
			// Accion
			List<ConsultaUrgente> listaConsultas = consultaUManager.getConsultaUrgentes();
			listaConsultas.forEach(c -> {
				try {
					checkFecha(c);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			// Comprobar consultas
			try {
				Thread.sleep(120000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	private void checkFecha(ConsultaUrgente c) throws InterruptedException {
		if (c.getFechaFin().isBefore(new Date().getTime()) && c.getEstado() == EstadoConsulta.creada ) {
			//Modificar estado de la consulta
			c.setEstado(EstadoConsulta.noRespondida);
			consultaUManager.addConsultaUrgente(c);	
			//Notificar al cliente
			Mensaje mensaje = new Mensaje();
			mensaje.setDescripcion("Consulta No Resuelta: " + c.getTitulo());
			mensaje.setAsunto("Estimado Usuario le notificamos que la consulta no"+
			" ha sido resuelta en el plazo solicitado"+
			", por lo que procedemos a realizar un reembolso del importe pagado por la consulta (2 EUROS), "+
					"que se reembolsará en su cuenta en el plazo de 72 horas."+
			" Agradecemos su confianza en nosotros y esperamos que haga uso de"+
					" nuestros servicios en un futuro próximo."
					+ "Atentamente, CogniSpatium" );
			mensaje.setUsuarioOrigen(AdminManager.getAdmins().get(0));
			mensaje.setUsuarioDestino(c.getClienteOrigen());
			mensaje.setEstado(EstadoMensaje.noLeido);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			long millis=System.currentTimeMillis();
			java.util.Date date=new java.util.Date(millis);
			dateFormat.format(date);
			mensaje.setFecha(date);
			mensajeManager.addMensaje(mensaje);
		}
	}

}