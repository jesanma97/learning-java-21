package com.tienda.pedido;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@Valid @RequestBody CrearPedidoRequest peticion) {
        var pedido = pedidoService.crear(peticion.cliente(), peticion.aLineasSolicitadas());
        return ResponseEntity
                .created(URI.create("/pedidos/" + pedido.id()))
                .body(PedidoResponse.desde(pedido));
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return pedidoService.listar().stream()
                .map(PedidoResponse::desde)
                .toList();
    }

    @GetMapping("/{id}")
    public PedidoResponse obtener(@PathVariable Long id) {
        return PedidoResponse.desde(pedidoService.obtener(id));
    }

    // Cancelar NO es borrar (el pedido sigue existiendo, con estado CANCELADO): por eso no es DELETE.
    // Se modela como la creación de una "cancelación" del pedido. PATCH con {"estado": "CANCELADO"} también es defendible.
    @PostMapping("/{id}/cancelacion")
    public PedidoResponse cancelar(@PathVariable Long id) {
        return PedidoResponse.desde(pedidoService.cancelar(id));
    }
}
