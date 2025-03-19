package cl.caranguizh.repository;

import cl.caranguizh.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    List<Categoria> findAll();
    Optional<Categoria> findById(Integer id);
    Optional<Categoria> findByNombre(String nombre);
    Categoria save(Categoria categoria);
    void deleteById(Integer id);
	List<Categoria> findByNombreContaining(String nombre);
	List<Categoria> findProductosByCategoriaId(Integer id);
}