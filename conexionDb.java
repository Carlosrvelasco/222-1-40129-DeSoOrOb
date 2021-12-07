import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class conexionDb {
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbvecorBueno"; //nombre de la base de datos
    private static final String USUARIO = "root"; //usuario de base de datos
    private static final String CLAVE = "admin"; //contraseña de la base de datos
    public static ResultSet rs;
    public static Statement s;
	public Connection conectar() {
        Connection conexion = null;
        
        try {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión OK");

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        
        return conexion;
    }
	
	public static ArrayList<String> llenarCombosProducto(Connection conexionDb, String query){
		ArrayList<String> comboItems = new ArrayList<String>();
		System.out.println(query);
		try { 
			s = conexionDb.createStatement(); //crea la conexion a la base de datos.
			rs = s.executeQuery(query); //se ejecuta la consulta.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(rs);
			int i=0;
			while (rs.next()) {
	
				
				comboItems.add(rs.getString("producto")); //se llena la lista basado en el resultado de la consulta que se le hizo a la base de datos
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return comboItems;
	}
	
	public static double llenarCombosPrecioVenta(Connection conexionDb, String query){
		double precio = 0.0;
		System.out.println(query);
		try { 
			s = conexionDb.createStatement(); //crea la conexion a la base de datos.
			rs = s.executeQuery(query); //se ejecuta la consulta.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(rs);
			int i=0;
			while (rs.next()) {
	
				
				precio = rs.getDouble("precioVenta"); //se llena la lista basado en el resultado de la consulta que se le hizo a la base de datos
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return precio;
	}
	
	
	public static ArrayList<String> llenarCombosTickets(Connection conexionDb, String query){
		ArrayList<String> comboTicket = new ArrayList<String>();
		System.out.println(query);
		try { 
			s = conexionDb.createStatement(); //crea la conexion a la base de datos.
			rs = s.executeQuery(query); //se ejecuta la consulta.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(rs);
			int i=0;
			while (rs.next()) {
	
				
				comboTicket.add(rs.getString("idTicket")); //se llena la lista basado en el resultado de la consulta que se le hizo a la base de datos
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return comboTicket;
	}
	
	public static String obtenerExistencias(Connection conexion, String query){
		ArrayList<String> listaExistencias = new ArrayList<String>();
		String existencias = ""; 
		System.out.println(query);
		try { 
			s = conexion.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				existencias=rs.getString("existencias");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return existencias;
	}
	
	public static boolean bajaDeRegistro(Connection conexionDb, String query) {
		boolean isBorrado = false;
		try {
			s = conexionDb.createStatement();
			s.executeUpdate(query);
			isBorrado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBorrado;
	} 

	public String obteneridProducto(Connection conexionDb, String query) {
		ArrayList<String> productoList = new ArrayList<String>();
		String id = ""; 
		System.out.println(query);
		try { 
			s = conexionDb.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				id=rs.getString("idProducto");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	
		// TODO Auto-generated method stub
		
	}
	
	
	
	public static ArrayList<String> llenarCombosUsuario(Connection conexionDb, String query){
		ArrayList<String> comboItems = new ArrayList<String>();
		System.out.println(query);
		try { 
			s = conexionDb.createStatement(); //crea la conexion a la base de datos.
			rs = s.executeQuery(query); //se ejecuta la consulta.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(rs);
			int i=0;
			while (rs.next()) {
	
				
				comboItems.add(rs.getString("nombre")); //se llena la lista basado en el resultado de la consulta que se le hizo a la base de datos
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return comboItems;
	}
	
	public String obteneridUsuario(Connection conexionDb, String query) {
		ArrayList<String> UsuarioList = new ArrayList<String>();
		String id = ""; 
		System.out.println(query);
		try { 
			s = conexionDb.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				id=rs.getString("idUsuario");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	
		// TODO Auto-generated method stub
		
	}
	
	
	
	public String obteneridCliente(Connection conexionDb, String query) {
		ArrayList<String> clienteList = new ArrayList<String>();
		String id = ""; 
		System.out.println(query);
		try { 
			s = conexionDb.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				id=rs.getString("idCliente");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	
		// TODO Auto-generated method stub
		
	}
	public static ArrayList<String> llenarCombosClientes(Connection conexionDb, String query){
		ArrayList<String> comboItems = new ArrayList<String>();
		System.out.println(query);
		try { 
			s = conexionDb.createStatement(); //crea la conexion a la base de datos.
			rs = s.executeQuery(query); //se ejecuta la consulta.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(rs);
			int i=0;
			while (rs.next()) {
	
				
				comboItems.add(rs.getString("nombre")); //se llena la lista basado en el resultado de la consulta que se le hizo a la base de datos
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return comboItems;
	}
	
	public static boolean actualizacionDeRegistro(Connection conexion, String query) {
		boolean isUpdate = false;
		try {
			s = conexion.createStatement();
			s.executeUpdate(query);
			isUpdate = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdate;
	} 
	
	public static ArrayList<String> llenarComboTicket(Connection conexionDb, String query){
		ArrayList<String> comboItems = new ArrayList<String>();
		System.out.println(query);
		try { 
			s = conexionDb.createStatement(); //crea la conexion a la base de datos.
			rs = s.executeQuery(query); //se ejecuta la consulta.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(rs);
			int i=0;
			while (rs.next()) {
	
				
				comboItems.add(rs.getString("idTicket")); //se llena la lista basado en el resultado de la consulta que se le hizo a la base de datos
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return comboItems;
	}
	
	
	public static DefaultTableModel llenarInventario (Connection conexionDb, String query){
		DefaultTableModel Model = new DefaultTableModel();
		// System.out.println(query); 
		Model.addColumn("idProducto");
		Model.addColumn("producto");
		Model.addColumn("precioCompra");
		Model.addColumn("precioVenta");
		Model.addColumn("existencias");
		Model.addColumn("idproveedor");
		try { 
			s = conexionDb.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int i=0;
			while (rs.next()) {
				String n,di,p,c,du,h;
				n=String.valueOf(rs.getString("idProducto"));
				di=String.valueOf(rs.getString("producto"));
				p=String.valueOf(rs.getString("precioCompra"));
				c=String.valueOf(rs.getString("precioVenta"));
				du=String.valueOf(rs.getString("existencias"));
				h=String.valueOf(rs.getString("proveedor_idproveedor"));
			
				Model.addRow(new Object[] {n,di,p,c,du,h});
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return Model;
	}
	
	
	public static DefaultTableModel llenarClientes (Connection conexionDb, String query){
		DefaultTableModel Model = new DefaultTableModel();
		// System.out.println(query); 
		Model.addColumn("idCliente");
		Model.addColumn("nombre");
		Model.addColumn("telefono");
		Model.addColumn("domicilio");
		
		try { 
			s = conexionDb.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int i=0;
			while (rs.next()) {
				String n,di,p,c;
				n=String.valueOf(rs.getString("idCliente"));
				di=String.valueOf(rs.getString("nombre"));
				p=String.valueOf(rs.getString("telefono"));
				c=String.valueOf(rs.getString("domicilio"));
			
				Model.addRow(new Object[] {n,di,p,c});
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return Model;
	}
	
}
