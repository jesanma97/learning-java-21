package com.tienda.legacy;

/**
 * DTO de producto escrito "como siempre se ha hecho".
 *
 *
 * Un DTO (Data Transfer Object, “objeto de transferencia de datos”) es un objeto que solo sirve para transportar datos de un sitio a otro,
 * normalmente entre tu API y quien la llama. No tiene lógica: solo lleva los datos.
 *
 */


public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private int stock;
    private boolean activo;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, String descripcion, double precio,
                       String categoria, int stock, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductoDTO that = (ProductoDTO) o;

        if (Double.compare(that.precio, precio) != 0) {
            return false;
        }
        if (stock != that.stock) {
            return false;
        }
        if (activo != that.activo) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) {
            return false;
        }
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) {
            return false;
        }
        return categoria != null ? categoria.equals(that.categoria) : that.categoria == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + stock;
        result = 31 * result + (activo ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", stock=" + stock +
                ", activo=" + activo +
                '}';
    }
}
