package Model;

public class CategoriaVenta {
    private String categoria;
    private int totalVentas;

    public CategoriaVenta(String categoria, int totalVentas) {
        this.categoria = categoria;
        this.totalVentas = totalVentas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }
}
