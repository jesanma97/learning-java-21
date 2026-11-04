package com.tienda.producto;

import java.math.BigDecimal;

/**
 * Modelo interno. El coste (lo que paga la tienda al proveedor) NUNCA debe salir por la API:
 * por eso los controladores devuelven ProductoResponse y no este record.
 */
public record Producto(String sku, String nombre, String categoria, BigDecimal precio, BigDecimal coste) {}
