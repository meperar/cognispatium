package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Usuario;

public class InMemoryProfessionalDao implements ProfessionalDao {

    private List<Usuario> professionalList;

    public InMemoryProfessionalDao(List<Usuario> professionalList) {
        this.professionalList = professionalList;
    }

    public List<Usuario> getProfessionalList() {
        return professionalList;
    }

    public void saveProfessional(Usuario prof) {
    }

}