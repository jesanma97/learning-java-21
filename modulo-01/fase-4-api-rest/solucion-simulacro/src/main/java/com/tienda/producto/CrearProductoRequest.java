package com.tienda.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * DTO de entrada: lo que el cliente puede enviar, con sus reglas de validación.
 */
public record CrearProductoRequest(
        @NotBlank(message = "es obligatorio")
        @Pattern(regexp = "[A-Z]{3}-\\d{2}", message = "debe tener el formato AAA-00")
        String sku,

        @NotBlank(message = "es obligatorio")
        String nombre,

        @NotBlank(message = "es obligatoria")
        String categoria,

        @NotNull(message = "es obligatorio")
        @Positive(message = "debe ser mayor que 0")
        BigDecimal precio,

        @NotNull(message = "es obligatorio")
        @Positive(message = "debe ser mayor que 0")
        BigDecimal coste
) {

    public Producto aProducto() {
        return new Producto(sku, nombre, categoria.toUpperCase(), precio, coste);
    }
}
