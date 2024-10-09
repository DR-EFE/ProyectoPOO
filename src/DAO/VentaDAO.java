package DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Model.CategoriaVenta;
import Model.Pasteles;
import Model.Ventas; // Asegúrate de importar la clase Venta o el nombre correcto de la clase en tu modelo
import Factory.ConnectionFactory;

public class VentaDAO {
	
	public void insertarVenta(Ventas venta) throws Exception {
	    String query = "INSERT INTO ventas (Folio, CantidadVendida, Subtotal, Total, FechaDeVenta, Users_FK, Codigo_Producto, Productos) " +
	                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    Connection connection = ConnectionFactory.getConnection();
	    PreparedStatement ps = connection.prepareStatement(query);

	    ps.setInt(1, venta.getFolio().get());  // Folio
	    ps.setInt(2, venta.getCantidadVendida().get());  // Cantidad vendida
	    ps.setFloat(3, venta.getSubtotal().get());  // Subtotal
	    ps.setFloat(4, venta.getTotal().get());  // Total
	    ps.setDate(5, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));  // Fecha de venta (fecha actual)
	    ps.setInt(6, venta.getUsersFK());  // ID del usuario que realiza la venta (clave foránea)
	    ps.setString(7, venta.getCodigodebarras().get());  // Código de barras del producto
	    ps.setString(8, venta.getProductos().get());  // Nombre del producto

	    ps.executeUpdate();
	}

	
	
	
	   public List<Ventas> obtenerVentas() throws Exception {
	        List<Ventas> ventas = new ArrayList<>();
	        try (Connection connection = ConnectionFactory.getConnection()) {
	        	String query = "SELECT * " +
	                    "FROM pasteleria.ventas " +
	                    "WHERE FechaDeVenta = CURDATE()";
	            try (PreparedStatement statement = connection.prepareStatement(query);
	                 ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                	 int Folio = resultSet.getInt("Folio");
	                	 int CantidadVendida = resultSet.getInt("CantidadVendida");
	                	 Float Subtotal = resultSet.getFloat("Subtotal");
	                	 Float Total = resultSet.getFloat("Total");
	                    String FechaDeVenta = resultSet.getString("FechaDeVenta");
	                    String Productos = resultSet.getString("Productos");
	                    Ventas Venta = new Ventas(Folio,CantidadVendida, Subtotal, Total, FechaDeVenta, Productos );
	                    ventas.add(Venta);
	                }
	            }
	        }
	        return ventas;
	    }
	
	
	   public void actualizarCantidadEnRefri(String codigoBarras, int nuevaCantidad) throws Exception {
		    String query = "UPDATE pasteles SET cantidad_en_refri = ? WHERE codigo_de_barras = ?";
		    Connection connection = ConnectionFactory.getConnection();
		    PreparedStatement ps = connection.prepareStatement(query);
		    
		    ps.setInt(1, nuevaCantidad);
		    ps.setString(2, codigoBarras);
		    
		    ps.executeUpdate();
		}

	
	
/*
	public Ventas buscarVentaPorNombreProducto(String nombreProducto) throws Exception {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    Ventas ventaEncontrada = null;
	   Pasteles Pasti = null;

	    try {
	        connection = ConnectionFactory.getConnection();
	        String query = "SELECT v.*, p.* FROM pasteles p " +
	                       "LEFT JOIN ventas v ON p.codigo_de_barras = v.Codigo_Producto " +
	                       " WHERE p.nombre LIKE ? " +
	                       " ORDER BY LENGTH(p.nombre) ASC " +  // Ordena por longitud del nombre en orden ascendente
	                       " LIMIT 1";
	        System.out.println("Consulta SQL: " + query);

	        statement = connection.prepareStatement(query);
	        statement.setString(1, "%" + nombreProducto + "%");
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            ventaEncontrada = new Ventas(
	                    resultSet.getInt("Folio"),
	                    resultSet.getInt("CantidadVendida"),
	                    resultSet.getFloat("Subtotal"),
	                    resultSet.getFloat("Total"),
	                    resultSet.getString("FechaDeVenta"),
	                    resultSet.getString("nombre"),
	                    resultSet.getString("codigo_de_barras"),
	                    resultSet.getString("categoria_FK")
	            );
	            Pasti = new Pasteles(
	            		  resultSet.getString("codigo_de_barras"),
	            		  resultSet.getString("nombre"),
	                    resultSet.getString("descripcion"),
	                    resultSet.getFloat("precio"),
	                    resultSet.getInt("peso"),
	                    resultSet.getInt("cantidad_en_refri"),
	                    resultSet.getString("fecha_de_elaboracion"),
	                    resultSet.getString("fecha_de_vencimiento"),
	                    resultSet.getString("categoria_FK")
	            );
	            
	            
	            System.out.println("Precio: " + Pasti.getPrecio());
	            System.out.println("nombre: " + ventaEncontrada.getProductos().get());
	            System.out.println("Folio: " + ventaEncontrada.getFolio().get());
	            System.out.println("CantidadVendida: " + ventaEncontrada.getCantidadVendida().get());
	            System.out.println("Subtotal: " + ventaEncontrada.getSubtotal().get());
	            System.out.println("Total: " + ventaEncontrada.getTotal().get());  // Puedes seguir seteando otras propiedades según sea necesario
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.close(resultSet, statement, connection);
	    }

	    return ventaEncontrada  ;
	  
	}
	*/
	
	
	public Pasteles buscarVenta(String nombreProducto) throws Exception {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    
	   Pasteles Pasti = null;

	    try {
	        connection = ConnectionFactory.getConnection();
	        String query = "SELECT * FROM pasteles " +
	                "WHERE nombre LIKE ? " +
	                "ORDER BY LENGTH(nombre) ASC " +  // Ordena por longitud del nombre en orden ascendente
	                "LIMIT 1";

	        System.out.println("Consulta SQL: " + query);

	        statement = connection.prepareStatement(query);
	        statement.setString(1, "%" + nombreProducto + "%");
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	         
	            Pasti = new Pasteles(
	            		  resultSet.getString("codigo_de_barras"),
	            		  resultSet.getString("nombre"),
	                    resultSet.getString("descripcion"),
	                    resultSet.getFloat("precio"),
	                    resultSet.getInt("peso"),
	                    resultSet.getInt("cantidad_en_refri"),
	                    resultSet.getString("fecha_de_elaboracion"),
	                    resultSet.getString("fecha_de_vencimiento"),
	                    resultSet.getString("categoria_FK")
	            );
	            
	            
	            System.out.println("Precio: " + Pasti.getPrecio());
	            System.out.println("nombre: " + Pasti.getNombre().get());
	           
	            System.out.println("Inventario: " + Pasti.getCantidad_en_refri().get());
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.close(resultSet, statement, connection);
	    }

	  
	    return Pasti  ;
	}

	
	public List<Ventas> obtenerVentasDelDiaActual() throws Exception {
        List<Ventas> ventas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener una conexión desde ConnectionFactory
            connection = ConnectionFactory.getConnection();
            // Preparar la consulta SQL
            String query = "SELECT * FROM pasteleria.ventas WHERE FechaDeVenta = CURDATE()";
            statement = connection.prepareStatement(query);
            // Ejecutar la consulta y obtener el conjunto de resultados
            resultSet = statement.executeQuery();
            // Procesar los resultados y crear objetos Ventas
            while (resultSet.next()) {
                int folio = resultSet.getInt("folio");
                int cantidadVendida = resultSet.getInt("cantidadVendida");
                float subtotal = resultSet.getFloat("subtotal");
                float total = resultSet.getFloat("total");
                String fechaVenta = resultSet.getString("fechaDeVenta");
                String productos = resultSet.getString("productos");
                Ventas venta = new Ventas(folio, cantidadVendida, subtotal, total, fechaVenta, productos);
                ventas.add(venta);
            }
        } finally {
            // Cerrar los recursos
            ConnectionFactory.close(resultSet, statement, connection);
        }
        
        return ventas;
    }

	
	
	public int obtenerNuevoFolio() throws Exception {
	    String query = "SELECT MAX(folio) FROM ventas";
	    Connection connection = ConnectionFactory.getConnection();
	    PreparedStatement ps = connection.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
	        return rs.getInt(1) + 1;  // Retornar el siguiente folio disponible
	    } else {
	        return 1;  // Si no hay ventas aún, empezar en 1
	    }
	}

    // Otros métodos y atributos según sea necesario
}
