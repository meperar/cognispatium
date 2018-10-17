package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import upv.etsinf.cognispatium.domain.Servicio;

@Repository(value = "ServicioDao")
public class JPAServicioDao implements ServicioDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Servicio> getServicioList() {
		return em.createQuery("select s from Servicio s order by s.id").getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Servicio> getServiciobyAmbito(String ambitoId) {
		return em.createQuery("select s from Servicio where s.ambito = ambitoId order by s.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveServicio(Servicio servicio) {
		em.merge(servicio);

	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Servicio getServicioById(Integer serviceId) {
		return em.find(Servicio.class, serviceId);
	}

}
