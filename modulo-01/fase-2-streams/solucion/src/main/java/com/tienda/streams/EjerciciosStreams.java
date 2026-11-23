package com.tienda.streams;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Solución de referencia. Regla común: los pedidos CANCELADOS no cuentan.
 */
public class EjerciciosStreams {

    // Un único sitio para la regla de negocio: si cambia, se cambia aquí.
    private Stream<Pedido> noCancelados(List<Pedido> pedidos) {
        return pedidos.stream()
                .filter(pedido -> pedido.estado() != EstadoPedido.CANCELADO);
    }

    // filter → map → reduce. reduce con BigDecimal.ZERO como valor inicial: con lista vacía devuelve 0.
    public BigDecimal totalFacturado(List<Pedido> pedidos) {
        return noCancelados(pedidos)
                .map(Pedido::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // groupingBy usa Cliente como clave de un HashMap: funciona porque un record trae equals y hashCode.
    public Map<Cliente, List<Pedido>> pedidosPorCliente(List<Pedido> pedidos) {
        return noCancelados(pedidos)
                .collect(Collectors.groupingBy(Pedido::cliente));
    }

    // flatMap: de un stream de pedidos a un stream de líneas (cada pedido aporta varias).
    // Sin el filtro de cancelados ganaría el ratón (13 unidades) en vez del cable (11).
    public Optional<Producto> productoMasVendido(List<Pedido> pedidos) {
        return noCancelados(pedidos)
                .flatMap(pedido -> pedido.lineas().stream())
                .collect(Collectors.groupingBy(LineaPedido::producto, Collectors.summingInt(LineaPedido::cantidad)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    // Sin el filtro de cancelados aparecería Luis (4 pedidos, uno cancelado).
    public List<Cliente> clientesConMasDe3Pedidos(List<Pedido> pedidos) {
        return pedidosPorCliente(pedidos).entrySet().stream()
                .filter(entrada -> entrada.getValue().size() > 3)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparing(Cliente::nombre))
                .toList();
    }

    // groupingBy con un collector "aguas abajo": reducing suma los subtotales de cada categoría.
    public Map<String, BigDecimal> facturacionPorCategoria(List<Pedido> pedidos) {
        return noCancelados(pedidos)
                .flatMap(pedido -> pedido.lineas().stream())
                .collect(Collectors.groupingBy(
                        linea -> linea.producto().categoria(),
                        Collectors.reducing(BigDecimal.ZERO, LineaPedido::subtotal, BigDecimal::add)));
    }

    // reversed() es de Java 21 (Sequenced Collections). Alternativa clásica:
    // .sorted(Comparator.comparing(Pedido::fecha).reversed())
    public List<Pedido> pedidosDelMasRecienteAlMasAntiguo(List<Pedido> pedidos) {
        return noCancelados(pedidos)
                .sorted(Comparator.comparing(Pedido::fecha))
                .toList()
                .reversed();
    }
}
