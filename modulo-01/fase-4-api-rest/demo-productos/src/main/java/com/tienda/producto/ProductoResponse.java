package com.tienda.producto;

import java.math.BigDecimal;

/**
 * DTO de salida: el contrato público de la API. Sin el coste.
 */
public record ProductoResponse(String sku, String nombre, String categoria, BigDecimal precio) {

    public static ProductoResponse desde(Producto producto) {
        return new ProductoResponse(producto.sku(), producto.nombre(), producto.categoria(), producto.precio());
    }
}
