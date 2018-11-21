package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Profesional;
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
		return em.createQuery("SELECT s FROM Servicio s WHERE s.ambito LIKE :ambt")
				.setParameter("ambt", ambitoId)
				.getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Profesional> getProfesionales(Integer servicioId) {
		return em.createQuery("SELECT s FROM Servicio s "+" join fetch s.profesionales "+"WHERE s.id = :serv")
				.setParameter("serv", servicioId)
				.getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Profesional> getProfsbyServicio(Integer serviceId) {
		return em.createQuery("SELECT p FROM Servicio s WHERE p.servicio LIKE :serviceId")
				.setParameter("serviceId", serviceId)
				.getResultList();
	}
	
	@Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Servicio> getServicioByProfesional(Integer profesionalId) {
    	
        return em.createQuery("SELECT p FROM Servicio s WHERE p.profesional LIKE :profesionalId ")
        		.setParameter("profesionalId", profesionalId)
        		.getResultList();
     }
	

	@Override
	@Transactional(readOnly = false)
	public void saveServicio(Servicio servicio) {
		em.merge(servicio);

	}
	
	@Override
	@Transactional(readOnly = true)
	public Servicio getServicioById(Integer serviceId) {
		return em.find(Servicio.class, serviceId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAmbitos() {
		return em.createQuery("select DISTINCT s.ambito from Servicio s order by s.ambito").getResultList();
	}

}
