package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upv.etsinf.cognispatium.domain.Solicitud;

@Repository(value = "SolicitudDao")
public class JPASolicitudDao implements SolicitudDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Solicitud> getSolicitudList() {
		return em.createQuery("select s from Solicitud order by s.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveSolicitud(Solicitud solicitud) {
		em.merge(solicitud);

	}

}
