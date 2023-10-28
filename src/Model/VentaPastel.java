package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VentaPastel {
	private IntegerProperty folioDeVenta;
	private StringProperty codigoDeBarraPastel;
	private StringProperty pastel;
	private StringProperty peso;

	public VentaPastel(int folioDeVenta, String codigoDeBarraPastel, String pastel, String peso) {
		super();
		this.folioDeVenta = new SimpleIntegerProperty(folioDeVenta);
		this.codigoDeBarraPastel = new SimpleStringProperty(codigoDeBarraPastel);
		this.pastel = new SimpleStringProperty(pastel);
		this.peso = new SimpleStringProperty(peso);
	}

	public IntegerProperty getFolioDeVenta() {
		return folioDeVenta;
	}

	public void setFolioDeVenta(IntegerProperty folioDeVenta) {
		this.folioDeVenta = folioDeVenta;
	}

	public StringProperty getCodigoDeBarraPastel() {
		return codigoDeBarraPastel;
	}

	public void setCodigoDeBarrasPastel(StringProperty codigoDeBarraPastel) {
		this.codigoDeBarraPastel = codigoDeBarraPastel;
	}

	public StringProperty getPastel() {
		return pastel;
	}

	public void setPastel(StringProperty pastel) {
		this.pastel = pastel;
	}

	public StringProperty getPeso() {
		return peso;
	}

	public void setPeso(StringProperty peso) {
		this.peso = peso;
	}

}