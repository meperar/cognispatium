package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Servicio;
import upv.etsinf.cognispatium.domain.Solicitud;


public interface SolicitudDao {

    public List<Solicitud> getSolicitudList();

    public void saveSolicitud(Solicitud solicitud);

	public List<Solicitud> getSolicitudListbyService(Servicio servicioConsulta);

	public Solicitud getSolicitudById(int solicitudId);

}