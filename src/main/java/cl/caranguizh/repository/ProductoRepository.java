package cl.caranguizh.repository;

import java.util.List;
import java.util.Optional;

import cl.caranguizh.model.Producto;

/**
 * Interfaz que define los métodos que debe implementar un repositorio de productos.
 */
public interface ProductoRepository {
    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    void save(Producto producto);
    void deleteById(Integer id);
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByCategoriaId(Integer categoriaId);
    List<Producto> findByCategoriaIdAndNombreContaining(Integer categoriaId, String nombre);
	List<Producto> findByPrecioBetween(Integer min, Integer max);
	List<Producto> findByCategoriaIdOrNombreContaining(Integer categoriaId, String nombre);
}