package Controller;

import Model.UserModel;
import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import DAO.UserDAO;

import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField btnusernameField;
    @FXML
    private PasswordField btnLastName1;
	@FXML
	protected Button btnNext;
	@FXML
	protected Button btnRegistrar;
    @FXML
    private PasswordField btnLasName2;
    @FXML
    private PasswordField btnPhone;
    @FXML
    private ComboBox<String> btnTipoDeChamba;

    private UserDAO userDAO;
    
    Ventanas Ventana = new   Ventanas();

 @FXML
    private void siguiente(ActionEvent event) {
	// Obtiene el Node que generó el evento (en este caso, el botón)
     Node source = (Node) event.getSource();
     // Obtiene la Stage (ventana) a la que pertenece el Node
     Stage stage = (Stage) source.getScene().getWindow();
     // Cierra la ventana actual
     stage.close();
     
    
     Ventana.MostrarPane2("/Vista/Register22.fxml");
	 
	 
 }
    
    public RegisterController() throws Exception {
        this.userDAO = new UserDAO();
    }

    @FXML
    private void initialize() {
        // Cargar tipos de trabajo al ComboBox
        btnTipoDeChamba.getItems().addAll("Tipo 1", "Tipo 2", "Tipo 3");
    }

    @FXML
    private void guardarRegistro() {
        String username = btnusernameField.getText().trim();
        String apellidoPaterno = btnLastName1.getText().trim();
        String apellidoMaterno = btnLasName2.getText().trim();
        String phone = btnPhone.getText().trim();
        String tipoDeChamba = btnTipoDeChamba.getValue();

        // Validar campos (agrega tu lógica de validación aquí)

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setUserApellidoP(apellidoPaterno);
        user.setUserApellidoM(apellidoMaterno);
        user.setPhone(phone);
        user.setTipoDeChamba(tipoDeChamba);

        try {
            boolean guardado = userDAO.guardarUsuario(user);

            if (guardado) {
                Utilitaria.mostrarAlerta("Éxito", "El registro se ha guardado correctamente.");
                limpiarCampos();
            } else {
                Utilitaria.mostrarAlerta("Error", "No se pudo guardar el registro.");
            }
        } catch (SQLException e) {
            Utilitaria.mostrarAlerta("Error", "Ocurrió un error al guardar el registro: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        btnusernameField.clear();
        btnLastName1.clear();
        btnLasName2.clear();
        btnPhone.clear();
        btnTipoDeChamba.getSelectionModel().clearSelection();
    }



}
