package DAO;

import Model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Factory.ConnectionFactory;

public class UserDAO {
    private Connection connection;

    public UserDAO() throws Exception {
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean guardarUsuario(UserModel user) throws SQLException {
        try {
            // SQL para insertar un nuevo usuario en la base de datos
            String insertSql = "INSERT INTO users (Username, UserApellidoP, UserApellidoM, TipoDeChamba, Phone) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getUserApellidoP());
            insertStatement.setString(3, user.getUserApellidoM());
            insertStatement.setString(4, user.getTipoDeChamba());
            insertStatement.setString(5, user.getPhone());

            // Ejecutar la consulta de inserción
            int rowsAffected = insertStatement.executeUpdate();

            // Si al menos una fila fue afectada, el usuario se ha guardado correctamente
            return rowsAffected > 0;
        } finally {
            // Cerrar la conexión (manejar excepciones adecuadamente aquí si es necesario)
            if (connection != null) {
                connection.close();
            }
        }
    }
}

