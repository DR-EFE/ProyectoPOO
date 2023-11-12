package Controller;

import Model.UserCredentials;
import Model.UserModel;
import Model.Ventanas;

import org.mindrot.jbcrypt.BCrypt;

import Controller.RegisterController;
import Model.PasswordUtil;
import DAO.UserCredentialsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExtendedRegisterController {
	
	
	private RegisterController registerController;
	

    
	
	
    @FXML
    private ComboBox<String> btnTipoDeChamba;
	
	@FXML
	private Button  btnAtras;
	
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    private UserCredentialsDAO userCredentialsDAO;
    Ventanas Ventana = new   Ventanas();
    
    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }
    
    public ExtendedRegisterController() {
        this.userCredentialsDAO = new UserCredentialsDAO();
    }

    @FXML
    private void Atras(ActionEvent event) {
    	
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        System.out.println("LLama metodo atras");
        Ventana.MostrarPane2("/Vista/Register.fxml");
    
       
            registerController.setUsuario();
         

        
    }
  
    
    @FXML
    private void handleRegisterButtonAction() {
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
                // Generar el Salt
                byte[] salt = BCrypt.gensalt().getBytes();

                // Crear un objeto UserCredentials con las credenciales del usuario
                UserCredentials userCredentials = new UserCredentials();
                userCredentials.setSalt(salt);

                // Hash de la contraseña con el Salt
                String hashedPassword = PasswordUtil.hashPassword(password, userCredentials.getSalt());

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




    @FXML
    private void RegistrarFinal(ActionEvent event) {
    	 registerController.guardarRegistro();
    	handleRegisterButtonAction();
    
    	
    	 
    	
    }
    
    
    

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


