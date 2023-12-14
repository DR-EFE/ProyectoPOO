package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pedidos2Controller extends Utilitaria implements Initializable{

    @FXML
    private TextField TextFieldFecha1;

    @FXML
    private TextField TextFieldHora1;

    @FXML
    private TextField TxtBuscarPedido;

    @FXML
    private Button btnBuscarPedido;

    @FXML
    private Button btnBuscarPedido1;

    @FXML
    private Button btnConfirmarPedido;

    @FXML
    private Button btnDatosCliente;

    @FXML
    private Button btnMenu;

    @FXML
    private TableColumn<?, ?> colAnticipo;

    @FXML
    private TableColumn<?, ?> colDescripcion;

    @FXML
    private TableColumn<?, ?> colEstatus;

    @FXML
    private TableColumn<?, ?> colFolio;

    @FXML
    private TableColumn<?, ?> colSubtotal;

    @FXML
    private TableColumn<?, ?> colTelefono_FK;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableView<?> tblPedido;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Crear un Timeline para actualizar automáticamente la hora cada segundo
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarHora1(event)));
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarFecha1(event)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();


	}

    @FXML
    void MostrarFecha1(ActionEvent event) {
    	// Obtener la fecha 
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Formatear la fecha y hora según tus preferencias
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentDateTime.format(formatter);
        // Establecer el texto formateado en el TextField
        TextFieldFecha1.setText(formattedDateTime);

    }

    @FXML
    void MostrarHora1(ActionEvent event) {
    	// Obtener la hora actual
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Formatear la fecha y hora según tus preferencias
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        // Establecer el texto formateado en el TextField
        TextFieldHora1.setText(formattedDateTime);
    }

    @FXML
    void buscarPedido(ActionEvent event) {

    }

    @FXML
    void openPedidosTienda(ActionEvent event) {

    }

    @FXML
    void recargarTablaPedido(ActionEvent event) {

    }

	
	@FXML
    void openWinMenu(ActionEvent event) {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample2.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Ventana Ventas");
            stage.show();

            Stage myStage = (Stage) this.btnMenu.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

}
