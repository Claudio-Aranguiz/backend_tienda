package cl.caranguizh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Compra {
    private Integer id;
    private Cliente cliente;
    private Date fechaCompra;
    private Integer total;
    private List<DetalleCompra> detalles = new ArrayList<>();
    
    public Compra() {
    }
    
    public Compra(Integer id, Cliente cliente, Date fechaCompra, Integer total) {
        this.id = id;
        this.cliente = cliente;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Date getFechaCompra() {
        return fechaCompra;
    }
    
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    
    public List<DetalleCompra> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", cliente=" + (cliente != null ? cliente.getNombre() : "null") +
                ", fechaCompra=" + fechaCompra +
                ", total=" + total +
                '}';
    }
}