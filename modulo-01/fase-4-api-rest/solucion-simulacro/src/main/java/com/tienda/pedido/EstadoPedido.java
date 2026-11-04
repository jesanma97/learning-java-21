package com.tienda.pedido;

public enum EstadoPedido {
    PENDIENTE,
    PAGADO,
    ENVIADO,
    ENTREGADO,
    CANCELADO;

    // La regla vive en el propio enum (fase 1). Exhaustivo: un estado nuevo obliga a decidir.
    public boolean sePuedeCancelar() {
        return switch (this) {
            case PENDIENTE, PAGADO -> true;
            case ENVIADO, ENTREGADO, CANCELADO -> false;
        };
    }
}
