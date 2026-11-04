package com.tienda.sinspring.inyeccion;

import java.math.BigDecimal;

/**
 * El servicio ya no crea nada: recibe sus dependencias por el constructor.
 * No sabe si el repositorio es memoria o PostgreSQL, ni si se notifica por email o SMS.
 */
public class PedidoService {

    private final ProductoRepositorio repositorio;
    private final Notificador notificador;

    public PedidoService(ProductoRepositorio repositorio, Notificador notificador) {
        this.repositorio = repositorio;
        this.notificador = notificador;
    }

    public BigDecimal crearPedido(String sku, int cantidad) {
        var total = repositorio.precioDe(sku).multiply(BigDecimal.valueOf(cantidad));
        notificador.notificar("Pedido creado por " + total + " EUR");
        return total;
    }
}
