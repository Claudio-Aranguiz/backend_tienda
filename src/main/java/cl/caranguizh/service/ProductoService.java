package cl.caranguizh.service;

import cl.caranguizh.model.Producto;
import cl.caranguizh.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    public Producto findById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    
    public void save(Producto producto) {
        productoRepository.save(producto);
    }
    
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }
    
    public List<Producto> findByNombreContaining(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }
    
    public List<Producto> findByCategoriaId(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    public List<Producto> findByCategoriaIdAndNombreContaining(Integer categoriaId, String nombre) {
        return productoRepository.findByCategoriaIdAndNombreContaining(categoriaId, nombre);
    }
}