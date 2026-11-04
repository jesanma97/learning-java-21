package com.tienda.legacy;

public class PagoTarjeta extends Pago {

    private final String numeroTarjeta;
    private final boolean internacional;

    public PagoTarjeta(double importe, String numeroTarjeta, boolean internacional) {
        super(importe);
        this.numeroTarjeta = numeroTarjeta;
        this.internacional = internacional;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public boolean isInternacional() {
        return internacional;
    }
}
