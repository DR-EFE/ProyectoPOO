package Controller;

import Model.Facturas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Factura2Controller extends UtilitariaNavegabilidad implements Initializable {

    @FXML
    TableView<Facturas> tblVentas;

    private static ObservableList<Facturas> listaFacturasCompartida = FXCollections.observableArrayList();
    private Factura2Controller factura2Controller;
    private FacturaController facturaController;
    private FacturaController factura3Controller;

    @FXML
    TextField TotalFinal2;

    private float sumaTotal2 = 0;
    @FXML
    private Button btnMenu;
    @FXML
    private TableColumn<Facturas, Integer> colFolio;
    @FXML
    private TableColumn<Facturas, Integer> colCantidadVendida;
    @FXML
    private TableColumn<Facturas, Float> colSubtotal;
    @FXML
    private TableColumn<Facturas, Float> colTotal;
    @FXML
    private TableColumn<Facturas, String> colFechaDeVenta;
    @FXML
    private TableColumn<Facturas, String> colProductos;

    private ObservableList<Facturas> listaFacturas;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
        colProductos.setCellValueFactory(dato -> dato.getValue().getProductos());
        colCantidadVendida.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
        colSubtotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
        colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
        colFechaDeVenta.setCellValueFactory(dato -> dato.getValue().getFechaDeVenta());
        listaFacturas = FXCollections.observableArrayList();

        tblVentas.setItems(listaFacturasCompartida);
    }

    public void setFacturaController(FacturaController controller) {
        facturaController = controller;
    }

    public void agregarFactura(Facturas factura) {
        // listaFacturasCompartida.add(factura);
        tblVentas.getItems().add(factura);

    }

    public void suma(Facturas factura) {
        sumaTotal2 += factura.getTotal().get();
        TotalFinal2.setText(String.valueOf(sumaTotal2));

    }

}

/*
 * establece una referencia al controlador de facturas (FacturaController).
 * Recibe como parámetro una instancia del controlador y la asigna a la variable
 * facturaController.
 * Esto permite que el controlador tenga acceso al otro controlador para
 * interactuar con él.
 * 
 * add(). Esto permite mostrar la factura recién agregada en la interfaz
 * 
 * setText(). Esto permite mostrar la suma total en la interfaz gráfica.
 * 
 */