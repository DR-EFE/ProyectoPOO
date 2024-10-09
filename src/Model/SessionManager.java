package Model;

public class SessionManager {
    private static String usuarioActual;
    private static int usuarioId;
    
    public static void iniciarSesion(String usuario) {
        usuarioActual = usuario;
      //  usuarioId = id;
    }

    public static String getUsuarioActual() {
        return usuarioActual;
    }

    public static int getUsuarioId() {
        return usuarioId;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        usuarioId = -1;  // Resetea la sesión
    }

    public static boolean sesionIniciada() {
        return usuarioActual != null;
    }
}
