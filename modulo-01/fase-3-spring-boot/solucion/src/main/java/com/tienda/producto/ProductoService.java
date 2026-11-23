package com.tienda.producto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    // Datos en memoria hasta el módulo 2. Lista inmutable: el bean es un singleton compartido por todas las peticiones.
    private final List<Producto> productos = List.of(
            new Producto("TEC-01", "Teclado mecánico", "PERIFERICOS", new BigDecimal("89.99")),
            new Producto("RAT-01", "Ratón inalámbrico", "PERIFERICOS", new BigDecimal("25.50")),
            new Producto("MON-01", "Monitor 27 pulgadas", "PANTALLAS", new BigDecimal("299.00")),
            new Producto("AUR-01", "Auriculares", "AUDIO", new BigDecimal("59.90")),
            new Producto("CAB-01", "Cable USB-C", "ACCESORIOS", new BigDecimal("9.99"))
    );

    public List<Producto> listar() {
        log.debug("Listando {} productos", productos.size());
        return productos;
    }

    public List<Producto> listarPorCategoria(String categoria) {
        return productos.stream()
                .filter(producto -> producto.categoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public Optional<Producto> buscarPorSku(String sku) {
        return productos.stream()
                .filter(producto -> producto.sku().equals(sku))
                .findFirst();
    }
}
