package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Professional;

public class InMemoryProfessionalDao implements ProfessionalDao {

    private List<Professional> professionalList;

    public InMemoryProfessionalDao(List<Professional> professionalList) {
        this.professionalList = professionalList;
    }

    public List<Professional> getProfessionalList() {
        return professionalList;
    }

    public void saveProfessional(Professional prof) {
    }

}