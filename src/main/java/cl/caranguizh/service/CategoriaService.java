package cl.caranguizh.service;

import cl.caranguizh.model.Categoria;
import cl.caranguizh.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para la gestión de categorías.
 * Implementa la lógica de negocio relacionada con las categorías.
 */
@Service
@Transactional
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    /**
     * Obtiene todas las categorías disponibles.
     * @return Lista de todas las categorías
     */
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
    
    /**
     * Busca una categoría por su ID.
     * @param id ID de la categoría a buscar
     * @return La categoría encontrada
     * @throws RuntimeException si la categoría no existe
     */
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }
    
    /**
     * Guarda o actualiza una categoría.
     * @param categoria La categoría a guardar o actualizar
     */
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
    
    /**
     * Elimina una categoría por su ID.
     * @param id ID de la categoría a eliminar
     */
    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }
    
    /**
     * Busca categorías que contengan el nombre especificado.
     * @param nombre Texto a buscar en los nombres de categorías
     * @return Lista de categorías que coinciden con el criterio
     */
    public List<Categoria> findByNombreContaining(String nombre) {
        return categoriaRepository.findByNombreContaining(nombre);
    }
    
    /**
     * Verifica si una categoría está en uso (tiene productos asociados).
     * @param id ID de la categoría a verificar
     * @return true si la categoría tiene productos asociados, false en caso contrario
     */
    public boolean tieneProductosAsociados(Integer id) {
        // Esta implementación depende de cómo esté estructurado tu repositorio
        // Podrías implementarlo así:
        return !categoriaRepository.findProductosByCategoriaId(id).isEmpty();
        
        // O si no tienes ese método, podrías inyectar ProductoRepository y hacer:
        // return !productoRepository.findByCategoriaId(id).isEmpty();
    }
}