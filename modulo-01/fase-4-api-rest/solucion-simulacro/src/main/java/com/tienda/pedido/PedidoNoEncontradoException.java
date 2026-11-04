package com.tienda.pedido;

import com.tienda.error.RecursoNoEncontradoException;

public class PedidoNoEncontradoException extends RecursoNoEncontradoException {

    public PedidoNoEncontradoException(Long id) {
        super("No existe ningún pedido con id " + id);
    }
}
