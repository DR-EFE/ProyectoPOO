package Model;

import javafx.beans.property.StringProperty;

import Controller.UtilitariaNavegabilidad;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
	private StringProperty nombreCliente;
	private StringProperty apellidoPaterno;
	private StringProperty apellidoMaterno;

	private StringProperty telefono;
	private StringProperty formaDePago;

	public Cliente(String nombreCliente, String apellidoPaterno, String apellidoMaterno,
			String telefono, String formaDePago) {
		super();
		this.nombreCliente = new SimpleStringProperty(nombreCliente);
		this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
		this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
	
		this.telefono = new SimpleStringProperty(telefono);
		this.formaDePago = new SimpleStringProperty(formaDePago);
	}

	public StringProperty getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(StringProperty nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public StringProperty getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(StringProperty apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public StringProperty getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(StringProperty apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public StringProperty getTelefono() {
		return telefono;
	}

	public void setTelefono(StringProperty telefono) {
		this.telefono = telefono;
	}

	public StringProperty getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(StringProperty formaDePago) {
		this.formaDePago = formaDePago;
	}

	public static boolean validarCampos(String nombre, String aPaterno, String aMaterno,  String telefono, String formaDePago) {

		// Validar que se hayan ingresado todos los campos requeridos
		if (nombre.isEmpty() || aPaterno.isEmpty() || aMaterno.isEmpty()  || telefono.isEmpty() || formaDePago == null) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
			return false;
		}
		// Validar el formato del telefono (solo se permiten 10 dígitos numéricos)
		if (!telefono.matches("\\d{10}")) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "El teléfono debe contener exactamente 10 dígitos.");
			return false;
		}

		String nombreC = "^[a-zA-Z-zñÑáéíóúÁÉÍÓÚ]+$";
		String apellidoP = "^[a-zA-Z-zñÑáéíóúÁÉÍÓÚ]+$";
		String apellidoM = "^[a-zA-Z-zñÑáéíóúÁÉÍÓÚ]+$";

		if (!nombre.matches(nombreC)) {
			UtilitariaNavegabilidad.mostrarAlerta("Error",
					"El nombre solo debe contener letras, tildes y espacios, pero no números.");
			return false;
		}

		if (!aPaterno.matches(apellidoP)) {
			UtilitariaNavegabilidad.mostrarAlerta("Error",
					"El apellido paterno solo debe contener letras, tildes y espacios, pero no números.");
			return false;
		}

		if (!aMaterno.matches(apellidoM)) {
			UtilitariaNavegabilidad.mostrarAlerta("Error",
					"El apellido solo debe contener letras, tildes y espacios, pero no números.");
			return false;
		}

		return true;
	}

}