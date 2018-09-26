package upv.etsinf.cognispatium.domain;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="professionals")
public class Professional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	
    @Column
	private String name;
	private String surname;
	private Date birthDate;
	private int benefits;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getBenefits() {
		return benefits;
	}

	public void setBenefits(int benefits) {
		this.benefits = benefits;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Name: " + name + ";");
		buffer.append("Surname: " + surname);
		return buffer.toString();
	}
}