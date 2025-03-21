package cl.caranguizh.config;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Inicializador de la aplicación web.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Configuración de la aplicación.
     */
    // Se sobreescribe el método getRootConfigClasses para retornar null, ya que no se requiere configuración adicional.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * Configuración de la aplicación web.
     */
    // Se sobreescribe el método getServletConfigClasses para retornar la clase WebConfig, que contiene la configuración de la aplicación web.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    /**
     * Mapeo de los servlets de la aplicación.
     */
    // Se sobreescribe el método getServletMappings para retornar un arreglo con el mapeo de los servlets de la aplicación.
    // Se retorna un arreglo con el valor "/" para mapear el servlet principal a la raíz de la aplicación.
    @Override
    protected @NonNull String[] getServletMappings() {
        return new String[] { "/" };
    }
}