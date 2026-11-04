package com.tienda.streams;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Resuelve cada método con streams, sin bucles for ni while.
 * Regla común: los pedidos CANCELADOS no cuentan en ningún ejercicio.
 * Comprueba tu solución ejecutando EjerciciosStreamsTest.
 */
public class EjerciciosStreams {

    /**
     * 1. Suma del total de todos los pedidos no cancelados.
     */
    public BigDecimal totalFacturado(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 2. Pedidos agrupados por cliente.
     */
    public Map<Cliente, List<Pedido>> pedidosPorCliente(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 3. Producto con más unidades vendidas. Vacío si no hay pedidos.
     */
    public Optional<Producto> productoMasVendido(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 4. Clientes con más de 3 pedidos, ordenados por nombre.
     */
    public List<Cliente> clientesConMasDe3Pedidos(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    // ---------- Extras, para quien termine antes ----------

    /**
     * 5. Importe facturado por categoría de producto.
     */
    public Map<String, BigDecimal> facturacionPorCategoria(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * 6. Pedidos ordenados del más reciente al más antiguo.
     * Pista: Java 21 añadió un método a List que da la vuelta a la lista.
     */
    public List<Pedido> pedidosDelMasRecienteAlMasAntiguo(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Por implementar");
    }
}
