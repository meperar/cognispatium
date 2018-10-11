package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Cobro;

public interface CobroDao {

    public List<Cobro> getCobroList();

    public void saveCobro(Cobro cobro);

}