package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Mensaje;


public interface MensajeDao {

    public List<Mensaje> getMensajeList();

    public void saveMensaje(Mensaje mensaje);

	public Mensaje getMensajeById(Integer mensajeId);
	
	public List<Mensaje> getMensajeByClienteId(Integer clienteId);
	
	public List<Mensaje> getMensajeByProfId(Integer clienteId);

}