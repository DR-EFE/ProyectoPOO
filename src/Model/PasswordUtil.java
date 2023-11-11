package Model;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Arrays;
import java.util.Objects;




public class PasswordUtil {
    public static String hashPassword(String password) {
        // Generar un salt aleatorio
        String salt = BCrypt.gensalt();

        // Hash de la contraseña con el salt
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        // Verificar si la contraseña ingresada coincide con el hash almacenado
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }
}


	
	
	
	
	
	
	
	

