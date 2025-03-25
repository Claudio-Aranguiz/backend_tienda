package cl.caranguizh.console.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.caranguizh.console.service.DescuentoPorCantidad;
import cl.caranguizh.console.service.DescuentoPorCategoria;
import cl.caranguizh.console.service.interfaces.Descuento;

@Configuration
public class AppConfig {

    @Bean
    public Descuento descuentoPorCantidad() {
        return new DescuentoPorCantidad();
    }

    @Bean
    public Descuento descuentoPorCategoria() {
        return new DescuentoPorCategoria();
    }
}