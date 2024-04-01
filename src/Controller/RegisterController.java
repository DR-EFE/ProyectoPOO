package Controller;

import Model.UserModel;
import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import DAO.UserDAO;

import java.sql.SQLException;

public class RegisterController {
	
	

    @FXML
    private TextField btnusernameField;
    @FXML
    private TextField btnLastName1;
	@FXML
	protected Button btnNext;
	@FXML
	protected Button btnRegistrar;
    @FXML
    private TextField btnLasName2;
    @FXML
    private TextField btnPhone;
    @FXML
    private ComboBox<String> btnTipoDeChamba;

    
    private UserDAO userDAO;
    
    Ventanas Ventana = new   Ventanas();
   UserModel usuario = new UserModel();
  
   public RegisterController() throws Exception {
       this.userDAO = new UserDAO();
   }

   @FXML
   private void initialize() {
       // Cargar tipos de trabajo al ComboBox
       btnTipoDeChamba.getItems().addAll("Gerente", "Empleado");
   }

   
   public Stage getStage() {
       return (Stage) btnNext.getScene().getWindow();
   }
   
   
   
 @FXML
    private void siguiente(ActionEvent event) {

	
	 String nombre = btnusernameField.getText();
     String apellidoPaterno = btnLastName1.getText();
     String apellidoMaterno = btnLasName2.getText();
     String telefono = btnPhone.getText();
     

     // almacenar los datos en el objeto Usuario
     usuario.setUsername(nombre);
     usuario.setUserApellidoP(apellidoPaterno);
    usuario.setUserApellidoM(apellidoMaterno);
     usuario.setPhone(telefono);
     // Almacenar el tipo de trabajo seleccionado del ComboBox
     String tipoDeTrabajoSeleccionado = btnTipoDeChamba.getValue();
     usuario.setTipoDeChamba(tipoDeTrabajoSeleccionado);
     
  // En RegisterController
     
	 
	// Obtiene el Node que generó el evento (en este caso, el botón)
     Node source = (Node) event.getSource();
     // Obtiene la Stage (ventana) a la que pertenece el Node
     Stage stage = (Stage) source.getScene().getWindow();
     // Cierra la ventana actual
     stage.close();

  // Llama a MostrarPane2 pasando la instancia actual de RegisterController
     Ventana.MostrarPane3("/Vista/Register22.fxml", this);
	 setUsuario();
	 
 }
 
 
 
 public void setUsuario() {
	        // mostrar los datos del usuario en los campos correspondientes
	        btnusernameField.setText(usuario.getUsername());
	       
	        btnLastName1.setText(usuario.getUserApellidoP());
	        btnLasName2.setText(usuario.getUserApellidoM());
	        btnPhone.setText(usuario.getPhone());

	    // Mensajes de depuración
	   
	}


    @FXML
   public void guardarRegistro() {
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
              
                limpiarCampos();
            } else {
                UtilitariaNavegabilidad.mostrarAlerta("Error", "No se pudo guardar el registro.");
            }
        } catch (SQLException e) {
            UtilitariaNavegabilidad.mostrarAlerta("Error", "Ocurrió un error al guardar el registro: " + e.getMessage());
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
