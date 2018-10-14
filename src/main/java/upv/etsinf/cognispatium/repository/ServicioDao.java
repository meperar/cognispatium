package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Servicio;


public interface ServicioDao {

    public List<Servicio> getServicioList();

    public void saveServicio(Servicio servicio);

}