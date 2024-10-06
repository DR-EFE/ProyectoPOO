package Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import Controller.UtilitariaNavegabilidad;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class Pasteles {
	private StringProperty CodigodeBarras;

	private StringProperty nombre;
	private StringProperty descripcion;

	private FloatProperty precio;
	private IntegerProperty peso;
	private IntegerProperty cantidad_en_refri;
	private StringProperty fecha_de_elaboracion;
	private StringProperty fecha_de_vencimiento;
	private StringProperty categoria;

	public Pasteles(String CodigodeBarras, String nombre, String descripcion, float precio, int peso,
			int cantidad_en_refri, String fecha_de_elaboracion,
			String fecha_de_vencimiento,
			String categoria) {
		super();
		this.CodigodeBarras = new SimpleStringProperty(CodigodeBarras);
		this.nombre = new SimpleStringProperty(nombre);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.precio = new SimpleFloatProperty(precio);
		this.peso = new SimpleIntegerProperty(peso);
		this.cantidad_en_refri = new SimpleIntegerProperty(cantidad_en_refri);
		this.fecha_de_elaboracion = new SimpleStringProperty(fecha_de_elaboracion);
		this.fecha_de_vencimiento = new SimpleStringProperty(fecha_de_vencimiento);
		this.categoria = new SimpleStringProperty(categoria);
	}

	public Pasteles(String codigoBarras, String nombre2, String descripcion2, float precio2, int peso2,
			int cantidadRefri, String categoria2) {
		// TODO Auto-generated constructor stub
	}

	public StringProperty getNombre() {

		return nombre;
	}
	

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

	public FloatProperty getPrecio() {
		return precio;
	}

	public void setPrecio(FloatProperty precio) {
		this.precio = precio;
	}

	public IntegerProperty getPeso() {
		return peso;
	}

	public void setPeso(IntegerProperty peso) {
		this.peso = peso;
	}

	public IntegerProperty getCantidad_en_refri() {
		return cantidad_en_refri;
	}

	public void setCantidad_en_refri(IntegerProperty cantidad_en_refri) {
		this.cantidad_en_refri = cantidad_en_refri;
	}

	public StringProperty getFecha_de_elaboracion() {
		return fecha_de_elaboracion;
	}

	public void setFecha_de_elaboracion(StringProperty fecha_de_elaboracion) {
		this.fecha_de_elaboracion = fecha_de_elaboracion;
	}

	public StringProperty getFecha_de_vencimiento() {
		return fecha_de_vencimiento;
	}

	public void setFecha_de_vencimiento(StringProperty fecha_de_vencimiento) {
		this.fecha_de_vencimiento = fecha_de_vencimiento;
	}

	public StringProperty getCategoria() {
		return categoria;
	}

	public void setCategoria(StringProperty categoria) {
		this.categoria = categoria;
	}

	public StringProperty getCodigodeBarras() {
		return CodigodeBarras;
	}

	public void setCodigodeBarras(StringProperty codigodeBarras) {

	}

	
	
	public static boolean validarCampos(String codigoBarras, String nombre, String descripcion, String precioText,
			String pesoText, String cantidadRefriText, String fechaElaboracion, String fechaVencimiento,
			String categoria) {

		// Validar que se hayan ingresado todos los campos requeridos
		if (codigoBarras.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() ||
				fechaElaboracion == null || fechaVencimiento == null || categoria == null) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
			return false;
		}
		// Validar el formato del código de barras (solo se permiten 13 dígitos
		// numéricos)
		if (!codigoBarras.matches("\\d{10}")) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "El código de barras debe contener exactamente 10 dígitos numéricos.");
			return false;
		}

		String nombrePattern = "^[a-zA-Z0-9 ]+$";
		String descripcionPattern = "^[a-zA-Z0-9 ]+$";

		if (!nombre.matches(nombrePattern)) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "El nombre solo debe contener letras, números y espacios.");
			return false;
		}

		if (!descripcion.matches(descripcionPattern)) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "La descripción solo debe contener letras, números y espacios.");
			return false;
		}

		float precio;
		int peso;
		int cantidadRefri;
		try {
			precio = Float.parseFloat(precioText);
			peso = Integer.parseInt(pesoText);
			cantidadRefri = Integer.parseInt(cantidadRefriText);
		} catch (NumberFormatException e) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, ingrese valores numéricos válidos.");
			return false;
		}

		LocalDate fechaElaboracionDate;
		LocalDate fechaVencimientoDate;

		fechaElaboracionDate = LocalDate.parse(fechaElaboracion);
		fechaVencimientoDate = LocalDate.parse(fechaVencimiento);

	
		if (fechaVencimientoDate.isBefore(fechaElaboracionDate)) {
			UtilitariaNavegabilidad.mostrarAlerta("Error",
					"La fecha de vencimiento no puede ser anterior a la fecha de elaboración.");
			return false;
		}

		return true;
	}

	public String get(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStringProperity(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}