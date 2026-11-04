package com.tienda.legacy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Lógica de la tienda heredada de un proyecto en Java 8.
 */
public class GestorTienda {

    public static final String ESTADO_PENDIENTE = "PENDIENTE";
    public static final String ESTADO_PAGADO = "PAGADO";
    public static final String ESTADO_ENVIADO = "ENVIADO";
    public static final String ESTADO_ENTREGADO = "ENTREGADO";
    public static final String ESTADO_CANCELADO = "CANCELADO";

    public String describirEstado(String estado) {
        String descripcion;
        switch (estado) {
            case ESTADO_PENDIENTE:
                descripcion = "Pendiente de pago";
                break;
            case ESTADO_PAGADO:
                descripcion = "Pagado, preparando envío";
                break;
            case ESTADO_ENVIADO:
                descripcion = "En camino";
                break;
            case ESTADO_ENTREGADO:
                descripcion = "Entregado";
                break;
            case ESTADO_CANCELADO:
                descripcion = "Cancelado";
                break;
            default:
                throw new IllegalArgumentException("Estado desconocido: " + estado);
        }
        return descripcion;
    }

    public boolean sePuedeCancelar(String estado) {
        switch (estado) {
            case ESTADO_PENDIENTE:
            case ESTADO_PAGADO:
                return true;
            case ESTADO_ENVIADO:
            case ESTADO_ENTREGADO:
            case ESTADO_CANCELADO:
                return false;
            default:
                throw new IllegalArgumentException("Estado desconocido: " + estado);
        }
    }

    public double calcularComision(Pago pago) {
        if (pago instanceof PagoTarjeta) {
            PagoTarjeta tarjeta = (PagoTarjeta) pago;
            if (tarjeta.isInternacional()) {
                return tarjeta.getImporte() * 0.03;
            }
            return tarjeta.getImporte() * 0.015;
        } else if (pago instanceof PagoTransferencia) {
            return 0.5;
        } else if (pago instanceof PagoPaypal) {
            PagoPaypal paypal = (PagoPaypal) pago;
            return paypal.getImporte() * 0.034 + 0.35;
        } else {
            throw new IllegalArgumentException("Tipo de pago no soportado: " + pago.getClass().getName());
        }
    }

    public String toJson(ProductoDTO producto) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"id\": ").append(producto.getId()).append(",\n");
        sb.append("  \"nombre\": \"").append(producto.getNombre()).append("\",\n");
        sb.append("  \"precio\": ").append(producto.getPrecio()).append(",\n");
        sb.append("  \"stock\": ").append(producto.getStock()).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public String consultaProductosActivos(String categoria) {
        String sql = "SELECT id, nombre, precio, stock " +
                "FROM productos " +
                "WHERE categoria = '" + categoria + "' " +
                "AND activo = true " +
                "ORDER BY nombre";
        return sql;
    }

    public Map<String, List<ProductoDTO>> agruparPorCategoria(List<ProductoDTO> productos) {
        Map<String, List<ProductoDTO>> resultado = new HashMap<String, List<ProductoDTO>>();
        Iterator<ProductoDTO> iterador = productos.iterator();
        while (iterador.hasNext()) {
            ProductoDTO producto = iterador.next();
            List<ProductoDTO> lista = resultado.get(producto.getCategoria());
            if (lista == null) {
                lista = new ArrayList<ProductoDTO>();
                resultado.put(producto.getCategoria(), lista);
            }
            lista.add(producto);
        }
        return resultado;
    }
}
