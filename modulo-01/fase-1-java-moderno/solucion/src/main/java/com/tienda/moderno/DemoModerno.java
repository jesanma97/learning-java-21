package com.tienda.moderno;

import com.tienda.moderno.Pago.PayPal;
import com.tienda.moderno.Pago.Tarjeta;
import com.tienda.moderno.Pago.Transferencia;

import java.util.List;

/**
 * Misma salida que DemoLegacy, salvo la consulta SQL (se corrigió la inyección).
 */
public class DemoModerno {

    public static void main(String[] args) {
        var gestor = new GestorTienda();

        var teclado = new Producto(1L, "Teclado mecánico", "Switches rojos", 89.99, "PERIFERICOS", 10, true);
        var copia = new Producto(1L, "Teclado mecánico", "Switches rojos", 89.99, "PERIFERICOS", 10, true);
        var monitor = new Producto(2L, "Monitor 27 pulgadas", "IPS 144 Hz", 299.0, "PANTALLAS", 5, true);
        var raton = new Producto(3L, "Ratón inalámbrico", "Sensor óptico", 25.5, "PERIFERICOS", 0, true);

        System.out.println("== Igualdad ==");
        System.out.println("teclado.equals(copia): " + teclado.equals(copia));
        var copiaConMenosStock = copia.conStock(3);
        System.out.println("tras cambiar el stock de la copia: " + teclado.equals(copiaConMenosStock));

        System.out.println("== Estados ==");
        for (var estado : EstadoPedido.values()) {
            System.out.println(estado + ": " + gestor.describirEstado(estado)
                    + " | cancelable: " + gestor.sePuedeCancelar(estado));
        }

        System.out.println("== Comisiones ==");
        List<Pago> pagos = List.of(
                new Tarjeta(100.0, "4111111111111111", false),
                new Tarjeta(100.0, "4111111111111111", true),
                new Transferencia(100.0, "ES9121000418450200051332"),
                new PayPal(100.0, "ana@correo.com")
        );
        for (var pago : pagos) {
            System.out.printf("Comisión: %.2f%n", gestor.calcularComision(pago));
        }

        System.out.println("== JSON ==");
        System.out.println(gestor.toJson(teclado));

        System.out.println("== SQL ==");
        System.out.println(gestor.consultaProductosActivos());

        System.out.println("== Agrupar por categoría ==");
        var porCategoria = gestor.agruparPorCategoria(List.of(teclado, monitor, raton));
        for (var entrada : porCategoria.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue().size() + " producto(s)");
        }
    }
}
