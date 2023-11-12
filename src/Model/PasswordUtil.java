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

    // Resto del código...


    // Resto del código...


    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        // Verificar si la contraseña ingresada coincide con el hash almacenado
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }

	
}


	
	
	
	
	
	
	
	

