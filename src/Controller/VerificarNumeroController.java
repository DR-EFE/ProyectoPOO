package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class VerificarNumeroController extends UtilitariaNavegabilidad {

    @FXML
    private Button btnVerificar;

    @FXML
    private TextField tfVerificarNumero;

    @FXML
    void VerificarNumero(ActionEvent event) {
    	
            Pane.MostrarPane(event,"/Vista/PedidoFinalizado.fxml");
}
}
