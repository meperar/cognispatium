package upv.etsinf.cognispatium.service;

import java.util.List;

import upv.etsinf.cognispatium.domain.Pago;
import upv.etsinf.cognispatium.repository.PagoDao;

public class SimplePagoManager implements PagoManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PagoDao pagoDao;
	
	@Override
	public List<Pago> getPagos() {
		
		return pagoDao.getPagoList();
	}

	@Override
	public void addPago(Pago pago) {
		
		pagoDao.savePago(pago);
	}

}
