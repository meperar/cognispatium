package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.EstadoMensaje;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Usuario;

@Repository(value = "MensajeDao")
public class JPAMensajeDao implements MensajeDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Mensaje> getMensajeList() {
		return em.createQuery("select m from Mensaje m order by m.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveMensaje(Mensaje mensaje) {
		em.merge(mensaje);

	}

	@Override
	@Transactional(readOnly = true)
	public Mensaje getMensajeById(Integer mensajeId) {
		return em.find(Mensaje.class, mensajeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mensaje> getMensajesbyUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return em.createQuery(
			    "SELECT m FROM Mensaje m WHERE m.usuarioDestino LIKE :usuarioDestino and (m.estado LIKE :estadoleido  or m.estado LIKE :estadonoleido)" )
			    .setParameter("usuarioDestino", usuario)
			    .setParameter("estadoleido", EstadoMensaje.leido)
			    .setParameter("estadonoleido", EstadoMensaje.noLeido)
			    .getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mensaje> getMensajesNoLeidosbyUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return em.createQuery(
			    "SELECT m FROM Mensaje m WHERE m.usuarioDestino LIKE :usuarioDestino and m.estado LIKE :estado")
			    .setParameter("usuarioDestino", usuario).setParameter("estado", EstadoMensaje.noLeido)
			    .getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mensaje> getMensajesLeidosbyUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return em.createQuery(
			    "SELECT m FROM Mensaje m WHERE m.usuarioDestino LIKE :usuarioDestino and m.estado LIKE :estado")
			    .setParameter("usuarioDestino", usuario).setParameter("estado", EstadoMensaje.leido)
			    .getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Mensaje> getMensajesEliminadosbyUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return em.createQuery(
			    "SELECT m FROM Mensaje m WHERE m.usuarioDestino LIKE :usuarioDestino and m.estado LIKE :estado")
			    .setParameter("usuarioDestino", usuario).setParameter("estado", EstadoMensaje.eliminado)
			    .getResultList();
	}

}