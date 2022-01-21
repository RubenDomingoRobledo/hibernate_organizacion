
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Departamento {
	private int id_dep;
	private String nom_dpto;
	private Set<Empleado> empleados;
	private Sede sede;
	
	public Departamento() {
	}
	
	public Departamento(String nom_dpto) {
		this.nom_dpto = nom_dpto;
	}

	@Id
	@GeneratedValue
	public int getId_dep() {
		return id_dep;
	}

	public void setId_dep(int id_dep) {
		this.id_dep = id_dep;
	}

	public String getNom_dpto() {
		return nom_dpto;
	}

	public void setNom_dpto(String nom_dpto) {
		this.nom_dpto = nom_dpto;
	}

	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}

	@ManyToOne
    @JoinColumn(name = "id_sede")
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
}
