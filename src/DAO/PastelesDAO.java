package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PastelesDAO {
    private Connection connection;

    public PastelesDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean eliminarPastel(String codigoBarras, String categoria) throws SQLException {
        try {
            String deleteVentasSql = "DELETE FROM ventas_pasteles WHERE CodigoBarrasPastel_FK = ?";
            PreparedStatement deleteVentasStatement = connection.prepareStatement(deleteVentasSql);
            deleteVentasStatement.setString(1, codigoBarras);
            deleteVentasStatement.executeUpdate();

            String deletePastelesSql = "DELETE FROM pasteles WHERE codigo_de_barras = ?";
            PreparedStatement deletePastelesStatement = connection.prepareStatement(deletePastelesSql);
            deletePastelesStatement.setString(1, codigoBarras);
            int rowsAffected = deletePastelesStatement.executeUpdate();

            String selectPastelesSql = "SELECT COUNT(*) FROM pasteles WHERE categoria_fk = ?";
            PreparedStatement selectPastelesStatement = connection.prepareStatement(selectPastelesSql);
            selectPastelesStatement.setString(1, categoria);
            int registrosCategoria = 0;
            try (ResultSet resultSet = selectPastelesStatement.executeQuery()) {
                if (resultSet.next()) {
                    registrosCategoria = resultSet.getInt(1);
                }
            }

            if (registrosCategoria == 0) {
                String deleteCategoriaSql = "DELETE FROM categorias WHERE nombre = ?";
                PreparedStatement deleteCategoriaStatement = connection.prepareStatement(deleteCategoriaSql);
                deleteCategoriaStatement.setString(1, categoria);
                deleteCategoriaStatement.executeUpdate();
            }

            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el registro: " + e.getMessage());
        }
    }
}
