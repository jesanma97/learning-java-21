package com.tienda.producto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    // Un solo constructor: Spring lo usa para inyectar sin necesidad de @Autowired.
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET /productos y GET /productos?categoria=PERIFERICOS
    @GetMapping
    public List<Producto> listar(@RequestParam(required = false) String categoria) {
        if (categoria == null) {
            return productoService.listar();
        }
        return productoService.listarPorCategoria(categoria);
    }

    // GET /productos/TEC-01 → 200 con el producto; GET /productos/NO-EXISTE → 404 sin cuerpo
    @GetMapping("/{sku}")
    public ResponseEntity<Producto> buscarPorSku(@PathVariable String sku) {
        return ResponseEntity.of(productoService.buscarPorSku(sku));
    }
}
