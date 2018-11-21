package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Presupuesto;

@Repository(value = "PresupuestoDao")
public class JPAPresupuestoDao implements PresupuestoDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Presupuesto> getPresupuestoList() {
		return em.createQuery("select p from Presupuesto p order by p.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void savePresupuesto(Presupuesto presupuesto) {
		em.merge(presupuesto);

	}
	
	@Override
	@Transactional(readOnly = false)
	public void dropPresupuesto(Presupuesto presupuesto) {
		em.remove(em.contains(presupuesto) ? presupuesto : em.merge(presupuesto));
	}

	@Override
	public Presupuesto getPresupuestoById(Integer presupuestoId) {
		return em.find(Presupuesto.class,presupuestoId);
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Presupuesto> getPresupuestosByProf(Integer profId) {
		return em.createQuery("SELECT p FROM Presupuesto p WHERE p.profesionalOrigen LIKE '"+ profId + "'").getResultList();
	}
}
