package Controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

import DAO.PastelesDAO;
import DAO.VentaDAO;
import Factory.ConnectionFactory;
import Model.Pasteles;
import Model.SessionManager;
import Model.Ventas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class VentaEmpleadoController extends UtilitariaNavegabilidad implements Initializable {
	private VentaDAO ventaDAO; // Debes inicializar esto en algún lugar, posiblemente en el initialize()
    
    @FXML
    protected TextField TextFieldFecha1;
    @FXML
    protected TextField TextFieldHora1;
    @FXML
    private TableView<Ventas> tblVentas;
    @FXML
    private TableColumn<Ventas, Integer> colFolio;
    @FXML
    private TableColumn<Ventas, String> colDescripcion;
    @FXML
    private TableColumn<Ventas, Integer> colCantidad;
    
    
    @FXML
    private TableColumn<Ventas, Float> colSubTotal;
    @FXML
    private TableColumn<Ventas, Float> colTotal;
    @FXML
    private Button btnMenu;
    @FXML
    private TextField TextFBuscar;
    @FXML
    private TextField TextFArticulo;
    @FXML
    private TextField TextFCodigodeBarras;
    @FXML
    private TextField TextFCategorias;
    @FXML
    private TextField TextFFolio;
    @FXML
    private Button btnVender;
    @FXML
    private Button btnAgregar;
    @FXML
    private TextField TextFInventario;
    
    private ObservableList<Ventas> listaVentas;
 // Aquí asegúrate de pasar la conexión o inicializarla correctamente.

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaVentas = FXCollections.observableArrayList();
        tblVentas.setItems(listaVentas);
        // Verifica si la sesión está activa y accede al usuario
        if (SessionManager.sesionIniciada()) {
            String usuario = SessionManager.getUsuarioActual();
            System.out.println("Usuario actual: " + usuario);
            // Usar el ID de usuario para interactuar con la base de datos o registrar actividades
        } else {
            System.out.println("No hay ninguna sesión activa.");
        }


        // Mostrar la fecha y hora actualizadas en los campos de texto
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarHora1(event)));
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarFecha1(event)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();
        
        // Configurar las celdas de la tabla
        configurarColumnasTabla();

        
        
        
        
        
        // Inicializar DAOs y otros componentes
        inicializarDAOs();
    }

    private void configurarColumnasTabla() {
        colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        colCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       
        colSubTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
    }

    private void inicializarDAOs() {
        try {
            PastelesDAO pastelesDAO = new PastelesDAO(ConnectionFactory.getConnection());
            ventaDAO = new VentaDAO();
            colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
            colDescripcion.setCellValueFactory(dato -> dato.getValue().getProductos());
            colCantidad.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
            colSubTotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
            colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void buscarArticulo() throws Exception {
        // Obtener el texto de búsqueda
        String textoBusqueda = TextFBuscar.getText();
        Pasteles Pasti = ventaDAO.buscarVenta(textoBusqueda);

        if (Pasti != null) {
            // Comprobar si las propiedades son nulas antes de acceder
            String codigoBarras = Pasti.getCodigodeBarras() != null ? Pasti.getCodigodeBarras().get() : "";
            String nombre = Pasti.getNombre() != null ? Pasti.getNombre().get() : "";
            String descripcion = Pasti.getDescripcion() != null ? Pasti.getDescripcion().get() : "";
            float precio = Pasti.getPrecio() != null ? Pasti.getPrecio().get() : 0.0f;
            int peso = Pasti.getPeso() != null ? Pasti.getPeso().get() : 0;
            int cantidadRefri = Pasti.getCantidad_en_refri() != null ? Pasti.getCantidad_en_refri().get() : 0;
            String categoria = Pasti.getCategoria() != null ? Pasti.getCategoria().get() : "";

            // Actualizar los campos de la interfaz con los datos encontrados
            TextFFolio.setText(codigoBarras);
            TextFCodigodeBarras.setText(codigoBarras);
            TextFCategorias.setText(categoria);
            TextFInventario.setText(String.valueOf(cantidadRefri));
            TextFArticulo.setText(String.valueOf(nombre));
            // Si es necesario, actualiza otros campos de la interfaz aquí.
        } else {
            // Si no se encuentra el pastel, limpiar los campos
            limpiarCampos();
        }
    }


    // Método para agregar una venta a la tabla
 //para la cantidad quiero que comience con 1 y que se pueda editar en la tabla 
    //esa sera la cantidad que se le va a decontar a la cantidad en refri 
    //El folio tiene que obtenerse de la base de datos ya que en la base de datos es un INT PK , AUTOINCREMENTABLE 
    //ASI QUE QUIERO LA MEJOR FORMA 
 // Método para agregar una venta a la tabla
    
    
	  public String obtenerUsuarioActualId() {
		  System.out.println("=============================================");
		  String usuario = SessionManager.getUsuarioActual();
		  System.out.println(usuario);
			
			 return usuario;
			}
	  
	  
	  
	  @FXML
	  private void agregarVentaATabla() {
	      // Obtener el texto de búsqueda (código de barras o nombre)
	      String textoBusqueda = TextFBuscar.getText();
	      
	      try {
	          // Buscar el pastel en la base de datos
	          Pasteles Pasti = ventaDAO.buscarVenta(textoBusqueda);
	          int nuevoFolio = ventaDAO.obtenerNuevoFolio();
	          SimpleIntegerProperty cantidadVendida = new SimpleIntegerProperty(1); // Comienza con 1
	          float precioUnitario = Pasti.getPrecio().get(); // Obtener el precio del pastel
	          
	          if (Pasti != null) {
	              // Verificar si hay suficiente inventario antes de agregar la venta
	              if (Pasti.getCantidad_en_refri().get() > 0) {
	                  // Crear el objeto Ventas utilizando el constructor con parámetros
	                  Ventas venta = new Ventas(
	                      nuevoFolio,                          // folio
	                      cantidadVendida.get(),               // cantidadVendida
	                      cantidadVendida.get() * precioUnitario, // subtotal
	                      cantidadVendida.get() * precioUnitario, // total (sin descuentos)
	                      LocalDate.now().toString(),         // fechaVenta (actual)
	                      Pasti.getNombre().get(),             // productos
	                      Pasti.getCodigodeBarras().get(),     // codigo
	                      obtenerUsuarioActualId()             // empleadoVenta
	                  );

	                  // Establecer la cantidad vendida
	                  venta.setCantidadVendida(cantidadVendida);

	                  // Agregar un listener para actualizar subtotal y total al cambiar la cantidad
	                  cantidadVendida.addListener((observable, oldValue, newValue) -> {
	                      float nuevoSubtotal = newValue.intValue() * precioUnitario;
	                      venta.setSubtotal(new SimpleFloatProperty(nuevoSubtotal));
	                      venta.setTotal(new SimpleFloatProperty(nuevoSubtotal)); // Puedes aplicar descuentos aquí si es necesario
	                  });

	                  // Establecer el resto de propiedades
	                  venta.setFolio(new SimpleIntegerProperty(nuevoFolio));
	                  venta.setProductos(Pasti.getNombre());
	                  venta.setCodigodebarras(Pasti.getCodigodeBarras());
	                  venta.setEmpleadoVenta(obtenerUsuarioActualId());

	                  // Agregar el objeto Venta a la tabla gráfica
	                  tblVentas.getItems().add(venta);

	               
	                  // Calcular la nueva cantidad en el refrigerador
	                  int nuevaCantidadRefri = Pasti.getCantidad_en_refri().get() - cantidadVendida.get();
	                  Pasti.getCantidad_en_refri().set(nuevaCantidadRefri);
	                  
	                  // Imprimir en consola
	                  System.out.println("Venta agregada a la tabla y guardada en la BD: " + Pasti.getNombre().get());
	              } else {
	                  System.out.println("No hay suficiente inventario para realizar la venta.");
	              }
	          } else {
	              System.out.println("No se encontró el pastel.");
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  
	  
	  @FXML
	  private void InsertarAlaBD() {
	      // Este método se llamará al presionar un botón
	      try {
	          // Recorrer la tabla de ventas y guardar cada venta en la base de datos
	          for (Ventas venta : tblVentas.getItems()) {
	              String codigoBarras = venta.getCodigodebarras().get();
	              int cantidadVendida = venta.getCantidadVendida().get(); // Obtener la cantidad vendida
	              Pasteles Pasti = ventaDAO.buscarVenta(codigoBarras); // Buscar el pastel en la base de datos para obtener la cantidad actual en refrigerador
	              
	              if (Pasti != null) {
	                  int cantidadActualRefri = Pasti.getCantidad_en_refri().get(); // Obtener cantidad actual del refrigerador
	                  int nuevaCantidadRefri = cantidadActualRefri - cantidadVendida; // Calcular la nueva cantidad en refrigerador

	                  // Llamar al método para actualizar la cantidad en el refrigerador en la base de datos
	                  ventaDAO.actualizarCantidadEnRefri(codigoBarras, nuevaCantidadRefri);
	                  // Insertar la venta en la base de datos
	                  ventaDAO.insertarVenta(venta);
	              } else {
	                  System.out.println("No se encontró el pastel con código de barras: " + codigoBarras);
	              }
	          }

	          System.out.println("Todas las ventas se han guardado en la base de datos.");
	      } catch (Exception e) {
	          e.printStackTrace();
	          System.out.println("Error al insertar en la base de datos: " + e.getMessage());
	      }
	  }


	  



    private void limpiarCampos() {
        TextFArticulo.clear();
        TextFCodigodeBarras.clear();
        TextFCategorias.clear();
        TextFFolio.clear();
        TextFInventario.clear();
    }
    
    
    

    @FXML
    void MostrarFecha1(ActionEvent event) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        TextFieldFecha1.setText(currentDateTime.format(formatter));
    }

    @FXML
    void MostrarHora1(ActionEvent event) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        TextFieldHora1.setText(currentDateTime.format(formatter));
    }
}

   