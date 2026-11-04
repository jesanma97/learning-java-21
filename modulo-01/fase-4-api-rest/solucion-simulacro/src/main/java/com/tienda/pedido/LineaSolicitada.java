package com.tienda.pedido;

/**
 * Lo que el cliente pide, en términos de dominio. El servicio no conoce los DTOs de la API.
 */
public record LineaSolicitada(String sku, int cantidad) {}
