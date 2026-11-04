package com.tienda.pedido;

import com.tienda.error.DatosNoProcesablesException;

public class ProductoNoDisponibleException extends DatosNoProcesablesException {

    public ProductoNoDisponibleException(String sku) {
        super("El producto " + sku + " no existe en el catálogo");
    }
}
