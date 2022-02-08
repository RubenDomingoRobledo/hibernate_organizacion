import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexion.Hibernate_Util;

public class ConsultasHQL {
	static Session s= Hibernate_Util.getSessionFactory().openSession();
	static Transaction t = null;
	
	public static void main(String[] args) {
		try {
			t = s.beginTransaction();
			
			System.out.println("1. MOSTRAR SEDES: ");
			ListQuerySede("from Sede");
			
			System.out.println("\n2. MOSTRAR DEPARTAMENTOS: ");
			ListQueryDepartamentos("from Departamento");
			
			System.out.println("\n3. MOSTRAR EMPLEADOS CON SUS DATOS: ");
			ListQueryEmpleados("from Empleado_datos_prof e inner join e.empleado");
			
			System.out.println("\n4. MOSTRAR PROYECTOS: ");
			ListQueryProyectos("from Proyecto");
			
			System.out.println("\n5. NUMERO DE EMPLEADOS Y PROYECTOS POR SEDE");
			//EmpleadosSede("select count(e.dni), count(proyecto.id_proy), sede.nom_sede from Empleado e inner join e.departamento as departamento inner join departamento.sede as sede inner join sede.id_sede as proy_sede inner join proy_sede.id_proy as proyecto group by sede.id_sede");
			
			System.out.println("\n6. MOSTRAR DEPARTAMENTOS A PARTIR DEL NOMBRE: ");
			DepartamentosPorNombre("select count(d.id_dep) from Departamento d where nom_dpto like :nombre","RRHH");
			
			System.out.println("\n7. MOSTRAR EMPLEADO CON MAYOR SUELDO: ");
			EmpleadoMayorSueldo("from Empleado_datos_prof e inner join e.empleado order by sueldo_bruto_anual DESC ");
			
			System.out.println("\n8. MOSTRAR EMPLEADO A PARTIR DEL DNI: ");
			//EmpleadoPorDni("from Empleado_datos_prof p inner join p.empleado where dni like :dni", "05236987A");
			
			System.out.println("\n9. MOSTRAR NUMERO DE EMPLEADOS POR CADA DEPARTAMENTO");
			EmpleadoDepartamento("select count(e.dni), e.departamento.nom_dpto from Empleado e inner join e.departamento group by e.departamento.nom_dpto");
			
			System.out.println("\n10. MOSTRAR NOMBRE Y DNI DE EMPLEADOS A PARTIR DE LA CATEGORIA");
			EmpleadoPorCategoria("select e.empleado.nom_emp, e.empleado.dni, e.categoria from Empleado_datos_prof e inner join e.empleado where e.categoria like: categoria", "Grupo1");
			
			System.out.println("\n11. ACTUALIZAR EMPLEADO POR SUELDO");
			EmpleadoMayorSueldo("from Empleado_datos_prof e inner join e.empleado order by sueldo_bruto_anual DESC ");
			ActualizarEmpleado("update Empleado_datos_prof set sueldo_bruto_anual = :sueldo where dni = :dni");
			EmpleadoMayorSueldo("from Empleado_datos_prof e inner join e.empleado order by sueldo_bruto_anual DESC ");

			System.out.println("\n12. ACTUALIZAR NOMBRE DE PROYECTO A PARTIR DEL ID");
			ActualizarProyecto("update Proyecto set nom_proy = :nombre where id_proy = :id_proy");
			ListQueryProyectos("from Proyecto");
			
			System.out.println("\n13. BORRAR DEPARTAMENTO");
			BorrarDepartamentos("delete from Departamento where id_dep = :id");
			
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
	public static void ListQuerySede(String consulta) {
		Query query = s.createQuery(consulta);
		List<Sede> listaSedes = ((org.hibernate.query.Query) query).list();
		 
		for (Sede sede : listaSedes) {
		    System.out.println("Sede [ID: "+ sede.getId_sede() + "; Nombre: " + sede.getNom_sede() + "]");
		}
	}
	
	public static void ListQueryDepartamentos(String consulta) {
		Query query = s.createQuery(consulta);
		List<Departamento> listaDepartamentos = ((org.hibernate.query.Query) query).list();
		 
		for (Departamento departamento : listaDepartamentos) {
		    System.out.println("Departamento [ID: "+ departamento.getId_dep() + "; Nombre: " + departamento.getNom_dpto() + "]");
		}
	}
	
	public static void ListQueryEmpleados(String consulta){
		Query query = s.createQuery(consulta);
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
			Empleado_datos_prof empleado_datos_prof = (Empleado_datos_prof) fila[0];
		    Empleado empleado = (Empleado) fila[1];
		    System.out.println("Empleado [DNI: "+ empleado.getDni() + "; Nombre: " + empleado.getNom_emp() + "; Categoria: " + empleado_datos_prof.getCategoria() + "; Sueldo Bruto: " + empleado_datos_prof.getSueldo_bruto_anual()+"]");
		}
	}
	
	public static void ListQueryProyectos(String consulta) {
		Query query = s.createQuery(consulta);
		List<Proyecto> listaProyectos = ((org.hibernate.query.Query) query).list();
		 
		for (Proyecto proyecto : listaProyectos) {
			System.out.println("Proyecto [ID: "+ proyecto.getId_proy() + "; Fecha Inicio: " + proyecto.getF_inicio() + "; Fecha Fin: " + proyecto.getF_fin() + "; Nombre: " + proyecto.getNom_proy()+"]");
		}
	}
	
	public static void EmpleadosSede(String consulta){
		Query query = s.createQuery(consulta);
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
		    int numEmpleado = (int) fila[0];
		    int numProyectos = (int) fila[1];
		    String nomSede = (String) fila[2];
		    System.out.println(numEmpleado + " - " + numProyectos + " - " + nomSede);
		}
	}
	
	public static void DepartamentosPorNombre(String consulta, String nombre){
		Query query = s.createQuery(consulta);
		query.setParameter("nombre", "%" + nombre + "%");
		
		int numDepartamentos = (int) ((Number)((org.hibernate.query.Query) query).uniqueResult()).longValue();
		System.out.println("Numero de departamentos con nombre = " + nombre + ": " + numDepartamentos);
		
	}
	
	public static void EmpleadoMayorSueldo(String consulta) {
		Query query = s.createQuery(consulta);
		query.setMaxResults(1);
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
			Empleado_datos_prof empleado_datos_prof = (Empleado_datos_prof) fila[0];
		    Empleado empleado = (Empleado) fila[1];
		    System.out.println("Empleado [DNI: "+ empleado.getDni() + "; Nombre: " + empleado.getNom_emp() + "; Categoria: " + empleado_datos_prof.getCategoria() + "; Sueldo Bruto: " + empleado_datos_prof.getSueldo_bruto_anual()+"]");
		}
	}
	
	public static void EmpleadoPorDni(String consulta, String dni){
		Query query = s.createNativeQuery(consulta);
		query.setParameter("dni", "%" + dni + "%");
		
		List<Object[]> objetos = (List<Object[]>) query.getResultList();
		for (Object[] objeto : objetos) {
			System.out.println("Empleado [DNI: "+ objeto[0] + "; Nombre: " + objeto[1] + "; Categoria: " + objeto[2] + "; Sueldo Bruto: " + objeto[3] +"]");
		}
	}
	
	public static void EmpleadoDepartamento(String consulta) {
		Query query = s.createQuery(consulta);
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
			long suma = (long) fila[0];
			String nombreDepartamento = (String) fila[1];
			System.out.println(nombreDepartamento + " - " + suma);
		}
	}
	
	public static void EmpleadoPorCategoria(String consulta, String categoria) {
		Query query = s.createQuery(consulta);
		query.setParameter("categoria", "%" + categoria + "%");
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
			String dni = (String) fila[0];
			String nombreEmpleado = (String) fila[1];
			System.out.println(dni + " - " + nombreEmpleado);
		}
	}
	
	public static void ActualizarEmpleado(String consulta) {
		Query query = s.createQuery(consulta);
		query.setParameter("sueldo", 25785.46);
		query.setParameter("dni", "73495801X");
		 
		int filasAfectadas = query.executeUpdate();
		if (filasAfectadas > 0) {
		    System.out.println(filasAfectadas + " filas actualizadas.");
		}
	}
	
	public static void ActualizarProyecto(String consulta) {
		Query query = s.createQuery(consulta);
		query.setParameter("nombre", "Queries HQL");
		query.setParameter("id_proy", 7);
		 
		int filasAfectadas = query.executeUpdate();
		if (filasAfectadas > 0) {
		    System.out.println(filasAfectadas + " filas actualizadas.");
		}
	}
	
	public static void BorrarDepartamentos(String consulta){
		Query query = s.createQuery(consulta);
		query.setParameter("id", 9);
		 
		int filasAfectadas = query.executeUpdate();
		if (filasAfectadas > 0) {
		    System.out.println(filasAfectadas + " filas borradas.");
		}
	}
}
