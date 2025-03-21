package cl.caranguizh.model;

import java.util.Objects;

/**
 * Clase que representa un detalle de una compra.
 */
//  * @param total Monto total de la compra.
//  */
public class DetalleCompra {
    private Integer id;
    private Compra compra;
    private Producto producto;
    private Integer cantidad;
    
    public DetalleCompra() {
    }
    
    /**
     * Constructor de la clase.
     *
     * @param id       Identificador del detalle de compra.
     * @param compra   Compra a la que pertenece el detalle.
     * @param producto Producto comprado.
     * @param cantidad Cantidad de productos comprados.
     */
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
    
    /**
     * Calcula el subtotal del detalle.
     *
     * @return Subtotal del detalle.
     */
    public Integer getSubtotal() {
        if (producto != null && cantidad != null) {
            return producto.getPrecio() * cantidad;
        }
        return 0;
    }
    
    /*
     * Métodos equals, hashCode y toString
     */
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