package com.tienda.error;

/**
 * Base de los "no existe": el manejador global la convierte en 404.
 */
public abstract class RecursoNoEncontradoException extends RuntimeException {

    protected RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
