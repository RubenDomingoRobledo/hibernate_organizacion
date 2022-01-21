import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexion.Hibernate_Util;

public class Organizacion {
	public static void main(String[] args) {
		try (Session s= Hibernate_Util.getSessionFactory().getCurrentSession()){
			Transaction t = null;
			
			try {
				t = s.beginTransaction();
				
				Proyecto proyecto1 = new Proyecto(LocalDate.of(20, 01, 2022), LocalDate.of(30, 06, 2022), "Creación Aplicacion Web");
				
				Proyecto_sede proyecto_sede1 = new Proyecto_sede(LocalDate.of(20, 01, 2022), LocalDate.of(28, 02, 2022));
				Proyecto_sede proyecto_sede2 = new Proyecto_sede(LocalDate.of(28, 02, 2022), LocalDate.of(7, 05, 2022));
				Proyecto_sede proyecto_sede3 = new Proyecto_sede(LocalDate.of(07, 05, 2022), LocalDate.of(30, 06, 2022));
				Set<Proyecto_sede> proyectos_sede = new HashSet<Proyecto_sede>();
					proyectos_sede.add(proyecto_sede1);
					proyectos_sede.add(proyecto_sede2);
					proyectos_sede.add(proyecto_sede3);
					
				proyecto1.setProyectos_sede(proyectos_sede);
				s.save(proyecto1); 
				
				Sede sedePrincipal = new Sede("Vodafone");
				sedePrincipal.setProyestos_sede(proyectos_sede);
				
				Departamento departamento1 = new Departamento("Contabilidad");
				Set<Empleado> empleados = new HashSet<Empleado>();
				Empleado empleado1 = new Empleado("05236987A","Francisco");
				Empleado empleado2 = new Empleado("07896542B", "Marta");
				empleados.add(empleado1);
				empleados.add(empleado2);
				departamento1.setEmpleados(empleados);
				s.save(departamento1);
						
				s.getTransaction().commit();
		        s.close();
			}
			
			catch (Exception e) {
				e.printStackTrace(System.err);
			    if (t != null) {
			    	t.rollback();
			    }
			}
		}
	}
}
