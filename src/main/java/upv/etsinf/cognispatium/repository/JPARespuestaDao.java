package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upv.etsinf.cognispatium.domain.Respuesta;

@Repository(value = "RespuestaDao")
public class JPARespuestaDao implements RespuestaDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Respuesta> getRespuestaList() {
		return em.createQuery("select r from Respuesta r order by r.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveRespuesta(Respuesta respuesta) {
		em.merge(respuesta);

	}
	
	@Override
	@Transactional(readOnly = false)
	public void dropRespuesta(Respuesta respuesta) {
		em.remove(em.contains(respuesta) ? respuesta : em.merge(respuesta));

	}

}
