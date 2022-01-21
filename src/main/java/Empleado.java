
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Empleado {
	private String dni;
	private String nom_emp;
	private Departamento departamento;
	
	public Empleado() {
	}

	public Empleado(String dni, String nom_emp) {
		this.dni = dni;
		this.nom_emp = nom_emp;
	}
	
	@Id
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom_emp() {
		return nom_emp;
	}

	public void setNom_emp(String nom_emp) {
		this.nom_emp = nom_emp;
	}

	@ManyToOne
    @JoinColumn(name = "id_dep")
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
