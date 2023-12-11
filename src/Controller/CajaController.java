package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CajaController {

    @FXML
    private TextField TxtBusquedaCliente;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnMenu;

    @FXML
    private TableColumn<?, ?> colApellidoMaterno;

    @FXML
    private TableColumn<?, ?> colApellidoPaterno;

    @FXML
    private TableColumn<?, ?> colCalle;

    @FXML
    private TableColumn<?, ?> colNombreCliente;

    @FXML
    private TableView<?> tblCliente;

    @FXML
    void buscarCliente(ActionEvent event) {

    }

    @FXML
    void openWintwo(ActionEvent event) {

    }

    @FXML
    void recargarTablaClientes(ActionEvent event) {

    }

}
