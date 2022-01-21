
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sede {
	private int id_sede;
	private String nom_sede;
	private Set<Departamento> departamentos;
	private Set<Proyecto_sede> proyestos_sede;
	
	public Sede() {
	}

	public Sede(String nom_sede) {
		this.nom_sede = nom_sede;
	}

	@Id
	@GeneratedValue
	public int getId_sede() {
		return id_sede;
	}

	public void setId_sede(int id_sede) {
		this.id_sede = id_sede;
	}

	public String getNom_sede() {
		return nom_sede;
	}

	public void setNom_sede(String nom_sede) {
		this.nom_sede = nom_sede;
	}

	@OneToMany(mappedBy = "sede", cascade = CascadeType.ALL)
	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	@OneToMany(mappedBy = "sede", cascade = CascadeType.ALL)
	public Set<Proyecto_sede> getProyestos_sede() {
		return proyestos_sede;
	}

	public void setProyestos_sede(Set<Proyecto_sede> proyestos_sede) {
		this.proyestos_sede = proyestos_sede;
	}
}
