package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Pago;
import upv.etsinf.cognispatium.domain.Usuario;

public interface UsuarioManager extends Serializable {
    
    public List<Usuario> getUsuarios();
    
    public void increaseBenefits (int increse);
    
    public Usuario getUsuariobyId(Integer usuarioId);

}