package cl.caranguizh.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import cl.caranguizh.model.Compra;

/**
 * Interfaz que define los métodos que debe implementar un repositorio de compras.
 */
public interface CompraRepository {
    List<Compra> findAll();
    Optional<Compra> findById(Integer id);
    List<Compra> findByClienteId(Integer clienteId);
    List<Compra> findByFechaCompraBetween(Date inicio, Date fin);
    Compra save(Compra compra);
    void deleteById(Integer id);
}