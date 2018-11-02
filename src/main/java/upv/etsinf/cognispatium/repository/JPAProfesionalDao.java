package upv.etsinf.cognispatium.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Profesional;

@Repository(value = "ProfesionalDao")
public class JPAProfesionalDao implements ProfesionalDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Profesional> getProfesionalList() {
        return em.createQuery("select p from Profesional p order by p.id").getResultList();
    }
    
   /* @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Profesional> getProfesionalesbyServicio(Integer serviceId) {
    	
        return em.createQuery("SELECT p FROM Profesional p where servicio = :serviceId ")
        		.setParameter("serviceId", serviceId)
        		.getResultList();
     }*/
    


    @Transactional(readOnly = false)
    public void saveProfesional(Profesional profesional) {
        em.merge(profesional);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void dropProfesional(Profesional profesional) {
    	em.remove(em.contains(profesional) ? profesional : em.merge(profesional));
    }

}
