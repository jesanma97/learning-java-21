package com.tienda.producto;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoResponse> listar(@RequestParam(required = false) String categoria) {
        var productos = categoria == null
                ? productoService.listar()
                : productoService.listarPorCategoria(categoria);
        return productos.stream()
                .map(ProductoResponse::desde)
                .toList();
    }

    // Si no existe, el servicio lanza ProductoNoEncontradoException y el manejador global responde 404.
    @GetMapping("/{sku}")
    public ProductoResponse buscarPorSku(@PathVariable String sku) {
        return ProductoResponse.desde(productoService.obtener(sku));
    }

    // 201 Created + cabecera Location con la URL del recurso nuevo.
    // @Valid: si el cuerpo no cumple las reglas, Spring responde 400 antes de entrar en el método.
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody CrearProductoRequest peticion) {
        var creado = productoService.crear(peticion.aProducto());
        return ResponseEntity
                .created(URI.create("/productos/" + creado.sku()))
                .body(ProductoResponse.desde(creado));
    }
}
