package com.tienda.streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tienda.streams.DatosTienda.SOFIA;
import static org.assertj.core.api.Assertions.assertThat;

class CalentamientoStreamsTest {

    private final CalentamientoStreams calentamiento = new CalentamientoStreams();
    private final List<Pedido> pedidos = DatosTienda.pedidos();

    @Test
    void idsDePedidosEnElMismoOrden() {
        var ids = calentamiento.idsDePedidos(pedidos);

        assertThat(ids).hasSize(16);
        assertThat(ids.getFirst()).isEqualTo("PED-01");
        assertThat(ids.getLast()).isEqualTo("PED-16");
    }

    @Test
    void pedidosPendientes() {
        assertThat(calentamiento.pedidosPendientes(pedidos))
                .extracting(Pedido::id)
                .containsExactly("PED-14", "PED-15", "PED-16");
    }

    @Test
    void contarCancelados() {
        assertThat(calentamiento.contarCancelados(pedidos)).isEqualTo(3);
    }

    @Test
    void nombresDeClientesSinRepetirYOrdenados() {
        assertThat(calentamiento.nombresDeClientes(pedidos))
                .containsExactly("Ana García", "Javier Ruiz", "Luis Pérez", "Marta López", "Sofía Martín");
    }

    @Test
    void ciudadesSinRepetir() {
        // Ana y Sofía son de Madrid: si sale 5, algo se repite.
        assertThat(calentamiento.ciudades(pedidos))
                .containsExactlyInAnyOrder("Madrid", "Sevilla", "Valencia", "Bilbao");
    }

    @Test
    void haPedidoAlgo() {
        var desconocido = new Cliente("C99", "Nadie", "Lugo");

        assertThat(calentamiento.haPedidoAlgo(pedidos, SOFIA)).isTrue();
        assertThat(calentamiento.haPedidoAlgo(pedidos, desconocido)).isFalse();
    }

    @Test
    void buscarPorIdExistente() {
        assertThat(calentamiento.buscarPorId(pedidos, "PED-07"))
                .get()
                .extracting(Pedido::cliente)
                .isEqualTo(DatosTienda.MARTA);
    }

    @Test
    void buscarPorIdInexistenteDevuelveVacio() {
        assertThat(calentamiento.buscarPorId(pedidos, "PED-99")).isEmpty();
    }

    @Test
    void pedidoMasCaroSinContarCancelados() {
        // El PED-08 (cancelado) vale 598.00: si sale ese, no has filtrado.
        assertThat(calentamiento.pedidoMasCaro(pedidos).map(Pedido::id)).contains("PED-10");
    }

    @Test
    void pedidoMasCaroSinPedidosEstaVacio() {
        assertThat(calentamiento.pedidoMasCaro(List.of())).isEmpty();
    }

    @Test
    void pedidosPorEstado() {
        var porEstado = calentamiento.pedidosPorEstado(pedidos);

        assertThat(porEstado)
                .containsEntry(EstadoPedido.ENTREGADO, 6L)
                .containsEntry(EstadoPedido.CANCELADO, 3L)
                .containsEntry(EstadoPedido.ENVIADO, 2L)
                .containsEntry(EstadoPedido.PAGADO, 2L)
                .containsEntry(EstadoPedido.PENDIENTE, 3L);
    }

    @Test
    void unidadesVendidasSinContarCancelados() {
        // Con los cancelados saldrían 37.
        assertThat(calentamiento.unidadesVendidas(pedidos)).isEqualTo(24);
    }
}
