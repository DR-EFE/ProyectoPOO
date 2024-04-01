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

public class Sample2Noc extends UtilitariaNavegabilidad implements Initializable  {

    @FXML
    private Button btnCategoria;

    
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
        
            Pane.MostrarPane(event, "/Vista/Sample.fxml");

    }

    @FXML
    void cambiarVista(ActionEvent event) {
      
            Pane.MostrarPane(event, "/Vista/Sample2.fxml");
           

    }

    @FXML
    void openWinFive(ActionEvent event) {
      
            Pane.MostrarPane(event, "/Vista/Principal.fxml");

    }

    @FXML
    void openWinFor(ActionEvent event) {
     
            Pane.MostrarPane(event, "/Vista/Cliente.fxml");

    }

    @FXML
    void openWinOcho(ActionEvent event) {
            Pane.MostrarPane(event, "/Vista/ventas_pasteles.fxml");

    }

    @FXML
    void openWinSeven(ActionEvent event) {
       
            Pane.MostrarPane(event, "/Vista/Ventas.fxml");
    }

    @FXML
    void openWinSix(ActionEvent event) {
    	Pane.MostrarPane(event, "/Vista/Pedido.fxml");
    }

    @FXML
    void openWinthree(ActionEvent event) {
       
            Pane.MostrarPane(event, "/Vista/Categorias.fxml");

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

    
    
    
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    
    
    
}
