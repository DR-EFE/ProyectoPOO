package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.UserCredentialsDAO;
import Factory.ConnectionFactory;
import Model.PasswordUtil;
import Model.UserCredentials;
import Model.UserModel;
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
        txtContra.setText("");
        txtUsuario.setText("");
    }

    @FXML
    void validateCredentials(ActionEvent event) throws Exception {
        String usuario = txtUsuario.getText();
        String contrasena = txtContra.getText();
        Connection connection = ConnectionFactory.getConnection();

    	String selectQuery = "SELECT * FROM users WHERE UserID = ?";
		PreparedStatement statement = connection.prepareStatement(selectQuery);
		//statement.setString(1, usuario);
		
        // Obtener las credenciales almacenadas en la base de datos
        UserCredentials userCredentials = UserCredentialsDAO.getUserCredentialsByUserName(usuario);
        UserCredentials userID = UserCredentialsDAO.getUserID2(usuario);   //  
       // String userID2 = UserModel.getTipoDeChamba() ;
        if (userID!=null && userCredentials != null && PasswordUtil.checkPassword(contrasena, userCredentials.getPasswordHash(), userCredentials.getSalt())) {

                openWintwo(event);

            }else if(userCredentials != null && PasswordUtil.checkPassword(contrasena, userCredentials.getPasswordHash(), userCredentials.getSalt())) {
            	openWintwo2(event);
            
            }
        		else {
        			//vamos	
                    // Credenciales inválidas, mostrar un mensaje de error
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error de autenticación");
                       alert.setHeaderText(null);
                       alert.setContentText("Usuario o contraseña incorrectos. Por favor, intente nuevamente.");
                       alert.showAndWait();
            	
            	
            }
        
           
    
    }
       
           
        
    


    @FXML
    void openWintwo(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample2.fxml"));
            Parent root = loader.load();

            SampleController2 controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Primera Ventana");

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
    public void openWintwo2(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Empleado.fxml"));
            Parent root = loader.load();

            EmpleadoController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
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

}
