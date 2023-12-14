package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
// Model para que funciones Caja 
public class Caja {
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty productosVendidos;
    private final SimpleIntegerProperty cantidadVendida;
    private final SimpleDoubleProperty total;

    public Caja(String fecha, String productosVendidos, int cantidadVendida, double total) {
        this.fecha = new SimpleStringProperty(fecha);
        this.productosVendidos = new SimpleStringProperty(productosVendidos);
        this.cantidadVendida = new SimpleIntegerProperty(cantidadVendida);
        this.total = new SimpleDoubleProperty(total);
    }

    

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    

    public SimpleStringProperty productosVendidosProperty() {
        return productosVendidos;
    }

    public void setProductosVendidos(String productosVendidos) {
        this.productosVendidos.set(productosVendidos);
    }

  
    public SimpleIntegerProperty cantidadVendidaProperty() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida.set(cantidadVendida);
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }
}
