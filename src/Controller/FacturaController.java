package Controller;

import Model.Facturas;
import Model.Ventas;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Factory.ConnectionFactory;

public class FacturaController extends Utilitaria implements Initializable {

    @FXML
    private TableView<Facturas> tblVentas;

    private static ObservableList<Facturas> listaFacturasCompartida = FXCollections.observableArrayList();

    private FacturaController facturaController;

    private Factura2Controller factura2Controller;

    @FXML
    private TextField TxtBuqueda;
    @FXML
    private TextField TotalFinal;
    @FXML
    private Button btnMenu;
    @FXML
    private Button FinalizarCompra;
    @FXML
    private Button btnEliminarFactura;
    @FXML
    private Button btnGuardarFactura;
    @FXML
    private Button btnActualizarFactura;

    private float sumaTotal = 0;

    @FXML
    private TextField txtFolio;
    @FXML
    private TextField txtCantidadVendida;
    @FXML
    private TextField txtSubtotal;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtFechaDeVenta;
    @FXML
    private TextField txtProductos;

    @FXML
    TableColumn<Facturas, Integer> colFolio;
    @FXML
    TableColumn<Facturas, Integer> colCantidadVendida;
    @FXML
    TableColumn<Facturas, Float> colSubtotal;
    @FXML
    TableColumn<Facturas, Float> colTotal;
    @FXML
    TableColumn<Facturas, String> colFechaDeVenta;
    @FXML
    TableColumn<Facturas, String> colProductos;

    private ObservableList<Facturas> listaFacturas;

    public void initialize(URL arg0, ResourceBundle arg1) {
        tblVentas.setEditable(true);
        colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colProductos.setCellFactory(TextFieldTableCell.forTableColumn());
        colCantidadVendida.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colSubtotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        colFechaDeVenta.setCellFactory(TextFieldTableCell.forTableColumn());
        colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
        colProductos.setCellValueFactory(dato -> dato.getValue().getProductos());
        colCantidadVendida.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
        colSubtotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
        colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
        colFechaDeVenta.setCellValueFactory(dato -> dato.getValue().getFechaDeVenta());
        listaFacturas = FXCollections.observableArrayList();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Usuario.fxml"));
            Parent root = loader.load();
            factura2Controller = loader.getController();
            factura2Controller.setFacturaController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Utilitaria.mostrarAlerta("Error", "No se pudo cargar la interfaz de Usuario.fxml");
        }

        tblVentas.setItems(listaFacturasCompartida);
    }

    @FXML
    private void guardarRegistroFactura(ActionEvent event) throws Exception {

        String folio = txtFolio.getText().trim();
        String productos = txtProductos.getText().trim();
        String cantidadVendida = txtCantidadVendida.getText().trim();
        String subtotal = txtSubtotal.getText().trim();
        String total = txtTotal.getText().trim();
        String fechaVenta = txtFechaDeVenta.getText().trim();

        if (!Ventas.validarCampos(String.valueOf(folio), String.valueOf(cantidadVendida), String.valueOf(subtotal),
                String.valueOf(total), fechaVenta, productos)) {
            return;
        }

        //
        int folioI = Integer.parseInt(folio);
        int cantidadVendidaD = Integer.parseInt(cantidadVendida);
        float subtotalD = Float.parseFloat(subtotal);
        float totalD = Float.parseFloat(total);

        Facturas venta = new Facturas(folioI, productos, cantidadVendidaD, subtotalD, totalD, fechaVenta);
        try {
            String MySql = "INSERT INTO ventas (Folio, CantidadVendida, Subtotal, Total, FechaDeVenta, Productos) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = ConnectionFactory.getConnection().prepareStatement(MySql);
            insertStatement.setInt(1, venta.getFolio().get());
            insertStatement.setInt(2, venta.getCantidadVendida().get());
            insertStatement.setFloat(3, venta.getSubtotal().get());
            insertStatement.setFloat(4, venta.getTotal().get());
            insertStatement.setString(5, venta.getFechaDeVenta().get());
            insertStatement.setString(6, venta.getProductos().get());

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                Utilitaria.mostrarAlerta("Éxito", "El registro se ha guardado correctamente.");
                // listaFacturas.add(venta);
                factura2Controller.agregarFactura(venta); // Agregar la venta al controlador de usuario
                agregarFacturaCompartida(venta);// Agregar la venta al controlado del empleado

                factura2Controller.suma(venta);// hacer la suma con el metodo
                limpiarCampos();

            } else {
                Utilitaria.mostrarAlerta("Error", "No se pudo guardar el registro.");
            }
        } catch (SQLException e) {
            Utilitaria.mostrarAlerta("Error", "Ocurrió un error al guardar el registro: " + e.getMessage());
        }
    }

    @FXML
    private void eliminarRegistroFactura(ActionEvent event) throws Exception {// el throws Exception siempre se ocupa
                                                                              // para las conexiones
        Facturas selectedProducto = tblVentas.getSelectionModel().getSelectedItem();

        if (selectedProducto == null) {
            Utilitaria.mostrarAlerta("Error", "No se ha seleccionado ningún registro.");
            return;
        }

        int folioVenta = selectedProducto.getFolio().get();

        try {
            // tiene que ser en ese orden ya que es un modelo relacional
            // Eliminar los registros asociados en la tabla "ventas_pasteles" primero
            String deleteVentasSql = "DELETE FROM ventas_pasteles WHERE folioVenta_FK = ?";
            PreparedStatement deleteVentasStatement = ConnectionFactory.getConnection()
                    .prepareStatement(deleteVentasSql);
            deleteVentasStatement.setInt(1, folioVenta);
            deleteVentasStatement.executeUpdate();

            // Eliminar el registro de la tabla "ventas"
            String deletePastelesSql = "DELETE FROM ventas WHERE Folio = ?";
            PreparedStatement deletePastelesStatement = ConnectionFactory.getConnection()
                    .prepareStatement(deletePastelesSql);
            deletePastelesStatement.setInt(1, folioVenta);
            int rowsAffected = deletePastelesStatement.executeUpdate();

            // rowsAffected se utiliza para almacenar el número de filas afectadas por una
            // operación de eliminación en una base de datos.
            // se utiliza para verificar si la eliminación del registro de la tabla
            if (rowsAffected > 0) {
                Utilitaria.mostrarAlerta("Éxito", "El registro se ha eliminado correctamente.");
                // quita la fila seleccionada
                listaFacturas.remove(selectedProducto);
            } else {
                Utilitaria.mostrarAlerta("Error", "No se pudo eliminar el registro.");
            }
        } catch (SQLException e) {
            Utilitaria.mostrarAlerta("Error", "Ocurrió un error al eliminar el registro: " + e.getMessage());
        }
    }

    @FXML
    private void actualizarRegistroFactura() throws Exception {
        Facturas selectedVentas = tblVentas.getSelectionModel().getSelectedItem();

        if (selectedVentas != null) {
            int folioVenta = selectedVentas.getFolio().get();
            int cantidad = selectedVentas.getCantidadVendida().get();
            double subtotal = selectedVentas.getSubtotal().get();
            double total = selectedVentas.getTotal().get();
            String fechaVenta = selectedVentas.getFechaDeVenta().get();
            String productos = selectedVentas.getProductos().get();

            if (!Ventas.validarCampos(String.valueOf(folioVenta), String.valueOf(cantidad), String.valueOf(subtotal),
                    String.valueOf(total), fechaVenta, productos)) {
                return;
            }

            try {
                Connection connection = ConnectionFactory.getConnection();
                String updateQuery = "UPDATE ventas SET CantidadVendida = ?, Subtotal = ?, Total = ?, FechaDeVenta = ?, Productos = ? WHERE Folio = ?";
                PreparedStatement statement = connection.prepareStatement(updateQuery);

                statement.setInt(1, cantidad);
                statement.setDouble(2, subtotal);
                statement.setDouble(3, total);
                statement.setString(4, fechaVenta);
                statement.setString(5, productos);
                statement.setInt(6, folioVenta);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    Utilitaria.mostrarAlerta("Éxito", "Registro actualizado correctamente en la base de datos.");
                } else {
                    Utilitaria.mostrarAlerta("Error", "No se encontraron registros con los criterios proporcionados.");
                }

                statement.close();
                connection.close();
            } catch (SQLException e) {
                Utilitaria.mostrarAlerta("Error", "Error al actualizar el registro: " + e.getMessage());
            }
        } else {
            Utilitaria.mostrarAlerta("Error", "No se ha seleccionado ningún registro para actualizar.");
        }
    }

    @FXML
    private void CompraFinalizada(ActionEvent event) {

        // Vaciar la tabla de empleados
        tblVentas.setItems(FXCollections.observableArrayList());
        // Vaciar la tabla de usuarios
        factura2Controller.tblVentas.setItems(FXCollections.observableArrayList());
        // vaciar los totales
        TotalFinal.clear();
        factura2Controller.TotalFinal2.clear();
    }

    private void limpiarCampos() {
        txtFolio.clear();
        txtCantidadVendida.clear();
        txtSubtotal.clear();
        txtTotal.clear();
        txtFechaDeVenta.clear();
        txtProductos.clear();
    }

    public void setFactura2Controller(Factura2Controller controller) {
        factura2Controller = controller;
    }

    private void agregarFacturaCompartida(Facturas factura) {
        listaFacturasCompartida.add(factura);

        sumaTotal += factura.getTotal().get();
        TotalFinal.setText(String.valueOf(sumaTotal));
    }

    @FXML
    public void openWintwo2(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Empleado.fxml"));
            Parent root = loader.load();

            EmpleadoController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Segunda Ventana");

            // Llama al método initialize() de SampleController2
            controlador.initialize(null, null);

            stage.show();

            // Cierra la ventana actual
            Stage myStage = (Stage) btnMenu.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
 * Utilize el método setItems() en la tabla tblVentas para asignar una lista
 * observable vacía (FXCollections.observableArrayList()).
 * 
 */