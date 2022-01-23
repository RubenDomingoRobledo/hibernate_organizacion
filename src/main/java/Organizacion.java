import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexion.Hibernate_Util;

public class Organizacion {
	public static void main(String[] args) {
		try (Session s= Hibernate_Util.getSessionFactory().openSession()){
			Transaction t = null;

			try {
				t = s.beginTransaction();
				
				Set<Proyecto> proyectos = new HashSet<Proyecto>();
				Proyecto proyecto1 = new Proyecto(LocalDate.of(2022, 01, 20), LocalDate.of(2022, 06, 30), "Creación Aplicacion Web");
				Proyecto proyecto2 = new Proyecto(LocalDate.of(2022, 07, 02), LocalDate.of(2023, 04, 13), "Creación Aplicacion en Android");
				proyectos.add(proyecto1);
				proyectos.add(proyecto2);
				
				Set<Sede> sedes = new HashSet<Sede>();
				Sede sedePrincipal = new Sede("Marca");
				Sede sedeSecundaria = new Sede("As");
				sedes.add(sedePrincipal);
				sedes.add(sedeSecundaria);
				
				proyecto1.setSedes(sedes);
				proyecto2.setSedes(sedes);
				
				sedePrincipal.setProyectos(proyectos);
				sedeSecundaria.setProyectos(proyectos);
				
				Set<Departamento> departamentosPrincipales = new HashSet<Departamento>();
				Departamento departamento1 = new Departamento("Contabilidad");
				Departamento departamento2 = new Departamento("RRHH");
				Departamento departamento3 = new Departamento("Logística");
				departamentosPrincipales.add(departamento1);
				departamentosPrincipales.add(departamento2);
				departamentosPrincipales.add(departamento3);
				
				Set<Departamento> departamentosSecundarios = new HashSet<Departamento>();
				Departamento departamento4 = new Departamento("Clientes");
				Departamento departamento5 = new Departamento("Inventario");
				departamentosSecundarios.add(departamento4);
				departamentosSecundarios.add(departamento2);
				departamentosSecundarios.add(departamento5);
				
				sedePrincipal.setDepartamentos(departamentosPrincipales);
				sedeSecundaria.setDepartamentos(departamentosSecundarios);
				
				Set<Empleado> empleados1 = new HashSet<Empleado>();
				Empleado empleado1 = new Empleado("05236987A","Francisco");
				Empleado empleado2 = new Empleado("07896542B", "Marta");
				empleados1.add(empleado1);
				empleados1.add(empleado2);
				departamento1.setEmpleados(empleados1);
				
				
				Empleado_datos_prof e1 = new Empleado_datos_prof("Grupo1", 15478.69);
				e1.setEmpleado(empleado1);
				Empleado_datos_prof e2 = new Empleado_datos_prof("Grupo1", 16548.69);
				e2.setEmpleado(empleado2);
				
				Set<Empleado> empleados2 = new HashSet<Empleado>();
				Empleado empleado3 = new Empleado("08569485U","Beatriz");
				Empleado empleado4 = new Empleado("45210368K", "Miryam");
				empleados2.add(empleado3);
				empleados2.add(empleado4);
				departamento2.setEmpleados(empleados2);
				
				Empleado_datos_prof e3 = new Empleado_datos_prof("Grupo2", 12478.87);
				e3.setEmpleado(empleado3);
				Empleado_datos_prof e4 = new Empleado_datos_prof("Grupo2", 13456.78);
				e4.setEmpleado(empleado4);
				
				Set<Empleado> empleados3 = new HashSet<Empleado>();
				Empleado empleado5 = new Empleado("045678932L","Juan");
				empleados3.add(empleado5);
				departamento3.setEmpleados(empleados3);
				
				Empleado_datos_prof e5 = new Empleado_datos_prof("Grupo3", 16789.23);
				e5.setEmpleado(empleado5);
				
				s.save(sedePrincipal);
				s.save(sedeSecundaria);
				
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
