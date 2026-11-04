package com.tienda.legacy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Ejecuta este programa ANTES de refactorizar y guarda su salida.
 * Al terminar, la salida debe ser la misma (salvo donde corrijas un error).
 */
public class DemoLegacy {

    public static void main(String[] args) {
        GestorTienda gestor = new GestorTienda();

        ProductoDTO teclado = new ProductoDTO(1L, "Teclado mecánico", "Switches rojos", 89.99, "PERIFERICOS", 10, true);
        ProductoDTO copia = new ProductoDTO(1L, "Teclado mecánico", "Switches rojos", 89.99, "PERIFERICOS", 10, true);
        ProductoDTO monitor = new ProductoDTO(2L, "Monitor 27 pulgadas", "IPS 144 Hz", 299.0, "PANTALLAS", 5, true);
        ProductoDTO raton = new ProductoDTO(3L, "Ratón inalámbrico", "Sensor óptico", 25.5, "PERIFERICOS", 0, true);

        System.out.println("== Igualdad ==");
        System.out.println("teclado.equals(copia): " + teclado.equals(copia));
        copia.setStock(3);
        System.out.println("tras cambiar el stock de la copia: " + teclado.equals(copia));

        System.out.println("== Estados ==");
        String[] estados = {
                GestorTienda.ESTADO_PENDIENTE,
                GestorTienda.ESTADO_PAGADO,
                GestorTienda.ESTADO_ENVIADO,
                GestorTienda.ESTADO_ENTREGADO,
                GestorTienda.ESTADO_CANCELADO
        };
        for (String estado : estados) {
            System.out.println(estado + ": " + gestor.describirEstado(estado)
                    + " | cancelable: " + gestor.sePuedeCancelar(estado));
        }

        System.out.println("== Comisiones ==");
        List<Pago> pagos = Arrays.asList(
                new PagoTarjeta(100.0, "4111111111111111", false),
                new PagoTarjeta(100.0, "4111111111111111", true),
                new PagoTransferencia(100.0, "ES9121000418450200051332"),
                new PagoPaypal(100.0, "ana@correo.com")
        );
        for (Pago pago : pagos) {
            System.out.println(String.format("Comisión: %.2f", gestor.calcularComision(pago)));
        }

        System.out.println("== JSON ==");
        System.out.println(gestor.toJson(teclado));

        System.out.println("== SQL ==");
        System.out.println(gestor.consultaProductosActivos("PERIFERICOS"));

        System.out.println("== Agrupar por categoría ==");
        List<ProductoDTO> catalogo = Arrays.asList(teclado, monitor, raton);
        Map<String, List<ProductoDTO>> porCategoria = gestor.agruparPorCategoria(catalogo);
        for (Map.Entry<String, List<ProductoDTO>> entrada : porCategoria.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue().size() + " producto(s)");
        }
    }
}
