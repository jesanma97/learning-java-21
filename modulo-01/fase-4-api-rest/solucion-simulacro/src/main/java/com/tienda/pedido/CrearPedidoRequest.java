package com.tienda.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CrearPedidoRequest(
        @NotBlank(message = "es obligatorio")
        @Email(message = "debe ser un email válido")
        String cliente,

        // @Valid dentro del genérico: valida también cada línea, no solo que la lista exista.
        @NotEmpty(message = "debe tener al menos una línea")
        List<@NotNull(message = "no puede ser nula") @Valid Linea> lineas
) {

    public record Linea(
            @NotBlank(message = "es obligatorio")
            String sku,

            @Positive(message = "debe ser mayor que 0")
            @Max(value = 99, message = "no puede superar 99 unidades")
            int cantidad
    ) {}

    public List<LineaSolicitada> aLineasSolicitadas() {
        return lineas.stream()
                .map(linea -> new LineaSolicitada(linea.sku(), linea.cantidad()))
                .toList();
    }
}
