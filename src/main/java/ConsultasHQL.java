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
			
			//GroupByQuery("select count(p.dni), count(p.id_proy) from Empleado p group by sede");
			
			System.out.println("\n6. MOSTRAR DEPARTAMENTOS A PARTIR DEL NOMBRE: ");
			DepartamentosPorNombre("select count(d.id_dep) from Departamento d where nom_dpto like :nombre","RRHH");
			
			System.out.println("\n7. MOSTRAR EMPLEADO CON MAYOR SUELDO: ");
			EmpleadoMayorSueldo("from Empleado_datos_prof e inner join e.empleado order by sueldo_bruto_anual DESC ");
			
			//EmpleadoPorDni("from Empleado_datos_prof p inner join p.empleado where dni like :dni", "05236987A");
			
			System.out.println("\n9. MOSTRAR NUMERO DE EMPLEADOS POR CADA DEPARTAMENTO");
			EmpleadoDepartamento("select count(e.dni), e.departamento.nom_dpto from Empleado e inner join e.departamento group by e.departamento.nom_dpto");
			
			System.out.println("\n10. MOSTRAR NOMBRE Y DNI DE EMPLEADOS A PARTIR DE LA CATEGORIA");
			EmpleadoPorCategoria("select e.empleado.nom_emp, e.empleado.dni, e.categoria from Empleado_datos_prof e inner join e.empleado where e.categoria like: categoria", "Grupo1");
			
			System.out.println("\n12. ACTUALIZAR NOMBRE DE PROYECTO A PARTIR DEL ID");
			ActualizarProyecto("update Proyecto set nom_proy = :nombre where id_proy = :id_proy");
			ListQueryProyectos("from Proyecto");
			
			System.out.println("\n13. BORRAR DEPARTAMENTO");
			BorrarDepartamentos("delete from Departamento where id_dep = :id");
			/*SearchQuery("from Producto where categoria.nombre = 'Lacteos'\n");
			QueryParametrizada("from Producto where descripcion like :clave","lentejas");
			InsertSelectQuery("insert into Categoria (id, nombre)" + " select id, nombre from Producto");
			UpdateQuery("update Producto set precio = :precio where id = :id");
			DeleteQuery("delete from Categoria where id = :id");
			JoinQuery("from Producto p inner join p.categoria");
			OrderByQuery("from Producto order by precio ASC");
			GroupByQuery("select sum(p.precio), p.categoria.nombre from Producto p group by categoria");
			PaginationQuery("from Producto");
			*/
			
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
	
	public static void GroupByQuery(String consulta){
		Query query = s.createQuery(consulta);
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
		    int numEmpleado = (int) fila[0];
		    int numProyectos = (int) fila[1];
		    System.out.println("");
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
		
		Object[] objeto = (Object[]) query.getSingleResult();
		
		System.out.println("Empleado [DNI: "+ objeto[0] + "; Nombre: " + objeto[1] + "; Categoria: " + objeto[2] + "; Sueldo Bruto: " + objeto[3] +"]");
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
		query.setParameter("id", 11);
		 
		int filasAfectadas = query.executeUpdate();
		if (filasAfectadas > 0) {
		    System.out.println(filasAfectadas + " filas borradas.");
		}
	}
	/*
	public static void QueryParametrizada(String consulta, String clave){
		Query query = s.createQuery(consulta);
		query.setParameter("clave", "%" + clave + "%");
		List<Producto> productos = ((org.hibernate.query.Query) query).list();
		 
		for (Producto p : productos) {
		    System.out.println(p.getNombre());
		}
	}
	
	public static void InsertSelectQuery(String consulta){
		Query query = s.createQuery(consulta);
		int filasAfectadas = query.executeUpdate();
		if (filasAfectadas > 0) {
		    System.out.println(filasAfectadas + " filas insertadas.");
		}
	}
	
	public static void UpdateQuery(String consulta){
		Query query = s.createQuery(consulta);
		query.setParameter("precio", 488.0f);
		query.setParameter("id", 2l);
		 
		int filasAfectadas = query.executeUpdate();
		if (filasAfectadas > 0) {
		    System.out.println(filasAfectadas + " filas actualizadas.");
		}
	}
	
	public static void DeleteQuery(String consulta){
		Query query = s.createQuery(consulta);
		query.setParameter("id", new Long(6));
		 
		int filasAfectadas = query.executeUpdate();
		if (filasAfectadas > 0) {
		    System.out.println(filasAfectadas + " filas borradas.");
		}
	}
	
	public static void JoinQuery(String consulta){
		Query query = s.createQuery(consulta);
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
		    Producto producto = (Producto) fila[0];
		    Categoria categoria = (Categoria) fila[1];
		    System.out.println(producto.getNombre() + " - " + categoria.getNombre());
		}
	}
	
	public static void OrderByQuery(String consulta) {
		Query query = s.createQuery(consulta);
		List<Producto> listaProductos = ((org.hibernate.query.Query) query).list();
		 
		for (Producto p : listaProductos) {
		    System.out.println(p.getNombre() + "\t - " + p.getPrecio());
		}
	}
	
	public static void GroupByQuery(String consulta){
		Query query = s.createQuery(consulta);
		List<Object[]> objetos = ((org.hibernate.query.Query) query).list();
		
		for (Object[] fila : objetos) {
		    Double suma = (Double) fila[0];
		    String categoria = (String) fila[1];
		    System.out.println(categoria + " - " + suma);
		}
	}
	
	public static void PaginationQuery(String consulta){
		Query query = s.createQuery(consulta);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Producto> productos = ((org.hibernate.query.Query) query).list();
		 
		for (Producto p : productos) {
		    System.out.println(p.getNombre() + "\t - " + p.getPrecio());
		}
	}
	
	public static void DataRangeQuery(String consulta) throws ParseException{
		Query query = s.createQuery(consulta);
		
		SimpleDateFormat formateoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaComienzo = formateoFecha.parse("2014-11-01");
		query.setParameter("fechaComienzo", fechaComienzo);
		 
		Date fechaFin = formateoFecha.parse("2014-11-22");
		query.setParameter("fechaFin", fechaFin);
		 
		List<Pedido> pedidos = ((org.hibernate.query.Query) query).list();
		 
		for (Pedido pedido : pedidos) {
		    System.out.println(pedido.getProducto().getNombre() + " - " +  pedido.getCantidad() + " - "+ pedido.getFechaPedido());
		}
	}
	*/
}
