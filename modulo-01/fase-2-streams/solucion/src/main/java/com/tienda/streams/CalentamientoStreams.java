package com.tienda.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Solución del calentamiento. Cada stream se lee de arriba abajo:
 * origen (stream) → operaciones intermedias (filter, map...) → operación terminal (toList, count...).
 * Sin operación terminal no se ejecuta nada.
 */
public class CalentamientoStreams {

    // 1. map transforma cada elemento: Pedido → String.
    public List<String> idsDePedidos(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(Pedido::id)
                .toList();
    }

    // 2. filter deja pasar solo los que cumplen la condición. No cambia el tipo.
    public List<Pedido> pedidosPendientes(List<Pedido> pedidos) {
        return pedidos.stream()
                .filter(pedido -> pedido.estado() == EstadoPedido.PENDIENTE)
                .toList();
    }

    // 3. count es terminal y devuelve long.
    public long contarCancelados(List<Pedido> pedidos) {
        return pedidos.stream()
                .filter(pedido -> pedido.estado() == EstadoPedido.CANCELADO)
                .count();
    }

    // 4. distinct usa equals; sorted sin argumentos usa el orden natural (String es Comparable).
    public List<String> nombresDeClientes(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> pedido.cliente().nombre())
                .distinct()
                .sorted()
                .toList();
    }

    // 5. Dos map seguidos: Pedido → Cliente → String. El Set elimina repetidos.
    public Set<String> ciudades(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(Pedido::cliente)
                .map(Cliente::ciudad)
                .collect(Collectors.toSet());
    }

    // 6. anyMatch corta en cuanto encuentra uno (igual que el return true del bucle).
    public boolean haPedidoAlgo(List<Pedido> pedidos, Cliente cliente) {
        return pedidos.stream()
                .anyMatch(pedido -> pedido.cliente().equals(cliente));
    }

    // 7. findFirst devuelve Optional: obliga a quien llama a pensar en el caso "no existe".
    public Optional<Pedido> buscarPorId(List<Pedido> pedidos, String id) {
        return pedidos.stream()
                .filter(pedido -> pedido.id().equals(id))
                .findFirst();
    }

    // 8. max necesita saber comparar: se lo decimos con un Comparator. Lista vacía → Optional vacío.
    public Optional<Pedido> pedidoMasCaro(List<Pedido> pedidos) {
        return pedidos.stream()
                .filter(pedido -> pedido.estado() != EstadoPedido.CANCELADO)
                .max(Comparator.comparing(Pedido::total));
    }

    // 9. groupingBy(clave, qué hacer con cada grupo). counting cuenta los elementos del grupo.
    public Map<EstadoPedido, Long> pedidosPorEstado(List<Pedido> pedidos) {
        return pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::estado, Collectors.counting()));
    }

    // 10. flatMap aplana: un Stream<Pedido> pasa a ser un Stream<LineaPedido> con TODAS las líneas.
    //     mapToInt pasa a IntStream, que sí tiene sum().
    public int unidadesVendidas(List<Pedido> pedidos) {
        return pedidos.stream()
                .filter(pedido -> pedido.estado() != EstadoPedido.CANCELADO)
                .flatMap(pedido -> pedido.lineas().stream())
                .mapToInt(LineaPedido::cantidad)
                .sum();
    }
}
