package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Usuario;


public interface MensajeDao {

    public List<Mensaje> getMensajeList();

    public void saveMensaje(Mensaje mensaje);

	public Mensaje getMensajeById(Integer mensajeId);

	public List<Mensaje> getMensajesbyUsuario(Usuario usuario);

	public List<Mensaje> getMensajesNoLeidosbyUsuario(Usuario usuario);

	public List<Mensaje> getMensajesLeidosbyUsuario(Usuario usuario);
	
	public List<Mensaje> getMensajesEliminadosbyUsuario(Usuario usuario);
	
	

}