package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;

import Controller.UtilitariaNavegabilidad;

public class Pedido {

	private IntegerProperty folio;
	private StringProperty fechaHoraSolicitud;
	private StringProperty descripcion;
	private DoubleProperty anticipo;
	private StringProperty tipo;
	private DoubleProperty subtotal;
	private DoubleProperty total;
	private StringProperty estatus;
	private StringProperty fechaHoraEntrega;
	private StringProperty telefonoFK;

	public Pedido(int folio, String fechaHoraSolicitud, String descripcion, double anticipo, String tipo,
			double subtotal, double total, String estatus, String fechaHoraEntrega, String telefonoFK) {
		this.folio = new SimpleIntegerProperty(folio);
		this.fechaHoraSolicitud = new SimpleStringProperty(fechaHoraSolicitud);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.anticipo = new SimpleDoubleProperty(anticipo);
		this.tipo = new SimpleStringProperty(tipo);
		this.subtotal = new SimpleDoubleProperty(subtotal);
		this.total = new SimpleDoubleProperty(total);
		this.estatus = new SimpleStringProperty(estatus);
		this.fechaHoraEntrega = new SimpleStringProperty(fechaHoraEntrega);
		this.telefonoFK = new SimpleStringProperty(telefonoFK);
	}

	public IntegerProperty getFolio() {
		return folio;
	}

	public void setFolio(IntegerProperty folio) {
		this.folio = folio;
	}

	public StringProperty getFechaHoraSolicitud() {
		return fechaHoraSolicitud;
	}

	public void setFechaHoraSolicitud(StringProperty fechaHoraSolicitud) {
		this.fechaHoraSolicitud = fechaHoraSolicitud;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

	public DoubleProperty getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(DoubleProperty anticipo) {
		this.anticipo = anticipo;
	}

	public StringProperty getTipo() {
		return tipo;
	}

	public void setTipo(StringProperty tipo) {
		this.tipo = tipo;
	}

	public DoubleProperty getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(DoubleProperty subtotal) {
		this.subtotal = subtotal;
	}

	public DoubleProperty getTotal() {
		return total;
	}

	public void setTotal(DoubleProperty total) {
		this.total = total;
	}

	public StringProperty getEstatus() {
		return estatus;
	}

	public void setEstatus(StringProperty estatus) {
		this.estatus = estatus;
	}

	public StringProperty getFechaHoraEntrega() {
		return fechaHoraEntrega;
	}

	public void setFechaHoraEntrega(StringProperty fechaHoraEntrega) {
		this.fechaHoraEntrega = fechaHoraEntrega;
	}

	public StringProperty getTelefonoFK() {
		return telefonoFK;
	}

	public void setTelefonoFK(StringProperty telefonoFK) {
		this.telefonoFK = telefonoFK;
	}

	public static boolean validarCampos(String folioText, String fechaHoraSolicitud, String descripcion,
			String anticipoText, String tipo,
			String subtotalText, String totalText, String estatus, String fechaHoraEntrega, String telefonoFK) {

		// Validar que se hayan ingresado todos los campos requeridos
		if (folioText.isEmpty() || fechaHoraSolicitud == null || descripcion.isEmpty() || anticipoText.isEmpty()
				|| tipo.isEmpty() ||
				subtotalText.isEmpty() || totalText.isEmpty() || estatus.isEmpty() || fechaHoraEntrega == null
				|| telefonoFK.isEmpty()) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
			return false;
		}

		if (!telefonoFK.matches("^\\d+$")) {
			// El número de teléfono contiene caracteres no válidos
			UtilitariaNavegabilidad.mostrarAlerta("Error", "El número de teléfono solo debe contener numeros.");
			return false;
		} else if (telefonoFK.length() < 10) {
			// El número de teléfono no tiene al menos 10 dígitos
			UtilitariaNavegabilidad.mostrarAlerta("Error", "El número de teléfono debe tener al menos 10 dígitos.");
			return false;
		}

		// El número de teléfono es válido

		// Validar que solo se acepten números
		// int telefonoFKD;

		int folio;
		double anticipo;
		double subtotal;
		double total;
		try {

			folio = Integer.parseInt(folioText);
			anticipo = Double.parseDouble(anticipoText);
			subtotal = Double.parseDouble(subtotalText);
			total = Double.parseDouble(totalText);
		} catch (NumberFormatException e) {
			UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, ingrese valores numéricos válidos");
			return false;
		}

		return true;
	}

}