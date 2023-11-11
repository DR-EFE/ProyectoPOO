package Controller;

import Model.Ventas;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Factory.ConnectionFactory;

public class VentasController extends Utilitaria implements Initializable {

	@FXML
	TableView<Ventas> tblVentas;

	@FXML
	private TextField TxtBuquedaVenta;
	
	@FXML
	private Button btnRecargarTablaVenta;

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
	TableColumn<Ventas, Integer> colFolio;
	@FXML
	TableColumn<Ventas, Integer> colCantidadVendida;
	@FXML
	TableColumn<Ventas, Float> colSubtotal;
	@FXML
	TableColumn<Ventas, Float> colTotal;
	@FXML
	TableColumn<Ventas, String> colFechaDeVenta;
	@FXML
	TableColumn<Ventas, String> colProductos;

	private ObservableList<Ventas> listaVentas;

	public void initialize(URL arg0, ResourceBundle arg1) {
		listaVentas = FXCollections.observableArrayList();

		try {

			ResultSet resVentas = ConnectionFactory.executeQuery("SELECT * FROM ventas");// devuelve un objeto

			// aquí se ingresan los nombres de los campos de la bd
			while (resVentas.next()) {
				listaVentas.add(new Ventas(
						resVentas.getInt("Folio"),
						resVentas.getInt("CantidadVendida"),
						resVentas.getFloat("Subtotal"),
						resVentas.getFloat("Total"),
						resVentas.getString("FechaDeVenta"),
						resVentas.getString("Productos")));
			}
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}

		tblVentas.setEditable(true);
		colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colProductos.setCellFactory(TextFieldTableCell.forTableColumn());
		colCantidadVendida.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colSubtotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colFechaDeVenta.setCellFactory(TextFieldTableCell.forTableColumn());

		// establesco y muestro los datos de mi base datos en la pantalla de fx
		colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
		colProductos.setCellValueFactory(dato -> dato.getValue().getProductos());
		colCantidadVendida.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
		colSubtotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
		colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
		colFechaDeVenta.setCellValueFactory(dato -> dato.getValue().getFechaDeVenta());

		tblVentas.setItems(listaVentas);

	}

	@FXML
	private void guardarVenta(ActionEvent event) throws Exception {
		if (validarCampos()) {
			int folio = Integer.parseInt(txtFolio.getText());
			int cantidadVendida = Integer.parseInt(txtCantidadVendida.getText());
			float subtotal = Float.parseFloat(txtSubtotal.getText());
			float total = Float.parseFloat(txtTotal.getText());
			String fechaVenta = txtFechaDeVenta.getText();
			String productos = txtProductos.getText();

			Ventas venta = new Ventas(folio, cantidadVendida, subtotal, total, fechaVenta, productos);
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
				// listaVentas.add(venta);
				// insertarVenta(venta);

				if (rowsAffected > 0) {
					Utilitaria.mostrarAlerta("Éxito", "El registro se ha guardado correctamente.");
					listaVentas.add(venta);
					limpiarCampos();
				} else {
					Utilitaria.mostrarAlerta("Error", "No se pudo guardar el registro.");
				}
			} catch (SQLException e) {
				Utilitaria.mostrarAlerta("Error", "Ocurrió un error al guardar el registro: " + e.getMessage());
			}
		}
	}

	private void limpiarCampos() {
		txtFolio.clear();
		txtCantidadVendida.clear();
		txtSubtotal.clear();
		txtTotal.clear();
		txtFechaDeVenta.clear();
		txtProductos.clear();
	}

	private boolean validarCampos() {
		return !txtFolio.getText().isEmpty()
				&& !txtProductos.getText().isEmpty()
				&& !txtCantidadVendida.getText().isEmpty()
				&& !txtSubtotal.getText().isEmpty()
				&& !txtTotal.getText().isEmpty()
				&& !txtFechaDeVenta.getText().isEmpty();
	}

	@FXML
	private void eliminarVenta(ActionEvent event) throws Exception {// el throws Exception siempre se ocupa para las
																	// conexiones
		Ventas selectedProducto = tblVentas.getSelectionModel().getSelectedItem();

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
				listaVentas.remove(selectedProducto);
			} else {
				Utilitaria.mostrarAlerta("Error", "No se pudo eliminar el registro.");
			}
		} catch (SQLException e) {
			Utilitaria.mostrarAlerta("Error", "Ocurrió un error al eliminar el registro: " + e.getMessage());
		}
	}

	@FXML
	private void actualizarVenta() throws Exception {
		Ventas selectedVentas = tblVentas.getSelectionModel().getSelectedItem();
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
	private void buscarVenta(ActionEvent event) throws Exception {
		String folioVenta = TxtBuquedaVenta.getText();
		if ( Ventas.validarCampoBusqueda(folioVenta)) {
			try {
				Connection connection = ConnectionFactory.getConnection();
				String selectQuery = "SELECT * FROM ventas WHERE Folio = ?";
				PreparedStatement statement = connection.prepareStatement(selectQuery);
				statement.setString(1, folioVenta);

				ResultSet resultSet = statement.executeQuery();
				// Limpiar la tabla antes de mostrar los nuevos resultados
				tblVentas.getItems().clear();

				while (resultSet.next()) {
					// Obtener los valores de cada columna del resultado de la consulta
					int folioV = resultSet.getInt("Folio");
					int cantidad = resultSet.getInt("CantidadVendida");
					float subtotal = resultSet.getFloat("Subtotal");
					float total = resultSet.getFloat("Total");
					String fechaVenta = resultSet.getString("FechaDeVenta");
					String productos = resultSet.getString("Productos");

					// Crear el objeto Pedido con los valores obtenidos
					Ventas nuevaVenta = new Ventas(folioV, cantidad, subtotal, total, fechaVenta, productos);

					// Agregar el objeto Pedido a la tabla
					tblVentas.getItems().add(nuevaVenta);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		
			recargarTablaVentas();
		}
		
		
			
			
			
		
		
		
	}


	private void recargarTablaVentas() throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String selectQuery = "SELECT * FROM ventas";
			// El Statement se utiliza para ejecutar la consulta SQL en la base de datos.
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);// epresenta el conjunto de resultados de la
																		// consulta.

			// Limpiar la tabla antes de mostrar los nuevos resultados
			tblVentas.getItems().clear();

			while (resultSet.next()) {
				int folioV = resultSet.getInt("Folio");
				int cantidad = resultSet.getInt("CantidadVendida");
				float subtotal = resultSet.getFloat("Subtotal");
				float total = resultSet.getFloat("Total");
				String fechaVenta = resultSet.getString("FechaDeVenta");
				String productos = resultSet.getString("Productos");

				// Crear un nuevo objeto Pasteles con los valores obtenidos
				Ventas nuevoProducto = new Ventas(folioV, cantidad, subtotal, total, fechaVenta, productos);

				// Agregar el objeto Producto a la tabla
				tblVentas.getItems().add(nuevoProducto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}