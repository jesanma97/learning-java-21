package com.tienda.moderno;

/**
 * Sustituye a las constantes String: un estado inventado ("PAGAO") ya no compila.
 */
public enum EstadoPedido {
    PENDIENTE,
    PAGADO,
    ENVIADO,
    ENTREGADO,
    CANCELADO
}
