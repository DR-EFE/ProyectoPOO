package Controller;

import Model.Pedido;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.util.converter.DoubleStringConverter;
import java.sql.Timestamp;

import Factory.ConnectionFactory;

public class PedidosController extends UtilitariaNavegabilidad implements Initializable {

	@FXML
	TableView<Pedido> tblPedido;

	@FXML
	private Button btnBuscarPedido;
	@FXML
	private Button btnRecargarTablaPedido;

	@FXML
	private TextField TxtBuscarPedido;

	@FXML
	private TextField txtFolio;
	@FXML
	private DatePicker FechaHoraDeSolicitud;
	@FXML
	private TextField txtDescripcionPedido;
	@FXML
	private TextField txtAnticipo;
	@FXML
	private TextField txtTipo;
	@FXML
	private TextField txtSubtotal;
	@FXML
	private TextField txtTotal;
	@FXML
	private TextField txtEstatus;
	@FXML
	private DatePicker FechaHoraEntrega;
	@FXML
	private TextField txtTelefono_FK;

	@FXML
	TableColumn<Pedido, Integer> colFolio;
	@FXML
	TableColumn<Pedido, String> colFechaHoraSolicitud;
	@FXML
	TableColumn<Pedido, String> colDescripcion;
	@FXML
	TableColumn<Pedido, Double> colAnticipo;
	@FXML
	TableColumn<Pedido, String> colTipo;
	@FXML
	TableColumn<Pedido, Double> colSubtotal;
	@FXML
	TableColumn<Pedido, Double> colTotal;
	@FXML
	TableColumn<Pedido, String> colEstatus;
	@FXML
	TableColumn<Pedido, String> colFechaHoraEntrega;
	@FXML
	TableColumn<Pedido, String> colTelefono_FK;

	private ObservableList<Pedido> listaPedido;

	public void initialize(URL arg0, ResourceBundle arg1) {
		listaPedido = FXCollections.observableArrayList();

		try {

			ResultSet resPedido = ConnectionFactory.executeQuery("SELECT * FROM pedidos");// devuelve un objeto

			while (resPedido.next()) {
				listaPedido.add(new Pedido(resPedido.getInt("Folio"), resPedido.getString("FechaHoraSolicitud"),
						resPedido.getString("Descripcion"), resPedido.getDouble("Anticipo"),
						resPedido.getString("Tipo"), resPedido.getDouble("Subtotal"),
						resPedido.getDouble("Total"), resPedido.getString("Estatus"),
						resPedido.getString("FechaHoraEntrega"), resPedido.getString("TelefonoFK")));
			}
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}

		tblPedido.setEditable(true);
		colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colFechaHoraSolicitud.setCellFactory(TextFieldTableCell.forTableColumn());
		colDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
		colAnticipo.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		colTipo.setCellFactory(TextFieldTableCell.forTableColumn());
		colSubtotal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		colEstatus.setCellFactory(TextFieldTableCell.forTableColumn());
		colFechaHoraEntrega.setCellFactory(TextFieldTableCell.forTableColumn());
		colTelefono_FK.setCellFactory(TextFieldTableCell.forTableColumn());

		colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
		colFechaHoraSolicitud.setCellValueFactory(dato -> dato.getValue().getFechaHoraSolicitud());
		// colFechaHoraSolicitud.setCellValueFactory(dato ->
		// Bindings.createObjectBinding(() ->
		// dato.getValue().getFechaHoraSolicitud().get(),
		// dato.getValue().getFechaHoraSolicitud()));
		colDescripcion.setCellValueFactory(dato -> dato.getValue().getDescripcion());
		colAnticipo.setCellValueFactory(dato -> dato.getValue().getAnticipo().asObject());
		colTipo.setCellValueFactory(dato -> dato.getValue().getTipo());
		colSubtotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
		colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
		colEstatus.setCellValueFactory(dato -> dato.getValue().getEstatus());
		colFechaHoraEntrega.setCellValueFactory(dato -> dato.getValue().getFechaHoraEntrega());
		// colFechaHoraEntrega.setCellValueFactory(dato ->
		// Bindings.createObjectBinding(() ->
		// dato.getValue().getFechaHoraEntrega().get(),
		// dato.getValue().getFechaHoraEntrega()));
		colTelefono_FK.setCellValueFactory(dato -> dato.getValue().getTelefonoFK());

		tblPedido.setItems(listaPedido);

	}

	@FXML
	private void actualizarRegistroPedido() throws Exception {
		Pedido selectedPedido = tblPedido.getSelectionModel().getSelectedItem();

		if (selectedPedido != null) {
			int folio = selectedPedido.getFolio().get();
			String fechaHoraSolicitud = selectedPedido.getFechaHoraSolicitud().get();
			String descripcion = selectedPedido.getDescripcion().get();
			double anticipo = selectedPedido.getAnticipo().get();
			String tipo = selectedPedido.getTipo().get();
			double subtotal = selectedPedido.getSubtotal().get();
			double total = selectedPedido.getTotal().get();
			String estatus = selectedPedido.getEstatus().get();
			String fechaHoraEntrega = selectedPedido.getFechaHoraEntrega().get();
			String telefonoFK = selectedPedido.getTelefonoFK().get();

			if (!Pedido.validarCampos(String.valueOf(folio), fechaHoraSolicitud, descripcion, String.valueOf(anticipo),
					tipo, String.valueOf(subtotal), String.valueOf(total), estatus, fechaHoraEntrega, telefonoFK)) {
				return;
			}

			try {
				Connection connection = ConnectionFactory.getConnection();
				String updateQuery = "UPDATE pedidos SET FechaHoraSolicitud = ?, Descripcion = ?, Anticipo = ?, Tipo = ?, Subtotal = ?, Total = ?, Estatus = ?, FechaHoraEntrega = ?, TelefonoFK = ? WHERE Folio = ?";
				PreparedStatement statement = connection.prepareStatement(updateQuery);

				statement.setTimestamp(1, Timestamp.valueOf(fechaHoraSolicitud)); // FechaHoraSolicitud
				statement.setString(2, descripcion); // Descripcion
				statement.setDouble(3, anticipo); // Anticipo
				statement.setString(4, tipo); // Tipo
				statement.setDouble(5, subtotal); // Subtotal
				statement.setDouble(6, total); // Total
				statement.setString(7, estatus); // Estatus
				statement.setTimestamp(8, Timestamp.valueOf(fechaHoraEntrega)); // FechaHoraEntrega
				statement.setString(9, telefonoFK); // TelefonoFK
				statement.setInt(10, folio); // Folio

				int rowsUpdated = statement.executeUpdate();

				if (rowsUpdated > 0) {
					UtilitariaNavegabilidad.mostrarAlerta("Éxito", "Registro actualizado correctamente en la base de datos.");
				} else {
					UtilitariaNavegabilidad.mostrarAlerta("Error", "No se encontraron registros con los criterios proporcionados.");
				}

				statement.close();
				connection.close();
			} catch (SQLException e) {
				UtilitariaNavegabilidad.mostrarAlerta("Error", "Error al actualizar el registro: " + e.getMessage());
			}
		} else {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "No se ha seleccionado ningún registro para actualizar.");
		}
	}

	@FXML
	private void eliminarRegistro(ActionEvent event) throws Exception {
		Pedido selectedPedido = tblPedido.getSelectionModel().getSelectedItem();

		if (selectedPedido == null) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "No se ha seleccionado ningún registro.");
			return;
		}

		int folio = selectedPedido.getFolio().get();

		try {
			Connection connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false); // Desactivar el modo de confirmación automática

			// Eliminar los registros asociados en la tabla "clientes" primero
			String deleteVentasSql = "DELETE FROM clientes WHERE Telefono = ?";
			PreparedStatement deleteVentasStatement = connection.prepareStatement(deleteVentasSql);
			deleteVentasStatement.setInt(1, folio);
			deleteVentasStatement.executeUpdate();

			// Eliminar el pedido de la tabla "Pedidos"
			String deletePedidoSql = "DELETE FROM Pedidos WHERE Folio = ?";
			PreparedStatement deletePedidoStatement = connection.prepareStatement(deletePedidoSql);
			deletePedidoStatement.setInt(1, folio);
			deletePedidoStatement.executeUpdate();

			connection.commit(); // Confirmar los cambios realizados en la base de datos

			UtilitariaNavegabilidad.mostrarAlerta("Éxito", "El registro se ha eliminado correctamente.");
			listaPedido.remove(selectedPedido);
		} catch (SQLException e) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Ocurrió un error al eliminar el registro: " + e.getMessage());
		}
	}

	@FXML
	public void guardarRegistro(ActionEvent event) throws Exception {

		String fechaHoraS = null;
		String fechaHoraE = null;
		if (FechaHoraDeSolicitud.getValue() != null) {
			fechaHoraS = FechaHoraDeSolicitud.getValue().toString();
		}
		if (FechaHoraEntrega.getValue() != null) {
			fechaHoraE = FechaHoraEntrega.getValue().toString();
		}

		// Obtener los valores ingresados en los campos de texto
		String folio = txtFolio.getText().trim();
		String descripcion = txtDescripcionPedido.getText().trim();
		String anticipo = txtAnticipo.getText().trim();
		String tipo = txtTipo.getText().trim();
		String subtotal = txtSubtotal.getText().trim();
		String total = txtTotal.getText().trim();
		String estatus = txtEstatus.getText().trim();
		String telefonoFK = txtTelefono_FK.getText().trim();

		// Validar los campos ingresados
		if (!Pedido.validarCampos(folio, fechaHoraS, descripcion, anticipo, tipo, subtotal, total, estatus, fechaHoraE,
				telefonoFK)) {
			return;
		}

		// validacion de campos numericos
		int folioI = Integer.parseInt(folio);
		double anticipoD = Double.parseDouble(anticipo);
		double subtotalD = Double.parseDouble(subtotal);
		double totalD = Double.parseDouble(total);

		// Crear el objeto Pedido con los valores ingresados
		Pedido nuevoPedido = new Pedido(folioI, fechaHoraS, descripcion, anticipoD, tipo, subtotalD, totalD, estatus,
				fechaHoraE, telefonoFK);

		try {
			// Insertar el nuevo registro en la base de datos
			String insertSql = "INSERT INTO Pedidos (Folio, FechaHoraSolicitud,Descripcion, Anticipo, Tipo, Subtotal, Total, Estatus,  FechaHoraEntrega, TelefonoFK) "
					+
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement insertStatement = ConnectionFactory.getConnection().prepareStatement(insertSql);
			// el .get() recupera el valor real ,Int,String,Double...
			insertStatement.setInt(1, nuevoPedido.getFolio().get());
			insertStatement.setString(2, nuevoPedido.getFechaHoraSolicitud().get());
			insertStatement.setString(3, nuevoPedido.getDescripcion().get());
			insertStatement.setDouble(4, nuevoPedido.getAnticipo().get());
			insertStatement.setString(5, nuevoPedido.getTipo().get());
			insertStatement.setDouble(6, nuevoPedido.getSubtotal().get());
			insertStatement.setDouble(7, nuevoPedido.getTotal().get());
			insertStatement.setString(8, nuevoPedido.getEstatus().get());
			insertStatement.setString(9, nuevoPedido.getFechaHoraEntrega().get());
			insertStatement.setString(10, nuevoPedido.getTelefonoFK().get());

			int rowsAffected = insertStatement.executeUpdate();

			if (rowsAffected > 0) {
				UtilitariaNavegabilidad.mostrarAlerta("Éxito", "El registro se ha guardado correctamente.");
				listaPedido.add(nuevoPedido);
				limpiarCampos();
			} else {
				UtilitariaNavegabilidad.mostrarAlerta("Error", "No se pudo guardar el registro.");
			}
		} catch (SQLException e) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Ocurrió un error al guardar el registro: " + e.getMessage());
		}
	}

	private void limpiarCampos() {
		txtFolio.setText("");
		txtDescripcionPedido.setText("");
		txtAnticipo.setText("");
		txtTipo.setText("");
		txtSubtotal.setText("");
		txtTotal.setText("");
		txtEstatus.setText("");
		txtTelefono_FK.setText("");
	}

	@FXML
	private void buscarPedido(ActionEvent event) throws Exception {
		String folioPedido = TxtBuscarPedido.getText();
		if (!folioPedido.isEmpty()) {
			try {
				Connection connection = ConnectionFactory.getConnection();
				String selectQuery = "SELECT * FROM pedidos WHERE Folio = ?";
				PreparedStatement statement = connection.prepareStatement(selectQuery);
				statement.setString(1, folioPedido);

				ResultSet resultSet = statement.executeQuery();
				// Limpiar la tabla antes de mostrar los nuevos resultados
				tblPedido.getItems().clear();

				while (resultSet.next()) {
					// Obtener los valores de cada columna del resultado de la consulta
					int folio = resultSet.getInt("Folio");
					String fechaHoraSoli = resultSet.getString("FechaHoraSolicitud");
					String descripcionP = resultSet.getString("Descripcion");
					double anticipo = resultSet.getDouble("Anticipo");
					String tipoP = resultSet.getString("Tipo");
					double subtotalP = resultSet.getDouble("Subtotal");
					double total = resultSet.getDouble("Total");
					String estatus = resultSet.getString("Estatus");
					String fechaHoraEntrega = resultSet.getString("FechaHoraEntrega");
					String telefono = resultSet.getString("TelefonoFK");

					// Crear el objeto Pedido con los valores obtenidos
					Pedido nuevoPedido = new Pedido(folio, fechaHoraSoli, descripcionP, anticipo,
							tipoP, subtotalP, total, estatus, fechaHoraEntrega, telefono);

					// Agregar el objeto Pedido a la tabla
					tblPedido.getItems().add(nuevoPedido);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Debe ingresar un código de barras del producto");
		}
	}

	@FXML
	private void recargarTablaPedido(ActionEvent event) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String selectQuery = "SELECT * FROM pedidos";
			// El Statement se utiliza para ejecutar la consulta SQL en la base de datos.
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);// epresenta el conjunto de resultados de la
																		// consulta.

			// Limpiar la tabla antes de mostrar los nuevos resultados
			tblPedido.getItems().clear();

			while (resultSet.next()) {
				// Obtener los valores de cada columna del resultado de la consulta
				int folio = resultSet.getInt("Folio");
				String fechaHoraSoli = resultSet.getString("FechaHoraSolicitud");
				String descripcionP = resultSet.getString("Descripcion");
				double anticipo = resultSet.getDouble("Anticipo");
				String tipoP = resultSet.getString("Tipo");
				double subtotalP = resultSet.getDouble("Subtotal");
				double total = resultSet.getDouble("Total");
				String estatus = resultSet.getString("Estatus");
				String fechaHoraEntrega = resultSet.getString("FechaHoraEntrega");
				String telefono = resultSet.getString("TelefonoFK");

				// Crear un nuevo objeto Pasteles con los valores obtenidos
				Pedido nuevoProducto = new Pedido(folio, fechaHoraSoli, descripcionP, anticipo, tipoP, subtotalP, total,
						estatus,
						fechaHoraEntrega, telefono);

				// Agregar el objeto Producto a la tabla
				tblPedido.getItems().add(nuevoProducto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}