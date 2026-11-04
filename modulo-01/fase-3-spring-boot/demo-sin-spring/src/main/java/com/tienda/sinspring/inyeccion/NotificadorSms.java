package com.tienda.sinspring.inyeccion;

public class NotificadorSms implements Notificador {

    @Override
    public void notificar(String mensaje) {
        System.out.println("[SMS] " + mensaje);
    }
}
