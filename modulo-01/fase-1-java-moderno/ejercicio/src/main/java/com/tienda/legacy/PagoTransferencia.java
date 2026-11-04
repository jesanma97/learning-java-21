package com.tienda.legacy;

public class PagoTransferencia extends Pago {

    private final String iban;

    public PagoTransferencia(double importe, String iban) {
        super(importe);
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }
}
