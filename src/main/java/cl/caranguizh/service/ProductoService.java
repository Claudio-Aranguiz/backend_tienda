package cl.caranguizh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.caranguizh.model.Producto;
import cl.caranguizh.repository.ProductoRepository;

/**
 * Clase que representa un servicio para la entidad Producto.
 */
//  * @param productoRepository Repositorio de productos.
@Service
@Transactional
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    /**
     * Constructor de la clase.
     *
     * @param productoRepository Repositorio de productos.
     */
    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    /**
     * Método que retorna todos los productos.
     *
     * @return Lista de productos.
     */
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    /**
     * Método que retorna un producto por su identificador.
     *
     * @param id Identificador del producto.
     * @return Producto.
     */
    public Producto findById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    
    /**
     * Método que guarda un producto.
     *
     * @param producto Producto a guardar.
     */
    public void save(Producto producto) {
        productoRepository.save(producto);
    }
    
    /**
     * Método que elimina un producto por su identificador.
     *
     * @param id Identificador del producto.
     */
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }
    
    /**
     * Método que retorna los productos que contienen un nombre.
     *
     * @param nombre Nombre a buscar.
     * @return Lista de productos.
     */
    public List<Producto> findByNombreContaining(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }
    
    /**
     * Método que retorna los productos de una categoría.
     *
     * @param categoriaId Identificador de la categoría.
     * @return Lista de productos.
     */
    public List<Producto> findByCategoriaId(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    /**
     * Método que retorna los productos de una categoría que contienen un nombre.
     *
     * @param categoriaId Identificador de la categoría.
     * @param nombre      Nombre a buscar.
     * @return Lista de productos.
     */
    public List<Producto> findByCategoriaIdAndNombreContaining(Integer categoriaId, String nombre) {
        return productoRepository.findByCategoriaIdAndNombreContaining(categoriaId, nombre);
    }
}