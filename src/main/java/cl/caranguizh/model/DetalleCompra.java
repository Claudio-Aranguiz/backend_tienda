package cl.caranguizh.model;

import java.util.Objects;

public class DetalleCompra {
    private Integer id;
    private Compra compra;
    private Producto producto;
    private Integer cantidad;
    
    public DetalleCompra() {
    }
    
    public DetalleCompra(Integer id, Compra compra, Producto producto, Integer cantidad) {
        this.id = id;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Compra getCompra() {
        return compra;
    }
    
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    // Método helper para calcular el subtotal
    public Integer getSubtotal() {
        if (producto != null && cantidad != null) {
            return producto.getPrecio() * cantidad;
        }
        return 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleCompra detalle = (DetalleCompra) o;
        return Objects.equals(id, detalle.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "DetalleCompra{" +
                "id=" + id +
                ", producto=" + (producto != null ? producto.getNombre() : "null") +
                ", cantidad=" + cantidad +
                "}";
    }
}