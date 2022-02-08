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
				Sede sedePrincipal = new Sede("Madrid");
				Sede sedeSecundaria = new Sede("Barcelona");
				sedes.add(sedePrincipal);
				sedes.add(sedeSecundaria);
				
				//Añadimos departamentos a cada sede
				Set<Departamento> departamentosDGT = new HashSet<Departamento>();
				Departamento departamento1 = new Departamento("Contabilidad");
				departamento1.setSede(sedePrincipal);
				Departamento departamento2 = new Departamento("RRHH");
				departamento2.setSede(sedePrincipal);
				Departamento departamento3 = new Departamento("Logistica");
				departamento3.setSede(sedePrincipal);
				departamentosDGT.add(departamento1);
				departamentosDGT.add(departamento2);
				departamentosDGT.add(departamento3);
				sedePrincipal.setDepartamentos(departamentosDGT);
				
				Set<Departamento> departamentosClubCorredores = new HashSet<Departamento>();
				Departamento departamento4 = new Departamento("Clientes");
				departamento4.setSede(sedeSecundaria);
				Departamento departamento5 = new Departamento("Inventario");
				departamento5.setSede(sedeSecundaria);
				Departamento departamento6 = new Departamento("RRHH");
				departamento6.setSede(sedeSecundaria);
				departamentosClubCorredores.add(departamento4);
				departamentosClubCorredores.add(departamento6);
				departamentosClubCorredores.add(departamento5);
				sedeSecundaria.setDepartamentos(departamentosClubCorredores);
				
				//Añadimos empleados con sus datos a cada departamento
				Set<Empleado> empleados1 = new HashSet<Empleado>();
				Empleado empleado1 = new Empleado("05236987A","Francisco");
				empleado1.setDepartamento(departamento1);
				Empleado_datos_prof e1 = new Empleado_datos_prof("Grupo1", 15478.69);
				e1.setEmpleado(empleado1);
				
				Empleado empleado2 = new Empleado("07896542B", "Marta");
				empleado2.setDepartamento(departamento1);
				Empleado_datos_prof e2 = new Empleado_datos_prof("Grupo1", 16548.69);
				e2.setEmpleado(empleado2);
				
				Empleado empleado3 = new Empleado("03264578J", "Julian");
				empleado3.setDepartamento(departamento1);
				Empleado_datos_prof e3 = new Empleado_datos_prof("Grupo1", 16548.69);
				e3.setEmpleado(empleado3);
				
				Empleado empleado4 = new Empleado("04127789O", "Belen");
				empleado4.setDepartamento(departamento1);
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
				empleado5.setDepartamento(departamento2);
				Empleado_datos_prof e5 = new Empleado_datos_prof("Grupo2", 19763.69);
				e5.setEmpleado(empleado5);
				
				Empleado empleado6 = new Empleado("04789632P", "Cristina");
				empleado6.setDepartamento(departamento2);
				Empleado_datos_prof e6 = new Empleado_datos_prof("Grupo2", 17456.69);
				e6.setEmpleado(empleado6);
				
				Empleado empleado7 = new Empleado("02698426U", "Karim");
				empleado7.setDepartamento(departamento2);
				Empleado_datos_prof e7 = new Empleado_datos_prof("Grupo2", 17456.69);
				e7.setEmpleado(empleado7);
				
				Empleado empleado8 = new Empleado("54789632A", "Laura");
				empleado8.setDepartamento(departamento2);
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
				empleado9.setDepartamento(departamento3);
				Empleado_datos_prof e9 = new Empleado_datos_prof("Grupo3", 20763.69);
				e9.setEmpleado(empleado9);
				
				Empleado empleado10 = new Empleado("74896523K", "Violeta");
				empleado10.setDepartamento(departamento3);
				Empleado_datos_prof e10 = new Empleado_datos_prof("Grupo3", 15423.69);
				e10.setEmpleado(empleado10);
				
				Empleado empleado11 = new Empleado("4710698V", "Paula");
				empleado11.setDepartamento(departamento3);
				Empleado_datos_prof e11 = new Empleado_datos_prof("Grupo2", 19763.69);
				e11.setEmpleado(empleado11);
				
				Empleado empleado12 = new Empleado("73581024B", "Zacarias");
				empleado12.setDepartamento(departamento3);
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
				empleado13.setDepartamento(departamento4);
				Empleado_datos_prof e13 = new Empleado_datos_prof("Grupo1", 18763.69);
				e13.setEmpleado(empleado13);
				
				Empleado empleado14 = new Empleado("45630217S", "Juan Carlos");
				empleado14.setDepartamento(departamento4);
				Empleado_datos_prof e14 = new Empleado_datos_prof("Grupo1", 17812.69);
				e14.setEmpleado(empleado14);
				
				Empleado empleado15 = new Empleado("35986421X", "Margarita");
				empleado15.setDepartamento(departamento4);
				Empleado_datos_prof e15 = new Empleado_datos_prof("Grupo2", 16423.69);
				e15.setEmpleado(empleado15);
				
				Empleado empleado16 = new Empleado("56987423G", "David");
				empleado16.setDepartamento(departamento4);
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
				empleado17.setDepartamento(departamento5);
				Empleado_datos_prof e17 = new Empleado_datos_prof("Grupo2", 21763.69);
				e17.setEmpleado(empleado17);
				
				Empleado empleado18 = new Empleado("78412568Z", "Ana");
				empleado18.setDepartamento(departamento5);
				Empleado_datos_prof e18 = new Empleado_datos_prof("Grupo2", 20812.69);
				e18.setEmpleado(empleado18);
				
				Empleado empleado19 = new Empleado("16982030I", "Violeta");
				empleado19.setDepartamento(departamento5);
				Empleado_datos_prof e19 = new Empleado_datos_prof("Grupo3", 15423.69);
				e19.setEmpleado(empleado19);
				
				Empleado empleado20 = new Empleado("00455967N", "Miguel");
				empleado20.setDepartamento(departamento5);
				Empleado_datos_prof e20 = new Empleado_datos_prof("Grupo1", 17812.69);
				e20.setEmpleado(empleado20);
				
				empleados5.add(empleado19);
				empleados5.add(empleado20);
				empleados5.add(empleado17);
				empleados5.add(empleado18);
				departamento5.setEmpleados(empleados5);
				
				//-----------------------------------------------------------------------------	
				Set<Empleado> empleados6 = new HashSet<Empleado>();
				Empleado empleado21 = new Empleado("73495801X","Rafael");
				empleado21.setDepartamento(departamento6);
				Empleado_datos_prof e21 = new Empleado_datos_prof("Grupo1", 22896.69);
				e21.setEmpleado(empleado21);
				
				Empleado empleado22 = new Empleado("05554447G", "Gonzalo");
				empleado22.setDepartamento(departamento6);
				Empleado_datos_prof e22 = new Empleado_datos_prof("Grupo3", 24812.69);
				e22.setEmpleado(empleado22);
				
				Empleado empleado23 = new Empleado("08888997O", "Belen");
				empleado23.setDepartamento(departamento6);
				Empleado_datos_prof e23 = new Empleado_datos_prof("Grupo3", 22423.69);
				e23.setEmpleado(empleado23);
				
				Empleado empleado24 = new Empleado("02223647U", "Pablo");
				empleado24.setDepartamento(departamento6);
				Empleado_datos_prof e24 = new Empleado_datos_prof("Grupo2", 18563.69);
				e24.setEmpleado(empleado24);
				
				empleados6.add(empleado21);
				empleados6.add(empleado22);
				empleados6.add(empleado23);
				empleados6.add(empleado24);
				departamento6.setEmpleados(empleados6);
				
				////Añadimos proyectos a cada sede
				Set<Proyecto> proyectos1 = new HashSet<Proyecto>();
				Proyecto proyecto1 = new Proyecto(LocalDate.of(2022, 01, 20), LocalDate.of(2022, 06, 30), "Proyecto web");
				Proyecto proyecto2 = new Proyecto(LocalDate.of(2022, 07, 02), LocalDate.of(2023, 04, 13), "Proyecto en Android");
				Proyecto proyecto3 = new Proyecto(LocalDate.of(2022, 01, 24), LocalDate.of(2023, 07, 15), "Proyecto en IOS");
				proyectos1.add(proyecto1);
				proyectos1.add(proyecto2);
				proyectos1.add(proyecto3);
				proyecto1.setSedes(sedes);
				sedePrincipal.setProyectos(proyectos1);
				
				Set<Proyecto> proyectos2 = new HashSet<Proyecto>();
				Proyecto proyecto4 = new Proyecto(LocalDate.of(2022, 01, 20), LocalDate.of(2022, 06, 30), "Creacion base de datos e inserciones");
				Proyecto proyecto5 = new Proyecto(LocalDate.of(2022, 07, 02), LocalDate.of(2023, 04, 13), "Creacion interfaz de Android");
				Proyecto proyecto6 = new Proyecto(LocalDate.of(2022, 01, 24), LocalDate.of(2023, 07, 15), "Creacion interfaz de Apple");
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
