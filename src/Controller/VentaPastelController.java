package Controller;

import Model.VentaPastel;

import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.converter.IntegerStringConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;

import java.sql.ResultSet;
import java.util.ResourceBundle;

import Factory.ConnectionFactory;

public class VentaPastelController extends Utilitaria implements Initializable {

	@FXML
	TableView<VentaPastel> tblVentaPastel;
	@FXML
	private Button btnMenu;
	@FXML
	private TextField TxtBuqueda;

	@FXML
	private Button btnGuardarVenta_Pastel;
	@FXML
	private Button btnEliminarVenta_Pastel;
	@FXML
	private Button btnActualizarVenta_Pastel;

	@FXML
	private TextField txtFolioDeVenta;
	@FXML
	private TextField txtCodigoDeBarrasPastel;
	@FXML
	private TextField txtPastel;
	@FXML
	private TextField txtPeso;

	@FXML
	TableColumn<VentaPastel, Integer> colFolioDeVenta;
	@FXML
	TableColumn<VentaPastel, String> colCodigoDeBarraPastel;
	@FXML
	TableColumn<VentaPastel, String> colPastel;
	@FXML
	TableColumn<VentaPastel, String> colPeso;

	private ObservableList<VentaPastel> listaVentaPastel;

	public void initialize(URL arg0, ResourceBundle arg1) {
		listaVentaPastel = FXCollections.observableArrayList();

		try {
			// executeQuery para ejecutar las consultas
			ResultSet resVentaPastel = ConnectionFactory.executeQuery("SELECT * FROM ventas_pasteles");// devuelve un
																										// objeto

			// Se itera sobre cada fila de resultados del objeto ResultSet utilizando el
			// método next()
			while (resVentaPastel.next()) {
				listaVentaPastel.add(new VentaPastel(resVentaPastel.getInt("folioVenta_FK"),
						resVentaPastel.getString("folioVenta_FK"),
						resVentaPastel.getString("Pastel"), resVentaPastel.getString("Peso")));
			}
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}

		tblVentaPastel.setEditable(true);
		colFolioDeVenta.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colCodigoDeBarraPastel.setCellFactory(TextFieldTableCell.forTableColumn());
		colPastel.setCellFactory(TextFieldTableCell.forTableColumn());
		colPeso.setCellFactory(TextFieldTableCell.forTableColumn());

		colFolioDeVenta.setCellValueFactory(dato -> dato.getValue().getFolioDeVenta().asObject());
		colCodigoDeBarraPastel.setCellValueFactory(dato -> dato.getValue().getCodigoDeBarraPastel());
		colPastel.setCellValueFactory(dato -> dato.getValue().getPastel());
		colPeso.setCellValueFactory(dato -> dato.getValue().getPeso());

		tblVentaPastel.setItems(listaVentaPastel);

	}

}