package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Pago;


public interface PagoDao {

    public List<Pago> getPagoList();

    public void savePago(Pago pago);

	public Pago getPagoById(Integer pagoId);

}