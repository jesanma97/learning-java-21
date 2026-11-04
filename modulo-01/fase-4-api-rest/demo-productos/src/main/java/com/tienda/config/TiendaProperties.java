package com.tienda.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

/**
 * Agrupa todas las propiedades "tienda.*" del application.yaml en un objeto tipado.
 * Spring rellena el record por su constructor al arrancar.
 */
@ConfigurationProperties(prefix = "tienda")
public record TiendaProperties(String nombre, String moneda, BigDecimal envioGratisDesde) {}
