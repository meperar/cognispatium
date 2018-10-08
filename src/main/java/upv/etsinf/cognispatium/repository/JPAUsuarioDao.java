package upv.etsinf.cognispatium.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Usuario;

@Repository(value = "productDao")
public class JPAUsuarioDao implements UsuarioDao {

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
    public List<Usuario> getUsuarioList() {
        return em.createQuery("select p from Usuario p order by p.id").getResultList();
    }

    @Transactional(readOnly = false)
    public void saveUsuario(Usuario prof) {
        em.merge(prof);
    }

}