package com.tienda.sinspring.acoplado;

public class NotificadorEmail {

    public void notificar(String mensaje) {
        // Imagina que aquí se conecta a un servidor SMTP real y envía un correo.
        System.out.println("[EMAIL] Conectando con el servidor de correo... " + mensaje);
    }
}
