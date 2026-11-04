package com.tienda.sinspring.inyeccion;

public class AppInyeccion {

    public static void main(String[] args) {
        // Raíz de composición: el ÚNICO sitio que decide qué implementación se usa.
        // Cambiar a SMS es cambiar esta línea; PedidoService no se toca.
        // Esto es exactamente lo que Spring automatiza.
        var repositorio = new ProductoRepositorioMemoria();
        var notificador = new NotificadorEmail();
        var pedidoService = new PedidoService(repositorio, notificador);

        System.out.println("Total: " + pedidoService.crearPedido("TEC-01", 2));
    }
}
