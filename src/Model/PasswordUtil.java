package Model;

import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;




public class PasswordUtil {
    public static String hashPassword(String password, byte[] salt) {
        // Convertir el salt de byte[] a String para utilizarlo con BCrypt
        String saltString = new String(salt, StandardCharsets.UTF_8);

        // Hash de la contraseña con el salt proporcionado
        return BCrypt.hashpw(password, saltString);
    }

    


    public static boolean checkPassword(String enteredPassword, byte[] storedPasswordHash, byte[] salt) {
        // Convertir el salt de byte[] a String para utilizarlo con BCrypt
        String saltString = new String(salt, StandardCharsets.UTF_8);

        // Generar el hash de la contraseña ingresada utilizando el salt almacenado
        String hashedPassword = BCrypt.hashpw(enteredPassword, saltString);

        // Comparar el hash generado con el hash almacenado
        return Arrays.equals(hashedPassword.getBytes(StandardCharsets.UTF_8), storedPasswordHash);
    }

	
}


	
	
	
	
	
	
	
	

