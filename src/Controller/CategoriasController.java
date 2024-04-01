package Controller;

import Model.Categoria;

import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;

import java.sql.ResultSet;
import java.util.ResourceBundle;

import Factory.ConnectionFactory;

public class CategoriasController extends UtilitariaNavegabilidad implements Initializable {

	@FXML
	TableView<Categoria> tblCategoria;

	@FXML
	private TextField txtNombre_Categoria;
	@FXML
	private TextField txtDescripcion_Categoria;

	@FXML
	TableColumn<Categoria, String> colNombreCategoria;
	@FXML
	TableColumn<Categoria, String> colDescripcionCategoria;

	private ObservableList<Categoria> listaCategorias;

	public void initialize(URL arg0, ResourceBundle arg1) {
		listaCategorias = FXCollections.observableArrayList();

		try {

			ResultSet resCategorias = ConnectionFactory.executeQuery("SELECT * FROM categorias");// devuelve un objeto

			while (resCategorias.next()) {
				listaCategorias.add(new Categoria(resCategorias.getString("Nombre"),
						resCategorias.getString("Descripcion")));
			}
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}

		tblCategoria.setEditable(true);
		colNombreCategoria.setCellFactory(TextFieldTableCell.forTableColumn());
		colDescripcionCategoria.setCellFactory(TextFieldTableCell.forTableColumn());

		colNombreCategoria.setCellValueFactory(dato -> dato.getValue().getNombreCategoria());
		colDescripcionCategoria.setCellValueFactory(dato -> dato.getValue().getDescripcionCategoria());

		tblCategoria.setItems(listaCategorias);

	}

}
