import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Proyecto {
	private int id_proy;
	private LocalDate f_inicio;
	private LocalDate f_fin;
	private String nom_proy;
	private Set<Sede> sedes;
	
	public Proyecto() {
	}

	public Proyecto( LocalDate f_inicio, LocalDate f_fin, String nom_proy) {
		super();
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.nom_proy = nom_proy;
	}

	@Id
	@GeneratedValue
	public int getId_proy() {
		return id_proy;
	}

	public void setId_proy(int id_proy) {
		this.id_proy = id_proy;
	}

	public LocalDate getF_inicio() {
		return f_inicio;
	}

	public void setF_inicio(LocalDate f_inicio) {
		this.f_inicio = f_inicio;
	}

	public LocalDate getF_fin() {
		return f_fin;
	}

	public void setF_fin(LocalDate f_fin) {
		this.f_fin = f_fin;
	}

	public String getNom_proy() {
		return nom_proy;
	}

	public void setNom_proy(String nom_proy) {
		this.nom_proy = nom_proy;
	}

	@ManyToMany(mappedBy = "proyectos")
	public Set<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(Set<Sede> sedes) {
		this.sedes = sedes;
	}
}
