package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Valoracion;

public interface ValoracionDao {

    public List<Valoracion> getValoracionList();

    public void saveValoracion(Valoracion valoracion);
    
    public void dropValoracion(Valoracion valoracion);
    
	public Valoracion getValoracionById(Integer valoracionId);
}