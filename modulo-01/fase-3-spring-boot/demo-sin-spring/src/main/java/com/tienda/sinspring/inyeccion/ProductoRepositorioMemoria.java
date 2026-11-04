package com.tienda.sinspring.inyeccion;

import java.math.BigDecimal;
import java.util.Map;

public class ProductoRepositorioMemoria implements ProductoRepositorio {

    private final Map<String, BigDecimal> precios = Map.of(
            "TEC-01", new BigDecimal("89.99"),
            "CAB-01", new BigDecimal("9.99")
    );

    @Override
    public BigDecimal precioDe(String sku) {
        var precio = precios.get(sku);
        if (precio == null) {
            throw new IllegalArgumentException("Producto no encontrado: " + sku);
        }
        return precio;
    }
}
