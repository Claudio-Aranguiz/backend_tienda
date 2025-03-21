package cl.caranguizh.repository;

import java.util.List;
import java.util.Optional;

import cl.caranguizh.model.DetalleCompra;

/**
 * Interfaz que define los métodos que debe implementar un repositorio de detalles de compra.
 */
public interface DetalleCompraRepository {
    List<DetalleCompra> findAll();
    Optional<DetalleCompra> findById(Integer id);
    List<DetalleCompra> findByCompraId(Integer compraId);
    List<DetalleCompra> findByProductoId(Integer productoId);
    DetalleCompra save(DetalleCompra detalleCompra);
    void deleteById(Integer id);
}