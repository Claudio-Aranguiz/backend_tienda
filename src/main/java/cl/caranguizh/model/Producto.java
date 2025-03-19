package cl.caranguizh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Producto {
    private Integer id;
    private String nombre;
    private Integer precio;
    private Categoria categoria;
    private List<DetalleCompra> detallesCompra = new ArrayList<>();
    
    public Producto() {
    }
    
    public Producto(Integer id, String nombre, Integer precio, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getPrecio() {
        return precio;
    }
    
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public List<DetalleCompra> getDetallesCompra() {
        return detallesCompra;
    }
    
    public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "null") +
                '}';
    }
}
