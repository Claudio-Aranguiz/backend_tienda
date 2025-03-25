package cl.caranguizh.console.model;

public class Detalle {
    private String nombreProducto;
    private int cantidad;
    private String categoria;

    public Detalle(String nombreProducto, int cantidad, String categoria) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }
}