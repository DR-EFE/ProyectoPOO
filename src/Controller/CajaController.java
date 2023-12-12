package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Factory.ConnectionFactory;
import Model.Caja;
import Model.Categoria;
import Model.Pasteles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class CajaController extends Utilitaria implements Initializable {

	@FXML
	TableView<Caja> tblCaja;

	@FXML
	TableColumn<Caja, String> colFecha;
	@FXML
	TableColumn<Caja, String>  colProductosVendidos;
	@FXML
	TableColumn<Caja, Integer> colCantidadVendida;
	@FXML
	TableColumn<Caja, Double>  colTotal;

	private ObservableList<Caja> lista;

	public void initialize(URL arg0, ResourceBundle arg1) {
	    lista = FXCollections.observableArrayList();

	    try {
	        ResultSet res = ConnectionFactory.executeQuery("SELECT FechaDeVenta AS Fecha, " +
	                                                        "Productos AS ProductosVendidos, " +
	                                                        "CantidadVendida, " +
	                                                        "Total " +
	                                                    "FROM ventas");

	        while (res.next()) {
	            lista.add(new Caja(res.getString("Fecha"),
	                                res.getString("ProductosVendidos"),
	                                res.getInt("CantidadVendida"),
	                                res.getDouble("Total")));
	        }
	    } catch (Exception ex) {
	        System.err.println("ERROR: " + ex.getMessage());
	        
	    }
	    
	    colFecha.setCellFactory(TextFieldTableCell.forTableColumn());
	    colProductosVendidos.setCellFactory(TextFieldTableCell.forTableColumn());
	   
	    
	 // mas validaciones para campos numericos
	    

	    	colCantidadVendida.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() {
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

	    
	    
	    
	 // mas validaciones para campos numericos
	    colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() {

	 			@Override
	 			public Double fromString(String string) {
	 				try {
	 					return Double.parseDouble(string);
	 				} catch (NumberFormatException e) {
	 					Utilitaria.mostrarAlerta("Error", "el valor que ingreso no es un numero");
	 					return null;// O lanzar una excepción personalizada
	 				}
	 			}
	 		}));


	    // Configuración de celdas y valores similar a tu código anterior...
	    // ...

	   colFecha.setCellValueFactory(dato -> dato.getValue().fechaProperty());
		colProductosVendidos.setCellValueFactory(dato -> dato.getValue().productosVendidosProperty());
		colCantidadVendida.setCellValueFactory(dato -> dato.getValue().cantidadVendidaProperty().asObject());
		
		colTotal.setCellValueFactory(dato -> dato.getValue().totalProperty().asObject());

	    // Establece la lista lista como la fuente de datos de la tabla.
	    tblCaja.setItems(lista);
	}






}
