package upv.etsinf.cognispatium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Pago;
import upv.etsinf.cognispatium.repository.PagoDao;

@Component
public class SimplePagoManager implements PagoManager{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private PagoDao pagoDao;
	
	
	public List<Pago> getPagos() {
		
		return pagoDao.getPagoList();
	}

	
	public void addPago(Pago pago) {
		
		pagoDao.savePago(pago);
	}
	
	 public Pago getPagobyId(Integer pagoId) {
	    	return  pagoDao.getPagoById(pagoId);
		}
	

	
	
	
	

}
