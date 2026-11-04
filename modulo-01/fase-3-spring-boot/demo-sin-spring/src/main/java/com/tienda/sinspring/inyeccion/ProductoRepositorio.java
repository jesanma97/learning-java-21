package com.tienda.sinspring.inyeccion;

import java.math.BigDecimal;

/**
 * El servicio depende de esta interfaz, no de una implementación concreta.
 */
public interface ProductoRepositorio {

    BigDecimal precioDe(String sku);
}
