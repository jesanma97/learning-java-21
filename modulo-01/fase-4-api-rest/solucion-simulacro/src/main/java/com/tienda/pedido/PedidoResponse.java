package com.tienda.pedido;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record PedidoResponse(Long id, String cliente, Instant fecha, EstadoPedido estado,
                             List<Linea> lineas, BigDecimal total) {

    public record Linea(String sku, String nombre, BigDecimal precioUnitario, int cantidad, BigDecimal subtotal) {}

    public static PedidoResponse desde(Pedido pedido) {
        var lineas = pedido.lineas().stream()
                .map(linea -> new Linea(linea.sku(), linea.nombre(), linea.precioUnitario(),
                        linea.cantidad(), linea.subtotal()))
                .toList();
        return new PedidoResponse(pedido.id(), pedido.cliente(), pedido.fecha(), pedido.estado(),
                lineas, pedido.total());
    }
}
