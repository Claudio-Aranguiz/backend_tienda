package cl.caranguizh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.caranguizh.model.Categoria;
import cl.caranguizh.model.Producto;
import cl.caranguizh.repository.CategoriaRepository;
import cl.caranguizh.repository.ProductoRepository;

/**
 * Controlador encargado de manejar las peticiones relacionadas con el mockup
 * de productos.
 * 
 * @version 2.0, 08-12-2021
 * @since 2.0, 08-12-2021
 */
@Controller
public class MockUpController {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    /**
     * Muestra el mockup de productos.
     *
     * @param busqueda Término de búsqueda.
     * @param categoriaId Identificador de la categoría.
     * @param model Modelo de la vista.
     * @return Nombre de la vista.
    */
    @GetMapping("/mock_up_v2")
    public String mostrarMockUp(
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) Integer categoriaId,
            Model model) {
        
        // Lógica de filtrado
        List<Producto> productos;
        if (categoriaId != null && categoriaId > 0) {
            if (busqueda != null && !busqueda.isEmpty()) {
                productos = productoRepository.findByCategoriaIdAndNombreContaining(categoriaId, busqueda);
            } else {
                productos = productoRepository.findByCategoriaId(categoriaId);
            }
        } else {
            if (busqueda != null && !busqueda.isEmpty()) {
                productos = productoRepository.findByNombreContaining(busqueda);
            } else {
                productos = productoRepository.findAll();
            }
        }
        
        List<Categoria> categorias = categoriaRepository.findAll();
        
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        
        return "mockup";
    }
}