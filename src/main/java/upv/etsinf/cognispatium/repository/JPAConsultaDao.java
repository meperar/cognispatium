package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upv.etsinf.cognispatium.domain.Consulta;

@Repository(value = "ConsultaDao")
public class JPAConsultaDao implements ConsultaDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Consulta> getConsultaList() {
		return em.createQuery("select c from Consulta order by c.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveConsulta(Consulta consulta) {
		em.merge(consulta);

	}

}
