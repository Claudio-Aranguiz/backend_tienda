package cl.caranguizh.console;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cl.caranguizh.console.config.AppConfig;
import cl.caranguizh.console.model.Detalle;
import cl.caranguizh.console.service.DescuentoPorCantidad;
import cl.caranguizh.console.service.interfaces.Descuento;

public class ConsoleApplication {
    public static void main(String[] args) {
        // Inicializar el contexto de Spring usando configuración basada en anotaciones
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Obtener el bean de la estrategia de descuento
        Descuento descuento = context.getBean(DescuentoPorCantidad.class);

        // Crear una lista de detalles para probar
        List<Detalle> detalles = Arrays.asList(
            new Detalle("Televisor", 10, "Electrodomésticos"),
            new Detalle("Refrigerador", 11, "Electrodomésticos")
        );

        // Calcular el descuento
        double descuentoAplicado = descuento.descuento(detalles);
        System.out.println("Descuento aplicado: " + descuentoAplicado);
    }
}