package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Pago;

@Repository(value = "PagoDao")
public class JPAPagoDao implements PagoDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Pago> getPagoList() {
		return em.createQuery("select p from Pago p order by p.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void savePago(Pago pago) {
		em.merge(pago);

	}

	@Override
	@Transactional(readOnly = true)
	public Pago getPagoById(Integer pagoId) {
		return em.find(Pago.class, pagoId);
	}

}
