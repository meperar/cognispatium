package upv.etsinf.cognispatium.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Admin;

@Repository(value = "AdminDao")
public class JPAAdminDao implements AdminDao {

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
    public List<Admin> getAdminList() {
        return em.createQuery("select a from Admin a order by a.id").getResultList();
    }

    @Transactional(readOnly = false)
    public void saveAdmin(Admin admin) {
        em.merge(admin);
    }

}
