package com.tienda.producto;

import java.math.BigDecimal;

public record Producto(String sku, String nombre, String categoria, BigDecimal precio) {}
