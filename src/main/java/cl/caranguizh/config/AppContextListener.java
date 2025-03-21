package cl.caranguizh.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cl.caranguizh.repository.CategoriaRepository;
import cl.caranguizh.repository.ProductoRepository;

/**
 * Listener para inicializar el contexto de la aplicación.
 */
public class AppContextListener implements ServletContextListener {


    /**
     * Inicializa el contexto de la aplicación.
     * @param sce
     */
    @SuppressWarnings("null")
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ctx);
        
        // Obtener beans de Spring y establecerlos como atributos del ServletContext
        ctx.setAttribute("productoRepository", springContext.getBean(ProductoRepository.class));
        ctx.setAttribute("categoriaRepository", springContext.getBean(CategoriaRepository.class));
    }
}