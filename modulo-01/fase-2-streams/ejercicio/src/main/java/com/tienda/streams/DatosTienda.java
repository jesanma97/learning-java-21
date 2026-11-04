package com.tienda.streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.tienda.streams.EstadoPedido.CANCELADO;
import static com.tienda.streams.EstadoPedido.ENTREGADO;
import static com.tienda.streams.EstadoPedido.ENVIADO;
import static com.tienda.streams.EstadoPedido.PAGADO;
import static com.tienda.streams.EstadoPedido.PENDIENTE;

/**
 * Datos de ejemplo de la tienda: 5 clientes, 5 productos y 16 pedidos (3 cancelados).
 */
public final class DatosTienda {

    public static final Cliente ANA = new Cliente("C1", "Ana García", "Madrid");
    public static final Cliente LUIS = new Cliente("C2", "Luis Pérez", "Sevilla");
    public static final Cliente MARTA = new Cliente("C3", "Marta López", "Valencia");
    public static final Cliente JAVIER = new Cliente("C4", "Javier Ruiz", "Bilbao");
    public static final Cliente SOFIA = new Cliente("C5", "Sofía Martín", "Madrid");

    public static final Producto TECLADO = new Producto("TEC-01", "Teclado mecánico", "PERIFERICOS", new BigDecimal("89.99"));
    public static final Producto RATON = new Producto("RAT-01", "Ratón inalámbrico", "PERIFERICOS", new BigDecimal("25.50"));
    public static final Producto MONITOR = new Producto("MON-01", "Monitor 27 pulgadas", "PANTALLAS", new BigDecimal("299.00"));
    public static final Producto AURICULARES = new Producto("AUR-01", "Auriculares", "AUDIO", new BigDecimal("59.90"));
    public static final Producto CABLE_USB_C = new Producto("CAB-01", "Cable USB-C", "ACCESORIOS", new BigDecimal("9.99"));

    private DatosTienda() {
    }

    public static List<Pedido> pedidos() {
        return List.of(
                pedido("PED-01", ANA, "2026-01-05", ENTREGADO, linea(TECLADO, 1), linea(CABLE_USB_C, 2)),
                pedido("PED-02", LUIS, "2026-01-08", ENTREGADO, linea(MONITOR, 1)),
                pedido("PED-03", MARTA, "2026-01-12", ENTREGADO, linea(AURICULARES, 1), linea(CABLE_USB_C, 1)),
                pedido("PED-04", ANA, "2026-01-20", ENTREGADO, linea(RATON, 1)),
                pedido("PED-05", JAVIER, "2026-02-02", ENTREGADO, linea(TECLADO, 1), linea(RATON, 1)),
                pedido("PED-06", LUIS, "2026-02-10", CANCELADO, linea(RATON, 10)),
                pedido("PED-07", MARTA, "2026-02-14", ENTREGADO, linea(CABLE_USB_C, 3)),
                pedido("PED-08", ANA, "2026-02-21", CANCELADO, linea(MONITOR, 2)),
                pedido("PED-09", SOFIA, "2026-03-01", CANCELADO, linea(AURICULARES, 1)),
                pedido("PED-10", MARTA, "2026-03-03", ENVIADO, linea(MONITOR, 1), linea(CABLE_USB_C, 1)),
                pedido("PED-11", LUIS, "2026-03-09", PAGADO, linea(AURICULARES, 2)),
                pedido("PED-12", ANA, "2026-03-15", ENVIADO, linea(CABLE_USB_C, 2), linea(RATON, 1)),
                pedido("PED-13", JAVIER, "2026-03-20", PAGADO, linea(AURICULARES, 1)),
                pedido("PED-14", MARTA, "2026-03-28", PENDIENTE, linea(TECLADO, 1)),
                pedido("PED-15", LUIS, "2026-04-02", PENDIENTE, linea(CABLE_USB_C, 1)),
                pedido("PED-16", ANA, "2026-04-05", PENDIENTE, linea(AURICULARES, 1), linea(CABLE_USB_C, 1))
        );
    }

    private static Pedido pedido(String id, Cliente cliente, String fecha, EstadoPedido estado, LineaPedido... lineas) {
        return new Pedido(id, cliente, LocalDate.parse(fecha), estado, List.of(lineas));
    }

    private static LineaPedido linea(Producto producto, int cantidad) {
        return new LineaPedido(producto, cantidad);
    }
}
