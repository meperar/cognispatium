package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Professional;

public interface ProfessionalDao {

    public List<Professional> getProfessionalList();

    public void saveProfessional(Professional prof);

}