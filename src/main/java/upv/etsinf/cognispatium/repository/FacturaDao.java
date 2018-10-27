package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Factura;

public interface FacturaDao {

    public List<Factura> getFacturaList();

    public void saveFactura(Factura factura);

}