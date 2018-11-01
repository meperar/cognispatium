package upv.etsinf.cognispatium.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Mensaje;
import upv.etsinf.cognispatium.domain.Usuario;

@Repository(value = "UsuarioDao")
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
        return em.createQuery("select u from Usuario u order by u.id").getResultList();
    }

    @Transactional(readOnly = false)
    public void saveUsuario(Usuario usuario) {
        em.merge(usuario);
    }
    
    @Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Usuario getUsuariobyId(Integer usuarioId) {
		return em.find(Usuario.class, usuarioId);
	}
    

    
}
