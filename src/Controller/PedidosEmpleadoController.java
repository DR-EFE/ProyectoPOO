package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PedidosEmpleadoController extends UtilitariaNavegabilidad {

    @FXML
    private Button btnRegistrar;


    @FXML
    void RegistrarCliente(ActionEvent event) {
    	
        	Pane.MostrarPane(event, "/Vista/VerificarNumero.fxml");
    }
    
    
    
    
}


