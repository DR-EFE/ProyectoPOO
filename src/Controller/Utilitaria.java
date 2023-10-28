package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public abstract class Utilitaria {
	@FXML
	private Button btnMenu;
	@FXML
	protected TextField txtFolio;
	@FXML
	protected Button btnEliminar;
	@FXML
	protected Button btnGuardar;
	@FXML
	protected Button btnActualizar;

	public static void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	@FXML
	public void openWintwo(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample2.fxml"));
			Parent root = loader.load();

			SampleController2 controlador = loader.getController();

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

	@FXML
	void cambiarVista(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/EmpleadoNocturno.fxml"));
			Parent root = loader.load();

			EmpleadoNocturnoC controlador = loader.getController();

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
