package com.tienda.sinspring.inyeccion;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PedidoServiceTest {

    @Test
    void calculaElTotalYNotificaSinTocarNadaReal() {
        // Las dos interfaces tienen un solo método: se pueden implementar con lambdas (fase 2).
        ProductoRepositorio repositorioFalso = sku -> new BigDecimal("10.00");
        var mensajes = new ArrayList<String>();
        Notificador notificadorFalso = mensajes::add;

        var pedidoService = new PedidoService(repositorioFalso, notificadorFalso);

        var total = pedidoService.crearPedido("CUALQUIERA", 3);

        assertThat(total).isEqualByComparingTo("30.00");
        assertThat(mensajes).containsExactly("Pedido creado por 30.00 EUR");
    }
}
