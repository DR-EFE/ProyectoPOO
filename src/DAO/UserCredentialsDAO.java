	package DAO;
	
	import Model.UserCredentials;
	import Model.UserModel;
	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Factory.ConnectionFactory;
	
	public class UserCredentialsDAO {
		
		
		private int userID;  // Campo para almacenar el UserID

	    public int getUserID() {
	        return userID;
	    }
		
	 // Método para insertar las credenciales del usuario en la base de datos
	    public boolean insertUserCredentials(UserCredentials userCredentials) throws Exception {
	        Connection connection = null;

	        try {
	            connection = ConnectionFactory.getConnection();

	            // SQL para insertar las credenciales en la tabla user_credentials
	            String insertSql = "INSERT INTO user_credentials (PasswordHash, Salt, UserID) VALUES (?, ?, ?)";

	            PreparedStatement insertStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

	            insertStatement.setBytes(1, userCredentials.getPasswordHash());
	            insertStatement.setBytes(2, userCredentials.getSalt());
	            insertStatement.setInt(3, userCredentials.getUserID());  // Asegúrate de asignar el UserID

	            int rowsAffected = insertStatement.executeUpdate();

	            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    // Obtiene el UserID generado y asigna a la instancia de UserCredentials
	                    int userID = generatedKeys.getInt(1);
	                    userCredentials.setUserID(userID);
	                } else {
	                    throw new SQLException("No se generó el UserID.");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();  // Manejo del error
	                return false;
	            }

	            return rowsAffected > 0;
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }
	}
	


