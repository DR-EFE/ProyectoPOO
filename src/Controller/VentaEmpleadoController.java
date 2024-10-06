package Controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

import DAO.PastelesDAO;
import DAO.VentaDAO;
import Factory.ConnectionFactory;
import Model.Pasteles;
import Model.Ventas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class VentaEmpleadoController extends UtilitariaNavegabilidad implements Initializable {
	private VentaDAO ventaDAO; // Debes inicializar esto en algún lugar, posiblemente en el initialize()
    
    @FXML
    protected TextField TextFieldFecha1;
    @FXML
    protected TextField TextFieldHora1;
    @FXML
    private TableView<Ventas> tblVentas;
    @FXML
    private TableColumn<Ventas, Integer> colFolio;
    @FXML
    private TableColumn<Ventas, String> colDescripcion;
    @FXML
    private TableColumn<Ventas, Integer> colCantidad;
    @FXML
    private TableColumn<Ventas, String> colTipo;
    @FXML
    private TableColumn<Ventas, Float> colPrecioUnitario;
    @FXML
    private TableColumn<Ventas, Float> colSubTotal;
    @FXML
    private TableColumn<Ventas, Float> colTotal;
    @FXML
    private Button btnMenu;
    @FXML
    private TextField TextFBuscar;
    @FXML
    private TextField TextFArticulo;
    @FXML
    private TextField TextFCodigodeBarras;
    @FXML
    private TextField TextFCategorias;
    @FXML
    private TextField TextFFolio;
    @FXML
    private Button btnVender;
    @FXML
    private Button btnAgregar;
    @FXML
    private TextField TextFInventario;
    
    private ObservableList<Ventas> listaVentas;
 // Aquí asegúrate de pasar la conexión o inicializarla correctamente.

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaVentas = FXCollections.observableArrayList();
        tblVentas.setItems(listaVentas);
       


        // Mostrar la fecha y hora actualizadas en los campos de texto
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarHora1(event)));
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarFecha1(event)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();
        
        // Configurar las celdas de la tabla
        configurarColumnasTabla();

        
        
        
        
        
        // Inicializar DAOs y otros componentes
        inicializarDAOs();
    }

    private void configurarColumnasTabla() {
        colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        colCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colTipo.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrecioUnitario.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        colSubTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
    }

    private void inicializarDAOs() {
        try {
            PastelesDAO pastelesDAO = new PastelesDAO(ConnectionFactory.getConnection());
            ventaDAO = new VentaDAO();
            colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
            colDescripcion.setCellValueFactory(dato -> dato.getValue().getProductos());
            colCantidad.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
            colSubTotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
            colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void buscarArticulo() throws Exception {
        // Obtener el texto de búsqueda
        String textoBusqueda = TextFBuscar.getText();
        Pasteles Pasti = ventaDAO.buscarVenta(textoBusqueda);

        if (Pasti != null) {
            // Comprobar si las propiedades son nulas antes de acceder
            String codigoBarras = Pasti.getCodigodeBarras() != null ? Pasti.getCodigodeBarras().get() : "";
            String nombre = Pasti.getNombre() != null ? Pasti.getNombre().get() : "";
            String descripcion = Pasti.getDescripcion() != null ? Pasti.getDescripcion().get() : "";
            float precio = Pasti.getPrecio() != null ? Pasti.getPrecio().get() : 0.0f;
            int peso = Pasti.getPeso() != null ? Pasti.getPeso().get() : 0;
            int cantidadRefri = Pasti.getCantidad_en_refri() != null ? Pasti.getCantidad_en_refri().get() : 0;
            String categoria = Pasti.getCategoria() != null ? Pasti.getCategoria().get() : "";

            // Actualizar los campos de la interfaz con los datos encontrados
            TextFFolio.setText(codigoBarras);
            TextFCodigodeBarras.setText(codigoBarras);
            TextFCategorias.setText(categoria);
            TextFInventario.setText(String.valueOf(cantidadRefri));
            TextFArticulo.setText(String.valueOf(nombre));
            // Si es necesario, actualiza otros campos de la interfaz aquí.
        } else {
            // Si no se encuentra el pastel, limpiar los campos
            limpiarCampos();
        }
    }


    // Método para agregar una venta a la tabla
 // Método para agregar una venta a la tabla
    @FXML
    private void agregarVentaATabla() {
        // Obtener el texto de búsqueda
        String textoBusqueda = TextFBuscar.getText();
        
        try {
            // Buscar el pastel en la base de datos
            Pasteles Pasti = ventaDAO.buscarVenta(textoBusqueda);

            if (Pasti != null) {
                // Crear un objeto de tipo Ventas a partir de los datos de Pasteles
                Ventas venta = new Ventas();

                venta.setFolio(new SimpleIntegerProperty(Integer.parseInt(TextFFolio.getText())));
                venta.setProductos(Pasti.getNombre());
                venta.setCantidadVendida(new SimpleIntegerProperty(1)); // Puedes pedir la cantidad al usuario
              //  venta.setPrecioUnitario(new SimpleFloatProperty(Pasti.getPrecio().get()));
                venta.setSubtotal(new SimpleFloatProperty(Pasti.getPrecio().get())); // En este caso, el subtotal es igual al precio
                venta.setTotal(new SimpleFloatProperty(Pasti.getPrecio().get()));    // Total, puedes añadir más lógica si tienes descuentos

                // Agregar el objeto Venta a la tabla
                tblVentas.getItems().add(venta);
                
                // Imprimir en consola
                System.out.println("Venta agregada a la tabla: " + Pasti.getNombre().get());
            } else {
                System.out.println("No se encontró el pastel.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void limpiarCampos() {
        TextFArticulo.clear();
        TextFCodigodeBarras.clear();
        TextFCategorias.clear();
        TextFFolio.clear();
        TextFInventario.clear();
    }

    @FXML
    void MostrarFecha1(ActionEvent event) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        TextFieldFecha1.setText(currentDateTime.format(formatter));
    }

    @FXML
    void MostrarHora1(ActionEvent event) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        TextFieldHora1.setText(currentDateTime.format(formatter));
    }
}

   