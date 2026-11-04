package com.tienda.error;

/**
 * Base de las peticiones bien formadas y válidas que no se pueden procesar
 * por una regla de negocio (por ejemplo, pedir un producto que no existe): 422.
 */
public abstract class DatosNoProcesablesException extends RuntimeException {

    protected DatosNoProcesablesException(String mensaje) {
        super(mensaje);
    }
}
