package Controller;

import Model.Cliente;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;	
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;

import Factory.ConnectionFactory;

public class ClienteController extends UtilitariaNavegabilidad implements Initializable {

	@FXML
	TableView<Cliente> tblCliente;
	


	

	@FXML
	private ComboBox<String> comboBoxFormaDePago;

	@FXML
	private TextField txtNombreCliente;
	@FXML
	private TextField txtApellidoPaterno;
	@FXML
	private TextField txtApellidoMaterno;

	
	@FXML
	private TextField txtTelefono;

	@FXML
	TableColumn<Cliente, String> colNombreCliente;
	@FXML
	TableColumn<Cliente, String> colApellidoPaterno;	
	@FXML
	TableColumn<Cliente, String> colApellidoMaterno;
	
	
	@FXML
	TableColumn<Cliente, String> colTelefono;
	@FXML
	TableColumn<Cliente, String> colFormaDePago;

	private ObservableList<Cliente> listaClientes;

	public void initialize(URL arg0, ResourceBundle arg1) {
		listaClientes = FXCollections.observableArrayList();

		try {
			// executeQuery para ejecutar las consultas
			ResultSet resClientes = ConnectionFactory.executeQuery("SELECT * FROM clientes");// devuelve un objeto

			// Se itera sobre cada fila de resultados del objeto ResultSet utilizando el
			// método next()
			while (resClientes.next()) {
				listaClientes.add(new Cliente(resClientes.getString("Nombre"), resClientes.getString("A_Paterno"),
						resClientes.getString("A_Materno"),
						resClientes.getString("Telefono"), resClientes.getString("Forma_de_pago")));
			}
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}

		tblCliente.setEditable(true);
		colNombreCliente.setCellFactory(TextFieldTableCell.forTableColumn());
		colApellidoPaterno.setCellFactory(TextFieldTableCell.forTableColumn());
		colApellidoMaterno.setCellFactory(TextFieldTableCell.forTableColumn());
		
		colTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
		colFormaDePago.setCellFactory(TextFieldTableCell.forTableColumn());

		colNombreCliente.setCellValueFactory(dato -> dato.getValue().getNombreCliente());
		colApellidoPaterno.setCellValueFactory(dato -> dato.getValue().getApellidoPaterno());
		colApellidoMaterno.setCellValueFactory(dato -> dato.getValue().getApellidoMaterno());
		
		
		colTelefono.setCellValueFactory(dato -> dato.getValue().getTelefono());
		colFormaDePago.setCellValueFactory(dato -> dato.getValue().getFormaDePago());

		tblCliente.setItems(listaClientes);

		

	}
/*
	@FXML
	private void guardarCliente(ActionEvent event) throws Exception {

		// Obtener los valores ingresados en los campos de texto
		String nombre = txtNombreCliente.getText();
		String aPaterno = txtApellidoPaterno.getText();
		String aMaterno = txtApellidoMaterno.getText();
		String calle = txtCalle.getText();
		String cp = txtCP.getText();
		String colonia = txtColonia.getText();
		String delegacion = txtDelegacion.getText();
		String telefono = txtTelefono.getText();
		String formaDePago = comboBoxFormaDePago.getValue();

		// Validar los campos ingresados
		if (!Cliente.validarCampos(nombre, aPaterno, aMaterno, calle, cp, colonia, delegacion, telefono, formaDePago)) {
			return;
		}

		// Crear el objeto Producto con los valores ingresados
		Cliente nuevoProducto = new Cliente(nombre, aPaterno, aMaterno, calle, cp, colonia, delegacion, telefono,
				formaDePago);

		try {
			// Insertar el nuevo registro en la base de datos
			String insertSql = "INSERT INTO clientes (Nombre, A_Paterno, A_Materno, Calle, CP, Colonia, Delegacion, Telefono, Forma_de_pago) "
					+
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement insertStatement = ConnectionFactory.getConnection().prepareStatement(insertSql);
			insertStatement.setString(1, nuevoProducto.getNombreCliente().get());
			insertStatement.setString(2, nuevoProducto.getApellidoPaterno().get());
			insertStatement.setString(3, nuevoProducto.getApellidoMaterno().get());
			insertStatement.setString(4, nuevoProducto.getCalle().get());
			insertStatement.setString(5, nuevoProducto.getCp().get());
			insertStatement.setString(6, nuevoProducto.getColonia().get());
			insertStatement.setString(7, nuevoProducto.getDelegacion().get());
			insertStatement.setString(8, nuevoProducto.getTelefono().get());
			insertStatement.setString(9, nuevoProducto.getFormaDePago().get());

			int rowsAffected = insertStatement.executeUpdate();

			if (rowsAffected > 0) {
				Utilitaria.mostrarAlerta("Éxito", "El registro se ha guardado correctamente.");
				listaClientes.add(nuevoProducto);
				limpiarCampos();
			} else {
				Utilitaria.mostrarAlerta("Error", "No se pudo guardar el registro.");
			}
		} catch (SQLException e) {
			Utilitaria.mostrarAlerta("Error", "Ocurrió un error al guardar el registro: " + e.getMessage());
		}

	}

	private void limpiarCampos() {
		txtNombreCliente.setText("");
		txtApellidoPaterno.setText("");
		txtApellidoMaterno.setText("");
		txtCalle.setText("");
		txtCP.setText("");
		txtColonia.setText("");
		txtDelegacion.setText("");
		txtTelefono.setText("");
	}
*/
	@FXML
	private void eliminarCliente(ActionEvent event) throws Exception {// el throws Exception siempre se ocupa para las
																		// conexiones
		Cliente selectedProducto = tblCliente.getSelectionModel().getSelectedItem();

		if (selectedProducto == null) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "No se ha seleccionado ningún registro.");
			return;
		}

		String telefono = selectedProducto.getTelefono().get();
		// String categoria = selectedProducto.getCategoria().get();

		try {
			// tiene que ser en ese orden ya que es un modelo relacional
			// Eliminar los registros asociados en la tabla "pedidos" primero
			String deletePedidosSql = "DELETE FROM pedidos WHERE TelefonoFK = ?";
			PreparedStatement deletePedidosStatement = ConnectionFactory.getConnection()
					.prepareStatement(deletePedidosSql);
			deletePedidosStatement.setString(1, telefono);

			// executeUpdate() es un método que ejecuta la consulta y devuelve el número de
			// filas afectadas por la operación
			deletePedidosStatement.executeUpdate();

			// Eliminar el registro de la tabla "clientes"
			String deleteClienteSql = "DELETE FROM clientes WHERE Telefono = ?";
			PreparedStatement deleteClienteStatement = ConnectionFactory.getConnection()
					.prepareStatement(deleteClienteSql);
			deleteClienteStatement.setString(1, telefono);
			int rowsAffected = deleteClienteStatement.executeUpdate();

		
			if (rowsAffected > 0) {
				UtilitariaNavegabilidad.mostrarAlerta("Éxito", "El registro se ha eliminado correctamente.");
				// quita la fila seleccionada
				listaClientes.remove(selectedProducto);
			} else {
				UtilitariaNavegabilidad.mostrarAlerta("Error", "No se pudo eliminar el registro.");
			}
		} catch (SQLException e) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Ocurrió un error al eliminar el registro: " + e.getMessage());
		}
	}
		
	@FXML
	private void actualizarCliente() throws Exception {
	    Cliente selectedClientes = tblCliente.getSelectionModel().getSelectedItem();
	    if (selectedClientes != null) {
	        String nombre = selectedClientes.getNombreCliente().get();
	        String apellidoPaterno = selectedClientes.getApellidoPaterno().get();
	        String apellidoMaterno = selectedClientes.getApellidoMaterno().get();
	        String telefono = selectedClientes.getTelefono().get();
	        String formaDePago = selectedClientes.getFormaDePago().get();

	        if (!Cliente.validarCampos(nombre, apellidoPaterno, apellidoMaterno, telefono, formaDePago)) {
	            return;
	        }

	        try {
	            Connection connection = ConnectionFactory.getConnection();
	            String updateQuery = "UPDATE clientes SET Nombre = ?, A_Paterno = ?, A_Materno = ?, Forma_de_pago = ? WHERE Telefono = ?";
	            PreparedStatement statement = connection.prepareStatement(updateQuery);

	            // Corregir los índices aquí
	            statement.setString(1, nombre);
	            statement.setString(2, apellidoPaterno);
	            statement.setString(3, apellidoMaterno);
	            statement.setString(4, formaDePago);
	            statement.setString(5, telefono);

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
	private void buscarCliente(ActionEvent event) throws Exception {
		String telefono = TxtBusqueda.getText();
		if (!telefono.isEmpty()) {
			try {
				Connection connection = ConnectionFactory.getConnection();
				String selectQuery = "SELECT * FROM clientes WHERE Telefono = ?";
				PreparedStatement statement = connection.prepareStatement(selectQuery);
				statement.setString(1, telefono);

				ResultSet resultSet = statement.executeQuery();
				// Limpiar la tabla antes de mostrar los nuevos resultados
				tblCliente.getItems().clear();

				while (resultSet.next()) {
					// Obtener los valores de cada columna del resultado de la consulta
					String nombre = resultSet.getString("Nombre");
					String apellidoP = resultSet.getString("A_Paterno");
					String apellidoM = resultSet.getString("A_Materno");
					
					String telefonoC = resultSet.getString("Telefono");
					String formaPago = resultSet.getString("Forma_de_pago");

					// Crear el objeto Producto con los valores ingresados
					Cliente nuevoProducto = new Cliente(nombre, apellidoP, apellidoM, 
							telefonoC, formaPago);

					// Agregar el objeto Producto a la tabla
					tblCliente.getItems().add(nuevoProducto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Tiene que ingresar el número de teléfono del cliente");

		}
	}

	@FXML
	private void recargarTablaClientes(ActionEvent event) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String selectQuery = "SELECT * FROM clientes";
			// El Statement se utiliza para ejecutar la consulta SQL en la base de datos.
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);// epresenta el conjunto de resultados de la
																		// consulta.

			// Limpiar la tabla antes de mostrar los nuevos resultados
			tblCliente.getItems().clear();

			while (resultSet.next()) {
				// Obtener los valores de cada columna del resultado de la consulta
				String nombre = resultSet.getString("Nombre");
				String apellidoP = resultSet.getString("A_Paterno");
				String apellidoM = resultSet.getString("A_Materno");
			
			
				String telefonoC = resultSet.getString("Telefono");
				String formaPago = resultSet.getString("Forma_de_pago");

				// Crear un nuevo objeto Pasteles con los valores obtenidos
				Cliente nuevoProducto = new Cliente(nombre, apellidoP, apellidoM, 
						telefonoC, formaPago);

				// Agregar el objeto Producto a la tabla
				tblCliente.getItems().add(nuevoProducto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
