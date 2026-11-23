package com.tienda.moderno;

import com.tienda.moderno.Pago.PayPal;
import com.tienda.moderno.Pago.Tarjeta;
import com.tienda.moderno.Pago.Transferencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorTienda {

    // Switch expression: devuelve un valor, sin break y sin default.
    // Si mañana se añade un estado nuevo, esto deja de compilar hasta tratarlo.
    public String describirEstado(EstadoPedido estado) {
        return switch (estado) {
            case PENDIENTE -> "Pendiente de pago";
            case PAGADO -> "Pagado, preparando envío";
            case ENVIADO -> "En camino";
            case ENTREGADO -> "Entregado";
            case CANCELADO -> "Cancelado";
        };
    }

    public boolean sePuedeCancelar(EstadoPedido estado) {
        return switch (estado) {
            case PENDIENTE, PAGADO -> true;
            case ENVIADO, ENTREGADO, CANCELADO -> false;
        };
    }

    // Pattern matching en switch + record patterns + guarda "when".
    // Exhaustivo gracias a sealed: no hace falta default ni casts.
    public double calcularComision(Pago pago) {
        return switch (pago) {
            case Tarjeta(var importe, var numeroTarjeta, var internacional) when internacional -> importe * 0.03;
            case Tarjeta tarjeta -> tarjeta.importe() * 0.015;
            case Transferencia transferencia -> 0.5;
            case PayPal(var importe, var email) -> importe * 0.034 + 0.35;
        };
    }

    // Text block: el JSON se lee tal cual. (En un proyecto real: Jackson, nunca JSON a mano).
    public String toJson(Producto producto) {
        return """
                {
                  "id": %d,
                  "nombre": "%s",
                  "precio": %s,
                  "stock": %d
                }""".formatted(producto.id(), producto.nombre(), producto.precio(), producto.stock());
    }

    // Text block + parámetro "?": el original era vulnerable a inyección SQL.
    // El text block mejora la lectura; lo que corrige el fallo es el parámetro.
    public String consultaProductosActivos() {
        return """
                SELECT id, nombre, precio, stock
                FROM productos
                WHERE categoria = ?
                  AND activo = true
                ORDER BY nombre""";
    }

    // var donde el tipo es obvio a la derecha. En la fase 2 esto será un groupingBy.
    public Map<String, List<Producto>> agruparPorCategoria(List<Producto> productos) {
        var resultado = new HashMap<String, List<Producto>>();
        for (var producto : productos) {
            resultado.computeIfAbsent(producto.categoria(), categoria -> new ArrayList<>())
                    .add(producto);
        }

        return resultado;
    }
}
