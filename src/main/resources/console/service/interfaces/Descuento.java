package cl.caranguizh.console.service.interfaces;

import cl.caranguizh.console.model.Detalle;
import java.util.List;

public interface Descuento {
    double descuento(List<Detalle> detalles);
}