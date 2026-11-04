package com.tienda.pedido;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Pedido(Long id, String cliente, Instant fecha, EstadoPedido estado, List<LineaPedido> lineas) {

    public Pedido {
        lineas = List.copyOf(lineas);
    }

    public BigDecimal total() {
        return lineas.stream()
                .map(LineaPedido::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // La regla de negocio está en el dominio, no en el controlador.
    public Pedido cancelar() {
        if (!estado.sePuedeCancelar()) {
            throw new PedidoNoCancelableException(id, estado);
        }
        return new Pedido(id, cliente, fecha, EstadoPedido.CANCELADO, lineas);
    }
}
