package Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import Controller.Utilitaria;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Facturas {

	private IntegerProperty folio;
	private StringProperty productos;
	private IntegerProperty cantidadVendida;
	private FloatProperty subtotal;
	private FloatProperty total;
	private StringProperty fechaDeVenta;

	public Facturas(int folio, String productos, int cantidadVendida, float subtotal, float total, String fechaVenta) {
		super();
		this.folio = new SimpleIntegerProperty(folio);
		this.productos = new SimpleStringProperty(productos);
		this.cantidadVendida = new SimpleIntegerProperty(cantidadVendida);
		this.subtotal = new SimpleFloatProperty(subtotal);
		this.total = new SimpleFloatProperty(total);
		this.fechaDeVenta = new SimpleStringProperty(fechaVenta);
	}

	public IntegerProperty getFolio() {
		return folio;
	}

	public void setFolio(IntegerProperty folio) {
		this.folio = folio;
	}

	public IntegerProperty getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(IntegerProperty cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public FloatProperty getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(FloatProperty subtotal) {
		this.subtotal = subtotal;
	}

	public FloatProperty getTotal() {
		return total;
	}

	public void setTotal(FloatProperty total) {
		this.total = total;
	}

	public StringProperty getFechaDeVenta() {
		return fechaDeVenta;
	}

	public void setFechaVenta(StringProperty fechaVenta) {
		this.fechaDeVenta = fechaVenta;
	}

	public StringProperty getProductos() {
		return productos;
	}

	public void setProductos(StringProperty productos) {
		this.productos = productos;
	}

}
