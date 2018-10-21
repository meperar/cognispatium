package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upv.etsinf.cognispatium.domain.Cobro;

@Repository(value = "CobroDao")
public class JPACobroDao implements CobroDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Cobro> getCobroList() {
		return em.createQuery("select c from Cobro c order by c.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveCobro(Cobro cobro) {
		em.merge(cobro);

	}

}
