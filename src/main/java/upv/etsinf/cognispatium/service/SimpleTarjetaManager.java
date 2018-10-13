package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Tarjeta;
import upv.etsinf.cognispatium.repository.TarjetaDao;

@Component
public class SimpleTarjetaManager implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private TarjetaDao tarjetaDao;
	
	
	public List<Tarjeta> getTarjetas() {
		
		return tarjetaDao.getTarjetaList();
	}


	public void setTarjetaDao(TarjetaDao tarjetaDao) {
		this.tarjetaDao = tarjetaDao;
	}
	
	public void addTarjeta(Tarjeta tarjeta) {
		
		tarjetaDao.saveTarjeta(tarjeta);
	}

}
