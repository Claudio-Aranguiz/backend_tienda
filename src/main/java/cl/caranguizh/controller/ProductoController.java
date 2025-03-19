package cl.caranguizh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.caranguizh.model.Categoria;
import cl.caranguizh.model.Producto;
import cl.caranguizh.service.CategoriaService;
import cl.caranguizh.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    
    @Autowired
    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }
    
    @GetMapping
    public String mostrarProductos(
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) Integer categoriaId,
            Model model) {
        
        // Aplicar filtros si se proporcionan
        List<Producto> productos;
        if (busqueda != null && !busqueda.isEmpty() && categoriaId != null && categoriaId > 0) {
            productos = productoService.findByCategoriaIdAndNombreContaining(categoriaId, busqueda);
        } else if (busqueda != null && !busqueda.isEmpty()) {
            productos = productoService.findByNombreContaining(busqueda);
        } else if (categoriaId != null && categoriaId > 0) {
            productos = productoService.findByCategoriaId(categoriaId);
        } else {
            productos = productoService.findAll();
        }
        
        List<Categoria> categorias = categoriaService.findAll();
        
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        
        return "productos";
    }
    
    @PostMapping
    public String procesarProducto(
            @RequestParam String accion,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer precio,
            @RequestParam(required = false) Integer categoriaId) {
        
        switch (accion) {
            case "crear" -> crearProducto(nombre, precio, categoriaId);
            case "actualizar" -> actualizarProducto(id, nombre, precio, categoriaId);
            case "eliminar" -> productoService.deleteById(id);
        }
        
        return "redirect:/productos";
    }
    
    private void crearProducto(String nombre, Integer precio, Integer categoriaId) {
        Categoria categoria = categoriaService.findById(categoriaId);
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        productoService.save(producto);
    }
    
    private void actualizarProducto(Integer id, String nombre, Integer precio, Integer categoriaId) {
        Producto producto = productoService.findById(id);
        Categoria categoria = categoriaService.findById(categoriaId);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        productoService.save(producto);
    }
}