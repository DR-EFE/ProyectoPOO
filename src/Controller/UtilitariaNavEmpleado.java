package Controller;


import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.Node;



public abstract class UtilitariaNavEmpleado {
	@FXML
	private MenuItem itemClaro;
	@FXML
	private MenuButton menuopciones;
	
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
	private Button btnVentas;

	@FXML
	private Button btnPedidos;

	@FXML
	private Button btnEstadoPedido;

	@FXML
	private MenuItem itemNocturno;



	@FXML
	private MenuItem itemCerrar;
	
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
	void openPedidosEmpleado(ActionEvent event) {
	
		Pane.MostrarPane(event, "/Vista/Pedido2.fxml");
	
	}

	@FXML
	void openVentasEmpleado(ActionEvent event) {

		Pane.MostrarPane(event,"/Vista/VentaEmpleado.fxml");
	}
	
	@FXML
	void openWinSeven(ActionEvent event) {

		Pane.MostrarPane(event,"/Vista/Factura.fxml");

	}

	@FXML
	void openWinSix2(ActionEvent event) {

		Pane.MostrarPane(event,"/Vista/Pedido2.fxml");

	}

	
	@FXML
	void openWinOne(ActionEvent event) {

		Pane.MostrarPane(event,"/Vista/Sample.fxml");
	}

	
	
	@FXML
	void cambiarVista(ActionEvent event) {
    Pane.MostrarPane(event,"/Vista/EmpleadoNocturno.fxml");

}
	@FXML
	void cambiarTemaClaro(ActionEvent event) {

		Pane.MostrarPane(event, "/Vista/Empleado.fxml");

	}
	
	
	
	
	
	
	
}
