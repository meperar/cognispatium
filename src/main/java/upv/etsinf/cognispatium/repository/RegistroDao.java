package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Registro;

public interface RegistroDao {

	public List<Registro> getRegistroList();

    public void saveRegistro(Registro registro);
    
    public Registro getRegistrobyId(Integer registroId);
    
    public List<Registro> getRegistrobyInfo(String username, String contraseña);
    
    public List<Registro> getRegistrobyUsuario(Integer usuarioId);
    
    public List<Registro> getRegistrobyUN(String username);
}
