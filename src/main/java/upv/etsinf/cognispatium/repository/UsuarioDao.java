package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Usuario;

public interface UsuarioDao {

    public List<Usuario> getUsuarioList();

    public void saveUsuario(Usuario usuario);
    
    public Usuario getUsuariobyId(Integer usuarioId);
    
    public void dropUsuario(Usuario usuario);
    
}