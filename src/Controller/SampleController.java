package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.UserCredentialsDAO;
import Model.PasswordUtil;
import Model.UserCredentials;
import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController implements Initializable {

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnNext;

    @FXML
    private TextField txtContra;

    @FXML
    private TextField txtUsuario;
    
    @FXML
    private PasswordField passwordField1;


    
    Ventanas Ventana = new Ventanas();
    
    @FXML
    void openRegister(ActionEvent event) {
    	// Obtiene el Node que generó el evento (en este caso, el botón)
        Node source = (Node) event.getSource();
        // Obtiene la Stage (ventana) a la que pertenece el Node
        Stage stage = (Stage) source.getScene().getWindow();
        // Cierra la ventana actual
        stage.close();
    	Ventana.MostrarPane2("/Vista/Register.fxml");
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    @FXML
    void Cancelar(ActionEvent event) {
    	txtUsuario.clear();
        passwordField1.clear(); 
    } 

    @FXML
    void validateCredentials(ActionEvent event) throws Exception {
        String usuario = txtUsuario.getText();
        //String contrasena = txtContra.getText();
        String password1 = passwordField1.getText();
        

        // Obtener las credenciales almacenadas en la base de datos
        UserCredentials userCredentials = UserCredentialsDAO.getUserCredentialsByUserName(usuario);

        if (userCredentials != null && PasswordUtil.checkPassword(password1, userCredentials.getPasswordHash(), userCredentials.getSalt())) {
            // Credenciales válidas, abrir la ventana correspondiente
        	openWintwo(event);
            } else {
            	 // Credenciales inválidas, mostrar un mensaje de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de autenticación");
                alert.setHeaderText(null);
                alert.setContentText("Usuario o contraseña incorrectos. Por favor, intente nuevamente.");
                alert.showAndWait();
            }    
        }
    
    @FXML
    void openWinNext(ActionEvent event) {
    	//Ventanas Pane1 = new Ventanas();
		Ventana.MostrarPane("/Vista/Sample2.fxml");
    }

    @FXML
    void openWintwo(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample2.fxml"));
            Parent root = loader.load();

            SampleController2 controlador = loader.getController();
            
            //Stage primaryStage = new Stage(); // recordar quitar esto sino jala :)
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Segunda Ventana");

            // Llama al método initialize() de SampleController2
            controlador.initialize(null, null);

            stage.show();

            // Cierra la ventana actual
            Stage myStage = (Stage) btnIngresar.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openWinEmpleado(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Empleado.fxml"));
            Parent root = loader.load();

            EmpleadoController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Segunda Ventana");
            stage.setFullScreen(true);

            // Llama al método initialize() de SampleController2
            controlador.initialize(null, null);

            stage.show();

            // Cierra la ventana actual
            Stage myStage = (Stage) btnIngresar.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
