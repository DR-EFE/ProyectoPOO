
package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
//import java.awt.TextField;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.util.Duration;

public class SampleController2 implements Initializable {
	
	 /* @FXML
	    private TextField TextFieldHora;
	  @FXML
	    private Button btnFecha;*/

	@FXML
    private TextField TextFieldFH;

	@FXML
    private Button btnHoraFecha;
	
	@FXML
    private Button btnCaja;

    @FXML
    private Button btnCatalogo;

    @FXML
    private Button btnCategoria;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnPasteles;

    @FXML
    private Button btnPedidos;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnVentaPastel;

    @FXML
    private Button btnVentas;

    @FXML
    private MenuItem itemCerrar;

    @FXML
    private MenuItem itemNocturno;

    @FXML
    private MenuButton menuopciones;
    
    @FXML
    void openWinOne(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample.fxml"));
            Parent root = loader.load();

            SampleController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            //stage.setTitle("Primera Ventana");

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
            stage.setFullScreen(true);
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


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    	// Crear un Timeline para actualizar automáticamente la hora cada segundo
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> mostrarHoraFecha(event)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnCategoria.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    
   /* public void RegistroSig(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Register2.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnCategoria.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(ExtendedRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/    
    
    @FXML
    void openCaja(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Caja.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Ventana Ventas");
            stage.show();

            Stage myStage = (Stage) this.btnCaja.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @FXML
    void openCatalogos(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Pedido2.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnCatalogo.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
           // Logger.getLogger(VentaEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        	ex.printStackTrace();
        }

    }

    @FXML
    void openCategorias(ActionEvent event) {
    	String direccionFX="/Vista/Categorias.fxml";
    	//String layautButton="btnCategoria"; 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(direccionFX));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Ventana Categoria");
            stage.show();

            Stage myStage = (Stage) this.btnCategoria.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @FXML
    void openClientes(ActionEvent event) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Cliente.fxml"));
                Parent root = loader.load();

                Sample2Noc controlador = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setFullScreen(true);
                stage.setScene(scene);

                // Llama al método initialize() de SampleController2
                controlador.initialize(null, null);
                stage.show();

                // Cierra la ventana actual
                Stage myStage = (Stage) btnClientes.getScene().getWindow();
                myStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    void openPedidos(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Pedido.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Ventana Pedidos");
            stage.show();

            Stage myStage = (Stage) this.btnPedidos.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @FXML
    void openUsuarios(ActionEvent event) {

    }

    @FXML
    void openVentas(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Ventas.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Ventana Ventas");
            stage.show();

            Stage myStage = (Stage) this.btnVentas.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
           // Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }


    }

    @FXML
    void openVentasPastel(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ventas_pasteles.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Ventana Venta Pasteles");
            stage.show();

            Stage myStage = (Stage) this.btnVentaPastel.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //Logger.getLogger(VentaPastelController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
    
    @FXML
    void OpenPasteles(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Principal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setTitle("Ventana Pasteles");
            stage.show();

            Stage myStage = (Stage) this.btnPasteles.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            //Logger.getLogger(PastelesController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
    
    @FXML
 // Método para mostrar la fecha y hora actual en el TextField
    void mostrarHoraFecha(ActionEvent event) {
    	// Obtener la fecha y hora actual
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Formatear la fecha y hora según tus preferencias
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // Establecer el texto formateado en el TextField
        TextFieldFH.setText(formattedDateTime);

    } 
}
