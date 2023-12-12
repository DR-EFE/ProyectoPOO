package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class EmpleadoController implements Initializable {

    @FXML
    private Button btnVentas2;

    @FXML
    private Button btnPedidos2;

    @FXML
    private MenuItem itemNocturno;

    @FXML
    private MenuButton menuopciones;

    @FXML
    private MenuItem itemCerrar;
    
    
    @FXML
    void openEstadoPedido(ActionEvent event) {
    	

    }
    
    @FXML
    void openPedidosEmpleado(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Pedido2.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Pedidos");
            stage.show();

            Stage myStage = (Stage) this.btnVentas2.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(VentaEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openVentasEmpleado(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentasEmpleado.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Ventas");
            stage.show();

            Stage myStage = (Stage) this.btnPedidos2.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(VentaEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
   @FXML
    void openWinSeven(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Factura.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Facturas");
            stage.show();

            Stage myStage = (Stage) this.btnVentas2.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void openWinSix2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Pedido2.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Pedidos");
            stage.show();

            Stage myStage = (Stage) this.btnPedidos2.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openWinOne(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample.fxml"));
            Parent root = loader.load();

            SampleController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Primera Ventana");

            // Llama al método initialize() de SampleController2
            controlador.initialize(null, null);

            stage.show();

            // Cierra la ventana actual
            Stage myStage = (Stage) btnVentas2.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cambiarVista(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/EmpleadoNocturno.fxml"));
            Parent root = loader.load();

            EmpleadoNocturnoC controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Segunda Ventana");

            // Llama al método initialize() de SampleController2
            controlador.initialize(null, null);

            stage.show();

            // Cierra la ventana actual
            Stage myStage = (Stage) btnVentas2.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

}
