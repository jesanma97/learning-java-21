package com.tienda.error;

/**
 * Un error de validación concreto: qué campo y qué le pasa.
 */
public record ErrorDeCampo(String campo, String mensaje) {}
