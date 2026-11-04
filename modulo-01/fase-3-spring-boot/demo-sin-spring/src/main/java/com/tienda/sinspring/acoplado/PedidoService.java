package com.tienda.sinspring.acoplado;

import java.math.BigDecimal;

/**
 * El servicio crea sus propias dependencias con new.
 * Pregunta para la clase: ¿cómo cambio el repositorio por una base de datos?
 * ¿Y cómo lo pruebo sin enviar un email de verdad?
 */
public class PedidoService {

    private final ProductoRepositorioMemoria repositorio = new ProductoRepositorioMemoria();
    private final NotificadorEmail notificador = new NotificadorEmail();

    public BigDecimal crearPedido(String sku, int cantidad) {
        var total = repositorio.precioDe(sku).multiply(BigDecimal.valueOf(cantidad));
        notificador.notificar("Pedido creado por " + total + " EUR");
        return total;
    }
}
