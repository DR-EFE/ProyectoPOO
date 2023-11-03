package DAO;


import Model.CategoriaVenta;
import Factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CategoriaVentaDAO {
    public List<CategoriaVenta> obtenerVentasPorCategoria() throws Exception {
        List<CategoriaVenta> ventasPorCategoria = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT c.Nombre AS Categoria, SUM(v.Subtotal) AS TotalVentas " +
                    "FROM ventas_pasteles vp " +
                    "JOIN pasteles p ON vp.CodigoBarrasPastel_FK = p.codigo_de_barras " +
                    "JOIN categorias c ON p.categoria_FK = c.Nombre " +
                    "JOIN ventas v ON vp.folioVenta_FK = v.Folio " +
                    "GROUP BY c.Nombre";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String categoria = resultSet.getString("Categoria");
                    int totalVentas = resultSet.getInt("TotalVentas");
                    CategoriaVenta categoriaVenta = new CategoriaVenta(categoria, totalVentas);
                    ventasPorCategoria.add(categoriaVenta);
                }
            }
        }
        return ventasPorCategoria;
    }
}

