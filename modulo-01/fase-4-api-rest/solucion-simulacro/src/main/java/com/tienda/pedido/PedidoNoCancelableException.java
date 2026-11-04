package com.tienda.pedido;

import com.tienda.error.ConflictoException;

public class PedidoNoCancelableException extends ConflictoException {

    public PedidoNoCancelableException(Long id, EstadoPedido estado) {
        super("El pedido " + id + " no se puede cancelar porque está " + estado);
    }
}
