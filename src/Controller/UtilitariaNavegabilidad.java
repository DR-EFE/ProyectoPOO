package Controller;


import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.Node;



public abstract class UtilitariaNavegabilidad {
	@FXML
	protected Button btnMenu;

	@FXML
	protected Button btnEliminar;
	@FXML
	protected Button btnGuardar;
	@FXML
	protected Button btnActualizar;
	@FXML
	private Button btnRecargar;
	@FXML
	protected Button btnBuscar;
	
	  @FXML
	protected TextField TextFieldFecha1;
		@FXML
		protected TextField txtFolio;
	  
	  @FXML
		protected TextField TxtBusqueda;

	    @FXML
		protected TextField TextFieldHora1;
		Ventanas Pane = new Ventanas();
		
		  Ventanas Ventana = new   Ventanas();
	
		 // Crea una instancia del controlador de la nueva escena si es necesario
        ExtendedRegisterController extendedController = new ExtendedRegisterController();

	public static void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	@FXML
	public void openWintwo(ActionEvent event) {
		       Pane.MostrarPane(event, "/Vista/Sample2.fxml");

	}

	
	
	@FXML
	public void openWintwo2(ActionEvent event) {
		     Pane.MostrarPane(event,"/Vista/Empleado.fxml");
	}


	
	@FXML
	void cambiarVista(ActionEvent event) {
    Pane.MostrarPane(event,"/Vista/EmpleadoNocturno.fxml");

}
	
	
	
	
	
	
	
	
}
