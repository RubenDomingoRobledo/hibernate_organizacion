
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Empleado_datos_prof implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoria;
	private Double sueldo_bruto_anual;
	private Empleado empleado;
	
	public Empleado_datos_prof(){
	}

	public Empleado_datos_prof(String categoria, Double sueldo_bruto_anual) {
		this.categoria = categoria;
		this.sueldo_bruto_anual = sueldo_bruto_anual;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getSueldo_bruto_anual() {
		return sueldo_bruto_anual;
	}

	public void setSueldo_bruto_anual(Double sueldo_bruto_anual) {
		this.sueldo_bruto_anual = sueldo_bruto_anual;
	}

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="dni")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}
