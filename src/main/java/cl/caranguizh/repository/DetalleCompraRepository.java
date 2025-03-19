package cl.caranguizh.repository;

import cl.caranguizh.model.DetalleCompra;
import java.util.List;
import java.util.Optional;

public interface DetalleCompraRepository {
    List<DetalleCompra> findAll();
    Optional<DetalleCompra> findById(Integer id);
    List<DetalleCompra> findByCompraId(Integer compraId);
    List<DetalleCompra> findByProductoId(Integer productoId);
    DetalleCompra save(DetalleCompra detalleCompra);
    void deleteById(Integer id);
}