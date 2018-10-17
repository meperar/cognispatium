package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Solicitud;
import upv.etsinf.cognispatium.domain.Tarjeta;

@Repository(value = "TarjetaDao")
public class JPATarjetaDao implements TarjetaDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Tarjeta> getTarjetaList() {
		return em.createQuery("select s from Tarjeta s order by s.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveTarjeta(Tarjeta tarjeta) {
		em.merge(tarjeta);

	}

}
