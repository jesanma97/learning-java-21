package com.tienda.pedido;

import java.math.BigDecimal;

/**
 * Copia el nombre y el precio del producto en el momento de la compra:
 * si mañana el producto sube de precio, los pedidos antiguos no cambian.
 */
public record LineaPedido(String sku, String nombre, BigDecimal precioUnitario, int cantidad) {

    public BigDecimal subtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
