package com.tienda.producto;

import com.tienda.error.RecursoNoEncontradoException;

public class ProductoNoEncontradoException extends RecursoNoEncontradoException {

    public ProductoNoEncontradoException(String sku) {
        super("No existe ningún producto con SKU " + sku);
    }
}
