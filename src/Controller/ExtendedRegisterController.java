package Controller;

import Model.UserCredentials;
import Model.Ventanas;
import Model.PasswordUtil;
import DAO.UserCredentialsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ExtendedRegisterController {
	@FXML
	private Button  btnAtras;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    private UserCredentialsDAO userCredentialsDAO;

    public ExtendedRegisterController() {
        this.userCredentialsDAO = new UserCredentialsDAO();
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validaciones
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios.");
        } else if (!password.equals(confirmPassword)) {
            showAlert("Error", "Las contraseñas no coinciden.");
        } else if (password.length() < 4) {
            showAlert("Error", "La contraseña debe tener al menos 4 caracteres.");
        } else {
            try {
                // Hash de la contraseña
                String hashedPassword = PasswordUtil.hashPassword(password);

                // Crear un objeto UserCredentials con las credenciales del usuario
                UserCredentials userCredentials = new UserCredentials();
                userCredentials.setPasswordHash(hashedPassword.getBytes());

                // Lógica para insertar las credenciales en la base de datos
                boolean registroExitoso = userCredentialsDAO.insertUserCredentials(userCredentials);

                if (registroExitoso) {
                    showAlert("Éxito", "Usuario registrado correctamente.");
                } else {
                    showAlert("Error", "Error al registrar el usuario en la base de datos.");
                }
            } catch (Exception e) {
                showAlert("Error", "Ocurrió un error al registrar el usuario: " + e.getMessage());
            }
        }
    }

    Ventanas Ventana = new   Ventanas();

    @FXML
       private void Atras(ActionEvent event) {
   	// Obtiene el Node que generó el evento (en este caso, el botón)
        Node source = (Node) event.getSource();
        // Obtiene la Stage (ventana) a la que pertenece el Node
        Stage stage = (Stage) source.getScene().getWindow();
        // Cierra la ventana actual
        stage.close();
        
       
        Ventana.MostrarPane2("/Vista/Register.fxml");
   	 
   	 
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


