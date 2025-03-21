package cl.caranguizh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.caranguizh.model.Categoria;
import cl.caranguizh.model.Producto;
import cl.caranguizh.service.CategoriaService;
import cl.caranguizh.service.ProductoService;

@Controller
public class CatalogoController {
    
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    
    @Autowired
    public CatalogoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }
    
    @GetMapping("/mock_up")
    public String mostrarCatalogo(
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) Integer categoriaId,
            Model model) {
        
        // Lógica de filtrado de productos
        List<Producto> productos;
        if (categoriaId != null && categoriaId > 0) {
            if (busqueda != null && !busqueda.isEmpty()) {
                productos = productoService.findByCategoriaIdAndNombreContaining(categoriaId, busqueda);
            } else {
                productos = productoService.findByCategoriaId(categoriaId);
            }
        } else {
            if (busqueda != null && !busqueda.isEmpty()) {
                productos = productoService.findByNombreContaining(busqueda);
            } else {
                productos = productoService.findAll();
            }
        }
        
        List<Categoria> categorias = categoriaService.findAll();
        
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        
        return "mockup"; // Nombre exacto del archivo JSP
    }
    
    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }
}