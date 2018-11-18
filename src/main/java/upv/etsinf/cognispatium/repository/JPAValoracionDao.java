package upv.etsinf.cognispatium.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Valoracion;

@Repository(value = "ValoracionDao")
public class JPAValoracionDao implements ValoracionDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Valoracion> getValoracionList() {
        return em.createQuery("select v from Valoracion v order by v.id").getResultList();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveValoracion(Valoracion valoracion) {
        em.merge(valoracion);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void dropValoracion(Valoracion valoracion) {
    	em.remove(em.contains(valoracion) ? valoracion : em.merge(valoracion));
    }

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Valoracion getValoracionById(Integer valoracionId) {
			return em.find(Valoracion.class, valoracionId);

	}
	

}
