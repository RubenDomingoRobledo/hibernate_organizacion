import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Proyecto_sede {
	private LocalDate f_inicio;
	private LocalDate f_fin;
	private Proyecto proyecto;
	private Sede sede;
	
	public Proyecto_sede() {
	}

	public Proyecto_sede(LocalDate f_inicio, LocalDate f_fin) {
		super();
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
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

	@ManyToOne
    @JoinColumn(name = "id_proy")
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
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
