package com.tienda.streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record Pedido(String id, Cliente cliente, LocalDate fecha, EstadoPedido estado, List<LineaPedido> lineas) {

    // Copia inmutable: sin esto, quien creó la lista podría modificar el pedido desde fuera.
    public Pedido {
        lineas = List.copyOf(lineas);
    }

    public BigDecimal total() {
        return lineas.stream()
                .map(LineaPedido::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
