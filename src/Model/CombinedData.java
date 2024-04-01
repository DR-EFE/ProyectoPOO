/*package Model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import Model.Pasteles;
import Model.Ventas;
public class CombinedData {
    private SimpleIntegerProperty folio;
    private SimpleStringProperty productos;
    private SimpleIntegerProperty cantidadVendida;
    private SimpleFloatProperty subtotal;
    private SimpleFloatProperty total;
    private SimpleStringProperty fechaDeVenta;
    private SimpleStringProperty codigodeBarras;
    private SimpleStringProperty categorias;
    private SimpleStringProperty nombre;
    private SimpleStringProperty descripcion;
    private SimpleFloatProperty precio;
    // Add other properties as needed

    public CombinedData(Ventas venta, Pasteles pastel) {
        this.folio = venta.getFolio();
        this.productos = venta.getProductos();
        this.cantidadVendida = venta.getCantidadVendida();
        this.subtotal = venta.getSubtotal();
        this.total = venta.getTotal();
        this.fechaDeVenta = venta.getFechaDeVenta();
        this.codigodeBarras = venta.getCodigodebarras();
        this.categorias = venta.getCategorias();
        this.nombre = pastel.getNombre();
        this.descripcion = pastel.getDescripcion();
        this.precio = pastel.getPrecio();
        // Initialize other properties as needed
    }

	public SimpleIntegerProperty getFolio() {
		return folio;
	}

	public void setFolio(SimpleIntegerProperty folio) {
		this.folio = folio;
	}

	public SimpleStringProperty getProductos() {
		return productos;
	}

	public void setProductos(SimpleStringProperty productos) {
		this.productos = productos;
	}

	public SimpleIntegerProperty getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(SimpleIntegerProperty cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public SimpleFloatProperty getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(SimpleFloatProperty subtotal) {
		this.subtotal = subtotal;
	}

	public SimpleFloatProperty getTotal() {
		return total;
	}

	public void setTotal(SimpleFloatProperty total) {
		this.total = total;
	}

	public SimpleStringProperty getFechaDeVenta() {
		return fechaDeVenta;
	}

	public void setFechaDeVenta(SimpleStringProperty fechaDeVenta) {
		this.fechaDeVenta = fechaDeVenta;
	}

	public SimpleStringProperty getCodigodeBarras() {
		return codigodeBarras;
	}

	public void setCodigodeBarras(SimpleStringProperty codigodeBarras) {
		this.codigodeBarras = codigodeBarras;
	}

	public SimpleStringProperty getCategorias() {
		return categorias;
	}

	public void setCategorias(SimpleStringProperty categorias) {
		this.categorias = categorias;
	}

	public SimpleStringProperty getNombre() {
		return nombre;
	}

	public void setNombre(SimpleStringProperty nombre) {
		this.nombre = nombre;
	}

	public SimpleStringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(SimpleStringProperty descripcion) {
		this.descripcion = descripcion;
	}

	public SimpleFloatProperty getPrecio() {
		return precio;
	}

	public void setPrecio(SimpleFloatProperty precio) {
		this.precio = precio;
	}

    // Add getters and setters as needed
}
*/