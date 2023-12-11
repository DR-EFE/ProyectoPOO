package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VentaEmpleadoController {

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnVender;

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

    @FXML
    void openWintwo2(ActionEvent event) {

    }

}
