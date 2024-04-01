package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CatalogoController extends UtilitariaNavegabilidad {

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
    void recargarTablaClientes(ActionEvent event) {

    }

}
