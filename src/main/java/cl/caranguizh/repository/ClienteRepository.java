package cl.caranguizh.repository;

import java.util.List;
import java.util.Optional;

import cl.caranguizh.model.Cliente;

/**
 * Interfaz que define los métodos que debe implementar un repositorio de clientes.
 */
public interface ClienteRepository {
    List<Cliente> findAll();
    Optional<Cliente> findById(Integer id);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByNombreContaining(String nombre);
    Cliente save(Cliente cliente);
    void deleteById(Integer id);
}