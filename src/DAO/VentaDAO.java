package DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Pasteles;
import Model.Ventas; // Asegúrate de importar la clase Venta o el nombre correcto de la clase en tu modelo
import Factory.ConnectionFactory;

public class VentaDAO {

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
	            
	            
	            System.out.println("Precio: " + Pasti.getPrecio().get());
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
	
	public Pasteles buscarVenta(String nombreProducto) throws Exception {
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
	            
	            
	            System.out.println("Precio2: " + Pasti.getPrecio().get());
	            System.out.println("nombre2: " + ventaEncontrada.getProductos().get());
	            System.out.println("Folio2: " + ventaEncontrada.getFolio().get());
	            System.out.println("CantidadVendida2: " + ventaEncontrada.getCantidadVendida().get());
	            System.out.println("Subtotal2: " + ventaEncontrada.getSubtotal().get());
	            System.out.println("Total2: " + ventaEncontrada.getTotal().get());  // Puedes seguir seteando otras propiedades según sea necesario
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.close(resultSet, statement, connection);
	    }

	  
	    return Pasti  ;
	}




    // Otros métodos y atributos según sea necesario
}
