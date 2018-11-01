package upv.etsinf.cognispatium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import upv.etsinf.cognispatium.domain.Registro;

@Repository(value = "RegistroDao")
public class JPARegistroDao implements RegistroDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Registro> getRegistroList() {
		return em.createQuery("select r from Registro r order by r.id").getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public void saveRegistro(Registro registro) {
		em.merge(registro);
		
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Registro getRegistrobyId(Integer registroId) {
		return em.find(Registro.class, registroId);
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Registro> getRegistrobyInfo(String username, String contraseña) {
		return em.createQuery("SELECT r FROM Registro r WHERE r.username LIKE '"+ username + "' AND r.contraseña LIKE '" + contraseña + "'").getResultList();
		//.setParameter("prbUsername", username).setParameter("prbContraseña", contraseña).
	}
	
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Registro> getRegistroByUsername(String username) {
		return em.createQuery("SELECT r FROM Registro r WHERE r.username LIKE '"+ username + "'").getResultList();
	}
}
