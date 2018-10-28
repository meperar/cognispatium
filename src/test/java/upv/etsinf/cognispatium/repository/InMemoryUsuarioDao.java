package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Usuario;

public class InMemoryUsuarioDao implements UsuarioDao {

    private List<Usuario> usuarioList;

    public InMemoryUsuarioDao(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void saveUsuario(Usuario user) {
    }
    
    public Usuario getUsuariobyId(Integer usuarioId) {
    	return null;
    	
    };

}