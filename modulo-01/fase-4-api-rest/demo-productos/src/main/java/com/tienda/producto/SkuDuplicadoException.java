package com.tienda.producto;

import com.tienda.error.ConflictoException;

public class SkuDuplicadoException extends ConflictoException {

    public SkuDuplicadoException(String sku) {
        super("Ya existe un producto con SKU " + sku);
    }
}
