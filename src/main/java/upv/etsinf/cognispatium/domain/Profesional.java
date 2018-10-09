package upv.etsinf.cognispatium.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Profesional extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
