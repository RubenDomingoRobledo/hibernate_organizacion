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
				
				//1. Añadimos sedes
				Set<Sede> sedes = new HashSet<Sede>();
				Sede sedePrincipal = new Sede("DGT");
				Sede sedeSecundaria = new Sede("Club Corredores Juan Carlos I");
				sedes.add(sedePrincipal);
				sedes.add(sedeSecundaria);
				
				//Añadimos departamentos a cada sede
				Set<Departamento> departamentosDGT = new HashSet<Departamento>();
				Departamento departamento1 = new Departamento("Contabilidad");
				Departamento departamento2 = new Departamento("RRHH");
				Departamento departamento3 = new Departamento("Logistica");
				departamentosDGT.add(departamento1);
				departamentosDGT.add(departamento2);
				departamentosDGT.add(departamento3);
				sedePrincipal.setDepartamentos(departamentosDGT);
				
				Set<Departamento> departamentosClubCorredores = new HashSet<Departamento>();
				Departamento departamento4 = new Departamento("Clientes");
				Departamento departamento5 = new Departamento("Inventario");
				departamentosClubCorredores.add(departamento4);
				departamentosClubCorredores.add(departamento2);
				departamentosClubCorredores.add(departamento5);
				sedeSecundaria.setDepartamentos(departamentosClubCorredores);
				
				//Añadimos empleados con sus datos a cada departamento
				Set<Empleado> empleados1 = new HashSet<Empleado>();
				Empleado empleado1 = new Empleado("05236987A","Francisco");
				Empleado_datos_prof e1 = new Empleado_datos_prof("Grupo1", 15478.69);
				e1.setEmpleado(empleado1);
				
				Empleado empleado2 = new Empleado("07896542B", "Marta");
				Empleado_datos_prof e2 = new Empleado_datos_prof("Grupo1", 16548.69);
				e2.setEmpleado(empleado2);
				
				Empleado empleado3 = new Empleado("03264578J", "Julian");
				Empleado_datos_prof e3 = new Empleado_datos_prof("Grupo1", 16548.69);
				e3.setEmpleado(empleado3);
				
				Empleado empleado4 = new Empleado("04127789O", "Belen");
				Empleado_datos_prof e4 = new Empleado_datos_prof("Grupo1", 15478.69);
				e4.setEmpleado(empleado4);
				
				empleados1.add(empleado1);
				empleados1.add(empleado2);
				empleados1.add(empleado3);
				empleados1.add(empleado4);
				departamento1.setEmpleados(empleados1);
				
					//-----------------------------------------------------------------------------	
				Set<Empleado> empleados2 = new HashSet<Empleado>();
				Empleado empleado5 = new Empleado("05693247Y","Ruben");
				Empleado_datos_prof e5 = new Empleado_datos_prof("Grupo2", 19763.69);
				e5.setEmpleado(empleado5);
				
				Empleado empleado6 = new Empleado("04789632P", "Cristina");
				Empleado_datos_prof e6 = new Empleado_datos_prof("Grupo2", 17456.69);
				e6.setEmpleado(empleado6);
				
				Empleado empleado7 = new Empleado("02698426U", "Karim");
				Empleado_datos_prof e7 = new Empleado_datos_prof("Grupo2", 17456.69);
				e7.setEmpleado(empleado7);
				
				Empleado empleado8 = new Empleado("54789632A", "Laura");
				Empleado_datos_prof e8 = new Empleado_datos_prof("Grupo2", 19763.69);
				e8.setEmpleado(empleado8);
				
				empleados2.add(empleado5);
				empleados2.add(empleado6);
				empleados2.add(empleado7);
				empleados2.add(empleado8);
				departamento2.setEmpleados(empleados2);
				
				//-----------------------------------------------------------------------------	
				Set<Empleado> empleados3 = new HashSet<Empleado>();
				Empleado empleado9 = new Empleado("01862305T","Marcos");
				Empleado_datos_prof e9 = new Empleado_datos_prof("Grupo3", 20763.69);
				e9.setEmpleado(empleado9);
				
				Empleado empleado10 = new Empleado("74896523K", "Violeta");
				Empleado_datos_prof e10 = new Empleado_datos_prof("Grupo3", 15423.69);
				e10.setEmpleado(empleado10);
				
				Empleado empleado11 = new Empleado("4710698V", "Paula");
				Empleado_datos_prof e11 = new Empleado_datos_prof("Grupo2", 19763.69);
				e11.setEmpleado(empleado11);
				
				Empleado empleado12 = new Empleado("73581024B", "Zacarias");
				Empleado_datos_prof e12 = new Empleado_datos_prof("Grupo2", 17456.69);
				e12.setEmpleado(empleado12);
				
				empleados3.add(empleado11);
				empleados3.add(empleado12);
				empleados3.add(empleado9);
				empleados3.add(empleado10);
				departamento3.setEmpleados(empleados3);
				
				//-----------------------------------------------------------------------------	
				Set<Empleado> empleados4 = new HashSet<Empleado>();
				Empleado empleado13 = new Empleado("27896325M","Hector");
				Empleado_datos_prof e13 = new Empleado_datos_prof("Grupo1", 18763.69);
				e13.setEmpleado(empleado13);
				
				Empleado empleado14 = new Empleado("45630217S", "Juan Carlos");
				Empleado_datos_prof e14 = new Empleado_datos_prof("Grupo1", 17812.69);
				e14.setEmpleado(empleado14);
				
				Empleado empleado15 = new Empleado("35986421X", "Margarita");
				Empleado_datos_prof e15 = new Empleado_datos_prof("Grupo2", 16423.69);
				e15.setEmpleado(empleado15);
				
				Empleado empleado16 = new Empleado("56987423G", "David");
				Empleado_datos_prof e16 = new Empleado_datos_prof("Grupo3", 15423.69);
				e16.setEmpleado(empleado16);
				
				empleados4.add(empleado15);
				empleados4.add(empleado16);
				empleados4.add(empleado13);
				empleados4.add(empleado14);
				departamento4.setEmpleados(empleados4);
				
				//-----------------------------------------------------------------------------	
				Set<Empleado> empleados5 = new HashSet<Empleado>();
				Empleado empleado17 = new Empleado("6321547L","Maria");
				Empleado_datos_prof e17 = new Empleado_datos_prof("Grupo2", 21763.69);
				e17.setEmpleado(empleado17);
				
				Empleado empleado18 = new Empleado("78412568Z", "Ana");
				Empleado_datos_prof e18 = new Empleado_datos_prof("Grupo2", 20812.69);
				e18.setEmpleado(empleado18);
				
				Empleado empleado19 = new Empleado("16982030I", "Violeta");
				Empleado_datos_prof e19 = new Empleado_datos_prof("Grupo3", 15423.69);
				e19.setEmpleado(empleado19);
				
				Empleado empleado20 = new Empleado("00455967N", "Miguel");
				Empleado_datos_prof e20 = new Empleado_datos_prof("Grupo1", 17812.69);
				e20.setEmpleado(empleado20);
				
				empleados5.add(empleado19);
				empleados5.add(empleado20);
				empleados5.add(empleado17);
				empleados5.add(empleado18);
				departamento5.setEmpleados(empleados5);
				
				////Añadimos proyectos a cada sede
				Set<Proyecto> proyectos1 = new HashSet<Proyecto>();
				Proyecto proyecto1 = new Proyecto(LocalDate.of(2022, 01, 20), LocalDate.of(2022, 06, 30), "Examen condución por Badajoz");
				Proyecto proyecto2 = new Proyecto(LocalDate.of(2022, 07, 02), LocalDate.of(2023, 04, 13), "Clases de conducir Mostoles");
				Proyecto proyecto3 = new Proyecto(LocalDate.of(2022, 01, 24), LocalDate.of(2023, 07, 15), "Clases de conducir poligon industrial Mostoles");
				proyectos1.add(proyecto1);
				proyectos1.add(proyecto2);
				proyectos1.add(proyecto3);
				proyecto1.setSedes(sedes);
				sedePrincipal.setProyectos(proyectos1);
				
				Set<Proyecto> proyectos2 = new HashSet<Proyecto>();
				Proyecto proyecto4 = new Proyecto(LocalDate.of(2022, 01, 20), LocalDate.of(2022, 06, 30), "Competicion triple salto");
				Proyecto proyecto5 = new Proyecto(LocalDate.of(2022, 07, 02), LocalDate.of(2023, 04, 13), "Competicion salto longitud");
				Proyecto proyecto6 = new Proyecto(LocalDate.of(2022, 01, 24), LocalDate.of(2023, 07, 15), "Competicion salto de altura");
				proyectos2.add(proyecto4);
				proyectos2.add(proyecto5);
				proyectos2.add(proyecto6);
				proyecto2.setSedes(sedes);
				sedeSecundaria.setProyectos(proyectos2);
				
				//Guardamos sedes y datos de empleados
				s.save(sedePrincipal);
				s.save(sedeSecundaria);
				s.save(e1);
				s.save(e2);
				s.save(e3);
				s.save(e4);
				s.save(e5);
				s.save(e6);
				s.save(e7);
				s.save(e8);
				s.save(e9);
				s.save(e10);
				s.save(e11);
				s.save(e12);
				s.save(e13);
				s.save(e14);
				s.save(e15);
				s.save(e16);
				s.save(e17);
				s.save(e18);
				s.save(e19);
				s.save(e20);
				
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
