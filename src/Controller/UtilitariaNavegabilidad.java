package Controller;


import java.io.IOException;

import Model.SessionManager;
import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.Parent;
import javafx.scene.Scene;



public abstract class UtilitariaNavegabilidad {
	
	 @FXML
	protected TextField txtUsuario;
	    
	private Ventanas navegabilidad;
	@FXML
    private Button btnVentas2;

    @FXML
    private Button btnPedidos2;

    @FXML
    private MenuItem itemNocturno;

    @FXML
    private MenuButton menuopciones;

    @FXML
    private MenuItem itemCerrar;
	
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
        
        
        public UtilitariaNavegabilidad() {
            this.navegabilidad = new Ventanas();
        }

	public static void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
/*
	  @FXML
		public
	      void openWinOne(ActionEvent event) {
	          try {

	              FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample.fxml"));
	              Parent root = loader.load();

	              SampleController controlador = loader.getController();

	              Scene scene = new Scene(root);
	              Stage stage = new Stage();
	              stage.setScene(scene);
	              stage.setTitle("Primera Ventana");

	              // Llama al método initialize() de SampleController2
	              controlador.initialize(null, null);

	              stage.show();

	              // Cierra la ventana actual
	              Stage myStage = (Stage) btnVentas2.getScene().getWindow();
	              myStage.close();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	      }

	      @FXML
	   public   void cambiarVista(ActionEvent event) {
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
	              Stage myStage = (Stage) btnVentas2.getScene().getWindow();
	              myStage.close();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }

	      }
	 
	*/
	@FXML
	public void openWintwo(ActionEvent event) {
		       Pane.MostrarPane(event, "/Vista/Sample2.fxml");

	}

	
	
	@FXML
	public void openWintwo2(ActionEvent event) {
		     Pane.MostrarPane(event,"/Vista/Empleado.fxml");
	}

	 @FXML
	    void openWinthree(ActionEvent event) {
	       
	            Pane.MostrarPane(event, "/Vista/Categorias.fxml");

	    }
	 
	 @FXML
	    void openWinFor(ActionEvent event) {
	     
	            Pane.MostrarPane(event, "/Vista/Cliente.fxml");

	    }

	 @FXML
	    void openWinFive(ActionEvent event) {
	      
	            Pane.MostrarPane(event, "/Vista/Principal.fxml");

	    }
	 

	    @FXML
	    void openWinSix(ActionEvent event) {
	    	Pane.MostrarPane(event, "/Vista/Pedido.fxml");
	    }
	 

	    @FXML
	    void openWinSeven(ActionEvent event) {
	       
	            Pane.MostrarPane(event, "/Vista/Ventas.fxml");
	    }

	  @FXML
	    void openWinOcho(ActionEvent event) {
	            Pane.MostrarPane(event, "/Vista/ventas_pasteles.fxml");

	    }
	  @FXML
	    void openCatalogos(ActionEvent event) {
	            Pane.MostrarPane(event, "/Vista/ventas_pasteles.fxml");

	    }
	
	
	
	 @FXML
	    void openCaja(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/Caja.fxml");
	    }
	 @FXML
	    void openReportes(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/Reportes.fxml");
	    }
	 
	 
	 @FXML
	    void	 openReportesDeTendencia(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/VistaReportes/ReportesDeTendencia.fxml");
	    }
	 
	 
	 @FXML
	    void openReportesDeVentas(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/VistaReportes/ReportesVentas.fxml");
	    }
	 
	 @FXML
	    void openReportesDeVentasDiarias(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/Diarias.fxml");
	    }
	 
	 		
	 @FXML
	    void openUsuarios(ActionEvent event) {

	    } 
	 
	 
	 @FXML
	    void openRegister(ActionEvent event) {
	    	// Obtiene el Node que generó el evento (en este caso, el botón)
	        Node source = (Node) event.getSource();
	        // Obtiene la Stage (ventana) a la que pertenece el Node
	        Stage stage = (Stage) source.getScene().getWindow();
	        // Cierra la ventana actual
	        stage.close();
	    	Ventana.MostrarPane2("/Vista/Register.fxml");
	    }
	 
	 
	@FXML
	void CerrarMenu(ActionEvent event) {
	    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    navegabilidad.mostrarVentanaDesdeMenu(currentStage, "/Vista/Sample.fxml");
	}

	
	

    public void closeWindows(ActionEvent event) {
    	Pane.MostrarPane(event, "/Vista/Sample.fxml");
    }

	
	
	
	
}
