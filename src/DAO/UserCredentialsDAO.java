	package DAO;
	
	import Model.UserCredentials;
	import Model.UserModel;
	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	
	import Factory.ConnectionFactory;
	
	
	public class UserCredentialsDAO {
	    // Método para insertar las credenciales del usuario en la base de datos
	    public boolean insertUserCredentials(UserCredentials userCredentials) throws Exception {
	        Connection connection = null;
	
	        try {
	            connection = ConnectionFactory.getConnection();
	
	            // SQL para insertar las credenciales en la tabla user_credentials
	            String insertSql = "INSERT INTO user_credentials ( PasswordHash, Salt) VALUES ( ?, ?)";
	
	            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
	            //insertStatement.setInt(1, userCredentials.getUserID());
	            insertStatement.setBytes(2, userCredentials.getPasswordHash());
	            insertStatement.setBytes(3, userCredentials.getSalt());
	
	            int rowsAffected = insertStatement.executeUpdate();
	
	            return rowsAffected > 0;
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }
	}

