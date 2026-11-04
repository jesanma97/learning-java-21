package com.tienda.producto;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductoService {

    // Ahora los datos cambian (POST): ConcurrentHashMap porque el bean singleton lo usan varios hilos a la vez.
    private final Map<String, Producto> productos = new ConcurrentHashMap<>();

    public ProductoService() {
        guardar(new Producto("TEC-01", "Teclado mecánico", "PERIFERICOS", new BigDecimal("89.99"), new BigDecimal("52.00")));
        guardar(new Producto("RAT-01", "Ratón inalámbrico", "PERIFERICOS", new BigDecimal("25.50"), new BigDecimal("11.30")));
        guardar(new Producto("MON-01", "Monitor 27 pulgadas", "PANTALLAS", new BigDecimal("299.00"), new BigDecimal("210.00")));
        guardar(new Producto("AUR-01", "Auriculares", "AUDIO", new BigDecimal("59.90"), new BigDecimal("31.00")));
        guardar(new Producto("CAB-01", "Cable USB-C", "ACCESORIOS", new BigDecimal("9.99"), new BigDecimal("2.10")));
    }

    private void guardar(Producto producto) {
        productos.put(producto.sku(), producto);
    }

    // Un ConcurrentHashMap no tiene orden: se ordena para que la respuesta sea siempre la misma.
    public List<Producto> listar() {
        return productos.values().stream()
                .sorted(Comparator.comparing(Producto::sku))
                .toList();
    }

    public List<Producto> listarPorCategoria(String categoria) {
        return listar().stream()
                .filter(producto -> producto.categoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public Optional<Producto> buscarPorSku(String sku) {
        return Optional.ofNullable(productos.get(sku));
    }

    // El "no existe" se decide aquí, una vez, y no en cada controlador que lo use.
    public Producto obtener(String sku) {
        return buscarPorSku(sku).orElseThrow(() -> new ProductoNoEncontradoException(sku));
    }

    // putIfAbsent es atómico: dos peticiones simultáneas con el mismo SKU no pueden crear dos productos.
    public Producto crear(Producto producto) {
        var anterior = productos.putIfAbsent(producto.sku(), producto);
        if (anterior != null) {
            throw new SkuDuplicadoException(producto.sku());
        }
        return producto;
    }
}
