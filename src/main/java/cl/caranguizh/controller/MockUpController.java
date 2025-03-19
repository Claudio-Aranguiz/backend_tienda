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

@Controller
public class MockUpController {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @GetMapping("/mock_up")
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