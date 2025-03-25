package cl.caranguizh.console.service;

import cl.caranguizh.console.model.Detalle;
import cl.caranguizh.console.service.interfaces.Descuento;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescuentoPorCategoria implements Descuento {
    @Override
    public double descuento(List<Detalle> detalles) {
        Map<String, Integer> cantidadPorCategoria = new HashMap<>();

        for (Detalle detalle : detalles) {
            cantidadPorCategoria.put(detalle.getCategoria(), 
                cantidadPorCategoria.getOrDefault(detalle.getCategoria(), 0) + detalle.getCantidad());
        }

        for (Map.Entry<String, Integer> entry : cantidadPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            int cantidad = entry.getValue();

            if (categoria.equals("Electrodomésticos")) {
                if (cantidad >= 3 && cantidad <= 5) {
                    return 0.10; // 10% de descuento
                } else if (cantidad >= 6 && cantidad <= 10) {
                    return 0.20; // 20% de descuento
                }
            } else if (categoria.equals("Electrónicos")) {
                if (cantidad > 5) {
                    return 0.15; // 15% de descuento
                }
            } else if (categoria.equals("Muebles")) {
                if (cantidad >= 2 && cantidad <= 5) {
                    return 0.20; // 20% de descuento
                } else if (cantidad > 5) {
                    return 0.30; // 30% de descuento
                }
            }
        }

        return 0.0; // No hay descuento
    }
}