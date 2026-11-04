package com.tienda.sinspring.inyeccion;

public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(String mensaje) {
        System.out.println("[EMAIL] Conectando con el servidor de correo... " + mensaje);
    }
}
