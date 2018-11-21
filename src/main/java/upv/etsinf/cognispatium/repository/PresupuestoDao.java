package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Presupuesto;

public interface PresupuestoDao {

    public List<Presupuesto> getPresupuestoList();

    public void savePresupuesto(Presupuesto presupuesto);
    
    public void dropPresupuesto(Presupuesto presupuesto);

	public Presupuesto getPresupuestoById(Integer presupuestoId);
	
	public List<Presupuesto> getPresupuestosByProf(Integer profId);

}