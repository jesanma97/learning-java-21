package com.tienda.sinspring.acoplado;

public class AppAcoplada {

    public static void main(String[] args) {
        var pedidoService = new PedidoService();
        System.out.println("Total: " + pedidoService.crearPedido("TEC-01", 2));
    }
}
