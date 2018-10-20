package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upv.etsinf.cognispatium.domain.ConsultaUrgente;
import upv.etsinf.cognispatium.domain.Solicitud;

@Repository(value = "ConsultaUrgenteDao")
public class JPAConsultaUrgenteDao implements ConsultaUrgenteDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ConsultaUrgente> getConsultaUrgenteList() {
		return em.createQuery("select c from ConsultaUrgente c order by c.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveConsultaUrgente(ConsultaUrgente consultaUrgente) {
		em.merge(consultaUrgente);

	}

	
	@Override
	public ConsultaUrgente getConsultaUrgenteById(int consultaUrgenteId) {
		return em.find(ConsultaUrgente.class, consultaUrgenteId);
	}
}
