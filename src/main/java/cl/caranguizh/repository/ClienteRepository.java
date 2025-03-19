package cl.caranguizh.repository;

import cl.caranguizh.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    List<Cliente> findAll();
    Optional<Cliente> findById(Integer id);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByNombreContaining(String nombre);
    Cliente save(Cliente cliente);
    void deleteById(Integer id);
}