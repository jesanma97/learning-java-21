package com.tienda.moderno;

/**
 * Sustituye a Pago, PagoTarjeta, PagoTransferencia y PagoPaypal (4 ficheros).
 * Al ser sealed, el compilador conoce TODOS los tipos de pago posibles:
 * un switch sobre Pago no necesita default.
 */
public sealed interface Pago {

    double importe();

    record Tarjeta(double importe, String numeroTarjeta, boolean internacional) implements Pago {}

    record Transferencia(double importe, String iban) implements Pago {}

    record PayPal(double importe, String email) implements Pago {}
}
