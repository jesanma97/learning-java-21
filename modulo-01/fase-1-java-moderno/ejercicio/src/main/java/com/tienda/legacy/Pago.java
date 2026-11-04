package com.tienda.legacy;

/**
 * Clase base de todos los métodos de pago.
 */
public abstract class Pago {

    private final double importe;

    protected Pago(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }
}
