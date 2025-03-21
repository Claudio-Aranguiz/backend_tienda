package cl.caranguizh.repository;

import java.util.List;
import java.util.Optional;

import cl.caranguizh.model.Categoria;

/**
 * Interfaz que define los métodos que debe implementar un repositorio de categorías.
 */
public interface CategoriaRepository {
    List<Categoria> findAll();
    Optional<Categoria> findById(Integer id);
    Optional<Categoria> findByNombre(String nombre);
    Categoria save(Categoria categoria);
    void deleteById(Integer id);
	List<Categoria> findByNombreContaining(String nombre);
	List<Categoria> findProductosByCategoriaId(Integer id);
}