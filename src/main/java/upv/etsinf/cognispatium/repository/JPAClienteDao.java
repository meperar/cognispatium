package upv.etsinf.cognispatium.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Cliente;

@Repository(value = "ClienteDao")
public class JPAClienteDao implements ClienteDao {

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
    public List<Cliente> getClienteList() {
        return em.createQuery("select c from Cliente c order by c.id").getResultList();
    }

    @Transactional(readOnly = false)
    public void saveCliente(Cliente cliente) {
        em.merge(cliente);
    }

}
