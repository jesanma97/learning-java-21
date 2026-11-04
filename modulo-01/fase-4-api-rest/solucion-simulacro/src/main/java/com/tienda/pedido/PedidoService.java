package com.tienda.pedido;

import com.tienda.producto.ProductoService;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PedidoService {

    private final Map<Long, Pedido> pedidos = new ConcurrentHashMap<>();
    // AtomicLong: dos peticiones simultáneas nunca reciben el mismo id.
    private final AtomicLong secuencia = new AtomicLong();

    private final ProductoService productoService;
    private final Clock reloj;

    public PedidoService(ProductoService productoService, Clock reloj) {
        this.productoService = productoService;
        this.reloj = reloj;
    }

    public Pedido crear(String cliente, List<LineaSolicitada> solicitadas) {
        // Primero se validan todas las líneas: si un SKU no existe, no se consume ningún id.
        var lineas = solicitadas.stream()
                .map(this::aLineaPedido)
                .toList();
        var pedido = new Pedido(secuencia.incrementAndGet(), cliente, Instant.now(reloj), EstadoPedido.PENDIENTE, lineas);
        pedidos.put(pedido.id(), pedido);
        return pedido;
    }

    private LineaPedido aLineaPedido(LineaSolicitada solicitada) {
        var producto = productoService.buscarPorSku(solicitada.sku())
                .orElseThrow(() -> new ProductoNoDisponibleException(solicitada.sku()));
        return new LineaPedido(producto.sku(), producto.nombre(), producto.precio(), solicitada.cantidad());
    }

    public List<Pedido> listar() {
        return pedidos.values().stream()
                .sorted(Comparator.comparing(Pedido::id))
                .toList();
    }

    public Pedido obtener(Long id) {
        var pedido = pedidos.get(id);
        if (pedido == null) {
            throw new PedidoNoEncontradoException(id);
        }
        return pedido;
    }

    // computeIfPresent es atómico por clave: dos cancelaciones simultáneas no se pisan.
    // Si cancelar() lanza la excepción, el mapa no cambia.
    public Pedido cancelar(Long id) {
        var cancelado = pedidos.computeIfPresent(id, (clave, pedido) -> pedido.cancelar());
        if (cancelado == null) {
            throw new PedidoNoEncontradoException(id);
        }
        return cancelado;
    }
}
