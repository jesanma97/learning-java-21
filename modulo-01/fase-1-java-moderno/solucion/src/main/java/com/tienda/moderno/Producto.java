package com.tienda.moderno;

/**
 * Sustituye a las ~150 líneas de ProductoDTO.
 * El compilador genera constructor, accesores, equals, hashCode y toString.
 */
public record Producto(Long id, String nombre, String descripcion, double precio,
                       String categoria, int stock, boolean activo) {

    // Constructor compacto: valida sin repetir las asignaciones.
    public Producto {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    // Un record es inmutable: en vez de setStock, se crea una "ficha nueva".
    public Producto conStock(int nuevoStock) {
        return new Producto(id, nombre, descripcion, precio, categoria, nuevoStock, activo);
    }
}
