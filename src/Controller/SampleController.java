package Controller;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import DAO.UserCredentialsDAO;
import Factory.ConnectionFactory;
import Model.PasswordUtil;
import Model.SessionManager;
import Model.UserCredentials;
import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController extends UtilitariaNavegabilidad implements Initializable {

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnNext;

    @FXML
    private TextField txtContra;


    @FXML
    private PasswordField passwordField1;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    
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
    
   
    @FXML
    void Cancelar(ActionEvent event) {
    	txtUsuario.clear();
        passwordField1.clear(); 
    } 
    
    @FXML
    void validateCredentials(ActionEvent event) throws Exception {
        String usuario = txtUsuario.getText();
        String password1 = passwordField1.getText();
        Connection connection = ConnectionFactory.getConnection();

    	String selectQuery = "SELECT * FROM users WHERE UserID = ?";
		PreparedStatement statement = connection.prepareStatement(selectQuery);
		//statement.setString(1, usuario);
		
        // Obtener las credenciales almacenadas en la base de datos
        UserCredentials userCredentials = UserCredentialsDAO.getUserCredentialsByUserName(usuario);
        UserCredentials userID = UserCredentialsDAO.getUserID2(usuario);   //  
       // String userID2 = UserModel.getTipoDeChamba() ;
        if (userID!=null && userCredentials != null && PasswordUtil.checkPassword(password1, userCredentials.getPasswordHash(), userCredentials.getSalt())) {
        	 SessionManager.iniciarSesion(usuario);

                openWintwo(event);

            }else if(userCredentials != null && PasswordUtil.checkPassword(password1, userCredentials.getPasswordHash(), userCredentials.getSalt())) {
            	 SessionManager.iniciarSesion(usuario);

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
	public
    void openWintwo(ActionEvent event) {

       
            Pane.MostrarPane(event, "/Vista/Sample2.fxml");

        
    }

    @FXML
    public void openWintwo2(ActionEvent event) {

        Pane.MostrarPane(event, "/Vista/Empleado.fxml");
    }

}