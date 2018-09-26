package upv.etsinf.cognispatium.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Professional;

@Repository(value = "productDao")
public class JPAProfessionalDao implements ProfessionalDao {

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
    public List<Professional> getProfessionalList() {
        return em.createQuery("select p from Professional p order by p.id").getResultList();
    }

    @Transactional(readOnly = false)
    public void saveProfessional(Professional prof) {
        em.merge(prof);
    }

}
