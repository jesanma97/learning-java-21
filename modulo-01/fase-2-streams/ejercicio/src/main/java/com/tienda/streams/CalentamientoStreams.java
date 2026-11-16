package com.tienda.streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Calentamiento: ejercicios cortos para practicar UNA operación de stream cada vez.
 * Hazlos en orden, antes de EjerciciosStreams.
 *
 * Cada ejercicio te enseña cómo se haría con un bucle for (lo que ya sabes)
 * y te dice qué operación de stream lo sustituye.
 *
 * Cuidado: aquí NO hay regla común. Cada ejercicio dice si los cancelados cuentan o no.
 * Comprueba tu solución ejecutando CalentamientoStreamsTest.
 */
public class CalentamientoStreams {

    /**
     * 1. Los ids de todos los pedidos, en el mismo orden.
     * <pre>{@code
     * List<String> ids = new ArrayList<>();
     * for (Pedido pedido : pedidos) {
     *     ids.add(pedido.id());
     * }
     * }</pre>
     * Usa: map + toList.
     */
    public List<String> idsDePedidos(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 2. Los pedidos en estado PENDIENTE.
     * <pre>{@code
     * List<Pedido> pendientes = new ArrayList<>();
     * for (Pedido pedido : pedidos) {
     *     if (pedido.estado() == EstadoPedido.PENDIENTE) {
     *         pendientes.add(pedido);
     *     }
     * }
     * }</pre>
     * Usa: filter + toList.
     */
    public List<Pedido> pedidosPendientes(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 3. Cuántos pedidos están cancelados.
     * <pre>{@code
     * long cancelados = 0;
     * for (Pedido pedido : pedidos) {
     *     if (pedido.estado() == EstadoPedido.CANCELADO) {
     *         cancelados++;
     *     }
     * }
     * }</pre>
     * Usa: filter + count.
     */
    public long contarCancelados(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 4. Los nombres de los clientes que han hecho algún pedido, sin repetir y en orden alfabético.
     * Cuentan todos los pedidos, también los cancelados.
     * Usa: map + distinct + sorted + toList.
     */
    public List<String> nombresDeClientes(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 5. Las ciudades desde las que se ha hecho algún pedido (cancelados incluidos).
     * Un Set ya no admite repetidos, así que aquí no hace falta distinct.
     * Pista: el pedido tiene un cliente, y el cliente tiene una ciudad. Puedes encadenar dos map.
     * Usa: map + Collectors.toSet.
     */
    public Set<String> ciudades(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 6. ¿Ha hecho el cliente algún pedido? (cancelados incluidos).
     * <pre>{@code
     * for (Pedido pedido : pedidos) {
     *     if (pedido.cliente().equals(cliente)) {
     *         return true;
     *     }
     * }
     * return false;
     * }</pre>
     * Usa: anyMatch.
     */
    public boolean haPedidoAlgo(List<Pedido> pedidos, Cliente cliente) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 7. Busca un pedido por su id. Si no existe, devuelve un Optional vacío (nunca null).
     * Usa: filter + findFirst.
     */
    public Optional<Pedido> buscarPorId(List<Pedido> pedidos, String id) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 8. El pedido no cancelado con el total más alto. Vacío si no hay pedidos.
     * Pista: Comparator.comparing(Pedido::total) sabe comparar pedidos por su total.
     * Usa: filter + max.
     */
    public Optional<Pedido> pedidoMasCaro(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 9. Cuántos pedidos hay en cada estado (cancelados incluidos).
     * <pre>{@code
     * Map<EstadoPedido, Long> resultado = new HashMap<>();
     * for (Pedido pedido : pedidos) {
     *     resultado.merge(pedido.estado(), 1L, Long::sum);
     * }
     * }</pre>
     * Usa: collect(Collectors.groupingBy(..., Collectors.counting())).
     */
    public Map<EstadoPedido, Long> pedidosPorEstado(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 10. Unidades vendidas en total, sumando las cantidades de todas las líneas
     * de los pedidos no cancelados.
     * Es el puente hacia EjerciciosStreams: cada pedido tiene VARIAS líneas.
     * <pre>{@code
     * int unidades = 0;
     * for (Pedido pedido : pedidos) {
     *     if (pedido.estado() != EstadoPedido.CANCELADO) {
     *         for (LineaPedido linea : pedido.lineas()) {   // bucle dentro de bucle = flatMap
     *             unidades += linea.cantidad();
     *         }
     *     }
     * }
     * }</pre>
     * Usa: filter + flatMap + mapToInt + sum.
     */
    public int unidadesVendidas(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }
}
