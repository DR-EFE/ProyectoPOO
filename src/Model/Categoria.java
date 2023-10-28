package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categoria {

	private StringProperty nombreCategoria;
	private StringProperty descripcionCategoria;
	// private List<Pasteles> pasteles = new ArrayList<>();

	public Categoria(String nombreCategoria, String descripcionCategoria) {
		super();
		this.nombreCategoria = new SimpleStringProperty(nombreCategoria);
		this.descripcionCategoria = new SimpleStringProperty(descripcionCategoria);
	}

	public StringProperty getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(StringProperty nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public StringProperty getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(StringProperty descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

}