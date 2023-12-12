package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/pasteleria?user=root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws Exception {
        // Cargar el driver de la base de datos
        Class.forName(DRIVER);
        // Establecer la conexión utilizando la URL, el usuario y la contraseña
        return DriverManager.getConnection(URL, "root", "Root12345");
    }

    // Método para ejecutar una consulta y obtener un conjunto de resultados
    public static ResultSet executeQuery(String query) throws Exception {
        // Obtener una conexión a la base de datos
        Connection connection = getConnection();
        // Crear un objeto Statement para ejecutar la consulta
        Statement statement = connection.createStatement();
        // Ejecutar la consulta y devolver el conjunto de resultados
        return statement.executeQuery(query);
    }

}
