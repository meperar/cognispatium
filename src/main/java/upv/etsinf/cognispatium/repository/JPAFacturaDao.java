package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upv.etsinf.cognispatium.domain.Factura;

@Repository(value = "FacturaDao")
public class JPAFacturaDao implements FacturaDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Factura> getFacturaList() {
		return em.createQuery("select f from Factura f order by f.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveFactura(Factura factura) {
		em.merge(factura);

	}

}
