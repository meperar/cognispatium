package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Consulta;
import upv.etsinf.cognispatium.domain.Solicitud;

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
		return em.createQuery("select c from Consulta c order by c.id").getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Consulta> getConsultasbyServicio(Integer serviceId) {
        return em.createQuery("SELECT c FROM Consulta c WHERE servicio = :serviceId ")
        .setParameter("serviceId", serviceId)
        .getResultList();
     }
	
	
	@Override
	@Transactional(readOnly = false)
	public void saveConsulta(Consulta consulta) {
		em.merge(consulta);

	}
	
	@Override
	@Transactional(readOnly = false)
	public void dropConsulta(Consulta consulta) {
		em.remove(em.contains(consulta) ? consulta : em.merge(consulta));

	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Consulta> getConsultasByCli(int cliId){
		return em.createQuery("SELECT s FROM Consulta s WHERE s.clienteOrigen LIKE '"+ cliId + "'").getResultList();
	}
	
	@Override
	public Consulta getConsultabyId(int consultaId) {
		return em.find(Consulta.class, consultaId);
	}

}
