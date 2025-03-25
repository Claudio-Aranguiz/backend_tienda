package cl.caranguizh.console.service;

import cl.caranguizh.console.model.Detalle;
import cl.caranguizh.console.service.interfaces.Descuento;
import java.util.List;

public class DescuentoPorCantidad implements Descuento {
    @Override
    public double descuento(List<Detalle> detalles) {
        int totalProductos = detalles.stream().mapToInt(Detalle::getCantidad).sum();

        if (totalProductos > 20) {
            return 0.30; // 30% de descuento
        } else if (totalProductos >= 10 && totalProductos <= 20) {
            return 0.10; // 10% de descuento
        } else if (totalProductos > 7) {
            return 0.05; // 5% de descuento
        } else if (totalProductos > 5) {
            return 0.03; // 3% de descuento
        } else {
            return 0.0; // No hay descuento
        }
    }
}