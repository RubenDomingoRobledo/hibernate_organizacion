
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Sede {
	private int id_sede;
	private String nom_sede;
	private Set<Departamento> departamentos;
	private Set<Proyecto> proyectos;
	
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "proyecto_sede",
			joinColumns = @JoinColumn(name = "id_sede"),
			inverseJoinColumns = @JoinColumn(name = "id_proy")
	)
	public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
}
