package com.tienda.error;

/**
 * Base de los conflictos con el estado actual del recurso: el manejador global la convierte en 409.
 */
public abstract class ConflictoException extends RuntimeException {

    protected ConflictoException(String mensaje) {
        super(mensaje);
    }
}
