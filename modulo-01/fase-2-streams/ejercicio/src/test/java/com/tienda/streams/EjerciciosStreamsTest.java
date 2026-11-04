package com.tienda.streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tienda.streams.DatosTienda.ANA;
import static com.tienda.streams.DatosTienda.CABLE_USB_C;
import static com.tienda.streams.DatosTienda.LUIS;
import static com.tienda.streams.DatosTienda.MARTA;
import static com.tienda.streams.DatosTienda.SOFIA;
import static org.assertj.core.api.Assertions.assertThat;

class EjerciciosStreamsTest {

    private final EjerciciosStreams ejercicios = new EjerciciosStreams();
    private final List<Pedido> pedidos = DatosTienda.pedidos();

    @Test
    void totalFacturadoExcluyeLosCancelados() {
        // BigDecimal: se compara con compareTo, no con equals (equals tiene en cuenta la escala).
        assertThat(ejercicios.totalFacturado(pedidos)).isEqualByComparingTo("1353.86");
    }

    @Test
    void pedidosPorClienteNoIncluyeLosCancelados() {
        var porCliente = ejercicios.pedidosPorCliente(pedidos);

        assertThat(porCliente).hasSize(4);
        assertThat(porCliente).doesNotContainKey(SOFIA);
        assertThat(porCliente.get(ANA)).hasSize(4);
        assertThat(porCliente.get(LUIS)).hasSize(3);
    }

    @Test
    void productoMasVendidoEsElCableUsbC() {
        assertThat(ejercicios.productoMasVendido(pedidos)).contains(CABLE_USB_C);
    }

    @Test
    void productoMasVendidoSinPedidosEstaVacio() {
        assertThat(ejercicios.productoMasVendido(List.of())).isEmpty();
    }

    @Test
    void clientesConMasDe3PedidosOrdenadosPorNombre() {
        assertThat(ejercicios.clientesConMasDe3Pedidos(pedidos)).containsExactly(ANA, MARTA);
    }

    @Test
    void extraFacturacionPorCategoria() {
        var porCategoria = ejercicios.facturacionPorCategoria(pedidos);

        assertThat(porCategoria).hasSize(4);
        assertThat(porCategoria.get("PERIFERICOS")).isEqualByComparingTo("346.47");
        assertThat(porCategoria.get("PANTALLAS")).isEqualByComparingTo("598.00");
        assertThat(porCategoria.get("AUDIO")).isEqualByComparingTo("299.50");
        assertThat(porCategoria.get("ACCESORIOS")).isEqualByComparingTo("109.89");
    }

    @Test
    void extraPedidosDelMasRecienteAlMasAntiguo() {
        var ordenados = ejercicios.pedidosDelMasRecienteAlMasAntiguo(pedidos);

        assertThat(ordenados).hasSize(13);
        assertThat(ordenados.getFirst().id()).isEqualTo("PED-16");
        assertThat(ordenados.getLast().id()).isEqualTo("PED-01");
    }
}
