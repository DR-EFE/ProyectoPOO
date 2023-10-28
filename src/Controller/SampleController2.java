
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.SampleController;
import Model.Ventanas;
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

public class SampleController2 implements Initializable {

    @FXML
    private Button btnCategoria;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnPasteles;

    @FXML
    private Button btnPedidos;

    @FXML
    private Button btnVentaPastel;

    @FXML
    private Button btnVentas;

    @FXML
    private MenuItem itemNocturno;

    @FXML
    private MenuButton menuopciones;

    @FXML
    private MenuItem itemCerrar;

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
            Stage myStage = (Stage) btnCategoria.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cambiarVista(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample2Noc.fxml"));
            Parent root = loader.load();

            Sample2Noc controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Segunda Ventana");

            // Llama al método initialize() de SampleController2
            controlador.initialize(null, null);

            stage.show();

            // Cierra la ventana actual
            Stage myStage = (Stage) btnCategoria.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openWinFive(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Principal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Pasteles");
            stage.show();

            Stage myStage = (Stage) this.btnPasteles.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(PastelesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openWinFor(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Cliente.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Clientes");
            stage.show();

            Stage myStage = (Stage) this.btnClientes.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openWinOcho(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ventas_pasteles.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Venta Pasteles");
            stage.show();

            Stage myStage = (Stage) this.btnVentaPastel.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(VentaPastelController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openWinSeven(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Ventas.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Ventas");
            stage.show();

            Stage myStage = (Stage) this.btnVentas.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openWinSix(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Pedido.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Pedidos");
            stage.show();

            Stage myStage = (Stage) this.btnPedidos.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openWinthree(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Categorias.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Ventana Categoria");
            stage.show();

            Stage myStage = (Stage) this.btnCategoria.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnCategoria.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
