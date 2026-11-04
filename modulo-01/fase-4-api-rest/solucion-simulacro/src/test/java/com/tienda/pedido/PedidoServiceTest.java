package com.tienda.pedido;

import com.tienda.producto.ProductoService;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PedidoServiceTest {

    private static final Instant AHORA = Instant.parse("2026-10-25T10:00:00Z");

    // Reloj fijo: el test no depende de la hora a la que se ejecute.
    private final PedidoService pedidoService =
            new PedidoService(new ProductoService(), Clock.fixed(AHORA, ZoneOffset.UTC));

    @Test
    void crearCalculaElTotalYEmpiezaPendiente() {
        var pedido = pedidoService.crear("ana@correo.com", List.of(
                new LineaSolicitada("TEC-01", 1),
                new LineaSolicitada("CAB-01", 2)));

        assertThat(pedido.id()).isEqualTo(1L);
        assertThat(pedido.estado()).isEqualTo(EstadoPedido.PENDIENTE);
        assertThat(pedido.fecha()).isEqualTo(AHORA);
        assertThat(pedido.total()).isEqualByComparingTo("109.97");
        assertThat(pedido.lineas().getFirst().nombre()).isEqualTo("Teclado mecánico");
    }

    @Test
    void crearConUnSkuInexistenteFallaYNoConsumeId() {
        assertThatThrownBy(() -> pedidoService.crear("ana@correo.com", List.of(new LineaSolicitada("NO-00", 1))))
                .isInstanceOf(ProductoNoDisponibleException.class)
                .hasMessage("El producto NO-00 no existe en el catálogo");

        var siguiente = pedidoService.crear("ana@correo.com", List.of(new LineaSolicitada("TEC-01", 1)));
        assertThat(siguiente.id()).isEqualTo(1L);
    }

    @Test
    void cancelarUnPedidoPendienteLoDejaCancelado() {
        var pedido = pedidoService.crear("ana@correo.com", List.of(new LineaSolicitada("TEC-01", 1)));

        var cancelado = pedidoService.cancelar(pedido.id());

        assertThat(cancelado.estado()).isEqualTo(EstadoPedido.CANCELADO);
        assertThat(pedidoService.obtener(pedido.id()).estado()).isEqualTo(EstadoPedido.CANCELADO);
    }

    @Test
    void cancelarDosVecesEsUnConflicto() {
        var pedido = pedidoService.crear("ana@correo.com", List.of(new LineaSolicitada("TEC-01", 1)));
        pedidoService.cancelar(pedido.id());

        assertThatThrownBy(() -> pedidoService.cancelar(pedido.id()))
                .isInstanceOf(PedidoNoCancelableException.class)
                .hasMessage("El pedido 1 no se puede cancelar porque está CANCELADO");
    }

    @Test
    void cancelarUnPedidoInexistenteEsNoEncontrado() {
        assertThatThrownBy(() -> pedidoService.cancelar(99L))
                .isInstanceOf(PedidoNoEncontradoException.class);
    }

    @Test
    void listarDevuelveLosPedidosOrdenadosPorId() {
        pedidoService.crear("ana@correo.com", List.of(new LineaSolicitada("TEC-01", 1)));
        pedidoService.crear("luis@correo.com", List.of(new LineaSolicitada("CAB-01", 3)));

        assertThat(pedidoService.listar())
                .extracting(Pedido::cliente)
                .containsExactly("ana@correo.com", "luis@correo.com");
    }
}
