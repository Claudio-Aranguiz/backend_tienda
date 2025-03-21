package cl.caranguizh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa una categoría de productos.
 */
public class Categoria {
    private Integer id;
    private String nombre;
    private List<Producto> productos = new ArrayList<>();
    
    public Categoria() {
    }
    
    /**
     * Constructor de la clase.
     *
     * @param id     Identificador de la categoría.
     * @param nombre Nombre de la categoría.
     */
    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
    
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    /*
     * Métodos equals, hashCode y toString
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}