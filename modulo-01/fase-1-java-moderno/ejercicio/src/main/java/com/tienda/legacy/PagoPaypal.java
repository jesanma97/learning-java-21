package com.tienda.legacy;

public class PagoPaypal extends Pago {

    private final String email;

    public PagoPaypal(double importe, String email) {
        super(importe);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
