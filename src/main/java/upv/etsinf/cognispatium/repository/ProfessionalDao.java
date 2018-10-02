package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Usuario;

public interface ProfessionalDao {

    public List<Usuario> getProfessionalList();

    public void saveProfessional(Usuario prof);

}