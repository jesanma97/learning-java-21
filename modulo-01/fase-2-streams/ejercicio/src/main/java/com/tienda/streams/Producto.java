package com.tienda.streams;

import java.math.BigDecimal;

// El dinero va con BigDecimal, nunca con double.
public record Producto(String sku, String nombre, String categoria, BigDecimal precio) {}
