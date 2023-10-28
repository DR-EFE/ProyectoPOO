package Controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import Factory.ConnectionFactory;
import Model.Pasteles;

public class PastelesController extends Utilitaria implements Initializable {

	@FXML
	TableView<Pasteles> tblPais;
	@FXML
	private Button btnRecargar;
	@FXML
	private Button closeButton;

	@FXML
	private Button btnMenu;
	@FXML
	private Button minimizeButton;
	// @FXML
	// private Button minimizeTButton;
	@FXML
	private Button btnFormulario;
	@FXML
	private DatePicker FechaDeVencimiento;
	@FXML
	private DatePicker FechaElab;

	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnActualizar;

	@FXML
	private Button BtnBusqueda;

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private TextField TxtBuqueda;

	@FXML
	private TextField txtCodigoBarras;

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtDescripcion;
	@FXML
	private TextField txtPeso;
	@FXML
	private TextField txtCantidadRefri;

	@FXML
	private TextField txtPrecio;
	// @FXML
	// private TextField txtFechaVencimiento;
	// @FXML
	// private TextField txtFechaElaboracion;

	@FXML
	TableColumn<Pasteles, String> colCodigodeBarras;
	@FXML
	TableColumn<Pasteles, Float> colPrecio;
	@FXML
	TableColumn<Pasteles, String> colNombre;
	@FXML
	TableColumn<Pasteles, Integer> colPeso;
	@FXML
	TableColumn<Pasteles, String> colDescripcion;
	@FXML
	TableColumn<Pasteles, Integer> colCantidad_en_refri;
	@FXML
	TableColumn<Pasteles, String> colFecha_de_elaboracion;
	@FXML
	TableColumn<Pasteles, String> colFecha_de_vencimiento;
	@FXML
	TableColumn<Pasteles, String> colCategoria_FK;

	private ObservableList<Pasteles> lista;

	public void initialize(URL arg0, ResourceBundle arg1) {
		lista = FXCollections.observableArrayList();

		try {
			ResultSet res = ConnectionFactory.executeQuery("SELECT * FROM pasteles");

			while (res.next()) {
				lista.add(new Pasteles(res.getString("Codigo_de_Barras"),
						res.getString("Nombre"),
						res.getString("Descripcion"),
						res.getFloat("Precio"),
						res.getInt("Peso"),
						res.getInt("Cantidad_en_refri"),
						res.getString("Fecha_de_elaboracion"),
						res.getString("Fecha_de_vencimiento"),
						res.getString("Categoria_FK")));
			}
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}
		// fábrica de celdas de las columnas para crear celdas de tipo
		// TextFieldTableCell

		colCodigodeBarras.setCellFactory(TextFieldTableCell.forTableColumn());
		colNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		colDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
		colFecha_de_elaboracion.setCellFactory(TextFieldTableCell.forTableColumn());
		colFecha_de_vencimiento.setCellFactory(TextFieldTableCell.forTableColumn());
		colCategoria_FK.setCellFactory(TextFieldTableCell.forTableColumn());

		// mas validaciones para campos numericos
		colPrecio.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter() {

			@Override
			public Float fromString(String string) {
				try {
					return Float.parseFloat(string);
				} catch (NumberFormatException e) {
					Utilitaria.mostrarAlerta("Error", "el valor que ingreso no es un numero");
					return null;// O lanzar una excepción personalizada
				}
			}
		}));

		colPeso.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() {
			@Override
			public Integer fromString(String string) {
				try {
					return Integer.parseInt(string);
				} catch (NumberFormatException e) {
					Utilitaria.mostrarAlerta("Error", "el valor que ingreso no es un numero entero");
					return null;
				}
			}
		}));

		colCantidad_en_refri.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() {
			@Override
			public Integer fromString(String string) {
				try {
					// Intenta convertir la cadena en un número entero
					return Integer.parseInt(string);
					// Si la conversión falla, muestra una alerta de error
				} catch (NumberFormatException e) {
					Utilitaria.mostrarAlerta("Error", "el valor que ingreso no es un numero entero");
					return null;
				}
			}
		}));

		// establece la fábrica de valores de la celda en la columna
		// obtener el valor de la columna del objeto asociado a la fila de la tabla.
		colCodigodeBarras.setCellValueFactory(dato -> dato.getValue().getCodigodeBarras());
		colNombre.setCellValueFactory(dato -> dato.getValue().getNombre());
		colDescripcion.setCellValueFactory(dato -> dato.getValue().getDescripcion());
		colPrecio.setCellValueFactory(dato -> dato.getValue().getPrecio().asObject());
		colPeso.setCellValueFactory(dato -> dato.getValue().getPeso().asObject());
		colCantidad_en_refri.setCellValueFactory(dato -> dato.getValue().getCantidad_en_refri().asObject());
		colFecha_de_elaboracion.setCellValueFactory(dato -> dato.getValue().getFecha_de_elaboracion());
		colFecha_de_vencimiento.setCellValueFactory(dato -> dato.getValue().getFecha_de_vencimiento());
		colCategoria_FK.setCellValueFactory(dato -> dato.getValue().getCategoria());

		// Establece la lista lista como la fuente de datos de la tabla.
		tblPais.setItems(lista);

		List<String> valores = Arrays.asList("Especial", "Jumbo", "Tradicional");
		comboBox.setItems(FXCollections.observableList(valores));
	}

	@FXML
	private void actualizarRegistro() throws Exception {
		Pasteles selectedPasteles = tblPais.getSelectionModel().getSelectedItem();
		if (selectedPasteles != null) {
			String codigoBarras = selectedPasteles.getCodigodeBarras().get();
			String nombre = selectedPasteles.getNombre().get();
			String descripcion = selectedPasteles.getDescripcion().get();
			float precio = selectedPasteles.getPrecio().get();
			int peso = selectedPasteles.getPeso().get();
			int cantidadRefri = selectedPasteles.getCantidad_en_refri().get();
			String fechaElaboracion = selectedPasteles.getFecha_de_elaboracion().get();
			String fechaVencimiento = selectedPasteles.getFecha_de_vencimiento().get();
			String categoria = selectedPasteles.getCategoria().get();

			if (!Pasteles.validarCampos(codigoBarras, nombre, descripcion, String.valueOf(precio), String.valueOf(peso),
					String.valueOf(cantidadRefri), fechaElaboracion, fechaVencimiento, categoria)) {
				return;
			}

			try {
				Connection connection = ConnectionFactory.getConnection();
				String updateQuery = "UPDATE pasteles SET nombre = ?, descripcion = ?, precio = ?, peso = ?, cantidad_en_refri = ?, fecha_de_elaboracion = ?, fecha_de_vencimiento = ?, categoria_FK = ? WHERE codigo_de_barras = ?";
				PreparedStatement statement = connection.prepareStatement(updateQuery);

				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setFloat(3, precio);
				statement.setInt(4, peso);
				statement.setInt(5, cantidadRefri);
				statement.setString(6, fechaElaboracion);
				statement.setString(7, fechaVencimiento);
				statement.setString(8, categoria);
				statement.setString(9, codigoBarras);

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
	private void eliminarRegistro(ActionEvent event) throws Exception {// el throws Exception siempre se ocupa para las
																		// conexiones
		Pasteles selectedProducto = tblPais.getSelectionModel().getSelectedItem();

		if (selectedProducto == null) {
			Utilitaria.mostrarAlerta("Error", "No se ha seleccionado ningún registro.");
			return;
		}

		String codigoBarras = selectedProducto.getCodigodeBarras().get();
		String categoria = selectedProducto.getCategoria().get();

		// obvio todo esta en un try
		try {
			// tiene que ser en ese orden ya que es un modelo relacional
			// Eliminar los registros asociados en la tabla "ventas_pasteles" primero
			String deleteVentasSql = "DELETE FROM ventas_pasteles WHERE CodigoBarrasPastel_FK = ?";
			PreparedStatement deleteVentasStatement = ConnectionFactory.getConnection()
					.prepareStatement(deleteVentasSql);

			deleteVentasStatement.setString(1, codigoBarras);
			// executeUpdate() es un método que ejecuta la consulta y devuelve el número de
			// filas afectadas por la operación
			deleteVentasStatement.executeUpdate();

			// Eliminar el registro de la tabla "pasteles"
			String deletePastelesSql = "DELETE FROM pasteles WHERE codigo_de_barras = ?";
			PreparedStatement deletePastelesStatement = ConnectionFactory.getConnection()
					.prepareStatement(deletePastelesSql);
			deletePastelesStatement.setString(1, codigoBarras);
			int rowsAffected = deletePastelesStatement.executeUpdate();

			// Verificar si la categoría aún tiene registros en la tabla "pasteles"
			String selectPastelesSql = "SELECT COUNT(*) FROM pasteles WHERE categoria_fk = ?";
			PreparedStatement selectPastelesStatement = ConnectionFactory.getConnection()
					.prepareStatement(selectPastelesSql);
			selectPastelesStatement.setString(1, categoria);
			ResultSet resultSet = selectPastelesStatement.executeQuery();
			resultSet.next();
			int registrosCategoria = resultSet.getInt(1);

			// Si no hay más registros en la tabla "pasteles" que referencien a la
			// categoría, eliminar la categoría
			if (registrosCategoria == 0) {
				String deleteCategoriaSql = "DELETE FROM categorias WHERE nombre = ?";
				PreparedStatement deleteCategoriaStatement = ConnectionFactory.getConnection()
						.prepareStatement(deleteCategoriaSql);
				deleteCategoriaStatement.setString(1, categoria);
				int categoriaRowsAffected = deleteCategoriaStatement.executeUpdate();
			}

			// rowsAffected se utiliza para almacenar el número de filas afectadas por una
			// operación de eliminación en una base de datos.
			// se utiliza para verificar si la eliminación del registro de la tabla
			if (rowsAffected > 0) {
				Utilitaria.mostrarAlerta("Éxito", "El registro se ha eliminado correctamente.");
				// quita la fila seleccionada
				lista.remove(selectedProducto);
			} else {
				Utilitaria.mostrarAlerta("Error", "No se pudo eliminar el registro.");
			}
		} catch (SQLException e) {
			Utilitaria.mostrarAlerta("Error", "Ocurrió un error al eliminar el registro: " + e.getMessage());
		}
	}

	@FXML
	private void guardarRegistro(ActionEvent event) throws Exception {

		String fechaElaboracion = null;
		String fechaVencimiento = null;
		if (FechaElab.getValue() != null) {
			fechaElaboracion = FechaElab.getValue().toString();
		}
		if (FechaDeVencimiento.getValue() != null) {
			fechaVencimiento = FechaDeVencimiento.getValue().toString();
		}

		// Obtener los valores ingresados en los campos de texto
		String codigoBarras = txtCodigoBarras.getText().trim();
		String nombre = txtNombre.getText();
		String descripcion = txtDescripcion.getText();
		String precioText = txtPrecio.getText().trim();
		String pesoText = txtPeso.getText().trim();
		String cantidadRefriText = txtCantidadRefri.getText().trim();
		// String fechaElaboracion = FechaElab.getValue().toString();
		// String fechaVencimiento = FechaDeVencimiento.getValue().toString();

		String categoria = comboBox.getValue();

		// Validar los campos ingresados
		if (!Pasteles.validarCampos(codigoBarras, nombre, descripcion, precioText, pesoText, cantidadRefriText,
				fechaElaboracion, fechaVencimiento, categoria)) {
			return;
		}

		// Validación de entrada para campos numéricos

		float precio = Float.parseFloat(precioText);
		int peso = Integer.parseInt(pesoText);
		int cantidadRefri = Integer.parseInt(cantidadRefriText);
		try {
			precio = Float.parseFloat(precioText);
			peso = Integer.parseInt(pesoText);
			cantidadRefri = Integer.parseInt(cantidadRefriText);
		} catch (NumberFormatException e) {
			Utilitaria.mostrarAlerta("Error", "Por favor, ingrese valores numéricos válidos.");
			return;
		}

		// Crear el objeto Producto con los valores ingresados
		Pasteles nuevoProducto = new Pasteles(codigoBarras, nombre, descripcion, precio,
				peso, cantidadRefri, fechaElaboracion, fechaVencimiento, categoria);

		try {
			// Insertar el nuevo registro en la base de datos
			String insertSql = "INSERT INTO pasteles (Codigo_de_Barras, Nombre, Descripcion, Precio, Peso, Cantidad_en_refri, Fecha_de_elaboracion, Fecha_de_vencimiento, Categoria_FK) "
					+
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement insertStatement = ConnectionFactory.getConnection().prepareStatement(insertSql);
			insertStatement.setString(1, nuevoProducto.getCodigodeBarras().get());
			insertStatement.setString(2, nuevoProducto.getNombre().get());
			insertStatement.setString(3, nuevoProducto.getDescripcion().get());
			insertStatement.setFloat(4, nuevoProducto.getPrecio().get());
			insertStatement.setInt(5, nuevoProducto.getPeso().get());
			insertStatement.setInt(6, nuevoProducto.getCantidad_en_refri().get());
			insertStatement.setString(7, nuevoProducto.getFecha_de_elaboracion().get());
			insertStatement.setString(8, nuevoProducto.getFecha_de_vencimiento().get());
			insertStatement.setString(9, nuevoProducto.getCategoria().get());

			int rowsAffected = insertStatement.executeUpdate();

			if (rowsAffected > 0) {
				Utilitaria.mostrarAlerta("Éxito", "El registro se ha guardado correctamente.");
				lista.add(nuevoProducto);
				limpiarCampos();
			} else {
				Utilitaria.mostrarAlerta("Error", "No se pudo guardar el registro.");
			}
		} catch (SQLException e) {
			Utilitaria.mostrarAlerta("Error", "Ocurrió un error al guardar el registro: " + e.getMessage());
		}

	}

	private void limpiarCampos() {
		txtCodigoBarras.setText("");
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtPeso.setText("");
		txtCantidadRefri.setText("");

	}

	@FXML
	private void buscar(ActionEvent event) throws Exception {
		String codigoBarras = TxtBuqueda.getText();
		if (!codigoBarras.isEmpty()) {
			try {
				Connection connection = ConnectionFactory.getConnection();
				String selectQuery = "SELECT * FROM pasteles WHERE codigo_de_barras = ?";
				PreparedStatement statement = connection.prepareStatement(selectQuery);
				statement.setString(1, codigoBarras);

				ResultSet resultSet = statement.executeQuery();
				// Limpiar la tabla antes de mostrar los nuevos resultados
				tblPais.getItems().clear();

				while (resultSet.next()) {
					// Obtener los valores de cada columna del resultado de la consulta
					String nombre = resultSet.getString("Nombre");
					String descripcion = resultSet.getString("Descripcion");
					Float precio = resultSet.getFloat("precio");
					int peso = resultSet.getInt("peso");
					int cantidadRefri = resultSet.getInt("cantidad_en_refri");
					String fechaElaboracion = resultSet.getString("cantidad_en_refri");
					String fechaVencimiento = resultSet.getString("cantidad_en_refri");
					String categoria = resultSet.getString("categoria_FK");
					// Obtener más propiedades según las columnas de tu tabla

					// Crear el objeto Producto con los valores ingresados
					Pasteles nuevoProducto = new Pasteles(codigoBarras, nombre, descripcion, precio,
							peso, cantidadRefri, fechaElaboracion, fechaVencimiento, categoria);
					// Configurar más propiedades del objeto Producto según las columnas de tu tabla

					// Agregar el objeto Producto a la tabla
					tblPais.getItems().add(nuevoProducto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			Utilitaria.mostrarAlerta("Error", "Tiene que ingresar un codigo de barras del producto");

		}

	}

	@FXML
	private void recargarTabla(ActionEvent event) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String selectQuery = "SELECT * FROM pasteles";
			// El Statement se utiliza para ejecutar la consulta SQL en la base de datos.
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);// epresenta el conjunto de resultados de la
																		// consulta.

			// Limpiar la tabla antes de mostrar los nuevos resultados
			tblPais.getItems().clear();

			while (resultSet.next()) {
				// Obtener los valores de cada columna del resultado de la consulta
				String codigoBarras = resultSet.getString("Codigo_de_Barras");
				String nombre = resultSet.getString("Nombre");
				String descripcion = resultSet.getString("Descripcion");
				Float precio = resultSet.getFloat("Precio");
				int peso = resultSet.getInt("Peso");
				int cantidadRefri = resultSet.getInt("Cantidad_en_refri");
				String fechaElaboracion = resultSet.getString("Fecha_de_elaboracion");
				String fechaVencimiento = resultSet.getString("Fecha_de_vencimiento");
				String categoria = resultSet.getString("Categoria_FK");
				// Obtener más propiedades según las columnas de tu tabla

				// Crear un nuevo objeto Pasteles con los valores obtenidos
				Pasteles nuevoProducto = new Pasteles(codigoBarras, nombre, descripcion, precio,
						peso, cantidadRefri, fechaElaboracion, fechaVencimiento, categoria);
				// Configurar más propiedades del objeto Producto según las columnas de tu tabla

				// Agregar el objeto Producto a la tabla
				tblPais.getItems().add(nuevoProducto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @FXML
	 * private void MinimizeTButtonAction(ActionEvent event) {
	 * Stage stage = (Stage) minimizeButton.getScene().getWindow();
	 * stage.setWidth(800); // Establece el nuevo ancho de la ventana
	 * stage.setHeight(700); // Establece el nuevo alto de la ventana
	 * }
	 * 
	 */

}

/*
 * Por si no le sabes padrino , aqui te pongo para que funciona muchas de las
 * cosas ocupadas en el proyecto
 * isEmpty() se utiliza para determinar si la cadena no contiene ningún carácter
 * y está vacía.
 * rowsAffected se utiliza para almacenar el número de filas afectadas por una
 * operación de eliminación en una base de datos.
 * 
 * Para las validaciones de tipo Regex
 * nombre.matches("[a-zA-Z\\s]+") verifica si la cadena nombre coincide con el
 * patron
 * representa que puede aceptar letras de la "a" a la "z", y que tambien acepta
 * valores en mayuscula
 * \\s se ocupa para que permita espacios en blanco
 * y el "+" indica que el rango de caracteres puede repetirse
 * 
 * \\d: Representa cualquier dígito numérico.
 * {4}: Indica que el dígito anterior (en este caso, \\d) debe repetirse
 * exactamente 4 veces.
 * -: Representa un guión.
 * {2}: Indica que el dígito anterior (en este caso, \\d) debe repetirse
 * exactamente 2 veces.
 * 
 * TextFieldTableCell.forTableColumn() se utiliza para crear una factoría de
 * celdas que permite editar las celdas como campos de texto.
 * 
 * Una expresión lambda es una forma concisa de representar una función anónima
 * que se
 * puede utilizar en lenguajes de programación
 * (parameter) -> { expresión o bloque de código }
 * 
 * 
 * 
 * Dentro del IntegerStringConverter, se sobrescribe el método fromString()
 * para realizar la conversión de cadena a entero. Si la conversión tiene éxito,
 * devuelve el valor entero. Si la conversión falla, se captura la excepción
 * NumberFormatException
 * y se muestra una alerta de error utilizando el método mostrarAlerta()
 * de la clase Utilitaria. Luego, se devuelve null para indicar que la
 * conversión falló.
 * 
 */
