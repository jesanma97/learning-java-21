package com.tienda.producto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Sin Spring: se prueba la lógica del controlador y del servicio.
 * La validación (@Valid) y la traducción a códigos HTTP las hace Spring: se prueban en el módulo 5 con MockMvc.
 */
class ProductoControllerTest {

    private final ProductoController controller = new ProductoController(new ProductoService());

    @Test
    void listaTodosLosProductosOrdenadosPorSku() {
        assertThat(controller.listar(null))
                .extracting(ProductoResponse::sku)
                .containsExactly("AUR-01", "CAB-01", "MON-01", "RAT-01", "TEC-01");
    }

    @Test
    void laRespuestaNoExponeElCoste() {
        // ProductoResponse no tiene componente "coste": si alguien lo añade, este test obliga a pensarlo.
        assertThat(ProductoResponse.class.getRecordComponents())
                .extracting(componente -> componente.getName())
                .containsExactly("sku", "nombre", "categoria", "precio");
    }

    @Test
    void buscarUnSkuInexistenteLanzaProductoNoEncontrado() {
        assertThatThrownBy(() -> controller.buscarPorSku("NO-00"))
                .isInstanceOf(ProductoNoEncontradoException.class)
                .hasMessage("No existe ningún producto con SKU NO-00");
    }

    @Test
    void crearDevuelve201ConLocation() {
        var peticion = new CrearProductoRequest("WEB-01", "Webcam", "perifericos", new BigDecimal("49.90"), new BigDecimal("20.00"));

        var respuesta = controller.crear(peticion);

        assertThat(respuesta.getStatusCode().value()).isEqualTo(201);
        assertThat(respuesta.getHeaders().getLocation()).hasToString("/productos/WEB-01");
        assertThat(respuesta.getBody().categoria()).isEqualTo("PERIFERICOS");
    }

    @Test
    void crearUnSkuRepetidoLanzaSkuDuplicado() {
        var peticion = new CrearProductoRequest("TEC-01", "Otro teclado", "PERIFERICOS", new BigDecimal("10"), new BigDecimal("5"));

        assertThatThrownBy(() -> controller.crear(peticion))
                .isInstanceOf(SkuDuplicadoException.class);
    }
}
