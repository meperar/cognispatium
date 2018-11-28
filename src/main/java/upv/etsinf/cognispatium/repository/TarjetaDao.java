package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Tarjeta;


public interface TarjetaDao {

    public List<Tarjeta> getTarjetaList();

    public void saveTarjeta(Tarjeta Tarjeta);

    public Tarjeta getTarjetaById(int tarjetaId);

}