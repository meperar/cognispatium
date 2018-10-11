package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Respuesta;

public interface RespuestaDao {

    public List<Respuesta> getRespuestaList();

    public void saveRespuesta(Respuesta respuesta);

}