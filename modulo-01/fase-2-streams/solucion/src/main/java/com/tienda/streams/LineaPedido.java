package com.tienda.streams;

import java.math.BigDecimal;

public record LineaPedido(Producto producto, int cantidad) {

    public BigDecimal subtotal() {
        return producto.precio().multiply(BigDecimal.valueOf(cantidad));
    }
}
