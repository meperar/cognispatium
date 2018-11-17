package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Cliente;
import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Profesional;

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
	@SuppressWarnings("unchecked")
	public Mensaje getMensajeById(Integer mensajeId) {
		return em.find(Mensaje.class, mensajeId);
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Mensaje> getMensajeByClienteId(Integer clienteId) {
		return em.createQuery("SELECT m FROM Mensaje m WHERE m.cliente LIKE '"+ clienteId + "'").getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Mensaje> getMensajeByProfId(Integer profId) {
		return em.createQuery("SELECT m FROM Mensaje m WHERE m.profesional LIKE '"+ profId + "'").getResultList();
	}
	
}