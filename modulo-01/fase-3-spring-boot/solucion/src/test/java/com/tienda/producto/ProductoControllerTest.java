package com.tienda.producto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Sin Spring: gracias a la inyección por constructor, el controlador se crea con new.
 * Arranca en milisegundos. Los tests HTTP de verdad llegan en el módulo 5.
 */
class ProductoControllerTest {

    private final ProductoController controller = new ProductoController(new ProductoService());

    @Test
    void listaTodosLosProductos() {
        assertThat(controller.listar(null)).hasSize(5);
    }

    @Test
    void filtraPorCategoriaSinDistinguirMayusculas() {
        assertThat(controller.listar("perifericos"))
                .extracting(Producto::sku)
                .containsExactly("TEC-01", "RAT-01");
    }

    @Test
    void devuelve404SiElProductoNoExiste() {
        assertThat(controller.buscarPorSku("NO-EXISTE").getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void devuelveElProductoSiExiste() {
        var respuesta = controller.buscarPorSku("TEC-01");

        assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
        assertThat(respuesta.getBody()).extracting(Producto::nombre).isEqualTo("Teclado mecánico");
    }
}
